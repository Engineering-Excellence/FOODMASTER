package kr.or.sw.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class SaleVO {  // 판매 테이블

    private int saleID; // 판매 ID, PK
    private int income; // 수입
    private int assets; // 보유금
    private java.sql.Date saleDate; // 판매일자
    private int memberID;   // 회원 ID, FK
    private String accountID;   // 계좌, FK
}
