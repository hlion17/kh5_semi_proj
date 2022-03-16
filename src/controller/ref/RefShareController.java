package controller.ref;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.face.RefService;
import service.impl.RefServiceImpl;

@WebServlet("/ref/share")
public class RefShareController extends HttpServlet {
	
	private RefService refService = new RefServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// get 요청 필요 없어 보인다. 
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 다른 회원의 냉장고를 공유할 수 있는 서비스
		refService.shareRef(req);
		
		// 냉장고 선택 페이지로 리다이렉트 - 회원아이디 쿼리파라미터로 전달
		// 쿼리파라미터로 넘기면 url에 회원아이디가 노출되는데 req 객체에 넣어서 reqRequestDispacher로 넘기는 방법 생각해보기
		String memberId = (String) req.getAttribute("memberId");
		resp.sendRedirect("/ref/choose?memberId=" + memberId);
	}

}
