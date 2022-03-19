package controller.dictionary;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.OpenRecipeService;
import service.impl.OpenRecipeServiceImpl;

@WebServlet("/openRecipe/search")
public class OpenRecipeSearchController extends HttpServlet {
	
	private OpenRecipeService openRecipeService = new OpenRecipeServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/dictionary/openRecipe/openRecipeSearch.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 오픈레시피 검색 
		openRecipeService.searchOpenRecipe(req);
				
		// View - 오픈레시피 검색 결과 페이지
		req.getRequestDispatcher("/WEB-INF/views/dictionary/openRecipe/openRecipe_search_result.jsp").forward(req, resp);
	}
}
