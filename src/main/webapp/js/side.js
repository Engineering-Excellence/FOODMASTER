'use strict'

// -- side menu 펼치기 부분
// 현재는 클릭시 펼치기로 했지만
// menuHeads의 이벤트리스너 종류를 클릭으로 변경하고 기존 함수를 지우고, 주석한 부분으로 변경
// menuContainer의 주석처리한 이벤트리스너 부분을 주석해제 하면 마우스를 올리면 펼치는 것으로 변경 가능
var menuContainer = document.querySelector(".side-container")
var menuHeads = document.querySelectorAll(".side-wrapper:nth-child(2n + 1)");
var menuBodies = document.querySelectorAll(".side-wrapper:nth-child(2n)");
var isMenuClicked = Array(menuHeads.length).fill(false);

// menuContainer.addEventListener("mouseleave", () => {
//     menuBodies.forEach((e) => {
//         e.style.setProperty("display", "none");
//     });
// });

menuHeads.forEach((e, i) => {
    e.addEventListener("click", () => {
        //
        // menuBodies.forEach((e) => {
        //     e.style.setProperty("display", "none");
        // });
        // e.nextElementSibling.style.setProperty("display", "flex");

        if (!isMenuClicked[i]) {
            isMenuClicked.fill(false);
            menuBodies.forEach((e) => {
                e.style.setProperty("display", "none");
            });
            e.nextElementSibling.style.setProperty("display", "flex");
        } else {
            menuBodies.forEach((e) => {
                e.style.setProperty("display", "none");
            });
        }
        isMenuClicked[i] = !isMenuClicked[i];
    });
});
// -- side menu 펼치기 부분 종료