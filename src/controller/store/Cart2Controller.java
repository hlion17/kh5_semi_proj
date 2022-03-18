package controller.store;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cartt")
public class Cart2Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/store/carttest.jsp").forward(req, resp);
	
	}
	

}
