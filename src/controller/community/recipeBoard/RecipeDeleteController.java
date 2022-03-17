package controller.community.recipeBoard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/recipe/delete")
public class RecipeDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("[TEST] RecipeDeleteController( /recipe/delete ) [GET] 호출");
		
		
		
		//JSP를 VIEW로 지정, View로 응답
		System.out.println("[TEST] RecipeDeleteController - list.jsp로 포워드");
		System.out.println();
		req.getRequestDispatcher("/WEB-INF/views/community/board/list.jsp").forward(req, resp);
	}

}
