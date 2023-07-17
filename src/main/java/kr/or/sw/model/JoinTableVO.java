package kr.or.sw.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class JoinTableVO {  // 상품과 재고의 다대다 관계를 표현하기 위한 중간 테이블

    private int productID;  // 상품 ID, 복합키
    private int stockID;    // 재고 ID, 복합키
    private int quantity;   // 수량
}
