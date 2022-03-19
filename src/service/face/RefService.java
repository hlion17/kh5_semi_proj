package service.face;

import javax.servlet.http.HttpServletRequest;

public interface RefService {

	/**
	 * 회원 아이디에 해당되는 냉장고 목록을 가져온다.
	 * @param req - 회원아이디 정보가 담긴 요청 객체
	 */
	void chooseRef(HttpServletRequest req);
	
	/**
	 * 하나의 냉장고 품목 상세 내용을 가져온다.
	 * @param req - 냉장고 품목번호 정보가 담긴 요청 객체
	 */
	void getItemDetail(HttpServletRequest req);

	/**
	 * 해당 냉장고의 품목 리스트를 보여준다. (기본정렬: 유통기한 오름차순)
	 * @param req - 냉장고 코드 정보가 담긴 요청 객체
	 */
	void getAllItems(HttpServletRequest req);
	
	// 필요없을 듯
	/**
	 * 냉장고의 품목 리스트를 내림차순으로 보여준다.
	 * @param req - 조회 할 냉장고 코드 정보가 담긴 요청 객체
	 */
	// void getAllItemsDesc(HttpServletRequest req);
	
	/**
	 * 해당 냉장고의 품목 리스트를 정렬 기준에 따라 정렬한다.
	 * @param req - 냉장고 코드 정보, 보관상태, 정렬기준이 담긴 요청 객체
	 */
	void getFilteredItems(HttpServletRequest req);
	
	/**
	 * 해당 냉장고의 품목을 상태코드로 필터링하고 등록일 기준으로 내림차순 정렬한다.
	 * @param req - 냉장고 코드, 보관상태 정보가 담긴 요청 객체
	 */
	void getFilteredItemsOrderByRegDate(HttpServletRequest req);
	
	/**
	 * 해당 냉장고에 품목을 추가한다.
	 * @param req - 냉장고 코드 정보, 추가 할 품목의 정보가 담긴 요청 객체
	 */
	void addRefItem(HttpServletRequest req);
	
	/**
	 * 해당 냉장고에 있는 품목 1개를 삭제한다.
	 * @param req - 냉장고 코드 정보, 삭제할 품목의 번호의 정보가 담긴 요청 객체
	 */
	void deleteRefItem(HttpServletRequest req);
	
	/**
	 * 해당 냉장고에 있는 품목 1개의 정보를 수정한다.
	 * @param req - 냉장고 코드 정보, 수정할 품목 내용의 정보가 담긴 요청 객체
	 */
	void updateRefItem(HttpServletRequest req);

	/**
	 * 다른 회원의 냉장고를 공유할 수 있게 하는 서비스
	 * 
	 * @param req - 공유 대상의 냉장고 코드의 정보가 담긴 요청 객체
	 */
	void shareRef(HttpServletRequest req);

	/**
	 * 공유 중인 냉장고를 취소하는 서비스
	 * @param req - 취소하려는 냉장고 코드와 
	 */
	void cancelSharingRef(HttpServletRequest req);

}
