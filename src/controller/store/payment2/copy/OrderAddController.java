package controller.store.payment2.copy;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.OrderingService;
import service.impl.OrderingServiceImpl;

@WebServlet("/order/add2")
public class OrderAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderingService orderingService = new OrderingServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("도착");
		// 주문 추가하는 서비스 이용
		orderingService.addOrder(req);
		
		// 주문성공 페이지로 이동
		// redirect 안하면  새로고침 하면 계속 주문되는데
		req.getRequestDispatcher("/WEB-INF/views/store/order/order_success.jsp").forward(req, resp);
	}
}
