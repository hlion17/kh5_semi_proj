package controller.notice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Notice;
import dto.NoticeFile;
import service.face.NoticeService;
import service.impl.NoticeServiceImpl;


@WebServlet("/notice/update")
public class NoticeUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private NoticeService boardService = new NoticeServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println(req.getParameter("boardno"));
		
		//전달파라미터 얻기 - boardno
		Notice boardno = boardService.getBoardno(req);

		
		//상세보기 결과 조회
		Notice updateBoard = boardService.view(boardno); 
		
		//조회결과 MODEL값 전달
		req.setAttribute("updateBoard", updateBoard);
		
		
		//닉네임 전달
		req.setAttribute("writerNick", boardService.getNick(updateBoard));

		
		//첨부파일 정보 조회
		NoticeFile boardFile = boardService.viewFile(updateBoard);
		
		//첨부파일 정보 MODEL값 전달
		req.setAttribute("boardFile", boardFile);
		
		
		//VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/notice/update.jsp").forward(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		boardService.update(req);
		
		resp.sendRedirect("/notice/list");
		
	}
}
