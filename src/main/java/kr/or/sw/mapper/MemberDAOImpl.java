package kr.or.sw.mapper;

import kr.or.sw.model.MemberDTO;
import kr.or.sw.util.MyBatisUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberDAOImpl implements MemberDAO {

    private static MemberDAO instance;

    public static synchronized MemberDAO getInstance() {
        if (instance == null) {
            instance = new MemberDAOImpl();
        }
        return instance;
    }

    @Override
    public List<MemberDTO> selectAllMembers() {
        log.info("selectAllMembers()");
        List<MemberDTO> memberList;
        try (SqlSession sqlSession = MyBatisUtil.getSession()) {
            memberList = sqlSession.selectList("selectAllMembers");
        }
        return memberList;
    }

    @Override
    public List<MemberDTO> selectMemberById(int memberID) {
        log.info("selectMemberById(): {}", memberID);

        List<MemberDTO> memberList;
        try (SqlSession sqlSession = MyBatisUtil.getSession()) {
            memberList = sqlSession.selectList("selectMemberById", memberID);
        }
        return memberList;
    }

    @Override
    public List<MemberDTO> selectMemberByName(String name) {
        log.info("selectMemberByName()");

        List<MemberDTO> memberList;
        try (SqlSession sqlSession = MyBatisUtil.getSession()) {
            memberList = sqlSession.selectList("selectMemberByName", name);
        }
        return memberList;
    }

    @Override
    public List<MemberDTO> selectMemberByEmail(String email) {
        log.info("selectMemberByEmail()");

        List<MemberDTO> memberList;
        try (SqlSession sqlSession = MyBatisUtil.getSession()) {
            memberList = sqlSession.selectList("selectMemberByEmail", email);
        }
        return memberList;
    }

    @Override
    public List<MemberDTO> selectMemberByContact(String contact) {
        log.info("selectMemberByContact()");

        List<MemberDTO> memberList;
        try (SqlSession sqlSession = MyBatisUtil.getSession()) {
            memberList = sqlSession.selectList("selectMemberByContact", contact);
        }
        return memberList;
    }

    @Override
    public MemberDTO selectMember(int memberID) {
        log.info("selectMember(): {}", memberID);

        MemberDTO memberDTO;
        try (SqlSession sqlSession = MyBatisUtil.getSession()) {
            memberDTO = sqlSession.selectOne("selectMember", memberID);
        }
        return memberDTO;
    }

    @Override
    public int updateMember(MemberDTO memberDTO) {
        log.info("updateMember(): {}", memberDTO);
        int result;
        try (SqlSession sqlSession = MyBatisUtil.getSession()) {
            result = sqlSession.update("updateMember", memberDTO);
            if (result > 0) sqlSession.commit();
        }
        return result;
    }

    @Override
    public int deleteMember(int memberID) {
        log.info("deleteMember(): {}", memberID);
        int result;
        try (SqlSession sqlSession = MyBatisUtil.getSession()) {
            result = sqlSession.delete("deleteMember", memberID);
            if (result > 0) sqlSession.commit();
        }
        return result;
    }
}
