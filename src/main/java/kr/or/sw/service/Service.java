package kr.or.sw.service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Service {

    void select(HttpServletRequest request, HttpServletResponse response);

    boolean insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    boolean delete(HttpServletRequest request, HttpServletResponse response);

    boolean update(HttpServletRequest request, HttpServletResponse response);
}
