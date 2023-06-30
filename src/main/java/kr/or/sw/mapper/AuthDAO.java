package kr.or.sw.mapper;

import kr.or.sw.model.MemberDTO;
import org.apache.ibatis.session.SqlSession;

public interface AuthDAO {

    MemberDTO selectCredentials(SqlSession sqlSession, String email);

    int checkEmail(SqlSession sqlsession, String email);

    int insertMember(SqlSession sqlSession, MemberDTO memberDTO);
}
