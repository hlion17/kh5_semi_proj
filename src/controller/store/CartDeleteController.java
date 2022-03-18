package controller.store;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Cart;
import dto.Product;
import service.face.StoreService;
import service.impl.StoreServiceImpl;

@WebServlet("/cart/delete")
public class CartDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private StoreService storeService = new StoreServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//전달파라미터 얻기 - cart(장바구니번호)
		Cart cart = storeService.getCartno(req);
		
//		storeService.delete(cart);
		
		//목록으로 리다이렉트
		resp.sendRedirect("/board/list");	

	}
	
	

    }
