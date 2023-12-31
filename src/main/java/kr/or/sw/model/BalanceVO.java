package kr.or.sw.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class BalanceVO {

    private String accountID;   // 계좌 ID, PK
    private int assets; // 보유금
    private int accIncome;  // 누적수입
    private int accExpense; // 누적비용
    private java.sql.Date lastUpdated;  // 변동일시
}
