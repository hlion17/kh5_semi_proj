package controller.social;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Follow;
import dto.SocialMember;
import service.face.SocialService;
import service.impl.SocialServiceImpl;
import util.Paging;

@WebServlet("/social/follower")
public class SocialFollowerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
private SocialService boardService = new SocialServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("[TEST] SocialFollowerController( /social/follower ) [GET] 호출");
		
		//전달파라미터에서 현재 페이징 객체 알아내기
		Paging paging = boardService.getPaging(req);
		
		//중복없는 모든 유저들의 페이징 목록 담기 - SocialService이용
		List<SocialMember> boardList = boardService.getList( paging );
		
		//조회결과 MODEL값 전달 - req.setAttribute
		req.setAttribute("boardList", boardList);
		
		//로그인 유저가 팔로우한 사람들 목록 조회
		List<Follow> followList = boardService.getListFollower( paging, req );
		
		//조회결과 MODEL값 전달 - req.setAttribute
		req.setAttribute("followList", followList);
		
		//페이징 MODEL값 전달
		req.setAttribute("paging", paging);

		//JSP를 VIEW로 지정, View로 응답
		System.out.println("[TEST] SocialFollowerController - socialFollower.jsp로 포워드");
		System.out.println();
		req.getRequestDispatcher("/WEB-INF/views/social/socialFollower.jsp").forward(req, resp);
		
	}
}
