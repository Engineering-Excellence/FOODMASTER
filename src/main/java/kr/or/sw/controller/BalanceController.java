package kr.or.sw.controller;

import kr.or.sw.service.BalanceService;
import kr.or.sw.service.BalanceServiceImpl;
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
@WebServlet(name = "BalanceController", urlPatterns = {"/balance/*"})
public class BalanceController extends HttpServlet {

    @Serial
    private static final long serialVersionUID = -3319583315447280816L;

    private BalanceService balanceService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("doGet()");

        String pathInfo = request.getPathInfo();
        switch (pathInfo) {
            case "/list" -> {
                log.info("/list");
                // 입출금 내역 목록 불러오기
//                handleSearch(request, response);
                balanceService.selectAll(request, response);
            }
            default -> handleInvalidAccess(request, response);
        }
        request.setAttribute("path", request.getRequestURI().substring(request.getContextPath().length()));
        request.getRequestDispatcher(request.getContextPath() + HOME_PATH).forward(request, response);       
    }

    @Override
    public void init() throws ServletException {
        log.info("init()");
        balanceService = BalanceServiceImpl.getInstance();
    }

    @Override
    public void destroy() {
        log.info("destroy()");
    }
    
//    private void handleSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("UTF-8");
//
//        int searchOption = 0;
//        if (request.getParameter("searchOption") != null) {
//            searchOption = Integer.parseInt(request.getParameter("searchOption"));
//        }
//
//        // searchOption이 0이면 전체 검색, 0이 아닌 다른 무언가면 그에 해당하는 검색을 진행
//        switch (searchOption) {
//            case 0 -> balanceService.selectAll(request, response);
////            case 1, 2, 3 -> balanceService.searchBy(request, response);
//            default -> handleInvalidAccess(request, response);
//        }
//    }
    
    
    
}