package kr.or.sw.mapper;

import java.util.List;

import kr.or.sw.model.BalanceVO;

public interface BalanceDAO {

    List<BalanceVO> selectAllBalances();

}