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
		// 회원가입 처리
		int result = memberDao.insert(conn, member);
		
		// 회원 ID를 얻어와 냉장고와 매핑 테이블에 insert 할 정보를 얻어온다.
		String memberId = member.getMemberid();
		Member foundMember = memberDao.selectrefCodeAndMemberNo(conn, memberId);
		
		// 냉장고, 냉장고_매핑 테이블에 회원정보 insert
		int result2 = refDao.insertRef(conn, foundMember);
		int result3 = refDao.insertRef_Member(conn, foundMember);
		
		// '회원가입, 냉장고 생성, 냉장고_회원 매핑 테이블에 관련 정보 삽입'이 하나의 트랜잭션으로 관리되도록 처리
		if(result == 1 && result2 == 1 && result3 == 1) {
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
	public Member updateMember(Member member) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		// 일치하는 정보가 있음
		if (memberDao.updateInfo(conn, member) > 0) {
			JDBCTemplate.commit(conn);
			return member;
		} else {
			JDBCTemplate.rollback(conn);
			return null;
		}
	}
	
	@Override
	public Member getUpdateInfoMember(HttpServletRequest req) {
		
		
		Member member = new Member();

		HttpSession session = req.getSession();
		
		
		member.setMemberpw(req.getParameter("memberpw"));
		member.setMembername(req.getParameter("membername"));
		member.setNick(req.getParameter("nick"));
		member.setEmail(req.getParameter("email"));
		member.setGender(req.getParameter("gender"));
		member.setPhone(req.getParameter("phone"));
		member.setZipcode(req.getParameter("zipcode"));
		member.setAddress(req.getParameter("address"));
		member.setIntro(req.getParameter("intro"));
		member.setMemberid((String)session.getAttribute("memberid"));
		
		
		return member;
		
	}


	
	public Member getMemberInfoBySession(HttpServletRequest req) {

		System.out.println("getMemberInfoSession 메소드 진입");
		Member member = new Member();

		HttpSession session = req.getSession();

		member.setMemberid((String) session.getAttribute("memberid"));
		member.setMemberpw((String) session.getAttribute("memberpw"));
		member.setMembername((String) session.getAttribute("membername"));
		member.setNick((String) session.getAttribute("nick"));
		member.setEmail((String) session.getAttribute("email"));
		member.setGender((String) session.getAttribute("gender"));
		member.setPhone((String) session.getAttribute("phone"));
		member.setZipcode((String) session.getAttribute("zipcode"));
		member.setAddress((String) session.getAttribute("address"));
		member.setIntro((String) session.getAttribute("intro"));
		
		System.out.println("getMemberInfoSession 메소드 리턴값" + member);
		return member;
		
	}
	
		
	
	
	
	
	
}
