package controller.ref;

import java.io.IOException;

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
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String refCode = req.getParameter("refCode");
		
		// 냉장고 품목을 필터링하고 정렬하는 서비스
		refService.getOrderedRefItemList(req);
		
		// 냉장고 메인페이지로 리다이렉트
		req.setAttribute("refCode", refCode);
		req.getRequestDispatcher("/WEB-INF/views/ref/ref_main.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
