package service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Member;

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
	 * 회원정보 전체 조회
	 * 
	 * @return List<Member> - 회원 정보 조회 결과 목록
	 */
	public List<Member> getInfoList(HttpServletRequest req);

	
}
