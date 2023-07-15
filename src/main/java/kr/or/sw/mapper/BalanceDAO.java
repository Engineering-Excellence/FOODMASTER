package kr.or.sw.mapper;

import java.util.List;

import kr.or.sw.model.BalanceVO;
import kr.or.sw.model.StockDTO;

public interface BalanceDAO {

	
	// selectAllBalance
	
	List<BalanceVO> selectAllBalances();

	BalanceVO selectBalance(int accountID);

	int balanceInsert(BalanceVO balanceVO);

	

	
	
}
