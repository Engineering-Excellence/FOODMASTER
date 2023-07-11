package kr.or.sw.mapper;

import kr.or.sw.model.ProductDTO;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

public interface ProductDAO {
	
	List<ProductDTO> selectAllProduct();
	
	List<ProductDTO> selectProductById(int productID);
	
	List<ProductDTO> selectProductByCategory(String category);
	
	List<ProductDTO> selectProductByName(String productName);

}
