package kr.or.sw.controller;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;

import static kr.or.sw.controller.HomeController.handleInvalidAccess;

@Slf4j
@WebServlet(name = "BalanceController", urlPatterns = {"/balance/*"})
public class BalanceController extends HttpServlet {

    @Serial
    private static final long serialVersionUID = -3319583315447280816L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("doGet()");

        String pathInfo = request.getPathInfo();
        switch (pathInfo) {
            case "/" -> {

            }
            default -> handleInvalidAccess(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("doPost()");

        request.setCharacterEncoding("UTF-8");
        String pathInfo = request.getPathInfo();
        switch (pathInfo) {
            case "/" -> {

            }
            default -> handleInvalidAccess(request, response);
        }
    }

    @Override
    public void init() throws ServletException {
        log.info("init()");
    }

    @Override
    public void destroy() {
        log.info("destroy()");
    }
}
