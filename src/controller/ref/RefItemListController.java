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

@WebServlet("/ref/itemlist")
public class RefItemListController extends HttpServlet {
 
	private RefService refService = new RefServiceImpl();
	private Logger logger = Logger.getLogger(RefServiceImpl.class.getName());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 파라미터 분석
		//String orderBy = req.getParameter("orderBy");
		//logger.info("/ref/itemlist 파라미터 - orderBy: " + orderBy);
		
		// 파라미터로 넘어온 정렬기준 값에 따라 다른 서비스 이용
//		if ("expire_date".equals(orderBy) || orderBy == null) {
//			// 유통기한 오름차순 정렬된 냉장고 품목 가져오는 서비스
//			refService.getAllItems(req);
//		} else if ("regDate".equals(orderBy)) {
//			// 등록일 내림차순 정렬된 냉장고 품목 가져오는 서비스
//			refService.getAllItemsDesc(req);
//		}
		
		refService.getAllItems(req);
		
		// 냉장고 메인페이지로 이동
		req.getRequestDispatcher("/WEB-INF/views/ref/ref_main.jsp").forward(req, resp);
		//req.getRequestDispatcher("/WEB-INF/views/ref/items.jsp").forward(req, resp);
	}
	
}
