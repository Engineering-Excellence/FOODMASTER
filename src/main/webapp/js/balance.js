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
