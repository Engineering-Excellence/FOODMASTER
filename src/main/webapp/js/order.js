'use strict'

const showAllOrder = () => {

};

window.onload = () => {
    $.ajax({
        url: "/product/orderList",
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

            showAllOrder();
        },
    });
};