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

@WebServlet("/ref/item/delete")
public class RefItemDeleteController extends HttpServlet {
	
	private RefService refService = new RefServiceImpl();
	private static Logger logger = Logger.getLogger(RefItemDeleteController.class.getName());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String refCode = req.getParameter("refCode");
		logger.info("품목 삭제 요청에서 받은 냉장고 코드: " + refCode);

		// 품목 삭제 서비스
		refService.deleteRefItem(req);
		
		// 냉장고 품목 삭제 후 냉장고 메인페이지로 리다이렉트
		resp.sendRedirect("/ref/itemlist?refCode=" + refCode);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
