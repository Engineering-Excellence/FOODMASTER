package kr.or.sw.mapper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.sw.model.BalanceVO;
import kr.or.sw.model.StockDTO;
import kr.or.sw.util.MyBatisUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
            balanceList = sqlSession.selectList("selectAllBalances");
        }
        return balanceList;
    }

    @Override
    public int balanceInsert(BalanceVO balanceVO) {
        log.info("balanceInsert(): {}", balanceVO);

        int result;
        if (balanceVO == null)
            return 0;
        try (SqlSession sqlSession = MyBatisUtil.getSession()) {
            result = sqlSession.insert("balanceInsert", balanceVO);
            if (result > 0)
                sqlSession.commit();
            else
                sqlSession.rollback();
        }
        return result;
    }

	@Override
	public BalanceVO selectBalance(int accountID) {
		// TODO Auto-generated method stub
		return null;
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
