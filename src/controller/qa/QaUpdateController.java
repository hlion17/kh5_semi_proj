package controller.qa;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Qa;
import dto.QaFile;
import service.face.QaService;
import service.impl.QaServiceImpl;

/**
 * Servlet implementation class QaUpdateController
 */
@WebServlet("/qa/update")
public class QaUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private QaService boardService = new QaServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println(req.getParameter("boardno"));

		// 전달파라미터 얻기 - boardno
		Qa boardno = boardService.getBoardno(req);

		// 상세보기 결과 조회
		Qa updateBoard = boardService.view(boardno);

		// 조회결과 MODEL값 전달
		req.setAttribute("updateBoard", updateBoard);

		// 닉네임 전달
		req.setAttribute("writerNick", boardService.getNick(updateBoard));

		// 첨부파일 정보 조회
		QaFile boardFile = boardService.viewFile(updateBoard);

		// 첨부파일 정보 MODEL값 전달
		req.setAttribute("boardFile", boardFile);

		// VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/qa/update.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		boardService.update(req);

		resp.sendRedirect("/qa/list");

	}
}
