'use strict'

$('#search-form').submit((e) => {
    if ($('#searchOption').val() < 0) {
        alert("검색조건을 선택해주세요")
        return false
    }
    if ($('#searchOption').val() == 1 && !$("#keyword").val().match(/^\d+$/)) {
        alert("번호는 숫자만 입력이 가능합니다")
        return false
    }
    return true
})

$('.product-data').each((index, element) => {
    $(element).click(() => {
        // 클릭한 데이터의 인덱스
        let productID = $('#' + element.id + ' td:first-child').text()
        console.log('productID:', productID)

        // 현재 선택한 방식
        // 1:수정 2:삭제 3:재료등록 4:재료수정
        let type = $('input[name=select-type]:checked').val()
        console.log('type:', type)

        switch (type) {
            case '1':
                location.href = '/product/update?productID=' + productID
                break;
            case '2':
                deleteProduct(productID)
                break;
            case '3':
                let name = '_blank';
                let left = (screen.width - 600) / 2;
                let top = (screen.height - 800) / 4;
                let specs = 'menubar=no,status=no,toolbar=no,innerWidth=600,innerHeight=800,chrome=1,centerscreen=1,top=' + top + ',left=' + left;
                let newWindow = window.open("/product/ingredient?productID=" + productID, name, specs);
                newWindow.focus();
                break;
            case '4':
                break;
            default:
                break;
        }
    })
})

const deleteProduct = productID => {
    console.log('deleteProduct')
    if (confirm('정말로 ' + productID + '번 상품을 삭제하시겠습니까?')) { // 관리자에게 확인 대화상자를 표시
        $.ajax({
            url: '/product/delete',
            type: 'POST',
            data: {
                'productID': productID
            },
            success: response => {
                console.log(response)
                alert(productID + '번 상품 삭제 완료')
                location.reload()
            },
            error: error => {
                console.error(error)
                alert(productID + '번 상품 삭제 실패')
            }
        })
    }
}
