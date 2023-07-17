package kr.or.sw.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.or.sw.service.CustomerService;
import kr.or.sw.service.CustomerServiceImpl;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;

import static kr.or.sw.controller.HomeController.VIEW_PATH;
import static kr.or.sw.controller.HomeController.handleInvalidAccess;

@Slf4j
@WebServlet(name = "CustomerController", urlPatterns = {"/customer/*"})
public class CustomerController extends HttpServlet {

    @Serial
    private static final long serialVersionUID = -2930158301476609066L;

    private CustomerService customerService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("doGet()");

        String pathInfo = request.getPathInfo();
        switch (pathInfo) {
            case "/home" -> {
                log.info("/home");
                request.getRequestDispatcher(VIEW_PATH + "customer.jsp").forward(request, response);
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
            case "/menu" -> {
                log.info("/menu");
                customerService.getMenu(request, response);
            }
            case "/insert" -> {
                log.info("/insert");
                handleInsert(request, response);
            }
            default -> handleInvalidAccess(request, response);
        }
    }

    @Override
    public void init() throws ServletException {
        log.info("/customer/*");
        customerService = CustomerServiceImpl.getInstance();
    }

    @Override
    public void destroy() {
        log.info("destroy()");
    }

    private void handleInsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        ObjectMapper objectMapper = new ObjectMapper();

        if (customerService.insert(request, response)) {
            try (PrintWriter out = response.getWriter()) {
                out.write(objectMapper.writeValueAsString(true));
                out.flush();
            }
        }
        else {
            try (PrintWriter out = response.getWriter()) {
                out.write(objectMapper.writeValueAsString(false));
                out.flush();
            }
        }
    }
}
