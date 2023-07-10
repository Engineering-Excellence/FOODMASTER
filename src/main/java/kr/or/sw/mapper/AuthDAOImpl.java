package kr.or.sw.mapper;

import kr.or.sw.model.MemberDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)    // Singleton
public class AuthDAOImpl implements AuthDAO {

    private static AuthDAO instance;

    public static synchronized AuthDAO getInstance() {  // Thread-safe
        if (instance == null) {
            instance = new AuthDAOImpl();   // Lazy Initialization
        }
        return instance;
    }

    @Override
    public MemberDTO selectCredentials(SqlSession sqlSession, String email) {
        // 계정 및 비밀번호 일치 여부 검사
        log.info("selectCredentials(): {}", email);
        return sqlSession.selectOne("selectCredentials", email);
    }

    @Override
    public int checkEmail(SqlSession sqlSession, String email) {
        // 입력한 이메일이 DB에 존재하는 개수를 반환
        log.info("checkEmail(): {}", email);
        return sqlSession.selectOne("checkEmail", email);
    }

    @Override
    public MemberDTO getQuestion(SqlSession sqlSession, String email) {
        // 입력한 이메일의 비밀번호 찾기 질문과 답을 반환
        log.info("getQustion(): {}", email);
        return sqlSession.selectOne("getQuestion", email);
    }

    @Override
    public int insertMember(SqlSession sqlSession, MemberDTO memberDTO) {
        // 회원 가입
        log.info("insertMember(): {}", memberDTO);
        return sqlSession.insert("insertMember", memberDTO);
    }

    @Override
    public int resetPassword(SqlSession sqlSession, MemberDTO memberDTO) {
        log.info("resetPassword(): {}", memberDTO);
        return sqlSession.insert("resetPassword", memberDTO);
    }
}
