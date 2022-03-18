package controller.ref;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.RefService;
import service.impl.RefServiceImpl;

@WebServlet("/ref/items/update")
public class RefItemUpdateController extends HttpServlet {
	
	private RefService refService = new RefServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException ,IOException {
		// 요청 파라미터 분석 - 냉장고 번호
		int refCode = Integer.parseInt(req.getParameter("refCode"));
		
		// 냉장고 품목 정보 수정
		refService.updateRefItem(req);
		
		// 냉장고 메인페이지로 리다이렉트
		resp.sendRedirect("/ref/itemlist?refCode=" + refCode);
	};

}
