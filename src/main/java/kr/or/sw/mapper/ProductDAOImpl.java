package kr.or.sw.mapper;

import kr.or.sw.model.JoinTableVO;
import kr.or.sw.model.ProductDTO;
import kr.or.sw.model.ProductImgDTO;
import kr.or.sw.model.StockVO;
import kr.or.sw.util.MyBatisUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductDAOImpl implements ProductDAO {

    private static ProductDAO instance;

    public static synchronized ProductDAO getInstance() {
        if (instance == null) {
            instance = new ProductDAOImpl();
        }
        return instance;
    }

    @Override
    public List<ProductDTO> selectAllProduct() {
        log.info("selectAllProducts()");
        List<ProductDTO> productList;
        try (SqlSession sqlSession = MyBatisUtil.getSession()) {
            productList = sqlSession.selectList("selectAllProducts");
        }
        return productList;
    }

    @Override
    public List<ProductDTO> selectProductById(int productID) {
        log.info("selectMemberById(): {}", productID);
        List<ProductDTO> productList;
        try (SqlSession sqlSession = MyBatisUtil.getSession()) {
            productList = sqlSession.selectList("selectProductById", productID);
        }
        return productList;
    }

    @Override
    public List<ProductDTO> selectProductByCategory(String category) {
        log.info("selectProductByCategory()");
        List<ProductDTO> productList;
        try (SqlSession sqlSession = MyBatisUtil.getSession()) {
            productList = sqlSession.selectList("selectProductByCategory", category);
        }
        return productList;
    }

    @Override
    public List<ProductDTO> selectProductByName(String productName) {
        log.info("selectProductByName()");
        List<ProductDTO> productList;
        try (SqlSession sqlSession = MyBatisUtil.getSession()) {
            productList = sqlSession.selectList("selectProductByName", productName);
        }
        return productList;
    }

    @Override
    public ProductDTO selectProduct(int productID) {
        log.info("selectProduct(): {}", productID);
        ProductDTO productDTO;
        try (SqlSession sqlSession = MyBatisUtil.getSession()) {
            productDTO = sqlSession.selectOne("selectProduct", productID);
        }
        return productDTO;
    }

    @Override
    public ProductImgDTO selectProductImg(int productID) {
        log.info("selectProduct(): {}", productID);
        ProductImgDTO ProductImgDTO;
        try (SqlSession sqlSession = MyBatisUtil.getSession()) {
            ProductImgDTO = sqlSession.selectOne("selectProductImg", productID);
        }
        return ProductImgDTO;
    }

    @Override
    public int insertProduct(ProductDTO productDTO) {
        log.info("insertProduct(): {}", productDTO);

        int result;
        int imgResult;
        if (productDTO == null) return 0;
        try (SqlSession sqlSession = MyBatisUtil.getSession()) {
            result = sqlSession.insert("insertProduct", productDTO);
            imgResult = sqlSession.insert("insertProductImg", productDTO.getImage());
            if (result > 0 && imgResult > 0) sqlSession.commit();
            else sqlSession.rollback();
        }
        return result;
    }

    @Override
    public int deleteProduct(int productID) {
        log.info("deleteProduct(): {}", productID);

        int result;
        int imgResult;
        try (SqlSession sqlSession = MyBatisUtil.getSession()) {
            imgResult = deleteProductImg(sqlSession, productID);
            result = sqlSession.delete("deleteProduct", productID);
            log.info("result: {}", result);
            if (result > 0 && imgResult > 0) {
                sqlSession.commit();
            } else sqlSession.rollback();
        }
        return result + imgResult;
    }

    private int deleteProductImg(SqlSession sqlSession, int productID) {
        log.info("deleteProductImg(): {}", productID);

        return sqlSession.delete("deleteProductImg", productID);
    }

    @Override
    public int updateProduct(ProductDTO productDTO) {
        log.info("updateProduct(): {}", productDTO);

        int result;
        int imgResult = 0;
        try (SqlSession sqlSession = MyBatisUtil.getSession()) {
            if (productDTO.getImage() != null) imgResult = sqlSession.update("updateProductImg", productDTO.getImage());
            result = sqlSession.update("updateProduct", productDTO);
            if (result > 0) sqlSession.commit();
            else sqlSession.rollback();
        }
        return result + imgResult;
    }

    @Override
    public int updateRecipe(List<JoinTableVO> joinTableVOList) {
        log.info("updateRecipe()");

        try (SqlSession sqlSession = MyBatisUtil.getSession()) {
            sqlSession.update("updateRecipe", joinTableVOList);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<StockVO> selectCurrentRecipe(int productID) {
        log.info("selectCurrentRecipe(): {}", productID);
        List<StockVO> recipeList;
        try (SqlSession sqlSession = MyBatisUtil.getSession()) {
            recipeList = sqlSession.selectList("selectCurrentRecipe", productID);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return recipeList;
    }
}
