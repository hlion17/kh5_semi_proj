package controller.ref;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.RefService;
import service.impl.RefServiceImpl;

@WebServlet("/ref/choose")
public class RefChooseController extends HttpServlet {
	
	private RefService refService = new RefServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 로그인한 회원의 냉장고 선택
		refService.chooseRef(req);
		
		req.getRequestDispatcher("/WEB-INF/views/ref/ref_choose.jsp").forward(req, resp);
	}

}
