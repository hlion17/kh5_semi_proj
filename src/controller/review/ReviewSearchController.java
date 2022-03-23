package controller.review;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.ReviewService;
import service.impl.ReviewServiceImpl;


@WebServlet("/review/search")
public class ReviewSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ReviewService reviewService = new ReviewServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/store/reviewSearch.jsp").forward(req, resp);
	}
	
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		
//		ReviewService.searchReview(req);
//		
//		req.getRequestDispatcher("/WEB-INF/views/store/reviewSearch.jsp").forward(req, resp);
//	}
	
	
}
