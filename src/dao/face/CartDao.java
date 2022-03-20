package dao.face;

import java.sql.Connection;
import java.util.List;

import dto.Cart;

public interface CartDao {

	/**
	 * 장바구니 리스트 조회
	 * @param conn
	 * @return
	 */
	public List<Cart> selectAll(Connection conn);

	/**
	 * 게시글 삭제
	 * 
	 * @param board - 삭제할 장바구니번호 담은 객체
	 */
	

	// 새로작성
	List<Cart> findAllByMemberNo(Connection conn, int memberNo);
	
	int insert(Connection conn, Cart cart);
	
	int update(Connection conn, Cart cart);
	
	int delete(Connection conn, Cart cart);
	
//    List<Cart> cartMoney();
//    void insert(Cart dto); //장바구니 추가
//    List<Cart> listCart(String userid); //장바구니 목록
//    void delete(int cart_id); //장바구니 개별 삭제
//    void deleteAll(String userid); //장바구니 비우기
//    void update(int cart_id); 
//    int sumMoney(String userid); //장바구니 금액 합계
//    int countCart(String userid, int product_id); //장바구니 상품 갯수
//    void updateCart(Cart dto); //장바구니 수정 
//    void modifyCart(Cart dto);
	
	
	

}
