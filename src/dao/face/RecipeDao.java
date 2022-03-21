package dao.face;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Recipe;
import dto.RecipeFile;
import util.Paging;

public interface RecipeDao {

	/**
	 * Recipe테이블 전체 조회
	 * 
	 * @param conn - DB연결 객체
	 * @return List<Recipe> - Recipe테이블 전체 조회 결과 목록
	 */
	public List<Recipe> selectAll(Connection conn);

	/**
	 * Recipe테이블 전체 조회
	 * 	-> 페이징 처리 추가
	 * 
	 * @param conn - DB연결 객체
	 * @param paging - 페이징 정보 객체
	 * @return List<Recipe> - Recipe테이블 전체 조회 결과 목록
	 */
	public List<Recipe> selectAll(Connection conn, Paging paging);
	
	/**
	 * Recipe테이블 전체 조회 - 추천순
	 * @param connection
	 * @return List<Recipe> - Recipe테이블 전체 랭킹 조회 결과 목록
	 */
	public List<Recipe> selectAllRank(Connection connection);
	
	/**
	 * 총 게시글 수 조회
	 * 
	 * @param conn - DB연결 객체
	 * @return int - Recipe테이블의 전체 행 수
	 */
	public int selectCntAll(Connection conn);

	/**
	 * 조회된 게시글의 조회수 증가시키기
	 * 
	 * @param conn - DB연결 객체
	 * @param boardno - 조회할 게시글의 boardno를 가진 DTO객체
	 * @return int - UPDATE 쿼리 수행 결과
	 */
	public int updateHit(Connection conn, Recipe boardno);

	/**
	 * 조회된 게시글의 조회수 증가시키기
	 * 
	 * @param conn - DB연결 객체
	 * @param boardno - 조회할 게시글의 boardno
	 * @return int - UPDATE 쿼리 수행 결과
	 */
	public int downHit(Connection conn, int boardno);
	
	/**
	 * 조회된 게시글의 추천수 증가시키기
	 * 
	 * @param conn - DB연결 객체
	 * @param boardno - 조회할 게시글의 boardno를 가진 DTO객체
	 * @return int - UPDATE 쿼리 수행 결과
	 */
	public int updateLike(Connection conn, Recipe boardno, HttpServletRequest req);

	/**
	 * 지정된 boardno의 게시글 조회하기
	 * 
	 * @param conn - DB연결 객체
	 * @param boardno - 조회할 게시글의 boardno를 가진 DTO객체
	 * @return Recipe - 조회된 게시글의 상세정보 DTO객체
	 */
	public Recipe selectBoardByBoardno(Connection conn, Recipe boardno);

	/**
	 * 게시글 입력
	 * 
	 * @param conn - DB연결 객체
	 * @param board - 삽입될 게시글 내용
	 * @return int - INSERT 쿼리 수행 결과
	 */
	public int insert(Connection conn, Recipe board);

	/**
	 * id를 이용해 nick을 조회한다
	 * 
	 * @param conn - DB연결 객체
	 * @param viewBoard - 조회할 id를 가진 객체
	 * @return String - 작성자 닉네임
	 */
	public String selectNickByUserid(Connection conn, Recipe viewBoard);

	/**
	 * 시퀀스를 이용하여 다음 게시글 번호를 조회한다
	 * 
	 * @param conn - DB연결 객체
	 * @return int - 다음 게시글 번호
	 */
	public int selectBoardno(Connection conn);

	/**
	 * 첨부파일 삽입
	 * 
	 * @param conn - DB연결 객체
	 * @param boardFile - 첨부파일 정보
	 * @return int - INSERT 쿼리 수행 결과
	 */
	public int insertFile(Connection conn, RecipeFile boardFile);

	/**
	 * 첨부파일 정보 조회
	 * 
	 * @param conn - DB연결 객체
	 * @param viewBoard - 조회할 게시글 번호
	 * @return RecipeFile - 첨부파일 정보
	 */
	public RecipeFile selectFile(Connection conn, Recipe viewBoard);

	/**
	 * 게시글 수정 
	 * 
	 * @param board - 수정할 내용을 담은 객체
	 */
	public int update(Connection conn, Recipe board);

	/**
	 * 게시글 삭제
	 * 
	 * @param board - 삭제할 게시글번호를 담은 객체
	 */
	public int delete(Connection conn, Recipe board);
	
	/**
	 * 게시글에 첨부된 파일 기록 삭제
	 * 
	 * @param board - 삭제할 게시글번호를 담은 객체
	 */
	public int deleteFile(Connection conn, Recipe board);

	/**
	 * 팔로우
	 * 
	 * @param conn - DB연결 객체
	 * @param followee - 팔로우당하는 사람
	 * @param follower - 팔로우하는 사람
	 * @return DB 수행 결과
	 */
	public int setFollow(Connection conn, int followee, int follower);

	/**
	 * 팔로우 검사조건 - 이미 팔로우 한적이 있는지 검사(PK 위반 방지)
	 * 
	 * @param conn - DB연결 객체
	 * @param followee - 팔로우당하는 사람
	 * @param follower - 팔로우하는 사람
	 * @return DB 수행 결과
	 */
	public int checkFollowEqual(Connection conn, int followee, int follower);

}
