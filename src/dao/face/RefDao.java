package dao.face;

import java.sql.Connection;
import java.util.List;

import dto.Ref;

public interface RefDao {

	/**
	 * DB 에서 회원이 가지고 있는 냉장고 목록을 가져온다.
	 * @param conn - DB 접속 객체
	 * @param memberId - 조회할 회원 아이디
	 * @return - 회원이 가지고 있는 냉장고 목록
	 */
	List<Ref> findByMemberId(Connection conn, String memberId);

}
