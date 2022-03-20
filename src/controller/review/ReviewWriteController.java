package controller.review;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Review;
import service.face.ReviewService;
import service.impl.ReviewServiceImpl;


@WebServlet("/review/write")
public class ReviewWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ReviewService reviewService = new ReviewServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		//전달파라미터 얻기 - pro_no
//		Review pro_no = reviewService.getreview_no(req);
//		
//		//상세보기 결과 조회
//		Review viewReview = reviewService.view(pro_no);
//		
//		//조회결과 MODEL값 전달
//		req.setAttribute("viewReview", viewReview);
		
//		System.out.println("ReviewWriteController getPro_no : " + viewReview.getPro_no() );
		
		//VIEW 지정
		req.getRequestDispatcher("/WEB-INF/views/store/reviewWrite.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		System.out.println( "/write content : " + req.getParameter("content") );
//		System.out.println( "/write pro_no : " + req.getParameter("pro_no") );
		
		//작성글 삽입
		reviewService.write(req);
		
		//목록으로 리다이렉션
		resp.sendRedirect("/review/list");
		
		
	}
	
}
