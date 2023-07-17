'use strict'

var stock;
var selectStock = new Set();
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

const updateSelectTable = () => {
    let htmls = "";
    const sortedList = Array.from([...selectStock].sort());
    console.log(sortedList);
    sortedList.forEach((stockIdx, idx) => {
        htmls += `<tr class="product-data">`
        htmls += `<td>${stock[stockIdx].stockID}</td>`
        htmls += `<td>${stock[stockIdx].stockName}</td>`
        htmls += '<td>'
        htmls += `<input type="number" class="form-control quantity" value="1" min="1" onkeypress="return (event.charCode !=8 && event.charCode ==0 || (event.charCode >= 48 && event.charCode <= 57))">`
        htmls += '</td>'
        htmls += '</tr>'
    });
    $("#select-table-body").html(htmls);
    $(".select-ingredient-result-count-container").text(`재료가 ${selectStock.size}가지 선택되었습니다`)
}
const addEventListenerOnStock = (list) => {
    const sortedList = Array.from([...list].sort());
    $("#search-table-body tr").each((idx, element) => {
        $(element).click(() => {
            console.log(idx);
            console.log(element);
            if (!selectStock.has(sortedList[idx])) {
                selectStock.add(sortedList[idx]);
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
        async: true,
        success: (res) => {
            stock = res;
            res.forEach((s, idx) => {
                console.log(s);
                console.log(idx)
                for (let i = 0; i < s.stockName.length; i++)
                    root.insert(root, s.stockName.substring(i), 0, idx);
            });

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
    let quantities = $(".quantity").each((idx, e) => {
        data.push({
            'productID': String(productID),
            'stockID': String(stock[sortedList[idx]].stockID),
            'quantity': String($(e).val())
        })
    });

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
