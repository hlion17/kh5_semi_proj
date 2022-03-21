package controller.community.rank;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Recipe;
import dto.RecipeFile;
import service.face.RecipeService;
import service.impl.RecipeServiceImpl;
import util.Paging;

@WebServlet("/rank/recipe")
public class RecipeRankController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private RecipeService boardService = new RecipeServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("[TEST] /rank/recipe - RecipeRankController [GET] 호출");
		
		//전달파라미터에서 현재 페이징 객체 알아내기
		Paging paging = boardService.getPaging(req);
		
		//게시글 페이징 목록 조회 - BoardService이용
		List<Recipe> boardList = boardService.getList( paging );
		
		//조회결과 MODEL값 전달 - req.setAttribute
		req.setAttribute("boardList", boardList);
		
		//페이징 MODEL값 전달
		req.setAttribute("paging", paging);
		
		//JSP를 VIEW로 지정, View로 응답
		System.out.println("[TEST] recipeRank.jsp로 포워드");
		req.getRequestDispatcher("/WEB-INF/views/community/rank/recipeRank.jsp").forward(req, resp);
	}
	
}
