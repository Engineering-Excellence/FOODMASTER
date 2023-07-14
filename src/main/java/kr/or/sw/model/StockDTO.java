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
    private int amount; // 재고수량
    private int unitPrice;  // 단가
    private java.sql.Date stockDate;    // 최근입고일자
    private int productID;  // 상품 ID, FK

    public StockDTO(int stockID, int amount, int unitPrice) {
        this.stockID = stockID;
        this.amount = amount;
        this.unitPrice = unitPrice;
    }

    public StockDTO(int stockID, String stockName, int amount, int unitPrice) {
        this.stockID = stockID;
        this.stockName = stockName;
        this.amount = amount;
        this.unitPrice = unitPrice;

    }

    public StockDTO(String stockName, int amount, int unitPrice, String stockDate, int productID) { // insert

        this.stockName = stockName;
        this.amount = amount;
        this.unitPrice = unitPrice;
        this.productID = productID;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.stockDate = new Date(sdf.parse(stockDate).getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
