package dao.face;

import java.sql.Connection;

import dto.Product;

public interface ProductDao {

	/**
	 * Product 테이블에서 pro_no로 조회된 상품 정보 가져오는 메서드
	 * @param conn - DB 접속 객체
	 * @param proNo - 가져올 상품 번호
	 * @return - 상품번호에 해당되는 상품 정보가 담긴 DTO
	 */
	Product findByNo(Connection conn, int proNo);

}
