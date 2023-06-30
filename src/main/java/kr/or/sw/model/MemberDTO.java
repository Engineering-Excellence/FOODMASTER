package kr.or.sw.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.text.SimpleDateFormat;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class MemberDTO {

    private int memberID; // 회원ID, PK
    private String mName;   // 회원명
    private String email;   // 이메일(계정)
    private String password;    // 비밀번호
    private String salt;    // salt
    private String contact; // 연락처
    private String question;   // 본인확인질문
    private String answer;    // 본인확인정답
    private java.sql.Date birthday; // 생일
    private java.sql.Date regDate;  // 등록일
    private int remainTime; // 잔여시간

    public MemberDTO(String mName, String email, String password, String salt, String contact, String question, String answer, String birthday) {
        this.mName = mName;
        this.email = email;
        this.password = password;
        this.salt = salt;
        this.contact = contact;
        this.question = question;
        this.answer = answer;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.birthday = new Date(sdf.parse(birthday).getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
