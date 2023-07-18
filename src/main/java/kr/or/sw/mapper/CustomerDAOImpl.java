package kr.or.sw.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.sw.model.ProductDTO;
import kr.or.sw.model.ProductImgDTO;
import kr.or.sw.util.MyBatisUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE) // Singleton
public class CustomerDAOImpl implements CustomerDAO {

	private static CustomerDAO instance;

	public static synchronized CustomerDAO getInstance() { // Thread-safe
		if (instance == null) {
			instance = new CustomerDAOImpl(); // Lazy Initialization
		}
		return instance;
	}

	@Override
	public List<ProductDTO> selectMenuInfo() {
		log.info("selectMenuInfo()");
		try (SqlSession sqlSession = MyBatisUtil.getSession()) {
			return sqlSession.selectList("selectMenuInfo");
		}
	}

	@Override
	public List<ProductImgDTO> selectAllImgList() {
		log.info("selectAllImgList()");
		try (SqlSession sqlSession = MyBatisUtil.getSession()) {
			return sqlSession.selectList("selectAllImgList");
		}
	}

    // CustomerDAOImpl.java
    @Override
    public int insertSale(Map<String, Object> map) {
        log.info("insertSale()");

        try (SqlSession sqlSession = MyBatisUtil.getSession()) {
            sqlSession.insert("insertSale", map);
            return 1;   // Procedure에서 :RESULT를 반환하는 것에 실패함
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
