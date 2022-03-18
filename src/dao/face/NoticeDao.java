package dao.face;
import java.sql.Connection;
import java.util.List;

import dto.Notice;
import dto.NoticeFile;
import util.Paging;

public interface NoticeDao {

	/**
	 * Notice테이블 전체 조회
	 * 
	 * @param conn - DB연결 객체
	 * @return List<Notice> - Notice테이블 전체 조회 결과 목록
	 */
	public List<Notice> selectAll(Connection conn);

	/**
	 * Notice테이블 전체 조회 -> 페이징 처리 추가
	 * 
	 * @param conn   - DB연결 객체
	 * @param paging - 페이징 정보 객체
	 * @return List<Notice> - Notice테이블 전체 조회 결과 목록
	 */
	public List<Notice> selectAll(Connection conn, Paging paging);

	/**
	 * 총 게시글 수 조회
	 * 
	 * @param conn - DB연결 객체
	 * @return int - Notice테이블의 전체 행 수
	 */
	public int selectCntAll(Connection conn);

	/**
	 * 조회된 게시글의 조회수 증가시키기
	 * 
	 * @param conn    - DB연결 객체
	 * @param boardno - 조회할 게시글의 Noticeno를 가진 DTO객체
	 * @return int - UPDATE 쿼리 수행 결과
	 */
	public int updateHit(Connection conn, Notice boardno);

	/**
	 * 지정된 Noticeno의 게시글 조회하기
	 * 
	 * @param conn    - DB연결 객체
	 * @param boardno - 조회할 게시글의 Noticeno를 가진 DTO객체
	 * @return Notice - 조회된 게시글의 상세정보 DTO객체
	 */
	public Notice selectBoardByBoardno(Connection conn, Notice boardno);

	/**
	 * 게시글 입력
	 * 
	 * @param conn  - DB연결 객체
	 * @param notice - 삽입될 게시글 내용
	 * @return int - INSERT 쿼리 수행 결과
	 */
	public int insert(Connection conn, Notice notice);

	/**
	 * id를 이용해 nick을 조회한다
	 * 
	 * @param conn      - DB연결 객체
	 * @param viewNotice - 조회할 id를 가진 객체
	 * @return String - 작성자 닉네임
	 */
	public String selectNickByMemberid(Connection conn, Notice viewBoard);

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
	 * @param conn      - DB연결 객체
	 * @param NoticeFile - 첨부파일 정보
	 * @return int - INSERT 쿼리 수행 결과
	 */
	public int insertFile(Connection conn, NoticeFile noticeFile);

	/**
	 * 첨부파일 정보 조회
	 * 
	 * @param conn      - DB연결 객체
	 * @param viewNotice - 조회할 게시글 번호
	 * @return noticeFile - 첨부파일 정보
	 */
	public NoticeFile selectFile(Connection conn, Notice viewBoard);

	/**
	 * 게시글 수정
	 * 
	 * @param notice - 수정할 내용을 담은 객체
	 */
	public int update(Connection conn, Notice notice);

	/**
	 * 게시글 삭제
	 * 
	 * @param notice - 삭제할 게시글번호를 담은 객체
	 */
	public int delete(Connection conn, Notice notice);

	/**
	 * 게시글에 첨부된 파일 기록 삭제
	 * 
	 * @param notice - 삭제할 게시글번호를 담은 객체
	 */
	public int deleteFile(Connection conn, Notice notice);

}























