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
    public int insertSale(List<ProductDTO> productDTOList) {
        log.info("insertSale()");
        for (ProductDTO pdto : productDTOList)
            log.info("data : {}", pdto);

        try (SqlSession sqlSession = MyBatisUtil.getSession()) {
            sqlSession.insert("insertSale", productDTOList);
            return 1;   // Procedure에서 :RESULT를 반환하는 것에 실패함
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
