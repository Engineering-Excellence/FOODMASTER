package kr.or.sw.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface BalanceService {

    void selectAll(HttpServletRequest request, HttpServletResponse response);
}