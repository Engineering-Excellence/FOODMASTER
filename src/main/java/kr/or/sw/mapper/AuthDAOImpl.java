package kr.or.sw.mapper;

import kr.or.sw.model.EmpDTO;
import kr.or.sw.model.MemberDTO;
import kr.or.sw.util.MyBatisUtil;
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
    public MemberDTO selectCredentials(String email) {
        // 계정 및 비밀번호 일치 여부 검사
        log.info("selectCredentials(): {}", email);
        try (SqlSession sqlSession = MyBatisUtil.getSession()) {
            return sqlSession.selectOne("selectCredentials", email);
        }
    }

    @Override
    public int checkEmail(String email) {
        // 입력한 이메일이 DB에 존재하는 개수를 반환
        log.info("checkEmail(): {}", email);
        try (SqlSession sqlSession = MyBatisUtil.getSession()) {
            return sqlSession.selectOne("checkEmail", email);
        }
    }

    @Override
    public MemberDTO getQuestion(String email) {
        // 입력한 이메일의 비밀번호 찾기 질문과 답을 반환
        log.info("getQustion(): {}", email);
        try (SqlSession sqlSession = MyBatisUtil.getSession()) {
            return sqlSession.selectOne("getQuestion", email);
        }
    }

    @Override
    public MemberDTO getMemberInfo(String email) {
        // 입력한 이메일의 기초적인 회원 정보를 반환
        log.info("getMemberInfo: {}", email);
        try (SqlSession sqlSession = MyBatisUtil.getSession()) {
            return sqlSession.selectOne("getMemberInfo", email);
        }
    }

    @Override
    public int insertMember(MemberDTO memberDTO) {
        // 회원 가입
        log.info("insertMember(): {}", memberDTO);
        try (SqlSession sqlSession = MyBatisUtil.getSession()) {
            int result = sqlSession.insert("insertMember", memberDTO);
            if (result > 0) sqlSession.commit();
            return result;
        }
    }

    @Override
    public int resetPassword(MemberDTO memberDTO) {
        log.info("resetPassword(): {}", memberDTO);
        try (SqlSession sqlSession = MyBatisUtil.getSession()) {
            int result = sqlSession.insert("resetPassword", memberDTO);
            if (result > 0) sqlSession.commit();
            return result;
        }
    }

    @Override
    public EmpDTO selectAdminCredentials(String account) {
        log.info("selectAdminCredentials(): {}", account);
        try (SqlSession sqlSession = MyBatisUtil.getSession()) {
            return sqlSession.selectOne("selectAdminCredentials", account);
        }
    }

    @Override
    public EmpDTO getAdminInfo(String account) {
        log.info("selectAdminCredentials(): {}", account);
        try (SqlSession sqlSession = MyBatisUtil.getSession()) {
            return sqlSession.selectOne("getAdminInfo", account);
        }
    }
}
