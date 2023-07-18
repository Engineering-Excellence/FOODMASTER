package kr.or.sw.mapper;

import kr.or.sw.model.JoinTableVO;
import kr.or.sw.model.ProductDTO;
import kr.or.sw.model.ProductImgDTO;
import kr.or.sw.model.StockVO;

import java.util.List;

public interface ProductDAO {

    List<ProductDTO> selectAllProduct();

    List<ProductDTO> selectProductById(int productID);

    List<ProductDTO> selectProductByCategory(String category);

    List<ProductDTO> selectProductByName(String productName);

    ProductDTO selectProduct(int productID);

    ProductImgDTO selectProductImg(int productID);

    int insertProduct(ProductDTO productDTO);

    int deleteProduct(int productId);

    int updateProduct(ProductDTO productDTO);

    int updateRecipe(List<JoinTableVO> joinTableVOList);

    List<StockVO> selectCurrentRecipe(int productID);
}
