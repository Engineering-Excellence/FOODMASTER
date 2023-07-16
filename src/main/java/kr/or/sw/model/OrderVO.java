package kr.or.sw.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class OrderVO { // 재고주문 테이블

    private int orderID;    // 주문 ID, PK
    private int quantity;   // 주문수량
    private java.sql.Date orderDate;    // 주문일자
    private int expenditure;    // 지출
    private int empID;  // 직원 ID, FK
    private int stockID;    // 재고 ID, FK
}
