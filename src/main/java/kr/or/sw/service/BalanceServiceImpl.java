package kr.or.sw.service;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    }
}