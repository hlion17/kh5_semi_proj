package controller.store.payment2.copy;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.OrderingService;
import service.impl.OrderingServiceImpl;

@WebServlet("/order/check2")
public class OrderCheckController extends HttpServlet {

	private OrderingService orderingService = new OrderingServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 주문 사항 표시
		orderingService.getOrders(req);
		
		// 주문 현황 표시
		req.getRequestDispatcher("/WEB-INF/views/store/order/order_check.jsp").forward(req, resp);
	}
}
