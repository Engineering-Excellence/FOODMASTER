package kr.or.sw.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.text.SimpleDateFormat;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class StockDTO { // 재고 테이블

    private int stockID;    // 재고 ID, PK
    private String stockName;   // 재고명
    private int price;  // 단가
    private int quantity; // 재고수량
    private java.sql.Date stockDate;    // 최근입고일자

    public StockDTO(int stockID, int price, int quantity) {
        this.stockID = stockID;
        this.price = price;
        this.quantity = quantity;
    }

    public StockDTO(int stockID, String stockName, int quantity, int price) {
        this.stockID = stockID;
        this.stockName = stockName;
        this.quantity = quantity;
        this.price = price;

    }

    public StockDTO(String stockName, int price, int quantity, String stockDate) { // insert

        this.stockName = stockName;
        this.price = price;
        this.quantity = quantity;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.stockDate = new Date(sdf.parse(stockDate).getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
