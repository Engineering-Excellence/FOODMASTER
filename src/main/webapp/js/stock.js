'use strict'

$('#search-form').submit((e) => {
    if ($('#searchOption').val() < 0) {
        alert("검색조건을 선택해주세요")
        return false
    }
    if ($('#searchOption').val() < 3 && !$("#keyword").val().match(/^\d+$/)) {
        alert("번호는 숫자만 입력이 가능합니다")
        return false
    }
    return true
})

$('.stock-data').each((index, element) => {
    $(element).click(() => {
        // 클릭한 데이터의 인덱스
        let stockID = $('#' + element.id + ' td:first-child').text()
        console.log('stockID:', stockID)

        // 현재 선택한 방식
        // 1:수정 2:삭제
        let type = $('input[name=select-type]:checked').val()
        console.log('type:', type)

        switch (type) {
            case '1':
                location.href = '/stock/order?stockID=' + stockID
                break;
            case '2':
                location.href = '/stock/update?stockID=' + stockID
                break;
            case '3':
                deleteStock(stockID)
                break;
            default:
                break;
        }
    })
})

const deleteStock = stockID => {
    console.log('deleteStock')
    if (confirm('정말로 ' + stockID + '번 재고정보를 삭제하시겠습니까?')) { // 관리자에게 확인 대화상자를 표시
        $.ajax({
            url: '/stock/delete',
            type: 'POST',
            data: {
                'stockID': stockID
            },
            success: response => {
                console.log(response)
                alert(stockID + '번 재고정보 삭제 완료')
                location.reload()
            },
            error: error => {
                console.log(error)
                alert(stockID + '번 재고정보 삭제 실패')
            }
        })
    }
}
