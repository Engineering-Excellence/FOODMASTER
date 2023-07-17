package kr.or.sw.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ProductService extends Service {

    void selectAll(HttpServletRequest request, HttpServletResponse response);

    void searchBy(HttpServletRequest request, HttpServletResponse response);

    void getOrder(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
