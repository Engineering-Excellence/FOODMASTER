let stage = 0;
let stageLength = $(".register-stage").length;
let emailChk = false;
let passwordChk = false;
let passwordConfirmChk = false;
let contacktCheck = false;
const emailRegex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
const passwordRegex = /(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
const contactRegex = /^[+]?[(]?[0-9]{2,3}[)]?[-\s\.]?[0-9]{3,4}[-\s\.]?[0-9]{4,6}$/
const checkValidate = (input, reg) => {
    return input.match(reg);
}
const changeInputOutline = (selector, flag) => {
    if (flag) {
        $(selector).removeClass("err");
    } else {
        $(selector).addClass("err");
    }
}
$("#email-chk").click(() => {
    if ($("#email").val().length == 0) {
        alert("이메일을 입력해주세요");
        return;
    }
    if (!checkValidate($("#email").val(), emailRegex)) {
        alert("이메일 형식이 아닙니다");
        $("#email").focus();
        return;
    }

    if (!emailChk) {
        $.ajax({
            url: "/auth/checkEmail",
            type: "post",
            data: {
                email: $("#email").val()
            },
            dataType: "json",
            success: (res) => {
                if (!res) {
                    $("#email").attr("disabled", "disabled");
                    $("#email-chk").toggleClass("btn-outline-secondary");
                    $("#email-chk").toggleClass("btn-success");
                    emailChk = true;
                    alert("사용 가능한 이메일입니다")
                } else {
                    alert("이메일이 중복됩니다");
                    return;
                }
            },
        });
    } else {
        $("#email").removeAttr("disabled");
        $("#email-chk").toggleClass("btn-outline-secondary");
        $("#email-chk").toggleClass("btn-success");
        $("#email").focus();
        emailChk = false;
    }
});
$("#nextstage").click(() => {
    if (!emailChk) {
        alert("이메일 중복 확인을 해주세요");
        return;
    }
    if ($("#password").val().length == 0) {
        alert("비밀번호를 입력해주세요");
        return;
    }

    if (!passwordChk) {
        alert("비밀번호가 조건에 맞지 않습니다\n8자 이상, 대소문자 1개 이상, 숫자 1개 이상, 특수문자 1개 이상이 포함되어야 합니다");
        return;
    }

    if ($("#password").val() !== $("#password-confirm").val()) {
        alert("비밀번호가 일치하지 않습니다");
        return;
    }

    stage++;
    $(".register-stage-wrapper").css({
        transform: `translateX(${-50 * (stage % stageLength)}%)`
    });
});
$("#prevstage").click(() => {
    stage--;
    $(".register-stage-wrapper").css({
        transform: `translateX(${-49 * (stage % stageLength)}%)`
    });
});

$("#register-form").submit((e) => {
    console.log($("#email").val());
    if ($("#mName").val().length == 0) {
        alert("이름을 입력해주세요");
        return false;
    }
    if ($("#contact").val().length == 0) {
        alert("연락처를 입력해주세요");
        return false;
    }
    if (!contacktCheck) {
        alert("올바른 전화번호 형식이 아닙니다");
        return false;
    }
    if ($("#birthday").val().length == 0) {
        alert("생년월일을 입력해주세요");
        return false;
    }
    if ($("#question").val().length == 0) {
        alert("비밀번호 찾기 질문을 입력해주세요");
        return false;
    }
    if ($("#answer").val().length == 0) {
        alert("비밀번호 찾기 답을 입력해주세요");
        return false;
    }
    $("#email").removeAttr("disabled");
    return true;
})
$("#register-form").on("keydown", (e) => {
    if (e.keyCode == 13)
        e.preventDefault();
})

$("#password").on("focus focusout", () => {
    $(".help").toggleClass("hide");
})
$("#password").on("keyup", () => {
    passwordChk = checkValidate($("#password").val(), passwordRegex);
    changeInputOutline("#password", passwordChk);
})
$("#password-confirm").on("keyup", () => {
    passwordConfirmChk = $("#password").val() === $("#password-confirm").val();
    changeInputOutline("#password-confirm", passwordConfirmChk);
})

$("#contact").on("keyup", () => {
    contacktCheck = checkValidate($("#contact").val(), contactRegex);
    changeInputOutline("#contact", contacktCheck);
})
