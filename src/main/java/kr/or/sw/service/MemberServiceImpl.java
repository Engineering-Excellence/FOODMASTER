package kr.or.sw.service;

import kr.or.sw.mapper.MemberDAO;
import kr.or.sw.mapper.MemberDAOImpl;
import kr.or.sw.model.MemberDTO;
import kr.or.sw.util.MyBatisUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberServiceImpl implements MemberService{

    private static MemberService instance;

    public static synchronized MemberService getInstance() {
        if (instance == null) {
            instance = new MemberServiceImpl();
        }
        return instance;
    }

    private final MemberDAO memberDAO = MemberDAOImpl.getInstance();

    @Override
    public void selectAll(HttpServletRequest request, HttpServletResponse response) {
        log.info("selectAll()");

        List<MemberDTO> list;
        try (SqlSession sqlSession = MyBatisUtil.getSession()) {
            list = new ArrayList<>(memberDAO.selectAllMembers(sqlSession));
        }

        request.setAttribute("memberList", list);
        request.setAttribute("page", Objects.requireNonNullElse(request.getParameter("page"), 1));
    }

    @Override
    public void searchBy(HttpServletRequest request, HttpServletResponse response) {
        log.info("searchBy()");

        List<MemberDTO> result;
        String keyword = request.getParameter("keyword");
        int searchOption = Integer.parseInt(request.getParameter("searchOption"));
        if (searchOption >= 2) keyword = "%" + keyword + "%";
        // searchOption이 1이면 id(숫자) 검색이므로 불필요
        // 나머지는 문자열 포함 여부 검색이므로 like 연산을 위해 앞뒤에 % 추가

        try (SqlSession sqlSession = MyBatisUtil.getSession()) {
            result = switch (searchOption) {
                case 1 -> memberDAO.selectMemberById(sqlSession, Integer.parseInt(keyword));
                case 2 -> memberDAO.selectMemberByName(sqlSession, keyword);
                case 3 -> memberDAO.selectMemberByEmail(sqlSession, keyword);
                case 4 -> memberDAO.selectMemberByContact(sqlSession, keyword);
                default -> throw new IllegalStateException("Unexpected value: " + searchOption);
            };
        }
        List<MemberDTO> list = new ArrayList<>(result);
        log.info("size: {}", list.size());

        request.setAttribute("memberList", list);
        request.setAttribute("page", Objects.requireNonNullElse(request.getParameter("page"), 1));
        request.setAttribute("searchOption", searchOption);
        request.setAttribute("keyword", keyword);
    }

    @Override
    public void select(HttpServletRequest request, HttpServletResponse response) {
        log.info("select()");

        int memberID = Integer.parseInt(request.getParameter("memberID"));
        MemberDTO memberDTO;
        try (SqlSession sqlSession = MyBatisUtil.getSession()) {
            memberDTO = memberDAO.selectMember(sqlSession, memberID);
        }
        request.setAttribute("memberDTO", memberDTO);
        log.info("memberDTO: {}", memberDTO);
    }

    @Override
    public boolean delete(HttpServletRequest request, HttpServletResponse response) {
        log.info("delete()");

        int memberID = Integer.parseInt(request.getParameter("memberID"));
        int result;
        try (SqlSession sqlSession = MyBatisUtil.getSession()) {
            result = memberDAO.deleteMember(sqlSession, memberID);
            sqlSession.commit();
        }
        return result == 1;
    }

    @Override
    public boolean update(HttpServletRequest request, HttpServletResponse response) {
        log.info("update()");

        int memberID = Integer.parseInt(request.getParameter("memberID"));
        String email = request.getParameter("email");
        String contact = request.getParameter("contact");
        int remainTime = Integer.parseInt(request.getParameter("remainTime"));
        MemberDTO memberDTO = new MemberDTO(memberID, email, contact, remainTime);

        int result;
        try (SqlSession sqlSession = MyBatisUtil.getSession()) {
            result = memberDAO.updateMember(sqlSession, memberDTO);
            sqlSession.commit();
        }
        return result == 1;
    }

    @Override
    public boolean insert(HttpServletRequest request, HttpServletResponse response) {
        log.info("insert()");
        // 관리자가 사용자의 회원가입을 대리하는 경우
        return false;
    }
}
