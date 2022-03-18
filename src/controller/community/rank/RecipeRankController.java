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

@WebServlet("/rank/recipe")
public class RecipeRankController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private RecipeService boardService = new RecipeServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("[TEST] /rank/recipe - RecipeRankController [GET] 호출");
		
		//게시글 랭킹 전체 조회 - RecipeService이용
		List<Recipe> boardList = boardService.getListRank();
		
		//조회결과 MODEL값 전달 - req.setAttribute
		req.setAttribute("boardList", boardList);
		
//		//전달파라미터 얻기 - boardno
//		Recipe boardno = boardService.getBoardno(req);
//		
//		//상세보기 결과 조회
//		Recipe viewBoard = boardService.view(boardno); 
//		
//		//조회결과 MODEL값 전달
//		req.setAttribute("viewBoard", viewBoard);
//		
//		//닉네임 전달
//		req.setAttribute("writerNick", boardService.getNick(viewBoard));
//
//		
//		//첨부파일 정보 조회
//		RecipeFile boardFile = boardService.viewFile(viewBoard);
//		
//		//첨부파일 정보 MODEL값 전달
//		req.setAttribute("boardFile", boardFile);
		
		
		//JSP를 VIEW로 지정, View로 응답
		System.out.println("[TEST] recipeRank.jsp로 포워드");
		req.getRequestDispatcher("/WEB-INF/views/community/rank/recipeRank.jsp").forward(req, resp);
	}
	
}
