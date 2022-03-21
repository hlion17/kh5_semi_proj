package dao.face;

import java.sql.Connection;

import dto.OrderResult;
import dto.Ordering;

public interface OrderDao {
	
	/**
	 * 주문 테이블의 PK값이 될 seqvalue를 생성하고 가져오는 메서드
	 * @param conn - DB 접속 객체
	 * @return - seqvalue
	 */
	int findOrderNo(Connection conn);

	/**
	 * 주문정보 생성하는 메서드
	 * @param conn - DB 생성 객체
	 * @param memberNo - 주문한 회원의 번호
	 * @param total - 주문 총 금액
	 * @return
	 */
	int insert(Connection conn, Ordering order);

	/**
	 * 주문_상품 매핑 테이블에 주문, 상품, 수량 정보 insert
	 * @param conn - DB 접속 객체
	 * @param orderNo - 주문번호
	 * @param proNo - 상품 번호
	 * @param proQty - 주문 수량
	 */
	int insertOrderAndProduct(Connection conn, int orderNo, int proNo, int proQty);

	/**
	 * 주문 결과 가져오는 메서드
	 * @param conn - DB 접속 객체
	 * @param orderNo - 주문 번호
	 * @return 주문 결과
	 */
	OrderResult getOrderResutl(Connection conn, int orderNo);

}
