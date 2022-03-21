package controller.community.recipeBoard;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Recipe;
import dto.RecipeFile;
import service.face.RecipeService;
import service.impl.RecipeServiceImpl;
import util.FollowEqualException;

@WebServlet("/recipe/follow")
public class RecipeFollowController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private RecipeService boardService = new RecipeServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("[TEST] RecipeFollowController( /recipe/content ) [GET] 호출");
		
		//팔로우 불가 메시지 세팅
		req.setAttribute("follow_error_msg", false);
		
		//전달파라미터 얻기 - 글작성자 memberno
		Recipe boardno = boardService.getBoardno(req);
		Recipe viewBoard = boardService.view(boardno);
		
		//전달할 파라미터 선언
		int followee = viewBoard.getUserid();
		int follower = (int)req.getSession().getAttribute("memberno");
		System.out.println("[TEST]팔로위 : " + followee);
		System.out.println("[TEST]팔로어 : " + follower);

		try {
			//글작성자를 이용자가 팔로우
			boardService.setFollow(followee, follower);
			
//		} catch (FollowEqualException e) { //자기자신을 팔로우하는 경우 followee == follower <<- 이럴때 이 예외처리에 들어와야하는데 어떻게 지정하는지 알아보기
//			req.setAttribute("follow_error_msg", true); //jsp에서 알람뜨게하기위한 키값
//			System.out.println("[TEST]follow_error_msg(true) : " + req.getAttribute("follow_error_msg"));
		} catch (SQLIntegrityConstraintViolationException e) { //이미 팔로우한 사람을 팔로우하려할때
			
		}
		
		//재조회 조회수증가방지
		boardService.downHit(boardno.getBoardno());
		boardService.downHit(boardno.getBoardno());
		
		//JSP를 VIEW로 지정, View로 응답
		System.out.println("[TEST] RecipeLikeController - /recipe/content로 포워드");
		System.out.println();
		req.getRequestDispatcher("/recipe/content").forward(req, resp);
	}
}


















