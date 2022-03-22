package service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Member;
import dto.ProfileFile;
import dto.Recipe;
import dto.RecipeFile;
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
	public ProfileFile viewFile(SocialMember viewBoard);
	
	/**
	 * 게시글 수정
	 * 
	 * @param req - 요청 정보 객체
	 */
	public void update(HttpServletRequest req);

}
