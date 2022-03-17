package service.impl;

import java.io.UnsupportedEncodingException;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import common.JDBCTemplate;
import dao.face.MemberDao;
import dao.face.RefDao;
import dao.impl.MemberDaoImpl;
import dao.impl.RefDaoImpl;
import dto.Member;
import service.face.MemberService;

public class MemberServiceImpl implements MemberService {

	private MemberDao memberDao = new MemberDaoImpl();
	private RefDao refDao = new RefDaoImpl();
	
	@Override
	public Member getLoginMember(HttpServletRequest req) {
		
		Member member = new Member();
		
		member.setMemberid( req.getParameter("memberid") );
		member.setMemberpw( req.getParameter("memberpw") );
		
		
		return member;
	}
	
	@Override
	public boolean login(Member member) {
		
		System.out.println("로그인서비스임플 " + member);
		//로그인 인증 성공
		if( memberDao.selectCntMemberByMemberidMemberpw(JDBCTemplate.getConnection(), member) > 0 ) {
			return true;
		}
		
		//로그인 인증 실패
		return false;
	}

	@Override
	public Member info(Member member) {
		
		return memberDao.selectMemberByMemberid(JDBCTemplate.getConnection(), member);
		
	}
	
	@Override
	public Member getJoinMember(HttpServletRequest req) {
		
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		Member member = new Member();
		
		member.setMemberid( req.getParameter("memberid") );
		member.setMemberpw( req.getParameter("memberpw") );
		member.setMembername( req.getParameter("membername") );
		member.setNick( req.getParameter("nick") );
		member.setGender(req.getParameter("gender"));
		member.setEmail(req.getParameter("email"));
		member.setPhone(req.getParameter("phone"));
		member.setZipcode(req.getParameter("zipcode"));
		member.setAddress(req.getParameter("address"));
		member.setIntro(req.getParameter("intro"));
		
		return member;
	}
	
	@Override
	public Member join(Member member) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		// edited by young
		int result = memberDao.insert(conn, member);
		
		String memberId = member.getMemberid();
		
		Member foundMember = memberDao.selectrefCodeAndMemberNo(conn, memberId);
		
		int refCode = foundMember.getMy_ref_code();
		
		int memberNo = foundMember.getMemberno();
		
		int result2 = refDao.insertRef(conn, refCode, memberNo);
		
		if( result > 0 && result2 > 0) {
			JDBCTemplate.commit(conn);
			return member;
		} else {
			JDBCTemplate.rollback(conn);
			return null;
		}
		
	}

	@Override
	public boolean checkIdDup(Member member) {
		//아이디 중복이라 사용 불가능
		if( memberDao.idCheck(JDBCTemplate.getConnection(), member) > 0 ) {
//			System.out.println("checkIdDup에서 false");
			return false;
		}
		
		//아이디 중복이 아니라 사용 가능!
//		System.out.println("checkIdDup에서 true");
		return true;
	}

	@Override
	public Member getIdMember(HttpServletRequest req) {
		Member member = new Member();
		
		member.setMemberid( req.getParameter("memberid") );
		
		
		return member;
	}

	@Override
	public String checkEmailPhone(Member member) {
		
		String Findedid = null;
		// 일치하는 정보가 있음(아이디 찾기 가능)
		if (memberDao.idFind(JDBCTemplate.getConnection(), member) != null) {
			Findedid = memberDao.idFind(JDBCTemplate.getConnection(), member);
		} else {
			System.out.println("Service에서 실패함");
		}
		return Findedid;

	}
	
	@Override
	public Member getIdFindMember(HttpServletRequest req) {
		Member member = new Member();
		
		member.setEmail( req.getParameter("email") );
		member.setPhone( req.getParameter("phone") );
		
		
		return member;
	}

	@Override
	public String checkIdEmailPhone(Member member) {
		String Findedpw = null;
		// 일치하는 정보가 있음(비밀번호 찾기 가능)
		if (memberDao.pwFind(JDBCTemplate.getConnection(), member) != null) {
			Findedpw = memberDao.pwFind(JDBCTemplate.getConnection(), member);
		} else {
			System.out.println("Service에서 실패함");
		}
		return Findedpw;
	}

	@Override
	public Member getPwFindMember(HttpServletRequest req) {
		Member member = new Member();

		member.setMemberid(req.getParameter("memberid"));
		member.setEmail(req.getParameter("email"));
		member.setPhone(req.getParameter("phone"));

		return member;
	}

	@Override
	public List<Member> getInfoList(HttpServletRequest req) {

		Member member = new Member();
		
		HttpSession session = req.getSession();
		member.setMemberid((String)session.getAttribute("memberid")); //session은 Object타입이라 타입변환을 꼭 정말 꼭 해줘야함
		
		//회원정보 전체 조회 결과 반환
		return memberDao.selectMemberInfoAll(JDBCTemplate.getConnection(), member);
	}

	
	
	
	
	
}
