package controller.member;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Member;
import service.face.MemberService;
import service.impl.MemberServiceImpl;

/**
 * Servlet implementation class UpdateInfoController
 */
@WebServlet("/member/updateinfo")
public class UpdateInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MemberService memberService = new MemberServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//전달 파라미터 얻기
		Member member = memberService.getUpdateInfoMember(req);
		
		System.out.println(member);
		
		//상세 결과 조회
		Member updateMember = memberService.info(member);
		
		// 조회결과 MODEL값 전달
		req.setAttribute("updateMember", updateMember);
		
		// VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/member/updateinfo.jsp").forward(req, resp);
		
//		Member member = memberService.getUpdateInfoMember(req);
//		
//		
		memberService.updateMember(member); 
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doGet(req, resp);
		
	}
}
