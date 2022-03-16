package dao.face;

import java.sql.Connection;
import java.util.List;

import dto.Ingredient;

public interface IngrDao {
	/**
	 * DB의 모든 재료를 조회
	 * @param conn - DB 접속 객체
	 * @return List<Ingredient> - 재료 목록 리스트
	 */
	List<Ingredient> findAll(Connection conn);
	
	/**
	 * 재료코드에 해당하는 하나의 재료 조회
	 * @param conn - DB 접속 객체
	 * @param ingrCode - 조회할 재료의 재료코드
	 * @return - Ingredient - 재료의 상세정보가 담긴 DTO 객체
	 */
	Ingredient findById(Connection conn, int ingrCode);

	/**
	 * 재료명으로 재료테이블 조회
	 * @param conn - DB 접속 객체
	 * @param ingrName - 조회할 재료명
	 * @return - List<Ingredient> - 조회된 재료레코드 목록
	 */
	List<Ingredient> findByName(Connection conn, String ingrName);

}
