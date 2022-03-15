package dao.face;

import java.sql.Connection;
import java.util.List;

import dto.Product;
import dto.Recipe;

public interface StoreDao {

	/**
	 * Board 테이블 전체 조회
	 * 
	 * @param conn - DB 연결 객체
	 * @return List<Product> - Product테이블 전체 조회 결과 목록
	 */
	List<Product> selectAll(Connection conn);
	

}
