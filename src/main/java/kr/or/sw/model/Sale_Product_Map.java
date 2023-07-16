package kr.or.sw.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Sale_Product_Map {

    private int sale_productID; // 판매_상품 ID, PK
    private int saleID; // 판매 ID, FK
    private int productID;  // 상품 ID, FK
}
