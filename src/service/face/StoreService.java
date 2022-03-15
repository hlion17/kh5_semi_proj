package service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Product;


public interface StoreService {

	/* 게시글 전체 조회 */
	List<Product> getList();
	
	



}
