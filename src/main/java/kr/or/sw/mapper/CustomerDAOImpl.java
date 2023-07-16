package kr.or.sw.mapper;

import kr.or.sw.model.ProductDTO;
import kr.or.sw.model.ProductImgDTO;
import kr.or.sw.util.MyBatisUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)    // Singleton
public class CustomerDAOImpl implements CustomerDAO {

    private static CustomerDAO instance;

    public static synchronized CustomerDAO getInstance() {  // Thread-safe
        if (instance == null) {
            instance = new CustomerDAOImpl();   // Lazy Initialization
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
    public void insertSale(List<ProductDTO> productDTOList) {
        log.info("insertSale()");

        try (SqlSession sqlSession = MyBatisUtil.getSession()) {
            sqlSession.update("insertSale", productDTOList);
            log.info("insertSale() 성공");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
