package controller.store;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Cart;
import dto.Payment;
import service.face.PaymentService;
import service.impl.PaymentServiceImpl;

@WebServlet("/payment")
public class xxx_StorePaymentControlelr extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private PaymentService paymentService = new PaymentServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/store/payment.jsp").forward(req, resp);
	}
}
