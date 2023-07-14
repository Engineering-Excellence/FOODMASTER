package kr.or.sw.service;

import kr.or.sw.mapper.ProductDAO;
import kr.or.sw.mapper.ProductDAOImpl;
import kr.or.sw.model.ProductDTO;
import kr.or.sw.model.ProductImgDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductServiceImpl implements ProductService {

    private static ProductService instance;

    public static synchronized ProductService getInstance() {
        if (instance == null) {
            instance = new ProductServiceImpl();
        }
        return instance;
    }

    private final ProductDAO productDAO = ProductDAOImpl.getInstance();

    @Override
    public void select(HttpServletRequest request, HttpServletResponse response) {
        log.info("select()");

        int productID = Integer.parseInt(request.getParameter("productID"));
        ProductDTO productDTO = productDAO.selectProduct(productID);
        request.setAttribute("productDTO", productDTO);
        log.info("productDTO: {}", productDTO);
    }

    @Override
    public void selectAll(HttpServletRequest request, HttpServletResponse response) {
        // 모든 상품 목록 불러오기
        log.info("selectAll()");

        List<ProductDTO> list = new ArrayList<>(productDAO.selectAllProduct());
        log.info("selectAll: {}", list);

        request.setAttribute("productList", list);
        request.setAttribute("page", Objects.requireNonNullElse(request.getParameter("page"), 1));
    }

    @Override
    public void searchBy(HttpServletRequest request, HttpServletResponse response) {
        log.info("searchBy()");

        List<ProductDTO> result;
        String keyword = request.getParameter("keyword");
        int searchOption = Integer.parseInt(request.getParameter("searchOption"));
        if (searchOption >= 2) keyword = "%" + keyword + "%";
        // searchOption이 1이면 id(숫자) 검색이므로 불필요

        result = switch (searchOption) {
            case 1 -> productDAO.selectProductById(Integer.parseInt(keyword));
            case 2 -> productDAO.selectProductByCategory(keyword);
            case 3 -> productDAO.selectProductByName(keyword);
            default -> throw new IllegalStateException("Unexpected value: " + searchOption);
        };

        List<ProductDTO> list = new ArrayList<>(result);
        log.info("size: {}", list.size());

        request.setAttribute("productList", list);
        request.setAttribute("page", Objects.requireNonNullElse(request.getParameter("page"), 1));
        request.setAttribute("searchOption", searchOption);
        request.setAttribute("keyword", keyword);
    }

    @Override
    public boolean insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("insert()");

        try {
            String uploadPath = request.getServletContext().getRealPath(File.separator + "upload");
            File uploadDirectory = new File(uploadPath);
            if (!uploadDirectory.exists()) {    // 업로드 디렉토리가 없을 경우 생성
                log.info("mkdir: {}", uploadDirectory.mkdirs());
            }

            Part part = request.getPart("productImg");
            UUID uuid = UUID.randomUUID();  // 파일명 중복 방지를 위한 random UUID
            String originalFileName = part.getSubmittedFileName();
            String fileName = uuid + "_" + originalFileName;
            String absPath = uploadPath + File.separator + fileName;
            String relPath = File.separator + "upload" + File.separator + fileName;
            log.info("filePath: {}, originalFileName: {}", absPath, originalFileName);
            part.write(absPath);

            ProductImgDTO productImgDTO = new ProductImgDTO(uuid.toString(), absPath, relPath, originalFileName);
            log.info("productImgDTO: {}", productImgDTO);

            ProductDTO productDTO = new ProductDTO(
                    request.getParameter("productName"),
                    request.getParameter("category"),
                    Integer.parseInt(request.getParameter("price")),
                    productImgDTO
            );

            int result = productDAO.insertProduct(productDTO);
            if (result > 0) return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(HttpServletRequest request, HttpServletResponse response) {
        log.info("delete()");

        int productID = Integer.parseInt(request.getParameter("productID"));
        ProductImgDTO productImgDTO = productDAO.selectProductImg(productID);
        String filePath = productImgDTO.getAbsPath();
        log.info("filePath: {}", filePath);

        File file = new File(filePath);
        return file.exists() && file.delete() && productDAO.deleteProduct(productID) == 2;
    }

    @Override
    public boolean update(HttpServletRequest request, HttpServletResponse response) {
        log.info("update()");

        try {
            int productID = Integer.parseInt(request.getParameter("productID"));
            ProductImgDTO productImgDTO = null;

            Part part = request.getPart("productImg");
            if (part != null && part.getSize() > 0) {
                String uploadPath = request.getServletContext().getRealPath(File.separator + "upload");
                File uploadDirectory = new File(uploadPath);
                if (!uploadDirectory.exists()) {    // 업로드 디렉토리가 없을 경우 생성
                    log.info("mkdir: {}", uploadDirectory.mkdirs());
                }

                String prevPath = productDAO.selectProductImg(productID).getAbsPath();
                File file = new File(prevPath);
                if (file.exists()) file.delete();   // 이미지를 수정할 경우 기존 이미지 삭제

                UUID uuid = UUID.randomUUID();
                String originalFileName = part.getSubmittedFileName();
                String fileName = uuid + "_" + originalFileName;
                String absPath = uploadPath + File.separator + fileName;
                String relPath = File.separator + "upload" + File.separator + fileName;
                part.write(absPath);

                productImgDTO = new ProductImgDTO(uuid.toString(), absPath, relPath, originalFileName, productID);
            }

            ProductDTO productDTO = new ProductDTO(
                    productID,
                    request.getParameter("productName"),
                    request.getParameter("category"),
                    Integer.parseInt(request.getParameter("price")),
                    productImgDTO
            );

            int result = productDAO.updateProduct(productDTO);
            if (result > 0) return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}
