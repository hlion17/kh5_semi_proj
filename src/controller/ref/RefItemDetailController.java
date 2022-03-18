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

@WebServlet("/ref/items/detail")
public class RefItemDetailController extends HttpServlet {
	
	private RefService refService = new RefServiceImpl();
	private static Logger logger = Logger.getLogger(RefServiceImpl.class.getName());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 요청 파라미터  - 아이템번호(itemNo), 냉장고 코드(refCode)
		refService.getItemDetail(req);
		
		// 냉장고 품목 상세페이지로 이동
		req.getRequestDispatcher("/WEB-INF/views/ref/itemDetail.jsp").forward(req, resp);
	}
	
}
