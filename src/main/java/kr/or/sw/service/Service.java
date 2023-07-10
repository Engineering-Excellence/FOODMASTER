package kr.or.sw.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Service {

    void select(HttpServletRequest request, HttpServletResponse response);

    boolean insert(HttpServletRequest request, HttpServletResponse response);

    boolean delete(HttpServletRequest request, HttpServletResponse response);

    boolean update(HttpServletRequest request, HttpServletResponse response);
}
