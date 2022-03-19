package controller.store;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Recipe;
import service.face.StoreService;
import service.impl.StoreServiceImpl;
import util.Paging;

@WebServlet("/store")
public class StoreMainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
	//VIEW 지정 및 응답 - forward
	req.getRequestDispatcher("/WEB-INF/views/store/storeList.jsp").forward(req, resp);
	
	}
	
}
