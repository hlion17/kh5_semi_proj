package controller.community.recipeBoard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.RecipeService;
import service.impl.RecipeServiceImpl;

@WebServlet("/recipe/insert")
public class RecipeInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private RecipeService boardService = new RecipeServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("[TEST] RecipeInsertController( /recipe/insert ) [GET] 호출");
		
		//로그인 되어있지 않으면 리다이렉트 
		if( req.getSession().getAttribute("login") == null ) {
			resp.sendRedirect("/");
			
			return;
		}
		
		//JSP를 VIEW로 지정, View로 응답
		System.out.println("[TEST] RecipeInsertController - write.jsp로 포워드");
		System.out.println();
		req.getRequestDispatcher("/WEB-INF/views/community/board/write.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("[TEST] RecipeInsertController( /recipe/insert ) [POST] 호출");
		
		//작성글 삽입
		boardService.write(req);
		
		//목록으로 리다이렉션
		System.out.println("[TEST] RecipeInsertController - list.jsp로 리다이렉션");
		System.out.println();
		resp.sendRedirect("/recipe/board");
		
	}
}
