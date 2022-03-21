package controller.qa;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.QaFile;
import dto.Member;
import dto.Qa;
import service.face.MemberService;
import service.face.QaService;
import service.impl.MemberServiceImpl;
import service.impl.QaServiceImpl;


/**
 * Servlet implementation class QaViewController
 */
@WebServlet("/qa/view")
public class QaViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private QaService boardService = new QaServiceImpl();
	private MemberService memberService = new MemberServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/notice/view [GET]");

		// 전달파라미터 얻기 - boardno
		Qa boardno = boardService.getBoardno(req);

		System.out.println(boardno);

		// 상세보기 결과 조회
		Qa viewBoard = boardService.view(boardno);

		System.out.println("상세보기 결과 조회" + viewBoard);

		// 조회결과 MODEL값 전달
		req.setAttribute("viewBoard", viewBoard);

		// 닉네임 전달
		req.setAttribute("writerNick", boardService.getNick(viewBoard));

		// 첨부파일 정보 조회
		QaFile boardFile = boardService.viewFile(viewBoard);

		// 첨부파일 정보 MODEL값 전달
		req.setAttribute("boardFile", boardFile);
		
		// 로그인 정보 넘기기
		Member member = memberService.getMemberInfoBySession(req);

		System.out.println(member);

		Member result = memberService.info(member);
		
		System.out.println("result" + result);

		req.setAttribute("result", result);

		// VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/qa/view.jsp").forward(req, resp);

	}
}
