package kr.or.sw.mapper;

import kr.or.sw.model.MemberDTO;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public interface MemberDAO {

    List<MemberDTO> selectAllMembers(SqlSession sqlSession);

    List<MemberDTO> selectMemberById(SqlSession sqlSession, int memberID);

    List<MemberDTO> selectMemberByName(SqlSession sqlSession, String name);

    List<MemberDTO> selectMemberByEmail(SqlSession sqlSession, String email);

    List<MemberDTO> selectMemberByContact(SqlSession sqlSession, String contact);

    MemberDTO selectMember(SqlSession sqlSession, int memberID);

    int updateMember(SqlSession sqlSession, MemberDTO memberDTO);

    int deleteMember(SqlSession sqlSession, int memberID);
}
