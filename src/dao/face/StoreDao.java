package dao.face;

import java.sql.Connection;
import java.util.List;

import dto.Cart;
import dto.Product;
import dto.Recipe;

public interface StoreDao {

	/**
	 * Product 테이블 전체 조회
	 * 
	 * @param conn - DB 연결 객체
	 * @return List<Product> - Product테이블 전체 조회 결과 목록
	 */
	List<Product> selectAll(Connection conn);

	
	/**
	 * 지정된 Productno의 상품 정보 조회하기
	 * 
	 * @param conn - DB연결 객체
	 * @param boardno - 조회할 게시글의 productno를 가진 DTO객체
	 * @return Product - 조회된 게시글의 상세정보 DTO객체
	 */
	Product selectProductByProductno(Connection conn, Product productNo);


	
	

}
