package kr.or.sw.mapper;

import kr.or.sw.model.ProductDTO;
import kr.or.sw.model.ProductImgDTO;

import java.util.List;

public interface CustomerDAO {

    public List<ProductDTO> selectMenuInfo();

    public List<ProductImgDTO> selectAllImgList();
}
