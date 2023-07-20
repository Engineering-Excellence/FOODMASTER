package kr.or.sw.service;

import kr.or.sw.mapper.BalanceDAO;
import kr.or.sw.mapper.BalanceDAOImpl;
import kr.or.sw.model.BalanceVO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BalanceServiceImpl implements BalanceService {

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

        List<HashMap<String, Object>> list = balanceDAO.selectAllincome();
        list.addAll(balanceDAO.selectAllExpense());

        list.sort((m1, m2) -> {
            Timestamp ts1 = (Timestamp) m1.get("DAY");
            Timestamp ts2 = (Timestamp) m2.get("DAY");
            return -ts1.compareTo(ts2);
        });

        log.info("selectAll: {}", list);

        request.setAttribute("balanceList", list);
        request.setAttribute("page", Objects.requireNonNullElse(request.getParameter("page"), 1));
    }

    @Override
    public void selectBalance(HttpServletRequest request, HttpServletResponse response) {
        log.info("selectBalance");

        BalanceVO balanceVO = balanceDAO.selectBalance();

        request.setAttribute("balance", balanceVO);
    }
}
