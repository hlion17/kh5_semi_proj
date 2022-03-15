package controller.ref;

import java.io.IOException;

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
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("삭제 컨트롤러 도착");
		
		String refCode = req.getParameter("refCode");
		
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
