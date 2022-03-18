package controller.dictionary;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.IngrService;
import service.impl.IngrServiceImpl;

@WebServlet("/ingr/detail")
public class IngrDetailController extends HttpServlet {
	
	private IngrService ingrService = new IngrServiceImpl();
	private static Logger logger = Logger.getLogger(IngrDetailController.class.getName());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 재료항목의 상세 내용을 조회하는 서비스
		ingrService.getIngrDetail(req);
		
		// View - 재료상세페이지
		req.getRequestDispatcher("/WEB-INF/views/dictionary/ingr/ingr_detail.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

}
