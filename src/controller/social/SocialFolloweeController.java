package controller.social;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Follow;
import dto.Recipe;
import dto.SocialMember;
import service.face.RecipeService;
import service.face.SocialService;
import service.impl.RecipeServiceImpl;
import service.impl.SocialServiceImpl;
import util.Paging;

@WebServlet("/social/follow")
public class SocialFolloweeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private SocialService boardService = new SocialServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("[TEST] SocialFolloweeController( /social/followee ) [GET] 호출");
		
		//용어때문에 정신나갈거 같지만 어찌됐든 이 follow url은 내가 팔로우 한 사람들을 보여주는 곳으로 설정
		//2가지 방법이 있는데 DB단에서 세팅해오는 방법, 여러 객체로 컨트롤러-JSP에서 해결하는 방법
		//계속 DB단에서 세팅해오기만 해봤으니 이번엔 여러객체로 나눠볼것
		
		//전달파라미터에서 현재 페이징 객체 알아내기
		Paging paging = boardService.getPaging(req);
		
		//중복없는 모든 유저들의 페이징 목록 담기 - SocialService이용
		List<SocialMember> boardList = boardService.getList( paging );
		
		//조회결과 MODEL값 전달 - req.setAttribute
		req.setAttribute("boardList", boardList);
		
		//로그인 유저가 팔로우한 사람들 목록 조회
		List<Follow> followList = boardService.getListFollow( paging, req );
		
		//조회결과 MODEL값 전달 - req.setAttribute
		req.setAttribute("followList", followList);
		
		//페이징 MODEL값 전달
		req.setAttribute("paging", paging);

		//JSP를 VIEW로 지정, View로 응답
		System.out.println("[TEST] SocialFolloweeController - socialFollowee.jsp로 포워드");
		System.out.println();
		req.getRequestDispatcher("/WEB-INF/views/social/socialFollowee.jsp").forward(req, resp);
		
	}
	
}
