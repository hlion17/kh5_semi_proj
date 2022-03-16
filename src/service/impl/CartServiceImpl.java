package service.impl;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.CartDao;
import dao.impl.CartDaoImpl;
import dto.Cart;
import service.face.CartService;

public class CartServiceImpl implements CartService {
	
	private CartDao cartDao = new CartDaoImpl();

	@Override
	public List<Cart> getList() {
		return cartDao.selectAll(JDBCTemplate.getConnection());
	}
	
	@Override
	public Cart getCartno(HttpServletRequest req) {
		//전달파라미터 cartno를 저장할 DTO객체 생성
		Cart cartno = new Cart();
		
		String param = req.getParameter("cartno");
		if( param != null && !"".equals( param ) ) {
			cartno.setCart_no( Integer.parseInt(param) );
		} else {
			System.out.println("[WARN] CartService getcartno() - cartno값이 null이거나 비어있음");
		}

		return cartno;
	}
	
	@Override
	public void delete(Cart cart) {
		Connection conn = JDBCTemplate.getConnection();

		if( cartDao.delete(conn, cart) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
	}
	


}
