package controller.community.recipeBoard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Recipe;
import service.face.RecipeService;
import service.impl.RecipeServiceImpl;

@WebServlet("/recipe/delete")
public class RecipeDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private RecipeService boardService = new RecipeServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("[TEST] RecipeDeleteController( /recipe/delete ) [GET] 호출");
		
		//전달파라미터 얻기 - boardno
		Recipe board = boardService.getBoardno(req);
		
		//삭제된 결과를 목록에 반영
		boardService.delete(board);
		
		//JSP를 VIEW로 지정, View로 응답
		System.out.println("[TEST] RecipeDeleteController - /recipe/board로 리다이렉트");
		System.out.println();
		resp.sendRedirect("/recipe/board");
	}

}
