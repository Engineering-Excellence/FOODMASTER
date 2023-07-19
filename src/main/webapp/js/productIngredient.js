'use strict'

var stock;
var selectStock = new Map();
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
const addEventListenerInput = () => {
    $(".quantity").each((idx, e) => {
        $(e).change(() => {
            selectStock.set(selectStock[idx][0], $(e).val());
            updateSelectTable();
        });
    });
}
const updateSelectTable = () => {
    let htmls = "";
    const sortedList = Array.from([...selectStock].sort());
    console.log(sortedList);
    sortedList.forEach((entry, idx) => {
        console.log(entry);
        htmls += `<tr class="product-data">`
        htmls += `<td>${stock[entry[0]].stockID}</td>`
        htmls += `<td>${stock[entry[0]].stockName}</td>`
        htmls += '<td>'
        htmls += `<input type="number" class="form-control quantity" value="${entry[1]}" min="1" onkeypress="return (event.charCode !=8 && event.charCode ==0 || (event.charCode >= 48 && event.charCode <= 57))">`
        htmls += '<div class="selected-ingredient-remove-btn-wrapper">'
        htmls += '<button class="selected-ingredient-remove-btn">&times;</button>'
        htmls += '</div>'
        htmls += '</td>'
        htmls += '</tr>'
    });
    $("#select-table-body").html(htmls);
    $(".select-ingredient-result-count-container").text(`재료가 ${selectStock.size}가지 선택되었습니다`)

    $(".selected-ingredient-remove-btn").each((idx, element) => {
        $(element).click(() => {
            selectStock.delete(sortedList[idx][0]);
            updateSelectTable();
        });
    })
    addEventListenerInput();
}
const addEventListenerOnStock = (list) => {
    const sortedList = Array.from([...list].sort());
    $("#search-table-body tr").each((idx, element) => {
        $(element).click(() => {
            console.log(idx);
            console.log(element);
            if (!selectStock.has(sortedList[idx])) {
                selectStock.set(sortedList[idx], 1);
                updateSelectTable();
            }
        });
    })
    console.log(list);
}
const showAllStock = () => {
    let htmls = "";
    stock.forEach((s, idx) => {
        htmls += `<tr class="product-data">`
        htmls += `<td>${s.stockID}</td>`
        htmls += `<td>${s.stockName}</td>`
        htmls += '</tr>'
    });
    $("#search-table-body").html(htmls);
    addEventListenerOnStock(Array.from(Array(stock.length).keys()));
    // 버튼 이벤트 리스너 달기
}

const showListMenu = (list) => {
    let htmls = "";
    list.forEach(idx => {
        htmls += `<tr class="product-data" id="productData${idx}">`
        htmls += `<td>${stock[idx].stockID}</td>`
        htmls += `<td>${stock[idx].stockName}</td>`
        htmls += '</tr>'
    });
    $("#search-table-body").html(htmls);
    addEventListenerOnStock(list);
}

$("#keyword").keyup(throttle(() => {
    let list = root.find(root, $("#keyword").val(), 0);
    showListMenu(list);
}, 250));

window.onload = () => {
    $.ajax({
        url: "/product/stock",
        type: "post",
        data: {
            productID: new URL(window.location.href).searchParams.get("productID")
        },
        async: true,
        success: (res) => {
            // 재고 목록 가져오는 부분
            console.log(res);
            stock = res.stock;

            let curr = 0;
            stock.forEach((s, idx) => {
                if (curr < res.recipe.length && s.stockID == res.recipe[curr].stockID) {
                    selectStock.set(idx, res.recipe[curr].quantity);
                    curr++;
                }

                for (let i = 0; i < s.stockName.length; i++)
                    root.insert(root, s.stockName.substring(i), 0, idx);
            });

            updateSelectTable();
            showAllStock();
        },
    });
};

$("#close-window").click(() => {
    window.close();
});
$("#confirm-window").click(() => {
    // {상품 ID, 재고 ID, 수량} 로 리스트로 해서 보내기
    let data = [];
    const sortedList = Array.from([...selectStock].sort());

    let productID = new URL(window.location.href).searchParams.get("productID");
    let chk = true;
    $(".quantity").each((idx, e) => {
        if ($(e).val() == 0) {
            chk = false;
            return;
        }

        data.push({
            'productID': String(productID),
            'stockID': String(stock[sortedList[idx][0]].stockID),
            'quantity': String($(e).val())
        })
    });

    if (!chk) {
        alert("수량은 0 이하가 될 수 없습니다");
        return;
    }

    console.log(data)

    $.ajax({
        url: "/product/recipe",
        type: "post",
        data: {
            recipe: JSON.stringify(data)
        },
        dataType: "json",
        traditional: true,
        success: (res) => {
            if (res) {
                alert("재료 등록 성공");
                window.close();
            } else {
                alert("재료 등록 실패")
                window.close();
            }
        },
    });
});
