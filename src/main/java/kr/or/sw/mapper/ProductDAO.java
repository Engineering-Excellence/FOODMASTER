package kr.or.sw.mapper;

import kr.or.sw.model.ProductDTO;
import kr.or.sw.model.ProductImgDTO;

import java.util.List;

public interface ProductDAO {

    List<ProductDTO> selectAllProduct();

    List<ProductDTO> selectProductById(int productID);

    List<ProductDTO> selectProductByCategory(String category);

    List<ProductDTO> selectProductByName(String productName);

    int insertProduct(ProductDTO productDTO);

    int deleteProduct(int productId);

    ProductDTO selectProduct(int productID);

    ProductImgDTO selectProductImg(int productID);
}
