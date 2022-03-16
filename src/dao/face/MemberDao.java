package dao.face;

import java.sql.Connection;

import dto.Member;


public interface MemberDao {

	/**
	 * memberid와 memberpw가 일치하는 회원의 수를 조회한다
	 * 	-> 로그인인증에 사용한다
	 * 
	 * @param conn - DB연결 객체
	 * @param member - 조회할 회원의 정보
	 * @return int - 0:존재하지 않는 회원, 1:존재하는 회원
	 */
	public int selectCntMemberByMemberidMemberpw(Connection conn, Member member);

	/**
	 * userid를 이용해 회원정보 조회하기
	 * 
	 * @param conn - DB연결 객체
	 * @param member - 조회할 userid를 가진 객체
	 * @return Member - 조회된 회원 정보
	 */
	public Member selectMemberByMemberid(Connection conn, Member member);

	/**
	 * 회원정보 삽입하기
	 * 
	 * @param conn - DB연결 객체
	 * @param member - 회원가입 정보 객체
	 * @return int - INSERT 수행 결과
	 */
	public int insert(Connection conn, Member member);

	// write by young
	/**
	 * 회원아이디로 회원번호를 조회하는 메서드
	 * @param conn - DB 연결 객체
	 * @param memberId - 회원아이디
	 * @return memberNo - 회원번호
	 */
	public int findMemberNoById(Connection conn, String memberId);

	// write by young
	/**
	 * 냉장고코드와 회원번호 가져오는 메서드
	 * @param conn
	 * @param memberId
	 * @return - 내장고 코드, 회원번호 담긴 회원 DTO
	 */
	public Member selectrefCodeAndMemberNo(Connection conn, String memberId);
	

	
	
	/**
	 * 아이디 중복 검사
	 * 
	 * @param conn - DB연결 객체
	 * @param member - 중복되는 회원 id 정보 객체
	 * @return int - 0 : 중복되지 않음, 1 : 중복됨
	 */
	public int idCheck(Connection conn, Member member);
	

	/**
	 * 아이디 찾기
	 * 
	 * @param conn - DB연결 객체
	 * @param member - 입력한 정보와 일치하는 회원 정보 객체
	 * @return 일치하는 회원의 id
	 */
	public String idFind(Connection connection, Member member);
}
