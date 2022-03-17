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
import service.impl.ReviewServiceImpl;


@WebServlet("/review/review")
public class ReviewViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ReviewService reviewService = new ReviewServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			System.out.println("/review/view [GET]");
			
			//전달파라미터 얻기 - pro_no
			Review pro_no = reviewService.getreview_no(req);
			//상세보기 결과 조회
			Review viewReview = reviewService.view(pro_no); 
			//조회결과 MODEL값 전달
			req.setAttribute("viewReview", viewReview);
			//닉네임 전달
			req.setAttribute("writerNick", reviewService.getNick(viewReview));
			//첨부파일 정보 조회
			ReviewFile reviewFile = reviewService.viewFile(viewReview);
			//첨부파일 정보 MODEL값 전달
			req.setAttribute("reviewFile", reviewFile);
			//VIEW 지정 및 응답 - forward
			req.getRequestDispatcher("/WEB-INF/views/store/review.jsp").forward(req, resp);
		
	}

}
