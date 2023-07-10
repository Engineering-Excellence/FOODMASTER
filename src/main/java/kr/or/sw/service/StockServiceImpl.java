package kr.or.sw.service;

import kr.or.sw.mapper.StockDAO;
import kr.or.sw.mapper.StockDAOImpl;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StockServiceImpl implements StockService {

    private static StockService instance;

    public static synchronized StockService getInstance() {
        if (instance == null) {
            instance = new StockServiceImpl();
        }
        return instance;
    }

    private final StockDAO stockDao = StockDAOImpl.getInstance();

    @Override
    public void select(HttpServletRequest request, HttpServletResponse response) {
        log.info("select()");
    }

    @Override
    public void selectAll(HttpServletRequest request, HttpServletResponse response) {
        log.info("selectAll()");
    }

    @Override
    public boolean insert(HttpServletRequest request, HttpServletResponse response) {
        log.info("insert()");
        return false;
    }

    @Override
    public boolean delete(HttpServletRequest request, HttpServletResponse response) {
        log.info("delete()");
        return false;
    }

    @Override
    public boolean update(HttpServletRequest request, HttpServletResponse response) {
        log.info("update()");
        return false;
    }
}
