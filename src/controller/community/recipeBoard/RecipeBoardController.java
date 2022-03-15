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
import util.Paging;

@WebServlet("/recipe/board")
public class RecipeBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private RecipeService recipeService = new RecipeServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("[TEST] /rank/board - RecipeBoardController [GET] 호출");
		
		//전달파라미터에서 현재 페이징 객체 알아내기
		Paging paging = recipeService.getPaging(req);
		System.out.println("BoardController doGet() - " + paging);
		
		//전달파라미터 얻기 - recipeIdx
		Recipe recipeIdx = recipeService.getRecipeIdx(req);

		//상세보기 결과 조회
		Recipe recipeContent = recipeService.recipeContent(recipeIdx);
		
		//조회결과 MODEL값 전달
		req.setAttribute("recipeContent", recipeContent);
		
		
		//닉네임 전달
		req.setAttribute("writerNick", recipeService.getNick(recipeContent));

		
		//첨부파일 정보 조회
		RecipeFile recipeFile = recipeService.viewFile(recipeContent);
		
		//첨부파일 정보 MODEL값 전달
		req.setAttribute("recipeFile", recipeFile);
		
		//JSP를 VIEW로 지정, View로 응답
		System.out.println("[TEST] recipeBoard.jsp.jsp로 포워드");
		req.getRequestDispatcher("/WEB-INF/views/community/recipeBoard/recipeBoard.jsp").forward(req, resp);
		
	}

}
