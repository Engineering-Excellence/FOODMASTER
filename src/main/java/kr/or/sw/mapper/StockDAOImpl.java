package kr.or.sw.mapper;

import kr.or.sw.model.StockVO;
import kr.or.sw.util.MyBatisUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

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

    @Override
    public List<StockVO> selectAllStocks() {
        log.info("selectAllStocks()");
        List<StockVO> stockList;
        try (SqlSession sqlSession = MyBatisUtil.getSession()) {
            stockList = sqlSession.selectList("selectAllStocks");
        }
        return stockList;
    }

    @Override
    public StockVO selectStock(int stockID) {
        log.info("selectStock(): {}", stockID);
        StockVO stockVO;
        try (SqlSession sqlSession = MyBatisUtil.getSession()) {

            stockVO = sqlSession.selectOne("selectStock", stockID);
        }
        return stockVO;
    }

    @Override
    public List<StockVO> selectStockById(int stockID) {
        log.info("selectStockById: {}", stockID);

        List<StockVO> stockList;
        try (SqlSession sqlSession = MyBatisUtil.getSession()) {

            stockList = sqlSession.selectList("selectStockById", stockID);
        }
        return stockList;
    }

    @Override
    public List<StockVO> selectStockByProductID(int productID) {
        log.info("selectStockByProductID: {}", productID);

        List<StockVO> stockList;
        try (SqlSession sqlSession = MyBatisUtil.getSession()) {

            stockList = sqlSession.selectList("selectStockByProductID", productID);
        }
        return stockList;
    }

    @Override
    public List<StockVO> selectStockByName(String stockName) {
        log.info("selectStockByName():{}", stockName);

        List<StockVO> stockList;
        try (SqlSession sqlSession = MyBatisUtil.getSession()) {

            stockList = sqlSession.selectList("selectStockByName", stockName);
        }
        return stockList;
    }

    @Override
    public int deleteStock(int stockID) {
        log.info("deleteStock(): {}", stockID);
        int result;
        try (SqlSession sqlSession = MyBatisUtil.getSession()) {
            result = sqlSession.delete("deleteStock", stockID);
            if (result > 0) sqlSession.commit();
        }
        return result;
    }

    @Override
    public int updateStock(StockVO stockVO) {
        log.info("updateStock(): {}", stockVO);
        int result;
        try (SqlSession sqlSession = MyBatisUtil.getSession()) {
            result = sqlSession.update("updateStock", stockVO);
            if (result > 0) sqlSession.commit();
        }
        return result;
    }

    @Override
    public int stockInsert(StockVO stockVO) {
        log.info("stockInsert(): {}", stockVO);

        int result;
<<<<<<< HEAD
        if (stockDTO == null)
=======

        if (stockVO == null)
>>>>>>> 5ec5c4cbfa9982f653a41a1ca0e467cab19389b0
            return 0;
        try (SqlSession sqlSession = MyBatisUtil.getSession()) {
            result = sqlSession.insert("stockInsert", stockVO);
            if (result > 0)
                sqlSession.commit();
            else
                sqlSession.rollback();
        }
        return result;
    }
}
