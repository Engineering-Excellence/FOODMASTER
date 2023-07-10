package kr.or.sw.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class OrderDTO { // 주문 테이블

    private int orderID;    // 주문 ID, PK
    private int quantity;   // 주문수량
    private java.sql.Date orderDate;    // 주문일자
    private int stockID;    // 재고 ID, FK
}
