package controller.review;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Review;
import dto.ReviewFile;
import service.face.ReviewService;
import service.face.StoreService;
import service.impl.ReviewServiceImpl;
import service.impl.StoreServiceImpl;


@WebServlet("/review/update")
public class ReviewUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ReviewService reviewService = new ReviewServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//전달파라미터 얻기 - review_no
		Review review_no = reviewService.getreview_no(req);
		//상세보기 결과 조회
		Review updateReview = reviewService.view(review_no); 
		//조회결과 MODEL값 전달
		req.setAttribute("updateReview", updateReview);
		//닉네임 전달
		req.setAttribute("writerNick", reviewService.getNick(updateReview));
		//첨부파일 정보 조회
		ReviewFile reviewFile = reviewService.viewFile(updateReview);
		//첨부파일 정보 MODEL값 전달
		req.setAttribute("reviewFile", reviewFile);
		//VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/store/reviewUpdate.jsp").forward(req, resp);
	}
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		reviewService.update(req);
		resp.sendRedirect("/board/list");
		
	}
}
