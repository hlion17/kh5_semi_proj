package controller.qa;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Qa;
import service.face.QaService;
import service.impl.QaServiceImpl;

/**
 * Servlet implementation class QaDeleteController
 */
@WebServlet("/qa/delete")
public class QaDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private QaService boardService = new QaServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Qa board = boardService.getBoardno(req);

		boardService.delete(board);

		// 목록으로 리다이렉트
		resp.sendRedirect("/qa/list");

	}
}
