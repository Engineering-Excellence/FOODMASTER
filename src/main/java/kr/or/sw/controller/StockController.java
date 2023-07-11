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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("doGet()");

        String pathInfo = request.getPathInfo();
        switch (pathInfo) {
            case "/list" -> {
                log.info("/list");
                // 모든 재고 목록을 불러오는 로직
//                request.setAttribute("stockList", stockService.selectAll());
            }
            case "/insert" ->{
            	log.info("/insert"); // 재고 추가
            }
            case "/update" ->{
            	log.info("/update");   // 재고 수정
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
       switch(pathInfo) {
       		case "/insert" -> {
       			log.info("/insert");
       			if(stockService.insert(request, response)) log.info("재고 추가 성공");
       			response.sendRedirect("stock/list");
       			
       		}
       		case "/update" -> {
       			log.info("/update");
       			if(stockService.update(request, response)) log.info("재고 수정 성공");
       			response.sendRedirect("stock/list");
       			
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
}