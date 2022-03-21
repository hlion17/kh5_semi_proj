package controller.store.order;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.OrderingService;
import service.impl.OrderingServiceImpl;

@WebServlet("/order")
public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderingService orderingService = new OrderingServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 주문페이지에 보여줄 상품 정보 얻어오는 서비스
		orderingService.getProList(req);
		
		// 주문페이지 이동
		req.getRequestDispatcher("/WEB-INF/views/store/order/order.jsp").forward(req, resp);
	}
}
