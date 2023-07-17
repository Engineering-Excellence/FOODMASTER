package kr.or.sw.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.or.sw.mapper.AuthDAO;
import kr.or.sw.mapper.AuthDAOImpl;
import kr.or.sw.model.EmpDTO;
import kr.or.sw.model.MemberDTO;
import kr.or.sw.util.CipherUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)    // Singleton
public class AuthServiceImpl implements AuthService {

    private static AuthService instance;

    public static synchronized AuthService getInstance() {  // Thread-safe
        if (instance == null) {
            instance = new AuthServiceImpl();   // Lazy Initialization
        }
        return instance;
    }

    private CipherUtil cipher;
    private final AuthDAO authDAO = AuthDAOImpl.getInstance();

    @Override
    public boolean login(HttpServletRequest request, HttpServletResponse response) {
        // 로그인
        log.info("login()");

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // 입력한 이메일에 해당하는 DB의 비밀번호와 솔트를 가져옴
        MemberDTO memberDTO = authDAO.selectCredentials(email);
        if (memberDTO == null) return false;

        // 입력한 비밀번호를 해싱 후 DB의 비밀번호와 일치 여부를 검사
        cipher = CipherUtil.getInstance();
        return cipher.hashPassword(password, memberDTO.getSalt()).equals(memberDTO.getPassword());
    }

    @Override
    public void checkEmail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 이메일 중복 여부 확인
        log.info("checkEmail()");

        String email = request.getParameter("email");

        try (PrintWriter out = response.getWriter()) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            int ret = authDAO.checkEmail(email);
            log.info("ret: " + ret);

            ObjectMapper objectMapper = new ObjectMapper();
            out.write(objectMapper.writeValueAsString(ret));
            out.flush();
            log.info("check email done");
        }
    }

    @Override
    public void getQuestion(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 입력한 이메일로 비밀번호 찾기 질문 가져옴
        log.info("getQuestion()");

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String email = request.getParameter("email");
        try (PrintWriter out = response.getWriter()) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            MemberDTO ret = authDAO.getQuestion(email);
            log.info("ret: " + ret);

            ObjectMapper objectMapper = new ObjectMapper();
            out.write(objectMapper.writeValueAsString(ret));
            out.flush();
            log.info("get question done");
        }
    }

    @Override
    public void setMemberInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("getMemberInfo()");

        request.setCharacterEncoding("UTF-8");
        String email = request.getParameter("email");
        MemberDTO ret = authDAO.getMemberInfo(email);
        log.info("ret: " + ret);

        request.getSession().setAttribute("info", ret);
    }

    @Override
    public boolean resetPassword(HttpServletRequest request, HttpServletResponse response) {
        log.info("resetPassword()");

        cipher = CipherUtil.getInstance();
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setEmail(request.getParameter("email"));
        String salt = cipher.generateSalt(),
                password = cipher.hashPassword(request.getParameter("password"), salt);
        memberDTO.setSalt(salt);
        memberDTO.setPassword(password);

        int ret = authDAO.resetPassword(memberDTO);
        return ret == 1;
    }

    @Override
    public boolean admin(HttpServletRequest request, HttpServletResponse response) {
        // 관리자 로그인
        log.info("admin()");

        String account = request.getParameter("account");
        String password = request.getParameter("password");

        // 입력한 이메일에 해당하는 DB의 비밀번호와 솔트를 가져옴
        EmpDTO empDTO = authDAO.selectAdminCredentials(account);
        if (empDTO == null) return false;

        // 입력한 비밀번호를 해싱 후 DB의 비밀번호와 일치 여부를 검사
        cipher = CipherUtil.getInstance();
        return cipher.hashPassword(password, empDTO.getSalt()).equals(empDTO.getPassword());
    }

    @Override
    public void setAdminInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("getMemberInfo()");

        request.setCharacterEncoding("UTF-8");
        String account = request.getParameter("account");
        EmpDTO ret = authDAO.getAdminInfo(account);
        log.info("ret: " + ret);

        request.getSession().setAttribute("info", ret);
    }

    @Override
    public boolean insert(HttpServletRequest request, HttpServletResponse response) {
        log.info("insert()");
        // 회원가입

        cipher = CipherUtil.getInstance();
        String name = request.getParameter("name"),
                email = request.getParameter("email"),
                salt = cipher.generateSalt(),
                password = cipher.hashPassword(request.getParameter("password"), salt),
                contact = request.getParameter("contact"),
                question = request.getParameter("question"),
                answer = request.getParameter("answer"),
                birthDate = request.getParameter("birthDate");

        MemberDTO memberDTO = new MemberDTO(name, email, password, salt, contact, question, answer, birthDate);

        int ret = authDAO.insertMember(memberDTO);
        return ret == 1;
    }

    @Override
    public void select(HttpServletRequest request, HttpServletResponse response) {
        // 회원정보(본인) 보기 - Front에서 이미 구현
        log.info("select()");
    }

    @Override
    public boolean delete(HttpServletRequest request, HttpServletResponse response) {
        // 회원탈퇴
        log.info("delete()");
        return false;
    }

    @Override
    public boolean update(HttpServletRequest request, HttpServletResponse response) {
        // 회원정보 수정
        log.info("update()");
        return false;
    }
}
