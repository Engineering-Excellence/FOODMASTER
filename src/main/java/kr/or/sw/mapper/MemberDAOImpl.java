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
        try (SqlSession sqlSession = MyBatisUtil.getSession()) {
            return sqlSession.selectList("selectAllMembers");
        }
    }

    @Override
    public List<MemberDTO> selectMemberById(int memberID) {
        log.info("selectMemberById(): {}", memberID);
        try (SqlSession sqlSession = MyBatisUtil.getSession()) {
            return sqlSession.selectList("selectMemberById", memberID);
        }
    }

    @Override
    public List<MemberDTO> selectMemberByName(String name) {
        log.info("selectMemberByName()");
        try (SqlSession sqlSession = MyBatisUtil.getSession()) {
            return sqlSession.selectList("selectMemberByName", name);
        }
    }

    @Override
    public List<MemberDTO> selectMemberByEmail(String email) {
        log.info("selectMemberByEmail()");

        try (SqlSession sqlSession = MyBatisUtil.getSession()) {
            return sqlSession.selectList("selectMemberByEmail", email);
        }
    }

    @Override
    public List<MemberDTO> selectMemberByContact(String contact) {
        log.info("selectMemberByContact()");

        try (SqlSession sqlSession = MyBatisUtil.getSession()) {
            return sqlSession.selectList("selectMemberByContact", contact);
        }
    }

    @Override
    public MemberDTO selectMember(int memberID) {
        log.info("selectMember(): {}", memberID);
        try (SqlSession sqlSession = MyBatisUtil.getSession()) {
            return sqlSession.selectOne("selectMember", memberID);
        }
    }

    @Override
    public int updateMember(MemberDTO memberDTO) {
        log.info("updateMember(): {}", memberDTO);
        int result = 0;
        try (SqlSession sqlSession = MyBatisUtil.getSession()) {
            result = sqlSession.update("updateMember", memberDTO);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int deleteMember(int memberID) {
        log.info("deleteMember(): {}", memberID);
        int result = 0;
        try (SqlSession sqlSession = MyBatisUtil.getSession()) {
            result = sqlSession.delete("deleteMember", memberID);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int updateMemberSelf(MemberDTO memberDTO) {
        log.info("updateMemberSelf()");
        try (SqlSession sqlSession = MyBatisUtil.getSession()) {
            int ret = sqlSession.update("updateMemberSelf", memberDTO);
            sqlSession.commit();
            return ret;
        }
    }

    public int updateMemberSelfWOPassword(MemberDTO memberDTO) {
        log.info("updateMemberSelfWOPassword()");
        try (SqlSession sqlSession = MyBatisUtil.getSession()) {
            int ret = sqlSession.update("updateMemberSelfWOPassword", memberDTO);
            sqlSession.commit();
            return ret;
        }
    }
}
