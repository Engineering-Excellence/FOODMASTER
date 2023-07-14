package kr.or.sw.mapper;

import kr.or.sw.model.StockDTO;

import java.util.List;

public interface StockDAO {

    List<StockDTO> selectAllStocks();

    List<StockDTO> selectStockById(int stockID);

    List<StockDTO> selectStockByProductID(int productID);

    List<StockDTO> selectStockByName(String stockName);

    int updateStock(StockDTO stockDTO);

    int deleteStock(int stockID);

    StockDTO selectStock(int stockID);

    int stockInsert(StockDTO stockDTO);
}
