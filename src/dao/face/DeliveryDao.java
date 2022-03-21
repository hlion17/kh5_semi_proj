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

}
