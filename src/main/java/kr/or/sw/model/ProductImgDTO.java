package kr.or.sw.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ProductImgDTO {

    private String uuid;    // 중복이름 방지를 위한 랜덤 UUID, PK
    private String absPath; // 업로드 절대경로
    private String relPath; // 업로드 상대경로
    private String fileName;    // 파일명
    private int productID;  // 상품 ID, FK

    public ProductImgDTO(String uuid, String absPath, String relPath, String fileName) {
        this.uuid = uuid;
        this.absPath = absPath;
        this.relPath = relPath;
        this.fileName = fileName;
    }
}
