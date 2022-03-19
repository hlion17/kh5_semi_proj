package controller.member;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Member;
import service.face.MemberService;
import service.impl.MemberServiceImpl;


@WebServlet("/member/infoView")
public class MemberInfoViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MemberService memberService = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("MemberInfoView [POST]");
		
		Member member = memberService.getMemberInfoBySession(req);
		
		System.out.println(member);
		
		Member result = memberService.info(member);
		
		req.setAttribute("result", result);
		
		// VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/member/infoView.jsp").forward(req, resp);
		

	}


}
