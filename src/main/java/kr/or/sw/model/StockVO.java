package kr.or.sw.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class StockVO { // 재고 테이블

    private int stockID;    // 재고 ID, PK
    private String stockName;   // 재고명
    private int price;  // 단가
    private int quantity; // 재고수량
    private java.sql.Date stockDate;    // 최근입고일자

    public StockVO(int stockID, int price, int quantity) {
        this.stockID = stockID;
        this.price = price;
        this.quantity = quantity;
    }

    public StockVO(int stockID, String stockName, int quantity, int price) {
        this.stockID = stockID;
        this.stockName = stockName;
        this.quantity = quantity;
        this.price = price;
    }

    public StockVO(String stockName, int price) { // insert
        this.stockName = stockName;
        this.price = price;
    }
}
