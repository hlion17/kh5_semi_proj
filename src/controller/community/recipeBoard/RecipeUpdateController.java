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

@WebServlet("/recipe/update")
public class RecipeUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private RecipeService boardService = new RecipeServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("[TEST] RecipeUpdateController( /recipe/update ) [GET] 호출");
		
		//로그인 되어있지 않으면 리다이렉트 
		if( req.getSession().getAttribute("login") == null ) {
			resp.sendRedirect("/main.jsp");
			
			return;
		}
		
		//전달파라미터 얻기 - boardno
		Recipe boardno = boardService.getBoardno(req);

		
		//상세보기 결과 조회
		Recipe updateBoard = boardService.view(boardno); 
		
		//조회결과 MODEL값 전달
		req.setAttribute("updateBoard", updateBoard);
		
		
		//닉네임 전달
		req.setAttribute("writerNick", boardService.getNick(updateBoard));

		
		//첨부파일 정보 조회
		RecipeFile boardFile = boardService.viewFile(updateBoard);
		
		//첨부파일 정보 MODEL값 전달
		req.setAttribute("boardFile", boardFile);
		
		//JSP를 VIEW로 지정, View로 응답
		System.out.println("[TEST] RecipeUpdateController - update.jsp로 포워드");
		System.out.println();
		req.getRequestDispatcher("/WEB-INF/views/community/board/update.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("[TEST] RecipeUpdateController( /recipe/update ) [POST] 호출");
		
		//로그인 되어있지 않으면 리다이렉트 
		if( req.getSession().getAttribute("login") == null ) {
			resp.sendRedirect("/main.jsp");
			
			return;
		}
		
		//수정된 결과를 목록에 반영
		boardService.update(req);
		
		//목록으로 리다이렉트
		System.out.println("[TEST] RecipeUpdateController - /recipe/board로 리다이렉트");
		System.out.println();
		resp.sendRedirect("/recipe/board");
	}

}
