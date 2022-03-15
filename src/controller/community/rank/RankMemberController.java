package controller.community.rank;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/rank/member")
public class RankMemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("[TEST] /rank/member - RankMemberController [GET] 호출");
		
		//JSP를 VIEW로 지정, View로 응답
		System.out.println("[TEST] rankMember.jsp로 포워드");
		req.getRequestDispatcher("/WEB-INF/views/community/rank/rankMember.jsp").forward(req, resp);
	}

}
