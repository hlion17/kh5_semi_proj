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
		System.out.println("[TEST] RecipeLikeController( /recipe/like ) [GET] 호출");
		
		//전달파라미터 얻기 - boardno
		Recipe boardno = boardService.getBoardno(req);
		
		//상세보기 결과 조회
		Recipe viewBoard = boardService.view(boardno); 
		
		//recipe테이블에 like값 1증가, 해당세션에 중복추천 방지용 플래그생성
		String like = "like_" + boardno.getBoardno();
		Object lf = req.getSession().getAttribute(like);;
		System.out.println("lf : " + lf);
		
		
		if ( lf == null ) { //처음 추천시 해당 세션속성이 아직 없어 null 값임 
			req.getSession().setAttribute(like, false);
			lf = req.getSession().getAttribute(like);
			System.out.println("false lf : " + lf);
			
			boardService.addLike(boardno, req);
		} else {
			//추천한적이 있다면 like_해당글번호 키값이 true로 존재
			req.getSession().setAttribute(like, true);
			lf = req.getSession().getAttribute(like);
			System.out.println("true lf : " + lf);
		}
		
		//재조회 조회수증가방지
		boardService.downHit(boardno.getBoardno());
//		
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
//		System.out.println("[TEST] RecipeLikeController - view.jsp로 포워드");
//		System.out.println();
//		req.getRequestDispatcher("/WEB-INF/views/community/board/view.jsp").forward(req, resp);
		
		System.out.println("[TEST] RecipeLikeController - /recipe/content로 포워드");
		System.out.println();
		req.getRequestDispatcher("/recipe/content").forward(req, resp);
	}
}

