package controller.notice;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Member;
import dto.Notice;
import service.face.MemberService;
import service.face.NoticeService;
import service.impl.MemberServiceImpl;
import service.impl.NoticeServiceImpl;
import util.Paging;


@WebServlet("/notice/list")
public class NoticeListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	private NoticeService boardService = new NoticeServiceImpl();
	private MemberService memberService = new MemberServiceImpl();


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//전달파라미터에서 현재 페이징 객체 알아내기
		Paging paging = boardService.getPaging(req);
		System.out.println("BoardController doGet() - " + paging);
		
		//게시글 전체 조회 - BoardService이용
//		List<Notice> boardList = boardService.getList();
		
		//게시글 페이징 목록 조회 - BoardService이용
		List<Notice> boardList = boardService.getList( paging );
		
		//조회결과 MODEL값 전달 - req.setAttribute
		req.setAttribute("boardList", boardList);
		
		//페이징 MODEL값 전달
		req.setAttribute("paging", paging);
		
		//로그인 정보 넘기기
		Member member = memberService.getMemberInfoBySession(req);

		System.out.println(member);

		Member result = memberService.info(member);

		req.setAttribute("result", result);
		
		//VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/notice/list.jsp").forward(req, resp);
		
	}
}
