package kr.or.sw.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.or.sw.mapper.AuthDAOImpl;
import kr.or.sw.mapper.CustomerDAO;
import kr.or.sw.mapper.CustomerDAOImpl;
import kr.or.sw.mapper.MemberDAOImpl;
import kr.or.sw.model.MemberDTO;
import kr.or.sw.model.ProductDTO;
import kr.or.sw.model.ProductImgDTO;
import kr.or.sw.util.CipherUtil;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE) // Singleton
public class CustomerServiceImpl implements CustomerService {

    private static CustomerService instance;

    public static synchronized CustomerService getInstance() { // Thread-safe
        if (instance == null) {
            instance = new CustomerServiceImpl(); // Lazy Initialization
        }
        return instance;
    }

    private CipherUtil cipher = CipherUtil.getInstance();
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

            for (int i = 0; i < productList.size(); i++) {
                productList.get(i).setImage(imgList.get(i));
            }

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

            String memberID = null;
            List<ProductDTO> productDTOList = new ArrayList<>();
            for (Object order : array) {
                JSONObject jsonObj = (JSONObject) order;

                String productID = (String) jsonObj.get("productID");
                String price = (String) jsonObj.get("price");   // 여기서 price는 단가가 아니라 품목별 합계임
                String quantity = (String) jsonObj.get("quantity");
                memberID = (String) jsonObj.get("memberID");

                log.info("productID: {}, price:{}, quantity: {}", productID, price, quantity);
                ProductDTO productDTO = new ProductDTO(Integer.parseInt(productID), Integer.parseInt(price), Integer.parseInt(quantity));
                productDTOList.add(productDTO);
            }
            Map<String, Object> map = new HashMap<>();
            assert memberID != null;
            map.put("memberID", Integer.parseInt(memberID));
            map.put("productDTOList", productDTOList);
            boolean result = customerDAO.insertSale(map) == 1;
            out.write(objectMapper.writeValueAsString(result));
            out.flush();
            return result;
        }
    }

    @Override
    public void select(HttpServletRequest request, HttpServletResponse response) {
        // 회원정보(본인) 보기 - Front에서 Session으로 구현 완료
    }

    @Override
    @SneakyThrows
    public boolean delete(HttpServletRequest request, HttpServletResponse response) {

        ObjectMapper objectMapper = new ObjectMapper();

        try (PrintWriter out = response.getWriter()) {
            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(request.getParameter("customer"));
            System.out.println(request.getParameter("customer"));
            /* id, email, password */
            int memberID = Integer.parseInt((String) object.get("memberID"));
            String email = (String) object.get("email");
            String password = (String) object.get("password");
            log.info("data: {}, {}", email, password);

            MemberDTO memberDTO = AuthDAOImpl.getInstance().selectCredentials(email);

            if (cipher.hashPassword(password, memberDTO.getSalt()).equals(memberDTO.getPassword())) {
                boolean ret = MemberDAOImpl.getInstance().deleteMember(memberID) == 1;
                log.info("success: {}", ret);

                out.write(objectMapper.writeValueAsString(ret));
                out.flush();
                return ret;
            }
            out.write(objectMapper.writeValueAsString(false));
            out.flush();
        }
        return false;
    }

    @Override
    @SneakyThrows
    public boolean update(HttpServletRequest request, HttpServletResponse response) {

        ObjectMapper objectMapper = new ObjectMapper();

        try (PrintWriter out = response.getWriter()) {
            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(request.getParameter("customerUpdate"));

            int memberID = Integer.parseInt((String) object.get("memberID"));
            String contact = (String) object.get("contact");
            String password = (String) object.get("password");

            log.info("data: {}, {}", contact, password);

            MemberDTO memberDTO = new MemberDTO();
            memberDTO.setMemberID(memberID);
            memberDTO.setContact(contact);
            int ret = 0;
            if (password == null) {
                log.info("password is null");
                ret = MemberDAOImpl.getInstance().updateMemberSelfWOPassword(memberDTO);
            } else {
                log.info("password is not null");
                String salt = CipherUtil.getInstance().generateSalt();
                memberDTO.setSalt(salt);
                memberDTO.setPassword(CipherUtil.getInstance().hashPassword(password, salt));
                ret = MemberDAOImpl.getInstance().updateMemberSelf(memberDTO);
                log.info("ret: {}", ret);
            }

            out.write(objectMapper.writeValueAsString(ret == 1));
            out.flush();
            return ret == 1;
        }
    }
}
