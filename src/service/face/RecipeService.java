package service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Recipe;
import dto.RecipeFile;
import util.Paging;

public interface RecipeService {
	
	/**
	 * 게시글 전체 조회
	 * @return 
	 */
	public List<Recipe> recipeBoard();
	
	/**
	 * 게시글 페이징 목록 조회
	 * 
	 * @param paging - 페이징 정보 객체
	 * @return List<Board> - 페이징이 반영된 게시글 조회 결과 목록
	 */
	public List<Recipe> getList(Paging paging);
	
	/**
	 * 페이징 객체 생성
	 * 
	 * @param req - 요청 정보 객체
	 * @return Paging - 페이징 계산이 완료된 Paging객체
	 */
	public Paging getPaging(HttpServletRequest req);

	/**
	 * 요청 파라미터 얻어오기
	 * 
	 * @param req - 요청 정보 객체
	 * @return Recipe - 전달파라미터 recipeIdx값을 포함한 DTO객체
	 */
	public Recipe getRecipeIdx(HttpServletRequest req);

	/**
	 * 전달된 recipeIdx를 이용하여 게시글을 조회한다
	 * 
	 * 조회된 게시글의 조회수를 1 증가시킨다
	 * 
	 * @param boardno - 조회할 recipeIdx를 가지고 있는 DTO객체
	 * @return Board - 조회된 게시글 정보
	 */
	public Recipe recipeContent(Recipe recipe);

	/**
	 * 게시글 작성
	 * 	입력한 게시글 내용을 DB에 저장
	 * 
	 * @param req - 요청정보 객체(게시글내용 + 첨부파일)
	 * 
	 */
//	public void recipeInsert(Recipe recipe);
	public void recipeInsert(HttpServletRequest req);

	/**
	 * 전달된 Recipe 객체의 id 를 이용한 닉네임 조회
	 * 
	 * @param viewRecipe - 조회할 게시글 정보
	 * @return String - 게시글 작성자의 닉네임
	 */
	public String getNick(Recipe viewRecipe);

	/**
	 * 첨부파일 정보 조회하기
	 * 
	 * @param viewRecipe - 첨부파일과 연결된 게시글의 번호
	 * @return RecipeFile - 첨부파일 정보 DTO객체
	 */
	public RecipeFile viewFile(Recipe viewRecipe);

	/**
	 * 게시글 수정
	 * 
	 * @param req - 요청 정보 객체
	 */
	public void recipeUpdate(HttpServletRequest req);

	/**
	 * 게시글 삭제
	 * 
	 * @param recipe - 삭제할 게시글 번호를 가진 객체
	 */
	public void recipeDelete(Recipe recipe);

	/**
	 * 레시피 랭킹
	 * @return
	 */
	public List<Recipe> recipeRank();
	
}