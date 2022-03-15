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
	 * - insertRef_Item과 하나의 트랜잭션으로 처리되어야 한다.
	 * - 해당 insert가 먼저 수행되어야 함(참조 대상 테이블)
	 * @param conn - DB 접속 객체
	 * @param refCode - 품목을 추가 할 냉장고 코드
	 * @param refItem - 추가할 품목의 내용이 담긴 DTO
	 * @return DB insert 결과를 반환 ( 1이상 - 성공, 0 - 실패)
	 */
	int insertItem(Connection conn, int refCode, RefItem refItem);
	
	/**
	 * DB에 냉장고-품목 매핑테이블에 정보를 추가한다.
	 * - insertItem과 하나의 트랜잭션으로 처리되어야 한다.
	 * - insertItem 메서드가 먼저 실행되어야 함(참조하는 테이블)
	 * @param conn
	 * @param refCode
	 * @param refItem
	 * @return
	 */
	int insertRef_Item(Connection conn, int refCode, RefItem refItem);

	/**
	 * DB에 있는 냉장고 품목을 삭제한다.
	 * @param conn - DB 접속 객체
	 * @param itemNo - 삭제할 품목의 번호
	 * @return - delete 결과( 1 - 삭제 성공, 0 - 삭제 실패)
	 */
	int deleteItem(Connection conn, int itemNo);
	
	/**
	 * DB에 있는 냉장고_품목 맵핑테이블의 품목을 삭제한다.
	 * @param conn
	 * @param itemNo
	 * @return
	 */
	int deleteRef_Item(Connection conn, int itemNo);

	/**
	 * DB에 있는 냉장고 품목의 내용을 변경한다.
	 * @param conn - DB 접속 객체
	 * @param refItem - 변경할 내용이 담긴 DTO
	 * @return - update 결과 ( 1 - 성공, 0 - 실패)
	 */
	int update(Connection conn, RefItem refItem);
	
	/**
	 * 냉장고 품목 식별값을 생성해서 가져온다.
	 * @param conn - DB 접속 객체
	 * @param refItem - 냉장고 품목 식별값을 담을 DTO 객체
	 * @return - 냉장고 품목 식별값이 들어 있는 DTO 객체
	 */
	RefItem getRefItemNo(Connection conn, RefItem refItem);

}
