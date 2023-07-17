package kr.or.sw.service;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.sw.mapper.BalanceDAO;
import kr.or.sw.model.BalanceVO;
import kr.or.sw.model.ProductDTO;

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

    @Override
    public void selectAll(HttpServletRequest request, HttpServletResponse response) {
        log.info("selectAll()");
        // 모든 입출금 내역을 불러와서 최신순으로 정렬
        
        List<BalanceVO> list = new ArrayList<>(BalanceDAO.selectBalanceList());
        log.info("selectAll: {}", list);

        request.setAttribute("balanceList", list);
        request.setAttribute("page", Objects.requireNonNullElse(request.getParameter("page"), 1));
        
        
    }

}
