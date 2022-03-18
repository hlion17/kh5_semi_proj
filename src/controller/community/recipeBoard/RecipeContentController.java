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

@WebServlet("/recipe/content")
public class RecipeContentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private RecipeService boardService = new RecipeServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("[TEST] RecipeContentController( /recipe/content ) [GET] 호출");
		
		//전달파라미터 얻기 - boardno
		Recipe boardno = boardService.getBoardno(req);

		
		//상세보기 결과 조회
		Recipe viewBoard = boardService.view(boardno); 
		
		//조회결과 MODEL값 전달
		req.setAttribute("viewBoard", viewBoard);
		
		//boardno 계승을 위해
		req.setAttribute("board_no", viewBoard.getBoardno());
		System.out.println("setAttribute : " + viewBoard.getBoardno());
		
		//닉네임 전달
		req.setAttribute("writerNick", boardService.getNick(viewBoard));

		
		//첨부파일 정보 조회
		RecipeFile boardFile = boardService.viewFile(viewBoard);
		
		//첨부파일 정보 MODEL값 전달
		req.setAttribute("boardFile", boardFile);
		
		//JSP를 VIEW로 지정, View로 응답
		System.out.println("[TEST] RecipeContentController - view.jsp로 포워드");
		System.out.println();
		req.getRequestDispatcher("/WEB-INF/views/community/board/view.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("[TEST] RecipeContentController( /recipe/content ) [POST] 호출");
		
		int board_no_check = Integer.parseInt( req.getParameter("board_no") );
		System.out.println("board_no_check : " + board_no_check);
		
		
		
	}
}


















