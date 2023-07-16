package kr.or.sw.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class BalanceVO {

    private String accountID;   // 계좌 ID, PK
    private int amount; // 보유금
    private java.sql.Date lastUpdated;  // 변동일시
}