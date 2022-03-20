package controller.store;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.CartService;
import service.impl.CartServiceImpl;

@WebServlet("/cart/update")
public class CartUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CartService cartService = new CartServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 장바구니 품목 수정 서비스
		cartService.updateCartItem(req);
		
		// 장바구니 페이지로 리다이렉트
		resp.sendRedirect("/cart");
	}
}
