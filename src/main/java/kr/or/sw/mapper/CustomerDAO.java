package kr.or.sw.mapper;

import kr.or.sw.model.ProductDTO;
import kr.or.sw.model.ProductImgDTO;

import java.util.List;

public interface CustomerDAO {

    List<ProductDTO> selectMenuInfo();

    List<ProductImgDTO> selectAllImgList();

    int insertSale(List<ProductDTO> productDTOList);
}
