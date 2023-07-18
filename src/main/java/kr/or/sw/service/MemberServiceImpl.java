package kr.or.sw.service;

import kr.or.sw.mapper.MemberDAO;
import kr.or.sw.mapper.MemberDAOImpl;
import kr.or.sw.model.MemberDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberServiceImpl implements MemberService {

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

        List<MemberDTO> list = new ArrayList<>(memberDAO.selectAllMembers());

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

        result = switch (searchOption) {
            case 1 -> memberDAO.selectMemberById(Integer.parseInt(keyword));
            case 2 -> memberDAO.selectMemberByName(keyword);
            case 3 -> memberDAO.selectMemberByEmail(keyword);
            case 4 -> memberDAO.selectMemberByContact(keyword);
            default -> throw new IllegalStateException("Unexpected value: " + searchOption);
        };
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
        MemberDTO memberDTO = memberDAO.selectMember(memberID);
        request.setAttribute("memberDTO", memberDTO);
        log.info("memberDTO: {}", memberDTO);
    }

    @Override
    public boolean delete(HttpServletRequest request, HttpServletResponse response) {
        log.info("delete()");

        int memberID = Integer.parseInt(request.getParameter("memberID"));
        return memberDAO.deleteMember(memberID) == 1;
    }

    @Override
    public boolean update(HttpServletRequest request, HttpServletResponse response) {
        log.info("update()");

        int memberID = Integer.parseInt(request.getParameter("memberID"));
        String email = request.getParameter("email");
        String contact = request.getParameter("contact");

        MemberDTO memberDTO = new MemberDTO(memberID, email, contact);
        return memberDAO.updateMember(memberDTO) == 1;
    }

    @Override
    public boolean insert(HttpServletRequest request, HttpServletResponse response) {
        log.info("insert()");
        // 관리자가 사용자의 회원가입을 대리하는 경우
        return false;
    }
}
