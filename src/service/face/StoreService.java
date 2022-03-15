package service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Product;
import util.Paging;


public interface StoreService {

	/**
	 * 상품 목록 전체 조회 
	 * @return List<Product> - 상품 전체 조회 결과 목록
	 */
	public List<Product> getList();

	
	/**
	 * 요청 파라미터 얻어오기
	 * @param req - 요청 정보 객체
	 * @return Product - 전달파라미터에 Pro_no(상품번호)값을 포함한 DTO객체
	 */
	public Product getProno(HttpServletRequest req);


	/**
	 * 전달된 productNo를 이용하여 상품 게시글을 조회한다
	 * @param productNo - 조회할 productNO를 가지고 있는 DTO 객체
	 * @return Product - 조회된 상품 정보
	 */
	public Product view(Product productNo);


	
	

	



}
