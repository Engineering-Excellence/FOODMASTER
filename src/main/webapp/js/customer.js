'use strict'

var menu;
var menuMaps = new Map();
var menuOrderMaps = new Map();
var shoppingList = new Map();
// (menu상의 index, 갯수) 쌍으로 데이터를 이루고 있음
var shoppingCount = 0;
var root = new Trie();

var isUpdatePassword = false;

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

const updateShoppingList = (idx, updateFlag) => {
	// updateFlag = false, minus
	// updateFlag = true, plug

	// 감소는 일단 1보다는 안내려가게 하고, 그냥 삭제가 있음
	if (!updateFlag) {
		if (!shoppingList.has(idx)) return;
		if (shoppingCount == 1) return;
		shoppingCount--;
		shoppingList.set(idx, shoppingList.get(idx) - 1);
	} else {
		shoppingCount++;
		if (!shoppingList.has(idx)) shoppingList.set(idx, 0);
		shoppingList.set(idx, parseInt(shoppingList.get(idx) + 1));
	}
}
const addEventListenerOnShoppingButton = (list) => {
	console.log(list);
	$(".shopping-btn").each((idx, element) => {
		$(element).click(() => {
			updateShoppingList(list[idx], true);
			console.log(shoppingList);
			updateShoppingModal();
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

const updateShoppingModal = () => {
	let htmls = "";
	const sortedList = Array.from([...shoppingList].sort());
	let total = 0;
	sortedList.forEach(entry => {
		htmls += '<div class="card border-secondary mb-3" style="max-width: 100%;">'
		htmls += '<div class="card-header">'
		htmls += `<div class="product-name-wrapper">${menu[entry[0]].productName}</div>`
		htmls += '<div class="product-remove-btn-wrapper">'
		htmls += '<button class="product-remove-btn">&times;</button>'
		htmls += '</div>'
		htmls += '</div>'
		htmls += '<div class="card-body text-secondary">'
		htmls += '<div class="shopping-count-wrapper">'
		htmls += '<button class="btn btn-outline-secondary shopping-product-count-minus">-</button>'
		htmls += `<input type="number" class="form-control shopping-product-count" name="keyword" value="${entry[1]}" style="text-align: center">`
		htmls += '<button class="btn btn-outline-secondary shopping-product-count-plus">+</button>'
		htmls += '</div>'
		htmls += '<div class="shopping-price-wrapper">'
		htmls += `<div class="shopping-price">${menu[entry[0]].price * entry[1]}원</div>`
		htmls += '</div>'
		htmls += '</div>'
		htmls += '</div>'

		total += menu[entry[0]].price * entry[1];
	});
	$("#shopping-modal-body").html(htmls);
	$("#total-price").text(total + "원");

	// 버튼들에 이벤트 리스너 추가

	$(".shopping-product-count-minus").each((idx, element) => {
		$(element).click(() => {
			console.log(sortedList[idx][0]);
			updateShoppingList(sortedList[idx][0], false);
			updateShoppingModal();
		});
	});
	$(".shopping-product-count").each((idx, element) => {
		$(element).keyup(() => {
			shoppingList.set(sortedList[idx][0], $(element).val());
			updateShoppingModal();
		});
	});
	$(".shopping-product-count-plus").each((idx, element) => {
		$(element).click(() => {
			updateShoppingList(sortedList[idx][0], true);
			updateShoppingModal();
		});
	});
	$(".product-remove-btn").each((idx, element) => {
		$(element).click(() => {
			shoppingList.delete(sortedList[idx][0]);
			shoppingCount -= sortedList[idx][1];
			$(".customer-info-shopping-count-text").text(shoppingCount);
			if (!shoppingCount) {
				$(".customer-info-shopping-count-wrapper").removeClass("show");
				$(".customer-info-shopping-count-wrapper").addClass("hide");
			}
			updateShoppingModal()
		});
	})
}

// $("#shopping-modal-btn").click(updateShoppingModal);

$("#shopping-make-order").click(() => {
	if (!shoppingCount) {
		alert("장바구니가 비어있습니다");
		return false;
	}
	const sortedList = Array.from([...shoppingList].sort());

    let data = [];
    sortedList.forEach((entry, idx) => {
        data.push({
            'memberID': String(memberID),
            'productName': menu[entry[0]].productName,
            'productID': String(menu[entry[0]].productID),
            'price':String(menu[entry[0]].price * entry[1]),
            'quantity': String(entry[1])
        });
    });
	console.log(data)
	// 서버에 주문을 보내는 ajax
	// string으로 바꿔 보내지 않으면 ""가 붙지 않아서
	// 후에 백엔드에서 JSON.simple로 파싱하는데 문제가 생기므로 주의
	$.ajax({
		url: "/customer/insert",
		type: "post",
		data: {
			order: JSON.stringify(data)
		},
		dataType: "json",
		traditional: true,
		success: (res) => {
			if (res) {
				shoppingList = new Map();
				shoppingCount = 0;
				$(".customer-info-shopping-count-text").text(shoppingCount);
				$(".customer-info-shopping-count-wrapper").removeClass("show");
				$(".customer-info-shopping-count-wrapper").addClass("hide");
				updateShoppingModal();

				alert("주문 완료");
			}
			else {
				alert("주문 중 문제가 생겼습니다");
			}
		},
	});
});

// 회원 탈퇴 ajax
$("#resign-button").on("click", function() {
	if (confirm("정말 탈퇴 하시겠습니까?")) {
		console.log(memberID);

		let data = {
			'memberID': String(memberID),
			'email': $("#resign-email").val(),
			'password': $("#resign-password").val()
		}

		console.log(data);
		$.ajax({
			url: "/customer/delete",
			type: "post",
			data: {
				customer: JSON.stringify(data)
			},
			dataType: "json",
			success: (res) => {
				console.log(res)
				if (res) {
					alert("탈퇴 완료");
					window.location.href = "/auth/logout";
				}
				else {
					alert("탈퇴 실패");
				}
			}
		})
	}
})

// 회원 수정 ajax
$("#update-button").on("click", function() {
	

	if (confirm("수정 하시겠습니까?")) {
		let data = {
			'memberID': String(memberID),
			'contact': $("#update-contact").val(),
			'password': $("#update-password").val()
		}

		console.log(data);
		$.ajax({
			url: "/customer/update",
			type: "post",
			data: {
				customerUpdate: JSON.stringify(data)
			},
			dataType: "json",
			success: (res) => {
				console.log(res)
				if (res) {
					alert("수정 완료");
					return true;
				}
				else {
					alert("수정 실패");
					return true;
				}
			}
		})
	}
	else
		return false;
})

$("#contact-update-btn").click(() => {
	if ($("#contact-update-btn").hasClass("btn-outline-secondary")) {
		$("#contact-update-btn").removeClass("btn-outline-secondary");
		$("#contact-update-btn").addClass("btn-success");
		$("#update-input-contact").removeAttr("readonly");
	}
	else  {
		$("#contact-update-btn").removeClass("btn-success");
		$("#contact-update-btn").addClass("btn-outline-secondary");
		$("#update-input-contact").attr("readonly", "readonly");
	}
});

$("#open-update-modal").click(() => {
	isUpdatePassword = false;
});

$("#password-update-btn").click(() => {
	isUpdatePassword = !isUpdatePassword;

	let height = 0;
	let mark = "▶"
	if (isUpdatePassword) {
		height = "fit-content";
		mark = "▼"
	}
	$(".password-update-wrapper").css("height", height);
	$("#password-update-btn").text(`비밀번호 수정 ${mark}`);
});