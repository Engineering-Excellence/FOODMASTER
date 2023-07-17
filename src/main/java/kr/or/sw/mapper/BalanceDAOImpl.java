package kr.or.sw.mapper;

import kr.or.sw.model.BalanceVO;
import kr.or.sw.util.MyBatisUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;

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
    public List<BalanceVO> selectAllBalances() {
        log.info("selectAllBalances()");
        List<BalanceVO> balanceList;
        try (SqlSession sqlSession = MyBatisUtil.getSession()) {
            // SqlSession 객체를 이용해서 모든 입출금 내역을 불러오기
            balanceList = sqlSession.selectList("selectAllBalances");
        }
        return balanceList;
    }
}