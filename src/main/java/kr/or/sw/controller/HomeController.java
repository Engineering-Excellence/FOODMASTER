package kr.or.sw.controller;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet(name = "home", value = "/home")
public class HomeController extends HttpServlet {

    private static final long serialVersionUID = 1586602244798946410L;

    private static final String HOME_PATH = "/WEB-INF/views/home.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("doGet()");
        request.getRequestDispatcher(request.getContextPath() + HOME_PATH).forward(request, response);
    }
}
