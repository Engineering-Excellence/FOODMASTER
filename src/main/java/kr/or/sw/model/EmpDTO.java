package kr.or.sw.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class EmpDTO {

    private int empID;  // 직원 ID, PK
    private String account; // 계정
    private String password; // 비밀번호
    private String salt;    // salt
}
