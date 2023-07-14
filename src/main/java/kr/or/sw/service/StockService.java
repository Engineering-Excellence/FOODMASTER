package kr.or.sw.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface StockService extends Service {

    void selectAll(HttpServletRequest request, HttpServletResponse response);

    void searchBy(HttpServletRequest request, HttpServletResponse response);
}
