package controller.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.SendResult;

import dto.Member;
import service.face.MemberService;
import service.impl.MemberServiceImpl;

/**
 * Servlet implementation class MemberSignOutController
 */
@WebServlet("/member/signout")
public class MemberSignOutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private MemberService memberService = new MemberServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/WEB-INF/views/member/signout.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			

		
//		Member member = memberService.getLoginMember(req);
		
		
		
		boolean signout = memberService.signout(req);

		System.out.println("회원탈퇴인증" + signout);
		
		req.getSession().invalidate();

		// 성공 여부 확인 : 개발자용
		if (signout == true) {
			System.out.println("회원탈퇴 완료.");
			

		} else {
			System.out.println("회원탈퇴 실패.");
			
		}
		
		resp.sendRedirect("/main");
		
		
		
	}

}
