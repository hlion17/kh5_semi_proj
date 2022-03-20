package controller.store;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.CartService;
import service.impl.CartServiceImpl;

@WebServlet("/cart/add")
public class CartAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CartService cartService = new CartServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 카트에 상품 추가 서비스
		cartService.addCartItem(req);
		
		// 쇼핑계속하기, 장바구니로 가기에 따라
		// redirect 위치 선택 기능 생각해보기
		resp.sendRedirect("/cart");
	}
}
