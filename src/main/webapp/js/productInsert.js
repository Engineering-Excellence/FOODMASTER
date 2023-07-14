'use strict'

$("#preview").on("error", () => {
    $("#preview").css("display", "none");
});

$("#image-upload").change(() => {
    var file = $("input[type=file]")[0].files[0];
    var reader = new FileReader();

    reader.onloadend = () => {
        $("#preview").css("display", "block");
        $("#preview").attr("src", reader.result);
        $("#image-upload-label").text(fileName);
    }

    if (file) {
        var fileName = file.name;
        console.log(fileName);
        reader.readAsDataURL(file);
    } else {
        $("#preview").attr("src", "");
        $("#image-upload-label").text("상품 이미지 선택");
    }
});

// submit시 validation 등등 하기