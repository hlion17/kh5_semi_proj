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


@WebServlet("/member/join")
public class MemberJoinController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MemberService memberService = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("/WEB-INF/views/member/join.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//요청 데이터의 한글 UTF-8 처리 설정
    	req.setCharacterEncoding("UTF-8");
		
		//회원가입 전달파라미터 추출하고 가입처리하기
		Member member = memberService.getJoinMember(req);
		
		//DB에 입력된 값을 View에 전달하기
    	req.setAttribute("result", result);
    	
    	//View지정하고 응답하기 - forward
    	req.getRequestDispatcher("/WEB-INF/views/member/result.jsp").forward(req, resp);
		
	}
	
}














