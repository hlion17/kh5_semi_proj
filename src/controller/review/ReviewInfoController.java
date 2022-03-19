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


@WebServlet("/review/info")
public class ReviewInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ReviewService reviewService = new ReviewServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			System.out.println("/review/info [GET]");
			
			//전달파라미터 얻기 - pro_no
			Review pro_no = reviewService.getreview_no(req);
			System.out.println("pro_no : " +pro_no);
			//상세보기 결과 조회
			Review viewReview = reviewService.view(pro_no); 
			//조회결과 MODEL값 전달
			req.setAttribute("viewReview", viewReview);
			
			//VIEW 지정 및 응답 - forward
			req.getRequestDispatcher("/WEB-INF/views/store/reviewInfo.jsp").forward(req, resp);
		
	}

}
