package controller.review;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import service.face.ReviewService;
import service.face.StoreService;
import service.impl.ReviewServiceImpl;
import service.impl.StoreServiceImpl;


@WebServlet("/review/update")
public class ReviewUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ReviewService reviewService = new ReviewServiceImpl();
}
