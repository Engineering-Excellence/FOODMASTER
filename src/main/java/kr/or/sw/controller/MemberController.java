package kr.or.sw.controller;

import kr.or.sw.service.MemberService;
import kr.or.sw.service.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;

import static kr.or.sw.controller.HomeController.HOME_PATH;
import static kr.or.sw.controller.HomeController.handleInvalidAccess;

@Slf4j
@WebServlet(name = "MemberController", urlPatterns = {"/member/*"})
public class MemberController extends HttpServlet {

    @Serial
    private static final long serialVersionUID = 5060263104786618675L;

    private MemberService memberService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("doGet()");

        String pathInfo = request.getPathInfo();
        switch (pathInfo) {
            case "/search" -> {
                log.info("/search");
                handleSearch(request, response);
            }
            case "/update" -> {
                log.info("/update");
                memberService.select(request, response);
//                request.getRequestDispatcher(VIEW_PATH + "member/memberUpdate.jsp").forward(request, response);
            }
            default -> handleInvalidAccess(request, response);
        }
        request.setAttribute("path", request.getRequestURI().substring(request.getContextPath().length()));
        request.getRequestDispatcher(request.getContextPath() + HOME_PATH).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("doPost()");

        String pathInfo = request.getPathInfo();
        switch (pathInfo) {
            case "/delete" -> {
                log.info("/delete");
                if (memberService.delete(request, response)) log.info("회원정보 삭제 성공");
            }
            case "/update" -> {
                log.info("/update");
                if (memberService.update(request, response)) log.info("회원정보 수정 성공");
                response.sendRedirect("member/search");
            }
            default -> handleInvalidAccess(request, response);
        }
    }

    @Override
    public void init() throws ServletException {
        log.info("/member/*");
        memberService = MemberServiceImpl.getInstance();
    }

    @Override
    public void destroy() {
        log.info("destroy()");
    }

    private void handleSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        int searchOption = 0;
        if (request.getParameter("searchOption") != null) {
            searchOption = Integer.parseInt(request.getParameter("searchOption"));
        }

        // searchOption이 0이면 전체 검색, 0이 아닌 다른 무언가면 그에 해당하는 검색을 진행
        switch (searchOption) {
            case 0 -> memberService.selectAll(request, response);
            case 1, 2, 3, 4 -> memberService.searchBy(request, response);
            default -> handleInvalidAccess(request, response);
        }
    }
}
