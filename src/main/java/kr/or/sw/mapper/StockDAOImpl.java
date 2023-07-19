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
    public int insertStock(StockVO stockVO) {
        log.info("insertStock(): {}", stockVO);

        int result;

        if (stockVO == null)
            return 0;
        try (SqlSession sqlSession = MyBatisUtil.getSession()) {
            result = sqlSession.insert("insertStock", stockVO);
            if (result > 0)
                sqlSession.commit();
            else
                sqlSession.rollback();
        }
        return result;
    }

    @Override
    public int orderStock(StockVO stockVO) {
        log.info("orderStock(): {}", stockVO);

        try (SqlSession sqlSession = MyBatisUtil.getSession()) {
            sqlSession.update("orderStock", stockVO);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
