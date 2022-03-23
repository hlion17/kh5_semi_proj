package controller.social;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Recipe;
import dto.SocialMember;
import service.face.RecipeService;
import service.face.SocialService;
import service.impl.RecipeServiceImpl;
import service.impl.SocialServiceImpl;
import util.Paging;

@WebServlet("/social/recipe")
public class SocialRecipeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private RecipeService boardService = new RecipeServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("[TEST] SocialRecipeController( /social/profile ) [GET] 호출");
		

		//전달파라미터에서 현재 페이징 객체 알아내기
		Paging paging = boardService.getPaging(req);
		System.out.println("SocialRecipeController doGet() - " + paging);
		
		//게시글 페이징 목록 조회 - BoardService이용
		List<Recipe> boardList = boardService.getListMyRecipe(paging, req);
		
		//조회결과 MODEL값 전달 - req.setAttribute
		req.setAttribute("boardList", boardList);
		
		//페이징 MODEL값 전달
		req.setAttribute("paging", paging);

		System.out.println("[TEST] SocialRecipeController - socialRecipe.jsp로 포워드");
		System.out.println();
		req.getRequestDispatcher("/WEB-INF/views/social/socialRecipe.jsp").forward(req, resp);
	}
}
