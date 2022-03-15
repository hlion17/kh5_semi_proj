package controller.ref;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.RefService;
import service.impl.RefServiceImpl;

@WebServlet("/ref/item/add")
public class RefItemAddController extends HttpServlet {
	
	private RefService refService = new RefServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String refCode = req.getParameter("refCode");
		req.setAttribute("refCode", refCode);
		
		req.getRequestDispatcher("/WEB-INF/views/ref/refitem_add.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 냉장고 아이템 등록 서비스
		refService.addRefItem(req);
		
		String refCode = req.getParameter("refCode");
		
		// 냉장고 메인페이지로 리다이렉트
		resp.sendRedirect("/ref/itemlist?refCode=" + refCode);
	}

}
