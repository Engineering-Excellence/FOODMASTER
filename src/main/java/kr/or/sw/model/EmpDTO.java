package kr.or.sw.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class EmpDTO {

    private int empID;   // 직원ID, PK
    private String eName; // 직원명
    private String email;   // 이메일
    private String password;    // 비밀번호
    private String contact;   // 연락처
    private String address; // 주소
}
