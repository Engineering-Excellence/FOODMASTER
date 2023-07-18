package kr.or.sw.mapper;

import kr.or.sw.model.StockVO;

import java.util.List;

public interface StockDAO {

    List<StockVO> selectAllStocks();

    List<StockVO> selectStockById(int stockID);

    List<StockVO> selectStockByProductID(int productID);

    List<StockVO> selectStockByName(String stockName);

    int updateStock(StockVO stockVO);

    int deleteStock(int stockID);

    StockVO selectStock(int stockID);

    int stockInsert(StockVO stockVO);
}
