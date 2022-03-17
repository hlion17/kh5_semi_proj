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




@WebServlet("/member/info")
public class MemberInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MemberService memberService = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 회원 정보 목록 조회 - MemberService이용
		Member MemberInfo = memberService.getInfoList(req);
		
		
		// 조회결과 MODEL값 전달 - req.setAttribute
		req.setAttribute("MemberInfoList", MemberInfo);

		
		// VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/member/info.jsp").forward(req, resp);
	}
	
}
