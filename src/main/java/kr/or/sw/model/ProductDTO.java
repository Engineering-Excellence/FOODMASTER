package kr.or.sw.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ProductDTO {   // 상품 테이블

    private int productID;  // 상품 ID, PK
    private String productName; // 상품명
    private String category;  // 카테고리
    private int price;  // 가격
    private int quantity;  // 상품수량
    private ProductImgDTO image;    // 상품 이미지 정보
    private boolean isRecipe;       // 재료 등록 여부

    public ProductDTO(String productName, String category, int price, ProductImgDTO image) {
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.image = image;
    }

    public ProductDTO(int productID, String productName, String category, int price, ProductImgDTO image) {
        this.productID = productID;
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.image = image;
    }

    public ProductDTO(int productID, int price, int quantity) {
        this.productID = productID;
        this.price = price;
        this.quantity = quantity;
    }
}
