package controller.store.payment2.copy;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.OrderingService;
import service.impl.OrderingServiceImpl;

@WebServlet("/order/delete2")
public class OrderDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderingService orderingService = new OrderingServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 주문 삭제 서비스
		orderingService.cancleOrder(req);
		
		// 주문 체크 페이지로 리다이렉션
		resp.sendRedirect("/order/check");
	}
}
