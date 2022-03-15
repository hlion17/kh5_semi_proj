package dao.face;

import java.sql.Connection;
import java.util.List;

import dto.Ref;
import dto.RefItem;

public interface RefDao {

	/**
	 * DB 에서 회원이 가지고 있는 냉장고 목록을 가져온다.
	 * @param conn - DB 접속 객체
	 * @param memberId - 조회할 회원 아이디
	 * @return - 회원이 가지고 있는 냉장고 목록
	 */
	List<Ref> findByMemberId(Connection conn, String memberId);

	/**
	 * DB에서 refCode에 해당하는 냉장고의 품목을 조회한다.
	 * @param conn - DB접속 객체
	 * @param refCode - 조회할 냉장고 코드
	 * @return
	 */
	List<RefItem> getItemListByRefCode(Connection conn, int refCode);

	/**
	 * DB에서 상태코드로 필터링된 냉장고 품목을 조회한다.
	 * @param conn - DB 접속 객체
	 * @param status - 품목의 상태코드
	 * @return
	 */
	List<RefItem> findAllByFiltering(Connection conn, int refCode, int status);

	/**
	 * DB에서 상태코드로 필터링하고 정렬기준으로 정렬한 냉장고 품목의 목록을 반환한다.
	 * @param conn - DB 접속 객체
	 * @param status - 필터링할 상태 코드
	 * @param orderBy - 정렬할 정렬기준
	 * @return
	 */
	List<RefItem> findAllByFilteringAndOrdering(Connection conn, int reqCode, int status, String orderBy);

	/**
	 * DB에 냉장고 품목을 추가한다.
	 * @param conn - DB 접속 객체
	 * @param refCode - 품목을 추가 할 냉장고 코드
	 * @param refItem - 추가할 품목의 내용이 담긴 DTO
	 * @return DB insert 결과를 반환 ( 1이상 - 성공, 0 - 실패)
	 */
	int insert(Connection conn, int refCode, RefItem refItem);

	/**
	 * DB에 있는 냉장고 품목을 삭제한다.
	 * @param conn - DB 접속 객체
	 * @param itemNo - 삭제할 품목의 번호
	 * @return - delete 결과( 1 - 삭제 성공, 0 - 삭제 실패)
	 */
	int delete(Connection conn, int itemNo);

	/**
	 * DB에 있는 냉장고 품목의 내용을 변경한다.
	 * @param conn - DB 접속 객체
	 * @param refItem - 변경할 내용이 담긴 DTO
	 * @return - update 결과 ( 1 - 성공, 0 - 실패)
	 */
	int update(Connection conn, RefItem refItem);

}
