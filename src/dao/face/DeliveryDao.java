package dao.face;

import java.sql.Connection;

import dto.Delivery;

public interface DeliveryDao {

	/**
	 * 배송정보 DB에 insert
	 * @param conn - DB 접속 개체
	 * @param deli - 배송정보가 담긴 DTO객체(주문번호, 주소, 연락처, 수취인)
	 * @return DB 수행결과
	 */
	int insert(Connection conn, Delivery deli);

	/**
	 * 주문 번호에 해당되는 배송정보 삭제
	 * @param conn - DB 접속 객체
	 * @param orderNo - 취소할 주문번호
	 * @return
	 */
	int delete(Connection conn, int orderNo);

}
