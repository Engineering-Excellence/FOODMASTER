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

// jquery에는 forEach 아니고 each인듯
$('.member-data').each((index, element) => {
    $(element).click(() => {
        // 클릭한 데이터의 인덱스
        // let elementIdx = parseInt(element.getAttribute("id").substring(10));
        let memberID = $('#' + element.id + ' td:first-child').text()
        console.log('memberID:', memberID)

        // 현재 선택한 방식
        // 1:수정 2:삭제
        let type = $('input[name=select-type]:checked').val()
        console.log('type:', type)
        /*
           두 변수를 적절히 사용하여 수정 혹은 삭제를 진행하는 코드를 작성할 것
         */
        switch (type) {
            case '1':
                location.href = '/member/update?memberID=' + memberID
                break;
            case '2':
                deleteMember(memberID)
                break;
            default:
                break;
        }
    })
})

const deleteMember = memberID => {
    console.log('deleteMember')
    if (confirm('정말로 ' + memberID + '번 회원을 삭제하시겠습니까?')) { // 관리자에게 확인 대화상자를 표시
        $.ajax({
            url: '/member/delete',
            type: 'POST',
            data: {
                'memberID': memberID
            },
            success: response => {
                console.log(response)
                location.reload()
            },
            error: error => {
                console.log(error)
            }
        })
    }
}
