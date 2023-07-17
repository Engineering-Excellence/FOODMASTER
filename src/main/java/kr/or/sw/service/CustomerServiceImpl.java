package kr.or.sw.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.or.sw.mapper.CustomerDAO;
import kr.or.sw.mapper.CustomerDAOImpl;
import kr.or.sw.model.ProductDTO;
import kr.or.sw.model.ProductImgDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
        response.setContentType("application/json");

        try (PrintWriter out = response.getWriter()) {
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
    @SneakyThrows
    public boolean insert(HttpServletRequest request, HttpServletResponse response) {
        log.info("insert()");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        ObjectMapper objectMapper = new ObjectMapper();

        try (PrintWriter out = response.getWriter()) {
            String data = request.getParameter("order");
            log.info("order: {}", data);

            JSONParser parser = new JSONParser();
            JSONArray array = (JSONArray) parser.parse(data);
            log.info("parsing success");

            List<ProductDTO> productDTOList = new ArrayList<>();
            for (Object order : array) {
                JSONObject jsonObj = (JSONObject) order;

                String productID = (String) jsonObj.get("productID");
                String quantity = (String) jsonObj.get("quantity");

                log.info("productID: {}, quantity: {}", productID, quantity);
                ProductDTO productDTO = new ProductDTO();
                productDTO.setProductID(Integer.parseInt(productID));
                productDTO.setQuantity(Integer.parseInt(quantity));
                productDTOList.add(productDTO);
            }
            boolean result = customerDAO.insertSale(productDTOList) == 1;
            out.write(objectMapper.writeValueAsString(result));
            out.flush();
            return result;
        }
    }

    @Override
    public void select(HttpServletRequest request, HttpServletResponse response) {
        // 회원정보(본인) 보기 - Front에서 Session으로 구현 완료
        log.info("select()");
    }

    @Override
    public boolean delete(HttpServletRequest request, HttpServletResponse response) {
        return false;
    }

    @Override
    public boolean update(HttpServletRequest request, HttpServletResponse response) {
        return false;
    }
}
