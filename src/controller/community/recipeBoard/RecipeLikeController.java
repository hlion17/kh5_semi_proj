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
		
		//recipe테이블에 like값 1증가, 해당세션에 중복추천 방지용 플래그생성
		//추천한적이 있다면 like_해당글번호 키값이 true로 존재
		String like = "like_" + boardno.getBoardno();
		
		Object lf = req.getSession().getAttribute(like);;
		
		System.out.println("lf : " + lf);
		if ( lf == null ) {
			req.getSession().setAttribute(like, false);
			lf = req.getSession().getAttribute(like);
			System.out.println("false lf : " + lf);
			
			boardService.addLike(boardno, req);
		} else {
			req.getSession().setAttribute(like, true);
			lf = req.getSession().getAttribute(like);
			System.out.println("true lf : " + lf);
		}
		
		//JSP를 VIEW로 지정, View로 응답
		System.out.println("[TEST] RecipeLikeController - /recipe/content로 포워드");
		System.out.println();
		req.getRequestDispatcher("/recipe/content").forward(req, resp);
	}
}

