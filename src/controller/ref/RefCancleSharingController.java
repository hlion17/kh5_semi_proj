package controller.ref;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.RefService;
import service.impl.RefServiceImpl;

@WebServlet({ "/RefCancleSharingController", "/ref/share/cancle" })
public class RefCancleSharingController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private RefService refService = new RefServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 공유 취소 서비스
		refService.cancelSharingRef(req);
		
		// 냉장고 공유 페이지로 리다이렉트
		resp.sendRedirect("/ref/share");
	}
}
