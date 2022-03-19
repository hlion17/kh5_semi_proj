package service.face;

import dto.OpenRecipe;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface OpenRecipeService {
    void getPageList(HttpServletRequest request, String itemName, String curPage);

    /**
     * 검색어를 받아 오픈레시피를 조회하는 서비스
     * @param req - 검색어 정보가 담긴 요청 객체
     */
	void searchOpenRecipe(HttpServletRequest req);

	/**
	 * 개별 오픈레시피의 상세 내용을 가져오는 메서드
	 * @param req - 레시피의 이름 정보가 담긴 요청 객체
	 */
	void getRecipeDetail(HttpServletRequest req);
}
