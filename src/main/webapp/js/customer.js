'use strict'

var menu;
var menuMaps = new Map();
var menuOrderMaps = new Map();
var shoppingList = new Map();
// (menu상의 index, 갯수) 쌍으로 데이터를 이루고 있음
var shoppingCount = 0;
var root = new Trie();

function throttle(callback, delay) {
    let timer
    return event => {
        if (timer) return;
        timer = setTimeout(() => {
            callback(event);
            timer = null;
        }, delay, event)
    }
}

// js에 queue가 없음;
class Queue {
    constructor() {
        this.storage = {};
        this.front = 0;
        this.rear = 0;
    }

    size() {
        if (this.storage[this.rear] === undefined) {
            return 0;
        } else {
            return this.rear - this.rear + 1;
        }
    }

    add(value) {
        if (this.size() === 0) {
            this.storage['0'] = value;
        } else {
            this.rear += 1;
            this.storage[this.rear] = value;
        }
    }

    pop() {
        let temp;
        if (this.front === this.rear) {
            temp = this.storage[this.front];
            delete this.storage[this.front];
            this.front = 0;
            this.rear = 0;
        } else {
            temp = this.storage[this.front];
            delete this.storage[this.front];
            this.front += 1;
        }
        return temp;
    }
}

function Trie() {
    this.output = new Set();
    this.fail = null;
    this.next = new Map();
}

Trie.prototype.insert = (self, key, stringIdx, menuIdx) => {
    if (key.length == stringIdx) {
        self.output.add(menuIdx);
    } else {
        let curr = key.charAt(stringIdx);
        self.output.add(menuIdx);
        if (!self.next.has(curr)) self.next.set(curr, new Trie());
        self.next.get(curr).insert(self.next.get(curr), key, stringIdx + 1, menuIdx);
    }
}

Trie.prototype.find = (self, key, stringIdx) => {
    if (key.length == stringIdx) return self.output;
    let curr = key.charAt(stringIdx);
    if (!self.next.has(curr)) return [];
    return self.next.get(curr).find(self.next.get(curr), key, stringIdx + 1);
}

// const makeFailure = (root) => {
//     let q = new Queue();
//     root.fail = root;
//     q.add(root);
//     while (q.size() > 0) {
//         let curr = q.pop();
//
//         for (let [key, next] of curr.next) {
//             if (curr == root) next.fail = root;
//             else {
//                 let dest = curr.fail;
//                 while (dest != root && !dest.next.has(key)) dest = dest.fail;
//
//                 if (dest.next.has(key)) dest = dest.next.get(key);
//                 next.fail = dest;
//             }
//
//             if (next.fail.output.length > 0) next.output.push(next.fail.output);
//             q.add(next);
//         }
//     }
// }
// const ahoCorasick = (s, root) => {
//     let curr = root;
//     let ret = [];
//     for (let char of s) {
//         while (curr != root && !curr.next.has(char)) curr = curr.fail;
//         if (curr.next.has(char)) curr = curr.next.get(char);
//         if (curr.output.length > 0) {
//             return ret = curr.output;
//         }
//     }
//     return ret;
// }
// 일대다 매칭이라 작성했다가
// 다시 생각해보니 Trie만으로도 가능해서 사용안함

const addEventListenerOnMenu = () => {
    $(".customer-menu").each((idx, element) => {
        $(element).mouseenter(() => {
            $(`.menu-shop-btn-wrapper:eq(${idx})`).removeClass("hide");
            $(`.menu-shop-btn-wrapper:eq(${idx})`).addClass("show");
        });
        $(element).mouseleave(() => {
            $(`.menu-shop-btn-wrapper:eq(${idx})`).removeClass("show");
            $(`.menu-shop-btn-wrapper:eq(${idx})`).addClass("hide");
        });
    });
}

const addEventListenerOnShoppingButton = (list) => {
    console.log(list);
    $(".shopping-btn").each((idx, element) => {
        $(element).click(() => {
            if (!shoppingCount) {
                $(".customer-info-shopping-count-wrapper").removeClass("hide");
                $(".customer-info-shopping-count-wrapper").addClass("show");
            }
            shoppingCount++;
            if (!shoppingList.has(list[idx])) shoppingList.set(list[idx], 0);
            shoppingList.set(list[idx], shoppingList.get(list[idx]) + 1);
            $(".customer-info-shopping-count-text").text(shoppingCount);

            console.log(shoppingList);
        });
    });
}

