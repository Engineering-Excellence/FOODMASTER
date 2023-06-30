package kr.or.sw.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.or.sw.mapper.AuthDAO;
import kr.or.sw.mapper.AuthDAOImpl;
import kr.or.sw.model.MemberDTO;
import kr.or.sw.util.CipherUtil;
import kr.or.sw.util.MyBatisUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)    // Singleton
public class AuthServiceImpl implements AuthService {

    private static final AuthService INSTANCE = new AuthServiceImpl();

    public static AuthService getInstance() {
        return INSTANCE;
    }

    private final AuthDAO authDAO = AuthDAOImpl.getInstance();

    @Override
    public boolean login(HttpServletRequest request, HttpServletResponse response) {
        log.info("login()");

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        SqlSession sqlSession = MyBatisUtil.getSession();
        MemberDTO memberDTO = authDAO.selectCredentials(sqlSession, email);
        sqlSession.close();

        return CipherUtil.getInstance().hashPassword(password, memberDTO.getSalt()).equals(memberDTO.getPassword());
    }

    @Override
    public void checkEmail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("checkEmail()");

        String email = request.getParameter("email");

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        ObjectMapper objectMapper = new ObjectMapper();
        SqlSession sqlSession = MyBatisUtil.getSession();
        int ret = authDAO.checkEmail(sqlSession, email);
        sqlSession.close();

        log.info("ret: " + ret);
        out.write(objectMapper.writeValueAsString(ret));
        out.flush();
        log.info("check email done");
    }

    @Override
    public boolean insertMember(HttpServletRequest request, HttpServletResponse response) {
        log.info("insertMember()");
        CipherUtil cipher = CipherUtil.getInstance();

        String mName = request.getParameter("mName"),
                email = request.getParameter("email"),
                salt = cipher.generateSalt(),
                password = cipher.hashPassword(request.getParameter("password"), salt),
                contact = request.getParameter("contact"),
                question = request.getParameter("question"),
                answer = request.getParameter("answer"),
                birthday = request.getParameter("birthday");

        MemberDTO memberDTO = new MemberDTO(mName, email, password, salt, contact, question, answer, birthday);

        SqlSession sqlSession = MyBatisUtil.getSession();
        int ret = authDAO.insertMember(sqlSession, memberDTO);
        sqlSession.commit();
        sqlSession.close();
        return ret == 1;
    }
}
