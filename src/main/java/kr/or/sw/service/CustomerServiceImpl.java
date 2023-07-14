package kr.or.sw.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.or.sw.mapper.CustomerDAO;
import kr.or.sw.mapper.CustomerDAOImpl;
import kr.or.sw.model.ProductDTO;
import kr.or.sw.model.ProductImgDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)    // Singleton
public class CustomerServiceImpl implements CustomerService {

    private static CustomerService instance;

    public static synchronized CustomerService getInstance() {  // Thread-safe
        if (instance == null) {
            instance = new CustomerServiceImpl();   // Lazy Initialization
        }
        return instance;
    }

    private final CustomerDAO customerDAO = CustomerDAOImpl.getInstance();

    @Override
    public void getMenu(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("getMenu()");
        List<ProductDTO> productList;
        List<ProductImgDTO> imgList;

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        try (PrintWriter out = response.getWriter()) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            productList = customerDAO.selectMenuInfo();
            log.info("selectMenuInfo Success");
            imgList = customerDAO.selectAllImgList();
            log.info("SelectAllImgList Success");

            for (int i = 0; i < productList.size(); i++)
                productList.get(i).setImage(imgList.get(i));

            ObjectMapper objectMapper = new ObjectMapper();
            out.write(objectMapper.writeValueAsString(productList));
            out.flush();
        }
    }

    @Override
    public boolean insert(HttpServletRequest request, HttpServletResponse response) {
        log.info("insert()");
        return false;
    }

    @Override
    public void select(HttpServletRequest request, HttpServletResponse response) {
        // 회원정보(본인) 보기
        log.info("select()");
    }

    @Override
    public boolean delete(HttpServletRequest request, HttpServletResponse response) {
        // 회원탈퇴
        log.info("delete()");
        return false;
    }

    @Override
    public boolean update(HttpServletRequest request, HttpServletResponse response) {
        // 회원정보 수정
        log.info("update()");
        return false;
    }
}
