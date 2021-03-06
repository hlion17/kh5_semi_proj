package controller.store;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Cart;
import dto.Product;
import service.face.CartService;
import service.face.StoreService;
import service.impl.CartServiceImpl;
import service.impl.StoreServiceImpl;

@WebServlet("/cart/delete")
public class CartDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CartService cartService = new CartServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 장바구니 품목 삭제하는 서비스
		cartService.deleteCartItem(req);
		
		// 장바구니 페이지로 리다이렉트
		resp.sendRedirect("/cart");
	}

}
