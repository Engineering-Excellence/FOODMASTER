package kr.or.sw.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class SaleVO {  // 판매 테이블

    private int saleID; // 판매 ID, PK
    private java.sql.Date orderDate; // 주문등록일자
    private java.sql.Date saleDate; // 판매일자
    private int income; // 수입
    private int memberID;   // 회원 ID, FK
}
