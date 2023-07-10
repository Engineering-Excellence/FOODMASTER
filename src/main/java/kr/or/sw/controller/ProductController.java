package kr.or.sw.controller;

import kr.or.sw.service.ProductService;
import kr.or.sw.service.ProductServiceImpl;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.Serial;

import static kr.or.sw.controller.HomeController.HOME_PATH;
import static kr.or.sw.controller.HomeController.handleInvalidAccess;

@Slf4j
@WebServlet(name = "ProductController", urlPatterns = {"/product/*"})
public class ProductController extends HttpServlet {

    @Serial
    private static final long serialVersionUID = 5019171277715891863L;

    private ProductService productService;
    public static String uploadPath;

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
                productService.selectAll(request, response);
            }
            default -> handleInvalidAccess(request, response);
        }
        request.setAttribute("path", request.getRequestURI().substring(request.getContextPath().length()));
        request.getRequestDispatcher(request.getContextPath() + HOME_PATH).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("doPost()");

        response.setContentType("application/json");
        String pathInfo = request.getPathInfo();
        switch (pathInfo) {
            case "/insert" -> {
                // 상품 추가
                log.info("/insert");
                if (productService.insert(request, response)) log.info("상품 추가 성공");
                response.sendRedirect("product/list");
            }
            default -> handleInvalidAccess(request, response);
        }
    }

    @Override
    public void init() throws ServletException {
        log.info("/product/*");
        productService = ProductServiceImpl.getInstance();
        uploadPath = getServletContext().getRealPath("/upload/");
        File uploadDirectory = new File(uploadPath);
        if (!uploadDirectory.exists()) {    // 업로드 디렉토리가 없을 경우 생성
            log.info("mkdir: {}", uploadDirectory.mkdirs());
        }
        log.info("uploadPath: {}", uploadPath);
    }

    @Override
    public void destroy() {
        log.info("destroy()");
    }
}
