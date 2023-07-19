package kr.or.sw.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class OrderVO { // 재고주문 테이블

    private int orderID;    // 주문 ID, PK
    private int quantity;   // 주문수량
    private int expense;    // 비용
    private int assets; // 보유금
    private java.sql.Date orderDate;    // 주문일자
    private int stockID;    // 재고 ID, FK
    private int empID;  // 직원 ID, FK
    private String accountID;
}
