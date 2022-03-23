package controller.store.payment2.copy;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.OrderingService;
import service.impl.OrderingServiceImpl;

@WebServlet("/order/update2")
public class OrderStatusUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderingService orderingService = new OrderingServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 주문 상태 변경하는 서비스
		orderingService.updateStatus(req);
		
		// 주문 확인 창으로 리다이렉트
		resp.sendRedirect("/order/check");
	}
}
