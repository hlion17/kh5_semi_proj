package controller.community.rank;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Member;
import dto.Recipe;
import service.face.MemberService;
import service.impl.MemberServiceImpl;

@WebServlet("/rank/member")
public class RankMemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberService boardService = new MemberServiceImpl(); 
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("[TEST] /rank/member - RankMemberController [GET] 호출");
		
		//게시글 랭킹 전체 조회 - RecipeService이용
//		List<Member> boardList = boardService.getListRank();
		
		//조회결과 MODEL값 전달 - req.setAttribute
//		req.setAttribute("boardList", boardList);
		
		//JSP를 VIEW로 지정, View로 응답
		System.out.println("[TEST] rankMember.jsp로 포워드");
		req.getRequestDispatcher("/WEB-INF/views/community/rank/rankMember.jsp").forward(req, resp);
	}

}
