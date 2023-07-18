package kr.or.sw.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.sw.mapper.BalanceDAO;
import kr.or.sw.mapper.BalanceDAOImpl;
import kr.or.sw.model.BalanceVO;
import kr.or.sw.model.ProductDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BalanceServiceImpl implements BalanceService{

    private static BalanceService instance;

    public static synchronized BalanceService getInstance() {
        if (instance == null) {
            instance = new BalanceServiceImpl();
        }
        return instance;
    }
    
    private final BalanceDAO balanceDAO = BalanceDAOImpl.getInstance();
    
    @Override
    public void selectAll(HttpServletRequest request, HttpServletResponse response) {
        log.info("selectAll()");
        // 모든 입출금 내역을 불러와서 최신순으로 정렬
        
        List<BalanceVO> list = new ArrayList<>(balanceDAO.selectAllBalances());
        log.info("selectAll: {}", list);

        request.setAttribute("balanceList", list);
        request.setAttribute("page", Objects.requireNonNullElse(request.getParameter("page"), 1));

    }
}
