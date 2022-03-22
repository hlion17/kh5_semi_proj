package service.face;

import javax.servlet.http.HttpServletRequest;

public interface OrderingService {

	/**
	 * 주문페이지에 보여줄 상품 리스트 얻어오는 서비스
	 * @param req - 상품 번호 정보가 담긴 요청 객체
	 */
	void getProList(HttpServletRequest req);

	/**
	 * 주문, 배송지 정보를 생성하는 서비스
	 * @param req - 주문(주문총금액), 배송(배송주소, 연락처, 수취인), 상품(상품번호, 수량) - 배열
	 */
	void addOrder(HttpServletRequest req);

	/** 
	 * 주문 결과를 조회한다.
	 * @param req 
	 */
	void getOrders(HttpServletRequest req);

}
