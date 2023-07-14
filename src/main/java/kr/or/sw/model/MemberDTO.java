package kr.or.sw.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.text.SimpleDateFormat;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class MemberDTO {    // 회원 테이블

    private int memberID; // 회원 ID, PK
    private String name;   // 회원명
    private String email;   // 이메일(계정)
    private String password;    // 비밀번호
    private String salt;    // salt
    private String contact; // 연락처
    private String question;   // 본인확인질문
    private String answer;    // 본인확인정답
    private java.sql.Date birthDate; // 생년월일
    private java.sql.Date regDate;  // 등록일
    private int remainTime; // 잔여시간

    public MemberDTO(int memberID, String email, String contact, int remainTime) {
        this.memberID = memberID;
        this.email = email;
        this.contact = contact;
        this.remainTime = remainTime;
    }

    public MemberDTO(String name, String email, String password, String salt, String contact, String question, String answer, String birthDate) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.salt = salt;
        this.contact = contact;
        this.question = question;
        this.answer = answer;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.birthDate = new Date(sdf.parse(birthDate).getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
