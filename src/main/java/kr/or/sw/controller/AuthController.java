package kr.or.sw.controller;

import kr.or.sw.service.AuthService;
import kr.or.sw.service.AuthServiceImpl;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;

import static kr.or.sw.controller.HomeController.VIEW_PATH;
import static kr.or.sw.controller.HomeController.handleInvalidAccess;

@Slf4j
@WebServlet(name = "AuthController", urlPatterns = {"/auth/*"})
public class AuthController extends HttpServlet {

    @Serial
    private static final long serialVersionUID = -2930158301476609066L;

    private AuthService authService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("doGet()");

        String pathInfo = request.getPathInfo();
        switch (pathInfo) {
            case "/index" -> {
                log.info("/index"); // 로그인 화면으로 이동
                redirectToIndex(request, response);
            }
            case "/logout" -> {
                log.info("/logout");    // 로그아웃
                request.getSession().invalidate();  // 로그인 세션 무효화
                redirectToIndex(request, response);
            }
            case "/register" -> {
                log.info("/register");
                request.getRequestDispatcher(VIEW_PATH + "auth/register.html").forward(request, response);
            }
            case "/password" -> {
                log.info("/register");
                request.getRequestDispatcher(VIEW_PATH + "auth/findPassword.html").forward(request, response);
            }
            default -> handleInvalidAccess(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("doPost()");

        request.setCharacterEncoding("UTF-8");
        String pathInfo = request.getPathInfo();
        log.info("pathInfo: {}", pathInfo);
        switch (pathInfo) {
            case "/login" -> {
                log.info("/login"); // 로그인
                handleLogin(request, response);
            }
            case "/register" -> {
                log.info("/register");
                if (authService.insert(request, response)) log.info("register success");
                redirectToIndex(request, response);
            }
            case "/checkEmail" -> {
                log.info("/checkEmail");
                authService.checkEmail(request, response);
            }
            case "/getQuestion" -> {
                log.info("/getQuestion");
                authService.getQuestion(request, response);
            }
            case "/resetPassword" -> {
                log.info("/resetPassword");
                if (authService.resetPassword(request, response)) log.info("resetPassword success");
                redirectToIndex(request, response);
            }
            default -> handleInvalidAccess(request, response);
        }
    }

    @Override
    public void init() throws ServletException {
        log.info("/auth/*");
        authService = AuthServiceImpl.getInstance();
    }

    @Override
    public void destroy() {
        log.info("destroy()");
    }

    private void redirectToIndex(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("redirectToIndex()");  // 로그인 화면으로 이동
        response.sendRedirect(request.getContextPath() + "/index.html");
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("handleLogin()");  // 로그인

        String email = request.getParameter("email");
        if (!authService.login(request, response)) {
            log.error("로그인 실패");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            redirectToIndex(request, response);
        } else {
            log.info("로그인 성공");
            request.getSession().setAttribute("email", email);  // 로그인 세션 저장
            request.setAttribute("redirect", "true");
            request.getRequestDispatcher(VIEW_PATH + "customer.jsp").forward(request, response);
        }
    }
}
