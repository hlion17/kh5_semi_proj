package controller.social;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Recipe;
import dto.RecipeFile;
import dto.SocialMember;
import service.face.RecipeService;
import service.face.SocialService;
import service.impl.RecipeServiceImpl;
import service.impl.SocialServiceImpl;

@WebServlet("/social/profile/update")
public class SocialProfileUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private SocialService boardService = new SocialServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("[TEST] SocialProfileUpdateController( /social/profile/update ) [GET] 호출");
		
		//전달파라미터 얻기 - boardno
		SocialMember boardno = boardService.getMemberno(req);

		//상세보기 결과 조회
		SocialMember updateBoard = boardService.view(boardno); 
		
		//조회결과 MODEL값 전달
		req.setAttribute("updateBoard", updateBoard);

		//이미 SocialMember 객체에서 가지고 있도록 설계함
//		//첨부파일 정보 조회
//		RecipeFile boardFile = boardService.viewFile(updateBoard);
//		
//		//첨부파일 정보 MODEL값 전달
//		req.setAttribute("boardFile", boardFile);
		
		//JSP를 VIEW로 지정, View로 응답
		System.out.println("[TEST] SocialProfileUpdateController - socialProfileUpdate.jsp.jsp로 포워드");
		System.out.println();
		req.getRequestDispatcher("/WEB-INF/views/social/socialProfileUpdate.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("[TEST] SocialProfileUpdateController( /social/profile/update ) [POST] 호출");
		
		//로그인 되어있지 않으면 리다이렉트 
		if( req.getSession().getAttribute("login") == null ) {
			resp.sendRedirect("/main.jsp");
			
			return;
		}
		
		//수정된 결과를 목록에 반영
		boardService.update(req);
		
		System.out.println("[TEST] SocialProfileUpdateController - socialProfileUpdate.jsp.jsp로 포워드");
		System.out.println();
		req.getRequestDispatcher("/WEB-INF/views/social/socialProfileUpdate.jsp").forward(req, resp);
	}

}
