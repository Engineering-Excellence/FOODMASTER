package kr.or.sw.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.sw.mapper.BalanceDAO;
import kr.or.sw.mapper.BalanceDAOImpl;
import kr.or.sw.model.BalanceVO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BalanceServiceImpl implements BalanceService {

    private static BalanceService instance;

    public static synchronized BalanceService getInstance() {
        if (instance == null) {
            instance = new BalanceServiceImpl();
        }
        return instance;
    }
	
    private final BalanceDAO balanceDAO = BalanceDAOImpl.getInstance();


    
    @Override
    public void select(HttpServletRequest request, HttpServletResponse response) {
        log.info("select()");

        int accountID = Integer.parseInt(request.getParameter("accountID"));
        BalanceVO balanceVO = balanceDAO.selectBalance(accountID);
        request.setAttribute("balanceVO", balanceVO);
        log.info("balanceVO:{}", balanceVO);
    }

    @Override
    public void selectAll(HttpServletRequest request, HttpServletResponse response) {
        log.info("selectAll()");

        List<BalanceVO> list = new ArrayList<>(balanceDAO.selectAllBalances());

        request.setAttribute("balanceList", list);
        request.setAttribute("page", Objects.requireNonNullElse(request.getParameter("page"), 1));
    }
    
    
	@Override
	public void searchBy(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}    
    
	
	
	
    

	@Override
	public boolean insert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return false;
	}


   

}

