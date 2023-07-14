package kr.or.sw.mapper;

import kr.or.sw.model.StockDTO;
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
    public List<StockDTO> selectAllStocks() {
        log.info("selectAllStocks()");
        List<StockDTO> stockList;
        try (SqlSession sqlSession = MyBatisUtil.getSession()) {
            stockList = sqlSession.selectList("selectAllStocks");
        }
        return stockList;
    }

    @Override
    public StockDTO selectStock(int stockID) {
        log.info("selectStock(): {}", stockID);
        StockDTO stockDTO;
        try (SqlSession sqlSession = MyBatisUtil.getSession()) {

            stockDTO = sqlSession.selectOne("selectStock", stockID);
        }
        return stockDTO;
    }

    @Override
    public List<StockDTO> selectStockById(int stockID) {
        log.info("selectStockById: {}", stockID);

        List<StockDTO> stockList;
        try (SqlSession sqlSession = MyBatisUtil.getSession()) {

            stockList = sqlSession.selectList("selectStockById", stockID);
        }
        return stockList;
    }

    @Override
    public List<StockDTO> selectStockByProductID(int productID) {
        log.info("selectStockByProductID: {}", productID);

        List<StockDTO> stockList;
        try (SqlSession sqlSession = MyBatisUtil.getSession()) {

            stockList = sqlSession.selectList("selectStockByProductID", productID);
        }
        return stockList;
    }

    @Override
    public List<StockDTO> selectStockByName(String stockName) {
        log.info("selectStockByName():{}", stockName);

        List<StockDTO> stockList;
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
    public int updateStock(StockDTO stockDTO) {
        log.info("updateStock(): {}", stockDTO);
        int result;
        try (SqlSession sqlSession = MyBatisUtil.getSession()) {
            result = sqlSession.update("updateStock", stockDTO);
            if (result > 0) sqlSession.commit();
        }
        return result;
    }

    @Override
    public int stockInsert(StockDTO stockDTO) {
        log.info("stockInsert(): {}", stockDTO);

        int result;

        if (stockDTO == null)
            return 0;
        try (SqlSession sqlSession = MyBatisUtil.getSession()) {
            result = sqlSession.insert("stockInsert", stockDTO);
            if (result > 0)
                sqlSession.commit();
            else
                sqlSession.rollback();
        }
        return result;
    }
}
