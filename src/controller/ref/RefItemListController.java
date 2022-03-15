package controller.ref;

import java.io.IOException;

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
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String refCode = req.getParameter("refCode");
		
		// 냉장고 품목 리스트를 가져오는 서비스
		refService.getRefItemList(req);
		
		req.setAttribute("refCode", refCode);
		
		// 냉장고 메인페이지로 이동
		req.getRequestDispatcher("/WEB-INF/views/ref/ref_main.jsp").forward(req, resp);
	}
	
	// 포스트 요청 없음
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
