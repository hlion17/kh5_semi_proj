package controller.dictionary;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.OpenRecipeService;
import service.impl.OpenRecipeServiceImpl;

@WebServlet("/openRecipe/detail")
public class OpenRecipeDetailController extends HttpServlet{
	
	private OpenRecipeService openRecipeService = new OpenRecipeServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 오픈레시피의 세부사항을 가져오는 서비스
		openRecipeService.getRecipeDetail(req);
		
		// View - 오픈레시피의 상세내용 페이지
		req.getRequestDispatcher("/WEB-INF/views/dictionary/openRecipe/openRecipe_detail.jsp").forward(req, resp);
	}

}
