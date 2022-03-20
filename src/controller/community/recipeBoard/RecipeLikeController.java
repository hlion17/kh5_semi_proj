package controller.community.recipeBoard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Recipe;
import dto.RecipeFile;
import service.face.RecipeService;
import service.impl.RecipeServiceImpl;

@WebServlet("/recipe/like")
public class RecipeLikeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private RecipeService boardService = new RecipeServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("[TEST] RecipeLikeController( /recipe/content ) [GET] 호출");
		
//		System.out.println("[TEST]/like-GET - like_msg_flag : " + req.getAttribute("like_msg_flag"));
		
		//전달파라미터 얻기 - boardno
		Recipe boardno = boardService.getBoardno(req);
		
		//해당 로그인 유저가 지금 세션에서 이글을 추천한적이 없으면 추천+1
		String likeFlag = "like_" + boardno.getBoardno(); //추천한적이 있다면 like_해당글번호 키값이 true로 존재
		boolean flag = (boolean)req.getSession().getAttribute(likeFlag);
		if( !flag ) {
			//recipe테이블에 like값 1증가, 해당세션에 중복추천 방지용 플래그생성
			System.out.println("추천한다아아아아아아아아아아아아");
			boardService.addLike(boardno, req);
			
//			req.setAttribute("like_msg_flag", false);
//			System.out.println("[TEST]/like-GET(추천했을때) - like_msg_flag : " + req.getAttribute("like_msg_flag"));
		}

		//상세보기 결과 조회
		Recipe viewBoard = boardService.view(boardno); 
		
		//조회결과 MODEL값 전달
		req.setAttribute("viewBoard", viewBoard);
		
		//닉네임 전달
		req.setAttribute("writerNick", boardService.getNick(viewBoard));
		
		//첨부파일 정보 조회
		RecipeFile boardFile = boardService.viewFile(viewBoard);
		
		//첨부파일 정보 MODEL값 전달
		req.setAttribute("boardFile", boardFile);
		
		//JSP를 VIEW로 지정, View로 응답
		System.out.println("[TEST] RecipeLikeController - view.jsp로 포워드");
		System.out.println();
		req.getRequestDispatcher("/WEB-INF/views/community/board/view.jsp").forward(req, resp);
	}
}

