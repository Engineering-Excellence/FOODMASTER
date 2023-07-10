package kr.or.sw.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class StockDTO { // 재고 테이블

    private int stockID;    // 재고 ID, PK
    private String stockName;   // 재고명
    private int amount; // 재고수량
    private int unitPrice;  // 단가
    private java.sql.Date stockDate;    // 최근입고일자
    private int productID;  // 상품 ID, FK
}
