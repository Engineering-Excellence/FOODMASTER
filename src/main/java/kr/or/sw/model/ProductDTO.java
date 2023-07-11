package kr.or.sw.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ProductDTO {   // 상품 테이블

    private int productID;  // 상품 ID, PK
    private String productName; // 상품명
    private String category;  // 카테고리
    private String description;  // 상품설명
    private int price;  // 가격
    private int stock;  // 재고수량
    private List<ProductImgDTO> imgList;    // 상품 이미지 리스트
}
