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


@WebServlet("/member/idfind")
public class IdFindController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberService memberService = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("/WEB-INF/views/member/idfind.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		//ajax로 값을 받기 때문에 UTF-8로 인코딩해준다
		
		Member member = memberService.getIdFindMember(req);
		
		PrintWriter out = resp.getWriter();
		
		String memberid = memberService.checkEmailPhone(member);
		
		System.out.println(memberid);
		
		int result = 0;
		// 성공 여부 확인 : 개발자용
		if(memberid == null) {
			System.out.println("일치하는 정보가 없습니다.");
			out.write(memberid + ""); //jsp에 memberid을 +""해 String타입으로 전달
			
			
		} else if (memberid != null) {
			System.out.println("일치하는 정보가 있습니다.");
			
			out.write(memberid + "");
		}
		
	}
	

}
