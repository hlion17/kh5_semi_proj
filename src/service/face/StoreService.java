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

	



}