const showAllMenu = () => {
    let htmls = "";
    menu.forEach((m) => {
        htmls += '<div class="customer-menu">';
        htmls += `<img class="menu-img" alt="상품" src="${m.image.relPath}">`;
        htmls += '<div class="menu-info-container">';
        htmls += `<div class="menu-name">${m.productName}</div>`;
        htmls += `<div class="menu-price">${m.price}원</div>`;
        htmls += '</div>';
        htmls += '<div class="menu-shop-btn-wrapper hide">';
        htmls += '<button class="shopping-btn btn btn-warning">담기</button>';
        htmls += '</div>';
        htmls += '</div>';
    });
    $(".customer-menu-body-container").html(htmls);
    addEventListenerOnMenu();
    addEventListenerOnShoppingButton(Array.from(Array(menu.length).keys()));
}

const showCategoryMenu = (category) => {
    let htmls = "";
    if (menuMaps.has(category)) {
        menuMaps.get(category).forEach(idx => {
            htmls += '<div class="customer-menu">';
            htmls += `<img class="menu-img" alt="상품" src="${menu[idx].image.relPath}">`;
            htmls += '<div class="menu-info-container">';
            htmls += `<div class="menu-name">${menu[idx].productName}</div>`;
            htmls += `<div class="menu-price">${menu[idx].price}원</div>`;
            htmls += '</div>';
            htmls += '<div class="menu-shop-btn-wrapper hide">';
            htmls += '<button class="shopping-btn btn btn-warning">담기</button>';
            htmls += '</div>';
            htmls += '</div>';
        });
    }
    $(".customer-menu-body-container").html(htmls);
    addEventListenerOnMenu();
    addEventListenerOnShoppingButton(menuMaps.get(category));
}

const showListMenu = (list) => {
    let htmls = "";
    list.forEach(idx => {
        htmls += '<div class="customer-menu">';
        htmls += `<img class="menu-img" alt="상품" src="${menu[idx].image.relPath}">`;
        htmls += '<div class="menu-info-container">';
        htmls += `<div class="menu-name">${menu[idx].productName}</div>`;
        htmls += `<div class="menu-price">${menu[idx].price}원</div>`;
        htmls += '</div>';
        htmls += '<div class="menu-shop-btn-wrapper hide">';
        htmls += '<button class="shopping-btn btn btn-warning">담기</button>';
        htmls += '</div>';
        htmls += '</div>';
    });
    $(".customer-menu-body-container").html(htmls);
    addEventListenerOnMenu();
    addEventListenerOnShoppingButton(Array.from(list));
}

$('.customer-menu-header-category').each((i, e) => {
    menuOrderMaps.set($(e).text().trim(), i);
})

$(".customer-menu-header-category-wrapper").each((i, e) => {
    $(e).click(() => {
        if (!$(e).hasClass('selected-category')) {
            $('.customer-menu-header-category-wrapper').each((idx, element) => {
                $(element).removeClass('selected-category');
            })
            $(e).addClass('selected-category');
            if (i > 0) {
                let value = $(`.customer-menu-header-category:eq(${i})`).text();
                showCategoryMenu(value.trim());
            } else {
                showAllMenu();
            }
        }
    });
});

$("#search-input").keyup(throttle(() => {
    $(".customer-menu-header-category-wrapper").each((i, e) => {
        $(e).removeClass("selected-category");
    });

    // let list = ahoCorasick($("#search-input").val(), root);
    let list = root.find(root, $("#search-input").val(), 0);
    showListMenu(list);
}, 250));

window.onload = () => {
    $.ajax({
        url: "/customer/menu",
        type: "post",
        async: true,
        success: (res) => {
            res.sort((m1, m2) => {
                return menuOrderMaps.get(m1.category) - menuOrderMaps.get(m2.category);
            })

            menu = res;
            console.log(res);
            res.forEach((m, idx) => {
                if (!menuMaps.has(m.category)) menuMaps.set(m.category, []);
                menuMaps.get(m.category).push(idx);

                for (let i = 0; i < m.productName.length; i++)
                    root.insert(root, m.productName.substring(i), 0, idx);
            });

            // makeFailure(root);
            showAllMenu();
        },
    });
}
