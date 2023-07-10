package kr.or.sw.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StockDAOImpl implements StockDAO {

    private static StockDAO instance;

    public static synchronized StockDAO getInstance() {
        if (instance == null) {
            instance = new StockDAOImpl();
        }
        return instance;
    }
}
