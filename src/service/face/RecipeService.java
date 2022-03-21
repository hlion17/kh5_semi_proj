package service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Recipe;
import dto.RecipeFile;
import util.Paging;

public interface RecipeService {
	
	/**
	 * 게시글 전체 조회
	 * 
	 * @return List<Recipe> - 게시글 전체 조회 결과 목록
	 */
	public List<Recipe> getList();
	
	/**
	 * 게시글 페이징 목록 조회
	 * 
	 * @param paging - 페이징 정보 객체
	 * @return List<Recipe> - 페이징이 반영된 게시글 조회 결과 목록
	 */
	public List<Recipe> getList(Paging paging);
	
	/**
	 * 게시글 랭킹 전체 조회
	 * 
	 * @return List<Recipe> - 게시글 랭킹 전체 조회 결과 목록
	 */
	public List<Recipe> getListRank();
	
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
	 * @return Recipe - 전달파라미터 boardno값을 포함한 DTO객체
	 */
	public Recipe getBoardno(HttpServletRequest req);
	
//	/**
//	 * 요청 파라미터 얻어오기
//	 * 
//	 * @param req - 요청 정보 객체
//	 * @return Recipe - 전달파라미터 like값을 포함한 DTO객체
//	 */
//	public Recipe getLike(HttpServletRequest req);

	/**
	 * 전달된 boardno를 이용하여 게시글을 조회한다
	 * 
	 * 조회된 게시글의 추천수를 1 증가시킨다
	 * 
	 * @param boardno - 조회할 boardno를 가지고 있는 DTO객체
	 * @return Recipe - 조회된 게시글 정보
	 */
	public void addLike(Recipe boardno, HttpServletRequest req);
	
	/**
	 * 전달된 boardno를 이용하여 게시글을 조회한다
	 * 
	 * 조회된 게시글의 조회수를 1 증가시킨다
	 * 
	 * @param boardno - 조회할 boardno를 가지고 있는 DTO객체
	 * @return Recipe - 조회된 게시글 정보
	 */
	public Recipe view(Recipe boardno);

	/**
	 * 게시글 작성
	 * 	입력한 게시글 내용을 DB에 저장
	 * 
	 * @param req - 요청정보 객체(게시글내용 + 첨부파일)
	 * 
	 */
	public void write(HttpServletRequest req);

	/**
	 * 전달된 Recipe 객체의 id 를 이용한 닉네임 조회
	 * 
	 * @param viewBoard - 조회할 게시글 정보
	 * @return String - 게시글 작성자의 닉네임
	 */
	public String getNick(Recipe viewBoard);

	/**
	 * 첨부파일 정보 조회하기
	 * 
	 * @param viewBoard - 첨부파일과 연결된 게시글의 번호
	 * @return RecipeFile - 첨부파일 정보 DTO객체
	 */
	public RecipeFile viewFile(Recipe viewBoard);

	/**
	 * 게시글 수정
	 * 
	 * @param req - 요청 정보 객체
	 */
	public void update(HttpServletRequest req);

	/**
	 * 게시글 삭제
	 * 
	 * @param board - 삭제할 게시글 번호를 가진 객체
	 */
	public void delete(Recipe board);
	
	/**
	 * 팔로우
	 * 
	 * @param followee - 팔로우당하는 사람, 해당 글작성자 memberno
	 * @param follower - 팔로우하는 사람, 현재 로그인세션 memberno
	 */
	public void setFollow(int followee, int follower);

	/**
	 * 조회수 역카운트
	 * @param boardno - 게시글 번호
	 */
	public void downHit(int boardno);

	/**
	 * 팔로우 검사조건 - 이미 팔로우 한적이 있는지 검사(PK 위반 방지)
	 * @param followee - 팔로우당하는 사람, 해당 글작성자 memberno
	 * @param follower - 팔로우하는 사람, 현재 로그인세션 memberno
	 */
	public boolean checkFollowFK(int followee, int follower);
	
}
