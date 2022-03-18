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


@WebServlet("/notice/view")
public class NoticeViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private NoticeService boardService = new NoticeServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/notice/view [GET]");
		
		//전달파라미터 얻기 - boardno
		Notice boardno = boardService.getBoardno(req);
		
		System.out.println(boardno);
	
		
		//상세보기 결과 조회
		Notice viewBoard = boardService.view(boardno); 
		
		System.out.println("상세보기 결과 조회" + viewBoard);
		
		//조회결과 MODEL값 전달
		req.setAttribute("viewBoard", viewBoard);
		
		
		//닉네임 전달
		req.setAttribute("writerNick", boardService.getNick(viewBoard));

		
		//첨부파일 정보 조회
		NoticeFile boardFile = boardService.viewFile(viewBoard);
		
		
		//첨부파일 정보 MODEL값 전달
		req.setAttribute("boardFile", boardFile);
		
		
		//VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/notice/view.jsp").forward(req, resp);
		
	}
}
