package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Member;
import dto.ProfileFile;
import service.face.MemberService;
import service.impl.MemberServiceImpl;

/**
 * Servlet implementation class ProfileViewController
 */
@WebServlet("/member/profileView")
public class ProfileViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	MemberService memberService = new MemberServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("/member/profileView [GET]");

		// 전달파라미터 얻기 - boardno
		Member memberno = memberService.getMembernoBySession(req);

		System.out.println("ProfileViewController - memberno" + memberno);

		// 프로필 정보 조회
		ProfileFile viewProfile = memberService.view(memberno);

		System.out.println("프로필 넘버" + viewProfile);

		// 조회결과 MODEL값 전달
		req.setAttribute("viewProfile", viewProfile);


		// VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/member/profileView.jsp").forward(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		// 프로필 삽입
//		memberService.uploadProfil(req);
	}
}
