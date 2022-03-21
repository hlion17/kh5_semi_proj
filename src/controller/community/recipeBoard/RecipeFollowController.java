package controller.community.recipeBoard;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Recipe;
import dto.RecipeFile;
import service.face.RecipeService;
import service.impl.RecipeServiceImpl;

@WebServlet("/recipe/follow")
public class RecipeFollowController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private RecipeService boardService = new RecipeServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("[TEST] RecipeFollowController( /recipe/follow ) [GET] 호출");
		
		//알람플래그 세팅
		HttpSession s = req.getSession();
		s.setAttribute("follow_myself", "자신은 팔로우 할 수 없습니다!");
		s.setAttribute("follow_myself_flag", false);
		s.setAttribute("follow_already", "이미 팔로우 하였습니다!");
		s.setAttribute("follow_already_flag", false);
		s.setAttribute("follow_success", "팔로우 하셨습니다 :)");
		s.setAttribute("follow_success_flag", false);
//		s.setAttribute("follow_unknown", "알 수 없는 경로입니다! 관리자에게 문의해주세요 010-0000-0000");
//		s.setAttribute("follow_unknown_flag", false);

		//전달파라미터 얻기 - 글작성자 memberno
		Recipe boardno = boardService.getBoardno(req);
		Recipe viewBoard = boardService.view(boardno);
		
		//전달할 파라미터 선언
		int followee = viewBoard.getUserid();
		int follower = (int)s.getAttribute("memberno");
		System.out.println("[TEST]팔로위 : " + followee);
		System.out.println("[TEST]팔로어 : " + follower);
		
		//팔로우기능
		if( followee != follower ) { //사전 검사1 - 자기자신을 팔로우하는 경우 followee == follower
			
			if( boardService.checkFollowPK(followee, follower) > 0 ) { //사전 검사2 - 이미 팔로우한 사람을 팔로우 못하게(무결성 위반 방지)
				boardService.setFollow(followee, follower, req); //글작성자를 이용자가 팔로우
				System.out.println("[TEST]팔로우 성공한 경우");
			} else {
				System.out.println("[TEST]이미 팔로우했거나 알수없는 경우");
				s.setAttribute("follow_already_flag", true); //else라서 정확한 구분이 안되지만 직접 타고들어가서 매겨도 애매한 경우가 너무많음, 추후고민
			}
			
		} else if ( followee == follower ) {
			System.out.println("[TEST]자기자신을 팔로우");
			s.setAttribute("follow_myself_flag", true);
		} else {
			System.out.println("[TEST]알수없는 경우");
		}
		
		//재조회 조회수증가방지
		boardService.downHit(boardno.getBoardno());
		
//		//조회결과 MODEL값 전달
//		req.setAttribute("viewBoard", viewBoard);
//				
//		//닉네임 전달
//		req.setAttribute("writerNick", boardService.getNick(viewBoard));
//		
//		//첨부파일 정보 조회
//		RecipeFile boardFile = boardService.viewFile(viewBoard);
//		
//		//첨부파일 정보 MODEL값 전달
//		req.setAttribute("boardFile", boardFile);
		
		//JSP를 VIEW로 지정, View로 응답
//		System.out.println("[TEST] RecipeFollowController - /recipe/like 로 포워드");
//		System.out.println();
//		req.getRequestDispatcher("/recipe/like").forward(req, resp);
		
		System.out.println("[TEST] RecipeFollowController - /recipe/content로 포워드");
		System.out.println();
		req.getRequestDispatcher("/recipe/content").forward(req, resp);
		
//		System.out.println("[TEST] RecipeFollowController - follow.jsp로 포워드");
//		System.out.println();
//		req.getRequestDispatcher("/WEB-INF/views/community/board/follow.jsp").forward(req, resp);
	}
}


















