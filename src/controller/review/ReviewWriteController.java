package controller.review;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.ReviewService;
import service.impl.ReviewServiceImpl;


@WebServlet("/review/write")
public class ReviewWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ReviewService reviewService = new ReviewServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//VIEW 지정
		req.getRequestDispatcher("/WEB-INF/views/store/reviewWrite.jsp").forward(req, resp);
	
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Enumeration<String> names = req.getParameterNames();
		if (names.hasMoreElements()) {
			String element = names.nextElement();
			System.out.println(element);
		}

		//작성글 삽입
		reviewService.write(req);
		
		//목록으로 리다이렉션
		resp.sendRedirect("/review/list");
		
		
	}
	
}
