package kr.or.sw.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ProductImgDTO {

    private String uuid;    // 중복이름 방지를 위한 랜덤 UUID, PK
    private String uploadPath;
    private String fileName;
    private int productID;  // 상품 ID, FK
}
