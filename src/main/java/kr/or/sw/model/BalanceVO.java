package kr.or.sw.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class BalanceVO {

    private String accountID;   // 계좌 ID, PK
<<<<<<< HEAD
    private int asset; // 보유금
    private int income; // 매출
    private int expense; // 비용
    private java.sql.Timestamp lastUpdated;

}
=======
    private int assets; // 보유금
    private int income; // 매출
    private int expense;    // 비용
    private java.sql.Date lastUpdated;  // 변동일시
}
>>>>>>> 5ec5c4cbfa9982f653a41a1ca0e467cab19389b0
