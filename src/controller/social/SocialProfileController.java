package controller.social;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Member;
import dto.ProfileFile;
import dto.Recipe;
import dto.RecipeFile;
import dto.SocialMember;
import service.face.MemberService;
import service.face.RecipeService;
import service.face.SocialService;
import service.impl.MemberServiceImpl;
import service.impl.RecipeServiceImpl;
import service.impl.SocialServiceImpl;

@WebServlet("/social/profile")
public class SocialProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private SocialService socialService = new SocialServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("[TEST] SocialProfileController( /recipe/content ) [GET] 호출");
		
		//전달파라미터 얻기 - boardno
		SocialMember memberno = socialService.getMemberno(req);
		
		//상세보기 결과 조회
		SocialMember viewBoard = socialService.view(memberno); 
		
		//조회결과 MODEL값 전달
		req.setAttribute("viewBoard", viewBoard);
				
		//첨부파일 정보 조회
		ProfileFile profileFile = socialService.viewFile(viewBoard);
		
		//첨부파일 정보 MODEL값 전달
		req.setAttribute("boardFile", profileFile);
		
		//JSP를 VIEW로 지정, View로 응답
//		System.out.println("[TEST] RecipeContentController - /recipe/follow.jsp로 포워드");
//		System.out.println();
//		req.getRequestDispatcher("/recipe/follow").forward(req, resp);
		
		System.out.println("[TEST] SocialProfileController - socialProfile.jsp로 포워드");
		System.out.println();
		req.getRequestDispatcher("/WEB-INF/views/social/socialProfile.jsp").forward(req, resp);
	}
}
