package kr.or.sw.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface AuthService extends Service {

    boolean login(HttpServletRequest request, HttpServletResponse response);

    void checkEmail(HttpServletRequest request, HttpServletResponse response) throws IOException;

//    boolean findAccount(HttpServletRequest request, HttpServletResponse response);

//    boolean resetPassword(HttpServletRequest request, HttpServletResponse response);
}
