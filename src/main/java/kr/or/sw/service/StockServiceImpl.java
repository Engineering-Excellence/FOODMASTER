package kr.or.sw.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.or.sw.mapper.StockDAO;
import kr.or.sw.mapper.StockDAOImpl;
import kr.or.sw.model.StockDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StockServiceImpl implements StockService {

    private static StockService instance;

    public static synchronized StockService getInstance() {
        if (instance == null) {
            instance = new StockServiceImpl();
        }
        return instance;
    }

    private final StockDAO stockDAO = StockDAOImpl.getInstance();

    @Override
    public void select(HttpServletRequest request, HttpServletResponse response) {
        log.info("select()");

        int stockID = Integer.parseInt(request.getParameter("stockID"));
        StockDTO stockDTO = stockDAO.selectStock(stockID);
        request.setAttribute("stockDTO", stockDTO);
        log.info("stockDTO:{}", stockDTO);
    }

    @Override
    public void selectAll(HttpServletRequest request, HttpServletResponse response) {
        log.info("selectAll()");

        List<StockDTO> list = new ArrayList<>(stockDAO.selectAllStocks());

        request.setAttribute("stockList", list);
        request.setAttribute("page", Objects.requireNonNullElse(request.getParameter("page"), 1));
    }

    @Override
    public boolean insert(HttpServletRequest request, HttpServletResponse response) {
        log.info("insert()");
        StockDTO stockDTO = new StockDTO(
                request.getParameter("stockName"),
                Integer.parseInt(request.getParameter("price")),
                Integer.parseInt(request.getParameter("quantity")),
                request.getParameter("stockDate"),
                Integer.parseInt(request.getParameter("productID"))
        );
        int result = stockDAO.stockInsert(stockDTO);
        return result > 0;
    }

    @Override
    public boolean delete(HttpServletRequest request, HttpServletResponse response) {
        log.info("delete()");

        int stockID = Integer.parseInt(request.getParameter("stockID"));
        return stockDAO.deleteStock(stockID) == 1;
    }

    @Override
    public boolean update(HttpServletRequest request, HttpServletResponse response) {
        log.info("update()");

        int stockID = Integer.parseInt(request.getParameter("stockID"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int price = Integer.parseInt(request.getParameter("price"));

        StockDTO stockDTO = new StockDTO(stockID, price, quantity);
        return stockDAO.updateStock(stockDTO) == 1;
    }

    @Override
    public void searchBy(HttpServletRequest request, HttpServletResponse response) {

        log.info("searchBy()");

        List<StockDTO> result;

        String keyword = request.getParameter("keyword");

        int searchOption = Integer.parseInt(request.getParameter("searchOption"));
//        if (searchOption == 2) keyword = "%" + keyword + "%";

        if (searchOption >= 3)
            keyword = "%" + keyword + "%";

        // searchOption이 1이면 id(숫자) 검색이므로 불필요
        // 나머지는 문자열 포함 여부 검색이므로 like 연산을 위해 앞뒤에 % 추가

        result = switch (searchOption) {
            case 1 -> stockDAO.selectStockById(Integer.parseInt(keyword));
            case 2 -> stockDAO.selectStockByProductID(Integer.parseInt(keyword));
            case 3 -> stockDAO.selectStockByName(keyword);
            default -> throw new IllegalStateException("Unexpected value: " + searchOption);
        };
        List<StockDTO> list = new ArrayList<>(result);
        log.info("size: {}", list.size());

        request.setAttribute("stockList", list);
        request.setAttribute("page", Objects.requireNonNullElse(request.getParameter("page"), 1));
        request.setAttribute("searchOption", searchOption);
        request.setAttribute("keyword", keyword);
    }

    public void getStocks(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("selectAll()");

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        try (PrintWriter out = response.getWriter()) {
            List<StockDTO> list = new ArrayList<>(stockDAO.selectAllStocks());

            ObjectMapper objectMapper = new ObjectMapper();
            out.write(objectMapper.writeValueAsString(list));
            out.flush();
        }
    }
}
