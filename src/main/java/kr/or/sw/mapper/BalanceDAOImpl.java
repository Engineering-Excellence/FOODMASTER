package kr.or.sw.mapper;

import kr.or.sw.util.MyBatisUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BalanceDAOImpl implements BalanceDAO {

    private static BalanceDAO instance;

    public static synchronized BalanceDAO getInstance() {
        if (instance == null) {
            instance = new BalanceDAOImpl();
        }
        return instance;
    }

    @Override
    public List<HashMap<String, Object>> selectAllincome() {
        log.info("selectAllincome()");
        List<HashMap<String, Object>> balanceIncome;
        try (SqlSession sqlSession = MyBatisUtil.getSession()) {
            // SqlSession 객체를 이용해서 모든 입출금 내역을 불러오기
            balanceIncome = sqlSession.selectList("selectAllincome");
            for (HashMap hm : balanceIncome) {
                log.info("data: {}", hm);
            }
        }
        return balanceIncome;
    }

    @Override
    public List<HashMap<String, Object>> selectAllExpense() {
        log.info("selectAllExpense()");
        List<HashMap<String, Object>> balanceExpense;
        try (SqlSession sqlSession = MyBatisUtil.getSession()) {
            // SqlSession 객체를 이용해서 모든 입출금 내역을 불러오기
            balanceExpense = sqlSession.selectList("selectAllExpense");
            for (HashMap hm : balanceExpense) {
                log.info("data: {}", hm);
            }
        }
        return balanceExpense;
    }
}