package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Member;
import service.face.MemberService;
import service.impl.MemberServiceImpl;

@WebServlet("/member/login")
public class MemberLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MemberService memberService = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("/WEB-INF/views/member/login.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//요청 데이터의 한글 UTF-8 처리 설정
    	req.setCharacterEncoding("UTF-8");
		
		//전달파라미터 얻기 - 로그인 정보(userid, userpw)
		Member member = memberService.getLoginMember(req);
		
		//로그인 인증 - MemberService이용
		boolean isLogin = memberService.login(member);
		System.out.println("로그인 컨트롤러 - 로그인 인증" + isLogin);
		
		//세션 정보 처리
		if( isLogin ) { //로그인 인증 성공
			
			member = memberService.info(member);
			
			//세션정보 저장하기
			HttpSession session = req.getSession();
			
			session.setAttribute("login", isLogin);
			session.setAttribute("memberno", member.getMemberno());
			session.setAttribute("memberid", member.getMemberid());
			session.setAttribute("memberpw", member.getMemberpw());
			session.setAttribute("nick", member.getNick());
			
			System.out.println("로그인 컨트롤러 - 로그인 성공");
			
		}
		
		//메인페이지로 리다이렉트
		resp.sendRedirect("/main.jsp");
		
	}
}
