package kr.or.sw.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ProductStockDTO {  // 상품과 재고의 다대다 관계를 표현하기 위한 중간 테이블

    private int productStockID; // 상품_재고 ID, PK
    private int productID;  // 상품 ID, FK
    private int stockID;    // 재고 ID, FK
}
