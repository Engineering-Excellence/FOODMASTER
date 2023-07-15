package kr.or.sw.controller;

import static kr.or.sw.controller.HomeController.HOME_PATH;
import static kr.or.sw.controller.HomeController.handleInvalidAccess;

import java.io.IOException;
import java.io.Serial;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.sw.service.BalanceService;
import kr.or.sw.service.BalanceServiceImpl;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@WebServlet(name = "BalanceController", urlPatterns = {"/balance/*"})
public class BalanceController extends HttpServlet{
	
	

    
    private BalanceService balanceService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.info("doGet()");
        
        String pathInfo = request.getPathInfo();
        switch (pathInfo) {
            case "/order" -> {
                // 주문 목록 페이지
                log.info("/order");
            }
            case "/insert" -> log.info("/insert");    // 상품 추가 페이지
            case "/list" -> {
                // 상품 목록 페이지
                log.info("/list");

            }
            default -> handleInvalidAccess(request, response);
        }        
        request.setAttribute("path", request.getRequestURI().substring(request.getContextPath().length()));
        request.getRequestDispatcher(request.getContextPath() + HOME_PATH).forward(request, response);        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.info("doPost()");

    }

    @Override
    public void init() throws ServletException {
        log.info("/balance/*");
        balanceService = BalanceServiceImpl.getInstance();
    }

    @Override
    public void destroy() {
        log.info("destroy()");
    }

}