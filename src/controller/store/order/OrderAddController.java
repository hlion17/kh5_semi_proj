package controller.store.order;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.OrderingService;
import service.impl.OrderingServiceImpl;

@WebServlet("/order/add")
public class OrderAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderingService orderingService = new OrderingServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("도착");
		// 주문 추가하는 서비스 이용
		orderingService.addOrder(req);
		
		// 주문성공 페이지로 이동
		req.getRequestDispatcher("/WEB-INF/views/store/order/order_success.jsp").forward(req, resp);
	}
}
