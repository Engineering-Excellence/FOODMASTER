package kr.or.sw.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface AuthService {

    boolean login(HttpServletRequest request, HttpServletResponse response);

    void checkEmail(HttpServletRequest request, HttpServletResponse response) throws IOException;

    void getQuestion(HttpServletRequest request, HttpServletResponse response) throws IOException;

    void setMemberInfo(HttpServletRequest request, HttpServletResponse response) throws IOException;

    boolean resetPassword(HttpServletRequest request, HttpServletResponse response);

    boolean admin(HttpServletRequest request, HttpServletResponse response);

    void setAdminInfo(HttpServletRequest request, HttpServletResponse response) throws IOException;

    boolean confirmQuestion(HttpServletRequest request, HttpServletResponse response) throws IOException;

    boolean register(HttpServletRequest request, HttpServletResponse response);
}
