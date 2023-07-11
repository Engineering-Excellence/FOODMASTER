package kr.or.sw.mapper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.sw.model.ProductDTO;
import kr.or.sw.util.MyBatisUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
}
