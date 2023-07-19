package kr.or.sw.controller;

import kr.or.sw.service.StockService;
import kr.or.sw.service.StockServiceImpl;
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
@WebServlet(name = "StockController", urlPatterns = {"/stock/*"})
public class StockController extends HttpServlet {

    @Serial
    private static final long serialVersionUID = 4974481185726010330L;

    private StockService stockService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.info("doGet()");

        String pathInfo = request.getPathInfo();
        switch (pathInfo) {
            case "/list" -> {
                log.info("/list");
                handleSearch(request, response);
            }
            case "/insert" -> {
                log.info("/insert");
            }
            case "/update" -> {
                log.info("/update");
                stockService.select(request, response);
            }
            case "/order" -> {
                log.info("/order");
                // 재고 주문 페이지 이동
                stockService.select(request, response);
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

        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        String pathInfo = request.getPathInfo();
        switch (pathInfo) {
            case "/delete" -> {
                log.info("/delete");
                if (stockService.delete(request, response)) log.info("재고정보 삭제 성공");
                request.getRequestDispatcher(request.getContextPath() + HOME_PATH).forward(request, response);
            }
            case "/insert" -> {
                log.info("/insert");
                if (stockService.insert(request, response)) log.info("재고 추가 성공");
                response.sendRedirect("/stock/list");
            }
            case "/update" -> {
                log.info("/update");
                if (stockService.update(request, response)) log.info("재고 수정 성공");
                response.sendRedirect("/stock/list"); // stock/list
            }
            case "/order" -> {
                log.info("/order");
                // 재고를 주문해서 보유금이 차감되고 재고수량이 증가하는 로직 작성
<<<<<<< HEAD
                
                
                
=======
                if (stockService.order(request, response)) log.info("재료 발주 성공");
                response.sendRedirect("/stock/list");
>>>>>>> 62536b1791866a4c96b98e5a429b833b722046b2
            }
            default -> handleInvalidAccess(request, response);
        }
    }

    @Override
    public void init() throws ServletException {
        log.info("/stock/*");
        stockService = StockServiceImpl.getInstance();
    }

    @Override
    public void destroy() {
        log.info("destroy()");
    }

    private void handleSearch(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        int searchOption = 0;
        if (request.getParameter("searchOption") != null) {
            searchOption = Integer.parseInt(request.getParameter("searchOption"));
        }
        // searchOption이 0이면 전체 검색, 0이 아닌 다른 무언가면 그에 해당하는 검색을 진행
        switch (searchOption) {
            case 0 -> stockService.selectAll(request, response);
            case 1, 2, 3 -> stockService.searchBy(request, response);
            default -> handleInvalidAccess(request, response);
        }
    }
}
