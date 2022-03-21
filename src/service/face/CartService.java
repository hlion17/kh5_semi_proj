package service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Cart;

public interface CartService {

	void getAllCartItemByMemberId(HttpServletRequest req);
	
	void addCartItem(HttpServletRequest req);
	
	void updateCartItem(HttpServletRequest req);
	
	void deleteCartItem(HttpServletRequest req);
	
	

	}
