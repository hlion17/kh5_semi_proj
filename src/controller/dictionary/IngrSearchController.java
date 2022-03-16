package controller.dictionary;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.IngrService;
import service.impl.IngrServiceImpl;

@WebServlet("/ingr/search")
public class IngrSearchController extends HttpServlet {

	private IngrService ingrService = new IngrServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 재료명으로 재료검색하는 서비스
		ingrService.findByIngrName(req);
		
		// View - 조회된 재료목록을 보여주는 페이지
		req.getRequestDispatcher("/WEB-INF/views/dictionary/ingrList.jsp").forward(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException ,IOException {
		
	};
}
