package kr.or.sw.mapper;

import kr.or.sw.model.MemberDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)    // Singleton
public class AuthDAOImpl implements AuthDAO {

    private static final AuthDAO INSTANCE = new AuthDAOImpl();

    public static AuthDAO getInstance() {
        return INSTANCE;
    }

    @Override
    public MemberDTO selectCredentials(SqlSession sqlSession, String email) {
        log.info("selectCredentials() - email: {}", email); // 계정 및 비밀번호 일치 여부 검사

        return sqlSession.selectOne("selectCredentials", email);
    }

    @Override
    public int checkEmail(SqlSession sqlSession, String email) {
        log.info("checkEmail() - email: {}", email);

        return sqlSession.selectOne("checkEmail", email);
    }

    @Override
    public int insertMember(SqlSession sqlSession, MemberDTO memberDTO) {
        log.info("insertMember() - memberDTO: {}", memberDTO);

        return sqlSession.insert("insertMember", memberDTO);
    }
}

    