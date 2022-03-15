package service.impl;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.MemberDao;
import dao.impl.MemberDaoImpl;
import dto.Member;
import service.face.MemberService;

public class MemberServiceImpl implements MemberService {

private MemberDao memberDao = new MemberDaoImpl();
	
	@Override
	public Member getLoginMember(HttpServletRequest req) {
		
		Member member = new Member();
		
		member.setMemberid( req.getParameter("memberid") );
		member.setMemberpw( req.getParameter("memberpw") );
		
		
		return member;
	}
	
	@Override
	public boolean login(Member member) {
		
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
		Connection conn = JDBCTemplate.getConnection();
		
		// member_seq의 nextval을 조회한다
		int next = memberDao.selectNextMemberno(conn);
		
		// 시퀀스의 nextval값을 member객체에 저장한다
		member.setMemberno(next);
		
		member.setMembername( req.getParameter("membername") );
		member.setNick( req.getParameter("nick") );
		member.setGender(req.getParameter("gender"));
		member.setEmail(req.getParameter("email"));
		member.setPhone(req.getParameter("phone"));
		member.setAddress(req.getParameter("address"));
		member.setIntro(req.getParameter("intro"));
		
		return member;
	}
	
	@Override
	public void join(Member member) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		if( memberDao.insert(conn, member) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
	}
}
