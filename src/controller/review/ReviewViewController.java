package controller.review;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import service.face.ReviewService;
import service.impl.ReviewServiceImpl;


@WebServlet("/review")
public class ReviewViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ReviewService reviewService = new ReviewServiceImpl();

}