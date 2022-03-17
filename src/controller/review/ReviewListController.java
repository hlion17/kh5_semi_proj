package controller.review;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Review;
import service.face.ReviewService;
import service.impl.ReviewServiceImpl;
import util.Paging;

@WebServlet("/review/list")
public class ReviewListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
		
	private ReviewService reviewService = new ReviewServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//전달파라미터에서 현재 페이징 객체 알아내기
		Paging paging = reviewService.getPaging(req);
		System.out.println("ReviewController doGet() - " + paging);
		
		//게시글 전체 조회 
//		List<Review> reviewList = boardService.getList();
		
		//게시글 페이징 목록 조회 - 
		List<Review> reviewList = reviewService.getList( paging );
		
		//조회결과 MODEL값 전달 - req.setAttribute
		req.setAttribute("reviewService", reviewService);
		
		//페이징 MODEL값 전달
		req.setAttribute("paging", paging);
		
		//VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/store/list.jsp").forward(req, resp);
		
	}
	
}
