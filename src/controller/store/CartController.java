package controller.store;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Cart;
import service.face.CartService;
import service.impl.CartServiceImpl;


@WebServlet("/cart")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CartService cartService = new CartServiceImpl();
 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//카트 내역 전체 보여주기
		List<Cart> cartList = cartService.getList();
		
		//조회값 model 전달
		req.setAttribute("cartList", cartList);
		
		//VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/store/cart.jsp").forward(req, resp);
	}
	

	

}
