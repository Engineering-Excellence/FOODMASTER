package kr.or.sw.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ProductDTO {   // 상품 테이블

    private int productID;  // 상품 ID, PK
    private String productName; // 상품명
    private int price;  // 가격
    private int stock;  // 재고수량
}
