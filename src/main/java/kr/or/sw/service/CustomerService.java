package kr.or.sw.service;

import org.json.simple.parser.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface CustomerService extends Service {
    void getMenu(HttpServletRequest request, HttpServletResponse response) throws IOException;
    void makeOrder(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException;
}
