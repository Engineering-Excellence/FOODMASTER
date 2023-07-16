package kr.or.sw.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Product_Stock_Map {  // 상품과 재고의 다대다 관계를 표현하기 위한 중간 테이블

    private int product_stockID; // 상품_재고 ID, PK
    private int productID;  // 상품 ID, FK
    private int stockID;    // 재고 ID, FK
}
