package service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import common.Pagination;
import dto.OpenRecipe;
import openapi.OpenRecipeApi;
import service.face.OpenRecipeService;

public class OpenRecipeServiceImpl implements OpenRecipeService {
	
	private static Logger logger = Logger.getLogger(OpenRecipeServiceImpl.class.getCanonicalName());

    // 페이지 정보에 해당하는 레시피 글 목록 가져오는 메서드
	// 보류
    @Override
    public void getPageList(HttpServletRequest req, String itemName, String paramCurPage) {
/*
        int curPage;

        // 현재페이지 없으면 1페이지로 설정
        if (paramCurPage == null) {
            curPage = 1;
        } else {
            curPage = Integer.parseInt(req.getParameter("curPage"));
        }

        // 총 검색결과 얻으려고 api 요청 생성
        // 페이지 정보를 얻어오려면 총 검색결과 개수가 필요한데
        // , 총 검색결과 개수를 얻어오려면 페이지 정보가 필요하다
        // 이거 어떻게 해결하지
        OpenRecipeApi tmp = new OpenRecipeApi(itemName, 1, 1);
        int total = tmp.getTotal();

        // 페이지 정보 생성
        Pagination p = new Pagination(total, curPage, 5);

        int startIndex = p.getStartIndex();  // 게시글 시작 인덱스
        int endIndex = startIndex + p.getPageSize() - 1;  // 게시글 끝 인덱스
        int pageCnt = p.getPageCnt();  // 총 페이지 개수

        //System.out.println(startIndex);
        //System.out.println(endIndex);

        // api 요청 생성
        OpenRecipeApi openRecipeApi = new OpenRecipeApi(itemName, startIndex, endIndex);

        // 조회결과
        List<OpenRecipe> result = openRecipeApi.getPageList();

        // 조회결과, 페이지 정보 저장, 검색어 저장
        req.setAttribute("item", itemName);
        req.setAttribute("total", total);
        req.setAttribute("pageCnt", pageCnt);
        req.setAttribute("list", result);
*/
    }

    // 새로만든거
    // 페이지네이션을 자바스크립트로 처리하고 전체 목록 가져오기
	@Override
	public void searchOpenRecipe(HttpServletRequest req) {
		// 요청 파라미터 분석
		String itemName = req.getParameter("itemName");
		logger.info("/openRecipe/search 파라미터 - 검색어: " + itemName);
		
		// OpenRecipe API에서 레시피 목록 받아오기
		List<OpenRecipe> list = new OpenRecipeApi(itemName, 1, 100).getPageList();
		if (list != null) logger.info("API 오픈레시피 검색결과: " + list.size());
		
		// View로 전달할 데이터 저장
		req.setAttribute("list", list);
	}

	@Override
	public void getRecipeDetail(HttpServletRequest req) {
		// 요청 파라미터 분석
		String itemName = req.getParameter("itemName");
		logger.info("/openRecipe/search 파라미터 - 검색어: " + itemName);
		
		// OpenRecipe API에서 레시피 상세내용 받아오기
		List<OpenRecipe> list = new OpenRecipeApi(itemName, 1, 1).getPageList();
		if (list != null) logger.info("API 오픈레시피 검색결과: " + list.size());
		
		// View로 전달할 데이터 저장
		req.setAttribute("list", list);
	}
}
