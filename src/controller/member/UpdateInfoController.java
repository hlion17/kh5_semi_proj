package controller.member;

import java.io.IOException;
import java.io.PrintWriter;

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
		
		
		//상세 결과 조회
//		Member updateMember = memberService.info(member);
		
		// 조회결과 MODEL값 전달
//		req.setAttribute("updateMember", updateMember);
		
		
		// VIEW 지정 및 응답 - forward
		//req.getRequestDispatcher("/WEB-INF/views/member/info.jsp").forward(req, resp);
		
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("/member/updateinfo Controller [POST] ");
		
		//전달 파라미터 얻기
		Member member = memberService.getUpdateInfoMember(req);
		
		System.out.println("추출한 회원 " +  member);
		
		Member result = memberService.updateMember(member);
		
		String alertMsg = "수정이 완료되었습니다.";
		
		String errorMsg = "수정이 실패하였습니다.";
		
		if(result != null) {
			req.setAttribute("alertMsg", alertMsg);
		} else {
			req.setAttribute("errorMsg", errorMsg);			
		}
		req.setAttribute("result", result);
		req.getRequestDispatcher("/WEB-INF/views/member/infoView.jsp").forward(req, resp);

//		System.out.println(member);

		
        
//		//메인페이지로 리다이렉트
//		resp.sendRedirect("/main");
		
	}
}
