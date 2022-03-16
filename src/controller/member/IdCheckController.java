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


@WebServlet("/IdCheckController")
public class IdCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private MemberService memberService = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		//ajax로 값을 받기 때문에 UTF-8로 인코딩해준다
		
		Member member = memberService.getIdMember(req);
		//join.jsp에서 받아온 key값이 memberid이고,
		//value값은 유저가 실제로 적은 값, String memberid에는 value값이 들어간다
		
		PrintWriter out = resp.getWriter();
		
		
		
		boolean idCheck = memberService.checkIdDup(member);
		
		// 성공 여부 확인 : 개발자용
		if(idCheck == false) {
			System.out.println("이미 존재하는 아이디입니다.");
		} else if (idCheck == true) {
			System.out.println("사용 가능한 아이디입니다.");
		}
		
		out.write(idCheck + ""); // --> ajax 결과값인 result가 됨
								// --> String으로 값을 내보낼 수 있도록 + ""를 해준다
	}

}
