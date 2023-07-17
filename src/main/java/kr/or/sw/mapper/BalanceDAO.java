package kr.or.sw.mapper;

import kr.or.sw.model.BalanceVO;

import java.util.List;

public interface BalanceDAO {

    List<BalanceVO> selectBalanceList();
}