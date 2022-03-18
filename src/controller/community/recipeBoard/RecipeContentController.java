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
				
//		System.out.println(req.getAttribute("like"));
		
		//전달파라미터 얻기 - boardno
		Recipe boardno = boardService.getBoardno(req);

		//상세보기 결과 조회
		Recipe viewBoard = boardService.view(boardno); 
		
		//조회결과 MODEL값 전달
		req.setAttribute("viewBoard", viewBoard);
		
//		System.out.println( "viewBoard.getBoardno() : " + viewBoard.getBoardno() );
//		System.out.println( "viewBoard.getLike() : " + viewBoard.getLike() );
		
//		//boardno 계승을 위해
//		req.setAttribute("board_no", viewBoard.getBoardno());
//		System.out.println("setAttribute : " + viewBoard.getBoardno());
		
		//닉네임 전달
		req.setAttribute("writerNick", boardService.getNick(viewBoard));
//		System.out.println(req.getAttribute("writerNick"));
		
		//첨부파일 정보 조회
		RecipeFile boardFile = boardService.viewFile(viewBoard);
		
		//첨부파일 정보 MODEL값 전달
		req.setAttribute("boardFile", boardFile);
		
		//JSP를 VIEW로 지정, View로 응답
		System.out.println("[TEST] RecipeContentController - view.jsp로 포워드");
		System.out.println();
		req.getRequestDispatcher("/WEB-INF/views/community/board/view.jsp").forward(req, resp);
	}
	
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println("[TEST] RecipeContentController( /recipe/content ) [POST] 호출");
//		
//		//로그인 되어있지 않으면 리다이렉트 
//		if( req.getSession().getAttribute("login") == null ) {
//			resp.sendRedirect("/main.jsp");
//			
//			return;
//		}
//		
//		doGet(req, resp);
//		//전달파라미터 얻기 - boardno
//		Recipe boardno = boardService.getBoardno(req);
////		boardno = boardService.getLike(req); 
//		
//		//전달파라미터 판단 - like
//		if ( 1 == req.getParameter("like") );
//		
//		//받은 like값에 + 1 해서 db에 넣기
//				
//		//상세보기 결과 조회
//		Recipe viewBoard = boardService.view(boardno); 
//		
//		//조회결과 MODEL값 전달
//		req.setAttribute("viewBoard", viewBoard);
		
//		//추천수 전달
//		req.setAttribute("like", "5");
//		doGet(req, resp);
		
//		System.out.println( "resp : " + resp );
//		resp.setHeader("like", "5");
//		System.out.println( resp.getHeader("like") );
//		System.out.println( "resp : " + resp );
//	}
}


















