package dao.impl;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import common.JDBCTemplate;
import dao.face.MemberDao;
import dto.Member;

public class MemberDaoImpl implements MemberDao {
	private PreparedStatement ps = null; //SQL수행 객체
	private ResultSet rs = null; //SQL조회 결과 객체
	
	@Override
	public int selectCntMemberByMemberidMemberpw(Connection conn, Member member) {
		
		String sql = "";
		sql += "SELECT count(*) FROM member";
		sql += " WHERE id = ?";
		sql += "	AND pw = ?";
		
		int cnt = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, member.getMemberid());
			ps.setString(2, member.getMemberpw());
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				cnt = rs.getInt(1); //1 : 존재하는 회원
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return cnt;
	}
	
	@Override
	public Member selectMemberByMemberid(Connection conn, Member member) {

		String sql = "";
		sql += "SELECT member_no, id, pw, name, nick, gender, email, phone, zipcode, address, intro, my_ref_code";
		sql += " FROM member";
		sql += " WHERE id = ?";
		
		//조회 결과를 저장할 DTO
		Member result = null;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, member.getMemberid());
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				result = new Member();
				
				result.setMemberno( rs.getInt("member_no") );
				result.setMemberid( rs.getString("id") );
				result.setMemberpw( rs.getString("pw") );
				result.setMembername( rs.getString("name") );
				result.setNick( rs.getString("nick") );
				result.setGender( rs.getString("gender") );
				result.setEmail( rs.getString("email") );
				result.setPhone( rs.getString("phone") );
				result.setZipcode( rs.getString("zipcode") );
				result.setAddress( rs.getString("address") );
				result.setIntro( rs.getString("intro") );
				result.setMy_ref_code(rs.getInt("my_ref_code"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return result;

	}
	
	
	

	@Override
	public int insert(Connection conn, Member member) {
		
		String sql = "";
		sql += "INSERT INTO member ( member_no, id, pw, name, nick, gender, email, phone, zipcode, address, intro, my_ref_code )";
		sql += " VALUES ( member_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, member.getMemberid());
			ps.setString(2, member.getMemberpw());
			ps.setString(3, member.getMembername());
			ps.setString(4, member.getNick());
			ps.setString(5, member.getGender());
			ps.setString(6, member.getEmail());
			ps.setString(7, member.getPhone());
			ps.setString(8, member.getZipcode());
			ps.setString(9, member.getAddress());
			ps.setString(10, member.getIntro());
			// write by young
			int hashCode = Math.abs(member.getMemberid().hashCode());
			ps.setInt(11, hashCode);
			// write by young
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}
	
	@Override
	public int idCheck(Connection conn, Member member) {
		
		String sql = "";
		sql += "SELECT count(*) FROM member";
		sql += " WHERE id = ?";
		
		int idCheck = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, member.getMemberid());
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				idCheck = rs.getInt(1); //1 : 존재하는 회원
//				System.out.println("Dao - 존재하는 회원");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return idCheck;
	}

	@Override
	public int findMemberNoById(Connection conn, String memberId) {
		int memberNo = -1;
		String sql = "";
		sql = "SELECT member_no FROM member WHERE id = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, memberId);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				memberNo = rs.getInt("member_no");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}
		
		return memberNo;
	}

	// write by young
	@Override
	public Member selectrefCodeAndMemberNo(Connection conn, String memberId) {
		Member member = new Member();
		String sql = "";
		sql = "SELECT member_no, my_ref_code, nick FROM member WHERE id = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, memberId);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				member.setMemberno(rs.getInt("member_no"));
				member.setMy_ref_code(rs.getInt("my_ref_code"));
				member.setNick(rs.getString("nick"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}
		
		return member;
	}
	// write by young

	@Override
	public String idFind(Connection conn, Member member) {
		
		String sql = "";
		sql += "SELECT id FROM member";
		sql += " WHERE email = ?";
		sql += "	AND phone = ?";
		
		String mid = null;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, member.getEmail());
			ps.setString(2, member.getPhone());
			
			rs = ps.executeQuery();
			
			if( rs.next() ) {
				mid = rs.getString("id"); //찾은 id 반환
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return mid;
	}

	@Override
	public String pwFind(Connection conn, Member member) {
		String sql = "";
		sql += "SELECT pw FROM member";
		sql += " WHERE id = ?";
		sql += "	AND email = ?";
		sql += "	AND phone = ?";
		
		String mid = null;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, member.getMemberid());
			ps.setString(2, member.getEmail());
			ps.setString(3, member.getPhone());
			
			rs = ps.executeQuery();
			
			if( rs.next() ) {
				mid = rs.getString("pw"); //찾은 비밀번호 반환
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return mid;
	}

	@Override
	public int updateInfo(Connection conn, Member member) {

		String sql = "";
		sql += "UPDATE member SET pw = ?, name = ?, nick = ?, email = ?, phone = ?";
		sql += "		, zipcode = ?, address = ?, intro = ?";
		sql += " WHERE id = ?";

		int res = 0;

		try {

			ps = conn.prepareStatement(sql);

			ps.setString(1, member.getMemberpw());
			ps.setString(2, member.getMembername());
			ps.setString(3, member.getNick());
			ps.setString(4, member.getEmail());
			ps.setString(5, member.getPhone());
			ps.setString(6, member.getZipcode());
			ps.setString(7, member.getAddress());
			ps.setString(8, member.getIntro());
			ps.setString(9, member.getMemberid());

			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return res;
	}

	@Override
	public Member findeByRefCode(Connection conn, int refCode) {
		Member member = null;
		String sql = "";
		sql = "SELECT "
				+ "member_no"
				+ ", id"
				+ ", pw"
				+ ", name"
				+ ", nick"
				+ ", gender"
				+ ", email"
				+ ", phone"
				+ ", address"
				+ ", intro"
				+ ", my_ref_code"
				+ ", zipcode "
				+ "FROM member "
				+ "WHERE my_ref_code = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, refCode);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				member = new Member();
				
				member.setMemberno( rs.getInt("member_no") );
				member.setMemberid( rs.getString("id") );
				member.setMemberpw( rs.getString("pw") );
				member.setMembername( rs.getString("name") );
				member.setNick( rs.getString("nick") );
				member.setGender( rs.getString("gender") );
				member.setEmail( rs.getString("email") );
				member.setPhone( rs.getString("phone") );
				member.setZipcode( rs.getString("zipcode") );
				member.setAddress( rs.getString("address") );
				member.setIntro( rs.getString("intro") );
				member.setMy_ref_code(rs.getInt("my_ref_code"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return member;
	}
	
}
