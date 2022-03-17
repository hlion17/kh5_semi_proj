package controller.store;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.StoreService;
import service.impl.StoreServiceImpl;

@WebServlet("/payment")
public class StorePaymentControlelr extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private StoreService storeService = new StoreServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		//로그인 되어있지 않으면 리다이렉트 
//		if( req.getSession().getAttribute("login") == null ) {
//			resp.sendRedirect("/");
//			
//			return;
//		}
		
		//VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/store/payment.jsp").forward(req, resp);

	
	}
	

}
