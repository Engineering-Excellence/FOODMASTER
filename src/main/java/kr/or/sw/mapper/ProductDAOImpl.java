package kr.or.sw.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductDAOImpl implements ProductDAO {

    private static ProductDAO instance;

    public static synchronized ProductDAO getInstance() {
        if (instance == null) {
            instance = new ProductDAOImpl();
        }
        return instance;
    }
}
