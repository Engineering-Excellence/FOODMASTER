package kr.or.sw.controller;

import kr.or.sw.service.ProductService;
import kr.or.sw.service.ProductServiceImpl;
import kr.or.sw.service.StockService;
import kr.or.sw.service.StockServiceImpl;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;

import static kr.or.sw.controller.HomeController.*;

@Slf4j
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(name = "ProductController", urlPatterns = {"/product/*"})
public class ProductController extends HttpServlet {

    @Serial
    private static final long serialVersionUID = 5019171277715891863L;

    private ProductService productService;
    private StockService stockService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
                handleSearch(request, response);
            }
            case "/update" -> {
                // 상품 수정
                log.info("/update");
                productService.select(request, response);
            }
            case "/ingredient" -> {
                log.info("/ingredient");
                request.getRequestDispatcher(request.getContextPath() + VIEW_PATH + "product/productSearchIngredient.jsp").forward(request, response);
                return;
            }
            default -> handleInvalidAccess(request, response);
        }
        request.setAttribute("path", request.getRequestURI().substring(request.getContextPath().length()));
        request.getRequestDispatcher(request.getContextPath() + HOME_PATH).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("doPost()");

        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        String pathInfo = request.getPathInfo();
        switch (pathInfo) {
            case "/insert" -> {
                // 상품 추가
                log.info("/insert");
                if (productService.insert(request, response)) log.info("상품 추가 성공");
                response.sendRedirect("/product/list");
            }
            case "/delete" -> {
                // 상품 삭제
                log.info("/delete");
                if (productService.delete(request, response)) {
                    log.info("상품 삭제 성공");
                    response.sendRedirect("/product/list");
                }
            }
            case "/update" -> {
                // 상품 수정
                log.info("/update");
                if (productService.update(request, response)) {
                    log.info("상품 수정 성공");
                    response.sendRedirect("/product/list");
                }
            }
            case "/orderList" -> {
                // 아직 판매하지 않은 주문 목록
                log.info("/orderList");
                productService.getOrder(request, response);
            }
            case "/stock" -> {
                log.info("/stock");
                stockService.getStocks(request, response);
            }
            default -> handleInvalidAccess(request, response);
        }
    }

    @Override
    public void init() throws ServletException {
        log.info("/product/*");
        productService = ProductServiceImpl.getInstance();
        stockService = StockServiceImpl.getInstance();
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
            case 0 -> productService.selectAll(request, response);
            case 1, 2, 3 -> productService.searchBy(request, response);
            default -> handleInvalidAccess(request, response);
        }
    }
}
