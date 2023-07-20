package kr.or.sw.mapper;

import kr.or.sw.model.BalanceVO;

import java.util.HashMap;
import java.util.List;

public interface BalanceDAO {

    List<HashMap<String, Object>> selectAllincome();

    List<HashMap<String, Object>> selectAllExpense();

    BalanceVO selectBalance();
}