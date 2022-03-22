package service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Follow;
import dto.SocialMember;
import util.Paging;

public interface SocialService {

	/**
	 * 요청 파라미터 얻어오기
	 * 
	 * @param req - 요청 정보 객체
	 * @return Member - 전달파라미터 memberno값을 포함한 DTO객체
	 */
	public SocialMember getProfileno(HttpServletRequest req);
	
	/**
	 * 요청 파라미터 얻어오기
	 * 
	 * @param req - 요청 정보 객체
	 * @return Member - 전달파라미터 memberno값을 포함한 DTO객체
	 */
	public SocialMember getMemberno(HttpServletRequest req);
	
	/**
	 * 페이징 객체 생성
	 * 
	 * @param req - 요청 정보 객체
	 * @return Paging - 페이징 계산이 완료된 Paging객체
	 */
	public Paging getPaging(HttpServletRequest req);
	
	/**
	 * 게시글 페이징 목록 조회
	 * 
	 * @param paging - 페이징 정보 객체
	 * @return List<Recipe> - 페이징이 반영된 게시글 조회 결과 목록
	 */
	public List<SocialMember> getList(Paging paging);

	public List<Follow> getListFollow(Paging paging, HttpServletRequest req);

	public List<Follow> getListFollower(Paging paging, HttpServletRequest req);
	
	/**
	 * 전달된 memberno를 이용하여 게시글을 조회한다
	 * 
	 * 조회된 게시글의 조회수를 1 증가시킨다
	 * 
	 * @param memberno - 조회할 memberno를 가지고 있는 DTO객체
	 * @return SocialMember - 조회된 게시글 정보
	 */
	public SocialMember view(SocialMember memberno);
	
	/**
	 * 첨부파일 정보 조회하기
	 * 
	 * @param viewBoard - 첨부파일과 연결된 게시글의 번호
	 * @return RecipeFile - 첨부파일 정보 DTO객체
	 */
	public SocialMember viewFile(SocialMember viewBoard);
	
	/**
	 * 게시글 수정
	 * 
	 * @param req - 요청 정보 객체
	 */
	public void update(HttpServletRequest req);

	
	/**
	 * 팔로우
	 * 
	 * @param followee - 팔로우당하는 사람, 해당 글작성자 memberno
	 * @param follower - 팔로우하는 사람, 현재 로그인세션 memberno
	 */
	public void setFollow(int followee, int follower, HttpServletRequest req);
	

	/**
	 * 팔로우 검사조건 - 이미 팔로우 한적이 있는지 검사(PK 위반 방지)
	 * @param followee - 팔로우당하는 사람, 해당 글작성자 memberno
	 * @param follower - 팔로우하는 사람, 현재 로그인세션 memberno
	 */
	public int checkFollowPK(int followee, int follower);
	

}
