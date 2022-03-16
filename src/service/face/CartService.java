package service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Cart;

public interface CartService {

	/**
	 * 장바구니 목록 전체 조회
	 * @return List<cart> - 장바구니 전체 조회 결과 목록
	 */
	public List<Cart> getList();
	
	/**
	 * 요청 파라미터 얻어오기
	 * 
	 * @param req - 요청 정보 객체
	 * @return Cart - 전달파라미터 cartno값을 포함한 DTO객체
	 */
	public Cart getCartno(HttpServletRequest req);
	
	/**
	 * 장바구니 품목 삭제
	 * 
	 * @param board - 삭제할 장바구니 번호를 가진 객체
	 */
	public void delete(Cart cart);

	
	
	

//		List<Cart> cartMoney();
//	    void insert(Cart dto);
//	    List<Cart> listCart(String userid);
//	    void delete(int cart_id);
//	    void deleteAll(String userid);
//	    void update(int cart_id);
//	    int sumMoney(String userid);
//	    int countCart(String userid, int product_id);
//	    void updateCart(Cart dto);
//	    void modifyCart(Cart dto);
	
	

	}
