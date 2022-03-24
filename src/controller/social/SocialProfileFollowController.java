package controller.social;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Recipe;
import dto.RecipeFile;
import dto.SocialMember;
import service.face.RecipeService;
import service.face.SocialService;
import service.impl.RecipeServiceImpl;
import service.impl.SocialServiceImpl;

@WebServlet("/social/profile/follow")
public class SocialProfileFollowController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private SocialService boardService = new SocialServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("[TEST] SocialProfileFollowController( /social/profile/follow ) [GET] 호출");
		
		//알람플래그 세팅
		HttpSession s = req.getSession();
		s.setAttribute("follow_myself", "자신은 팔로우 할 수 없습니다!");
		s.setAttribute("follow_myself_flag", false);
		s.setAttribute("follow_already", "이미 팔로우 하였습니다!");
		s.setAttribute("follow_already_flag", false);
		s.setAttribute("follow_success", "팔로우 하셨습니다 :)");
		s.setAttribute("follow_success_flag", false);

		//전달파라미터 얻기 - 글작성자 memberno
		SocialMember boardno = boardService.getMemberno(req);
		SocialMember viewBoard = boardService.view(boardno);
		String param = req.getParameter("memberno");
		
		//전달할 파라미터 선언
		int followee = viewBoard.getMemberno();
		int follower = Integer.parseInt(s.getAttribute("memberno").toString());
		System.out.println("[TEST]팔로위 : " + followee);
		System.out.println("[TEST]팔로어 : " + follower);
		
		//팔로우기능
		if( followee != follower ) { //사전 검사1 - 자기자신을 팔로우하는 경우 followee == follower
			
			if( boardService.checkFollowPK(followee, follower) > 0 ) { //사전 검사2 - 이미 팔로우한 사람을 팔로우 못하게(무결성 위반 방지)
				boardService.setFollow(followee, follower, req); //글작성자를 이용자가 팔로우
				System.out.println("[TEST]팔로우 성공한 경우");
			} else {
				System.out.println("[TEST]이미 팔로우했거나 알수없는 경우");
				s.setAttribute("follow_already_flag", true); //else라서 정확한 구분이 안되지만 직접 타고들어가서 매겨도 애매한 경우가 너무많음, 추후고민
			}
			
		} else if ( followee == follower ) {
			System.out.println("[TEST]자기자신을 팔로우");
			s.setAttribute("follow_myself_flag", true);
		} else {
			System.out.println("[TEST]알수없는 경우");
		}

		
		
		System.out.println("[TEST] SocialProfileFollowController - /rank/member로 리다이렉션");
		System.out.println();
		resp.sendRedirect("/rank/member");
		
//		System.out.println("[TEST] SocialProfileFollowController - /social/profile로 포워드");
//		System.out.println();
//		req.getRequestDispatcher("/social/member?"+"memberno="+param).forward(req, resp);
		
	}
}


















