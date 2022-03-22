package controller.community.rank;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Member;
import dto.RankMember;
import dto.Recipe;
import service.face.MemberService;
import service.impl.MemberServiceImpl;
import util.Paging;

@WebServlet("/rank/member")
public class RankMemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberService boardService = new MemberServiceImpl(); 
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("[TEST] /rank/member - RankMemberController [GET] 호출");
		
		//전달파라미터에서 현재 페이징 객체 알아내기
		Paging paging = boardService.getPaging(req);
		
		//게시글 페이징 목록 조회 - BoardService이용
		List<RankMember> boardList = boardService.getListRank( paging );
		
		//조회결과 MODEL값 전달 - req.setAttribute
		req.setAttribute("boardList", boardList);
		
		//페이징 MODEL값 전달
		req.setAttribute("paging", paging);
		
		//JSP를 VIEW로 지정, View로 응답
		System.out.println("[TEST] rankMember.jsp로 포워드");
		req.getRequestDispatcher("/WEB-INF/views/community/rank/rankMember.jsp").forward(req, resp);
	}

}
