'use strict'

let stage = 0;
let stageLength = $(".find-password-stage").length;
let emailChk = false;
let answerChk = false;
let passwordChk = false;
let passwordConfirmChk = false;
let answer;

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
            url: "/auth/getQuestion",
            type: "post",
            data: {
                email: $("#email").val()
            },
            dataType: "json",
            success: (res) => {
                if (res != null) {
                    $("#email").attr("readonly", "readonly");
                    $("#email-chk").toggleClass("btn-outline-secondary");
                    $("#email-chk").toggleClass("btn-success");
                    $("#question").val(res.question);
                    answer = res.answer;
                    emailChk = true;
                    alert("이메일 확인이 완료되었습니다");
                } else {
                    alert("없는 이메일 입니다");
                    return;
                }
            },
        });
    } else {
        $("#email").removeAttr("readonly");
        $("#email-chk").toggleClass("btn-outline-secondary");
        $("#email-chk").toggleClass("btn-success");
        $("#email").focus();
        emailChk = false;
    }
});

$(".nextstage").each((i, e) => {
    $(e).click(() => {
        if (i == 0) {
            if (!emailChk) {
                alert("이메일 확인을 해주세요");
                return;
            }
        } else if (i == 1) {
            if (!answerChk) {
                alert("정답 확인을 해주세요");
                return;
            }
        }

        stage++;
        $(".find-password-stage-wrapper").css({
            transform: `translateX(${-33.3 * (stage % stageLength)}%)`
        });
    });
});

$("#find-password-form").submit((e) => {
    if (!passwordChk) {
        alert("비밀번호가 조건에 맞지 않습니다\n8자 이상, 대소문자 1개 이상, 숫자 1개 이상, 특수문자 1개 이상이 포함되어야 합니다");
        return false;
    }

    if ($("#password").val() !== $("#password-confirm").val()) {
        alert("비밀번호가 일치하지 않습니다");
        return false;
    }

    return true;
})

$("#find-password-form").on("keydown", (e) => {
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

$("#answer-chk").click(() => {
    $.ajax({
        url: "/auth/confirmQuestion",
        type: "post",
        data: {
            email: $("#email").val(),
            answer: $("#answer").val()
        },
        dataType: "json",
        success: (res) => {
            if (res) {
                answerChk = true;
                $("#answer").attr("readonly", "readonly");
                $("#answer-chk").toggleClass("btn-outline-secondary");
                $("#answer-chk").toggleClass("btn-success");
                $("#answer-chk").off("click");
                alert("확인이 완료되었습니다");
            } else {
                alert("정답이 아닙니다");
            }
        },
        error: (err) => {
            console.error(err);
        }
    });
});
