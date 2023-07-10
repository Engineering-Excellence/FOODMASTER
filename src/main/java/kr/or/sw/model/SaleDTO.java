package kr.or.sw.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class SaleDTO {  // 판매 테이블

    private int saleID; // 판매 ID, PK
    private int quantity;   // 수량
    private int totalPrice; // 합계
    private java.sql.Date saleDate; // 판매일자
    private int productID;  // 상품 ID, FK
    private int memberID;   // 회원 ID, FK
}
