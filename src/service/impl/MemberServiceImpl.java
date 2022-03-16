package service.impl;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.MemberDao;
import dao.face.RefDao;
import dao.impl.MemberDaoImpl;
import dao.impl.RefDaoImpl;
import dto.Member;
import service.face.MemberService;
import service.face.RefService;

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
			return false;
		}
		
		//아이디 중복이 아니라 사용 가능!
		return true;
	}
	
	
	
}
