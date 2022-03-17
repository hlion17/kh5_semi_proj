package controller.ref;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.RefService;
import service.impl.RefServiceImpl;

@WebServlet("/ref/itemlist/filterAndSort")
public class RefItemOrderByController extends HttpServlet {
	
	private RefService refService = new RefServiceImpl();
	private static Logger logger = Logger.getLogger(RefServiceImpl.class.getName());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// String refCode = req.getParameter("refCode");
		String orderBy = req.getParameter("orderBy");
		logger.info("쿼리파라미터로 전달된 정렬기준: " + orderBy);
		
		// 파라미터로 넘어온 정렬기준 값에 따라 다른 서비스 이용
		if ("expire_date".equals(orderBy) || orderBy == null) {
			refService.getFilteredItems(req);
		} else if ("regDate".equals(orderBy)) {
			refService.getFilteredItemsOrderByRegDate(req);
		}
		
		// 냉장고 품목을 필터링하고 정렬하는 서비스
		// refService.getOrderedRefItemList(req);
		
		// 냉장고 메인페이지로 리다이렉트
		// req.setAttribute("refCode", refCode);
		// req.getRequestDispatcher("/WEB-INF/views/ref/ref_main.jsp").forward(req, resp);
		req.getRequestDispatcher("/WEB-INF/views/ref/items.jsp").forward(req, resp);
	}

}
