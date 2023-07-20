package kr.or.sw.mapper;

import java.util.HashMap;
import java.util.List;

public interface BalanceDAO {

    List<HashMap<String, Object>> selectAllincome();

    List<HashMap<String, Object>> selectAllExpense();
}