'use strict'

$("#updateMember").submit(function(){
	if (!checkValidate($("#contact").val(), contactRegex)) {
		alert("연락처를 다시 입력해주세요")
		return false;
	}
	return true;
});