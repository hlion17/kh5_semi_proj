package dao.face;

import java.sql.Connection;
import java.util.List;

import dto.Member;
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
	 * DB에서 refCode에 해당하는 냉장고의 품목을 조회한다. (기본정렬: 오름차순)
	 * @param conn - DB접속 객체
	 * @param refCode - 조회할 냉장고 코드
	 * @return
	 */
	List<RefItem> findAllItems(Connection conn, int refCode);
	
	RefItem findByItemNo(Connection conn, int itemNo);
	
	/**
	 * DB에서 refCode에 해당하는 냉장고 품목을 내림차순으로 조회한다.
	 * @param conn
	 * @param refCode
	 * @return
	 */
	// List<RefItem> findAllItemsDesc(Connection conn, int refCode);

	/**
	 * DB에서 상태코드로 필터링된 냉장고 품목을 조회한다.
	 * (삭제예정)
	 * @param conn - DB 접속 객체
	 * @param status - 품목의 상태코드
	 * @return
	 */
	List<RefItem> findAllByFiltering(Connection conn, int refCode, int status);

	/**
	 * DB에서 상태코드로 필터링하고 정렬기준으로 정렬한 냉장고 품목의 목록을 반환한다.(기본정렬: 유통기한 오름차순)
	 * @param conn - DB 접속 객체
	 * @param status - 필터링할 상태 코드
	 * @param orderBy - 정렬할 정렬기준
	 * @return
	 */
	List<RefItem> findFilteredItems(Connection conn, int reqCode, int status);

	/**
	 * DB에서 상태코드로 필터링하고 등록일 기준으로 내림차순 정렬한 냉장고 품목의 목록을 반환한다.
	 * @param conn - DB 접속 객체
	 * @param reqCode - 조회 할 냉장고 코드
	 * @param status - 필터링 할 상태코드
	 * @return
	 */
	List<RefItem> findFileredItemsOrderByRegDateDesc(Connection conn, int reqCode, int status);
	
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

	/**
	 * 냉장고_멤버 매핑 테이블에 공유 대상 냉장고 코드와 로그인한 회원 번호 insert
	 * @param conn - DB 접속 객체
	 * @param refCode - 공유 대상 냉장고 코드
	 * @param memberNo - 로그인 한 회원번호
	 * @return - insert 결과 ( 1 -  성공, 0 - 실패 ) 
	 */
	int insertSharingMember(Connection conn, int refCode, int memberNo);
	
	/**
	 * DB에 냉장고를 등록한다.
	 * - 회원가입과 하나의 트랜잭션으로 이루어져야 한다.
	 * @param conn - DB 접속 객체
	 * @param member - 등록 할 냉장고 코드, 회원번호, 회원닉네임(냉장고이름에 사용) 정보가 담긴 Member DTO
	 * @return - insert 결과 ( 1 - 성공, 0 - 실패)
	 */
	int insertRef(Connection conn, Member member);
	
	/**
	 * 회원가입 insert, 냉장고 insert 할 때 회원_냉장고 매핑 테이블에 정보 추가
	 * - 하나의 트랜잭션으로 관리
	 * @param conn - DB 접속 객체
	 * @param member - 회원ID, 냉장고 코드, 회원 닉네임이 담긴 회원 DTO
	 * @return insert 결과 (1 - 성공, 0 - 실패)
	 */
	int insertRef_Member(Connection conn, Member member);

}
