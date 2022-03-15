package dao.face;

import java.sql.Connection;
import java.util.List;

import dto.Recipe;
import dto.RecipeFile;
import util.Paging;

public interface RecipeDao {

	/**
	 * recipe테이블 전체 조회
	 * 
	 * @param conn - DB연결 객체
	 * @return List<Recipe> - recipe테이블 전체 조회 결과 목록
	 */
	public List<Recipe> selectAll(Connection conn);

	/**
	 * recipe테이블 전체 조회
	 * 	-> 페이징 처리 추가
	 * 
	 * @param conn - DB연결 객체
	 * @param paging - 페이징 정보 객체
	 * @return List<Recipe> - recipe테이블 전체 조회 결과 목록
	 */
	public List<Recipe> selectAll(Connection conn, Paging paging);
	
	/**
	 * 총 게시글 수 조회
	 * 
	 * @param conn - DB연결 객체
	 * @return int - recipe테이블의 전체 행 수
	 */
	public int selectCntAll(Connection conn);

	/**
	 * 조회된 게시글의 조회수 증가시키기
	 * 
	 * @param conn - DB연결 객체
	 * @param recipeIdx - 조회할 게시글의 recipeIdx를 가진 DTO객체
	 * @return int - UPDATE 쿼리 수행 결과
	 */
	public int updateHit(Connection conn, Recipe recipeIdx);

	/**
	 * 지정된 recipeIdx의 게시글 조회하기
	 * 
	 * @param conn - DB연결 객체
	 * @param recipeIdx - 조회할 게시글의 recipeIdx를 가진 DTO객체
	 * @return Recipe - 조회된 게시글의 상세정보 DTO객체
	 */
	public Recipe selectRecipeByRecipeIdx(Connection conn, Recipe recipeIdx);

	/**
	 * 게시글 입력
	 * 
	 * @param conn - DB연결 객체
	 * @param recipe - 삽입될 게시글 내용
	 * @return int - INSERT 쿼리 수행 결과
	 */
	public int insert(Connection conn, Recipe recipe);

	/**
	 * id를 이용해 nick을 조회한다
	 * 
	 * @param conn - DB연결 객체
	 * @param viewRecipe - 조회할 id를 가진 객체
	 * @return String - 작성자 닉네임
	 */
	public String selectNickByMemberId(Connection conn, Recipe viewRecipe);

	/**
	 * 시퀀스를 이용하여 다음 게시글 번호를 조회한다
	 * 
	 * @param conn - DB연결 객체
	 * @return int - 다음 게시글 번호
	 */
	public int selectRecipeIdx(Connection conn);

	/**
	 * 첨부파일 삽입
	 * 
	 * @param conn - DB연결 객체
	 * @param recipeFile - 첨부파일 정보
	 * @return int - INSERT 쿼리 수행 결과
	 */
	public int insertFile(Connection conn, RecipeFile recipeFile);

	/**
	 * 첨부파일 정보 조회
	 * 
	 * @param conn - DB연결 객체
	 * @param viewRecipe - 조회할 게시글 번호
	 * @return RecipeFile - 첨부파일 정보
	 */
	public RecipeFile selectFile(Connection conn, Recipe viewRecipe);

	/**
	 * 게시글 수정 
	 * 
	 * @param recipe - 수정할 내용을 담은 객체
	 */
	public int recipeUpdate(Connection conn, Recipe recipe);

	/**
	 * 게시글 삭제
	 * 
	 * @param recipe - 삭제할 게시글번호를 담은 객체
	 */
	public int recipeDelete(Connection conn, Recipe recipe);
	
	/**
	 * 게시글에 첨부된 파일 기록 삭제
	 * 
	 * @param recipe - 삭제할 게시글번호를 담은 객체
	 */
	public int recipeDeleteFile(Connection conn, Recipe recipe);
	
	
}
