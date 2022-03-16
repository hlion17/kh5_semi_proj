package service.face;

import dto.Ingredient;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public interface IngrService {
	/**
	 * 등록된 모든 재료 항목을 가져오는 서비스
	 * @return - 재료 리스트
	 */
    List<Ingredient> getAllIngrs();
    
    /**
     * 재료의 상세 정보를 가져오는 메서드
     * @param req - 재료의 등록번호 정보가 담긴 요청 객체
     */
    void getIngrDetail(HttpServletRequest req);

    /**
     * 재료명으로 검색하는 서비스
     * 
     * @param req - 재료명 정보가 담기 요청 객체
     */
	void findByIngrName(HttpServletRequest req);
}
