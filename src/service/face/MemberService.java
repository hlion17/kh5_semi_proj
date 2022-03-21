package service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Member;
import dto.ProfileFile;
import dto.RankMember;
import dto.Recipe;
import util.Paging;

public interface MemberService {

	/**
	 * 로그인 정보 추출하기
	 * 
	 * @param req - 요청 정보 객체
	 * @return Member - 로그인 정보
	 */
	public Member getLoginMember(HttpServletRequest req);

	/**
	 * 로그인 인증 처리
	 * 
	 * @param member - 로그인 정보
	 * @return boolean - true:인증 성공함, false:인증 실패함
	 */
	public boolean login(Member member);

	/**
	 * 유저 정보 가져오기
	 * 
	 * @param member - 조회할 회원 아이디를 가진 객체 
	 * @return Member - 조회된 회원 정보
	 */
	public Member info(Member member);

	/**
	 * 회원가입 정보 추출하기
	 * 
	 * @param req - 요청 정보 객체
	 * @return Member - 전달파라미터를 추출하여 저장한 DTO객체
	 */
	public Member getJoinMember(HttpServletRequest req);
	
	/**
	 * 회원가입 처리
	 * 
	 * @param member - 회원가입 정보 객체
	 * @return DB에 삽입된 회원 정보 객체
	 */
	public Member join(Member member);

	/**
	 * 아이디 중복 처리
	 * 
	 * @param member - 아이디 중복 정보
	 * @return boolean - true:중복이 아니라 사용 가능, false:중복이라 사용 불가능
	 */
	public boolean checkIdDup(Member member);
	
	/**
	 * 아이디 중복처리를 위한 아이디 정보 추출하기
	 * 
	 * @param req - 요청 정보 객체
	 * @return Member - 입력한 아이디를 가진 회원의 정보
	 */
	public Member getIdMember(HttpServletRequest req);

	/**
	 * 아이디 찾기 처리
	 * 
	 * @param member - 아이디 찾기에 입력한 회원과 일치하는 정보
	 * @return String - 일치하는 회원의 id
	 */
	public String checkEmailPhone(Member member);
	
	/**
	 * 아이디 찾기를 위한 회원 정보 추출하기
	 * 
	 * @param req - 요청 정보 객체
	 * @return Member - 입력한 이메일과 전화번호와 일치하는 회원의 정보
	 */
	public Member getIdFindMember(HttpServletRequest req);

	/**
	 * 비밀번호 찾기 처리
	 * 
	 * @param member - 비밀번호 찾기에 입력한 회원과 일치하는 정보
	 * @return String - 일치하는 회원의 pw
	 */
	public String checkIdEmailPhone(Member member);
	
	/**
	 * 비밀번호 찾기를 위한 회원 정보 추출하기
	 * 
	 * @param req - 요청 정보 객체
	 * @return Member - 입력한 이메일과 전화번호와 일치하는 회원의 정보
	 */
	public Member getPwFindMember(HttpServletRequest req);

	/**
	 * 회원정보 수정 처리
	 * 
	 * @param member - 회원정보 수정에 접속한 회원과 일치하는 정보
	 * @return Member - 수정한 회원의 정보
	 */
	public Member updateMember(Member member);
	
	/**
	 * 회원 정보 수정을 위한 회원 정보 추출하기
	 * 
	 * @param req - 요청 정보 객체
	 * @return Member - 회원정보 수정을 요청하는 회원의 정보
	 */
	public Member getUpdateInfoMember(HttpServletRequest req);

	/**
	 * 세션을 저장되어있는 회원 정보 추출하기
	 * 
	 * @param req - 요청 정보 객체
	 * @return Member - 회원정보 수정을 요청하는 회원의 정보
	 */
	public Member getMemberInfoBySession(HttpServletRequest req);
	
	/**
	 * 게시글 페이징 목록 조회
	 * 
	 * @param paging - 페이징 정보 객체
	 * @return List<Member> - 페이징이 반영된 게시글 조회 결과 목록
	 */
	public List<Member> getList(Paging paging);
	
	/**
	 * 게시글 페이징 목록 조회
	 * 
	 * @param paging - 페이징 정보 객체
	 * @return List<Member> - 페이징이 반영된 게시글 조회 결과 목록
	 */
	public List<RankMember> getListRank(Paging paging);
	
	/**
	 * 프로필 작성
	 * 	지정한 프로필 사진을 DB에 저장
	 * 
	 * @param req - 요청정보 객체(첨부파일)
	 * 
	 */
	public void uploadProfil(HttpServletRequest req);
	
	/**
	 * 세션을 저장되어있는 회원 넘버 추출하기
	 * 
	 * @param req - 요청 정보 객체
	 * @return Member - 프로필 사진 보려는 회원
	 */
	public Member getMembernoBySession(HttpServletRequest req);
	
	/**
	 * 프로필사진 조회하려는 회원넘버로 프로필사진넘버 조회하기
	 * 
	 * @param memberno - 프로필사진 조회하려는 회원넘버
	 * @return ProfileFile - 프로필사진넘버
	 */
	public ProfileFile view(Member memberno);
	
	/**
	 * 회원탈퇴 처리
	 * 
	 * @param member - 회원탈퇴 정보
	 * @return boolean - true:회원탈퇴완료, false:회원탈퇴 실패
	 */
	public boolean signout(HttpServletRequest req);
	
	/**
	 * 페이징 객체 생성
	 * 
	 * @param req - 요청 정보 객체
	 * @return Paging - 페이징 계산이 완료된 Paging객체
	 */
	public Paging getPaging(HttpServletRequest req);
}
