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
		sql += "SELECT id, pw, nick";
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
				
				result.setMemberid( rs.getString("userid") );
				result.setMemberpw( rs.getString("userpw") );
				result.setNick( rs.getString("usernick") );
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
	public int selectNextMemberno(Connection conn) {
		String sql = "";
		sql += "SELECT member_seq.nextval AS next FROM dual";
		
		int next = 0; //결과를 저장할 변수
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			rs.next();
			
			next = rs.getInt("next");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return next;
	}

	@Override
	public int insert(Connection conn, Member member) {
		
		String sql = "";
		sql += "INSERT INTO member ( memberno, id, pw, name, nick, gender, email, phone, address, intro )";
		sql += " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, member.getMemberno());
			ps.setString(2, member.getMemberid());
			ps.setString(3, member.getMemberpw());
			ps.setString(4, member.getMembername());
			ps.setString(5, member.getNick());
			ps.setString(6, member.getGender());
			ps.setString(7, member.getEmail());
			ps.setString(8, member.getPhone());
			ps.setString(9, member.getAddress());
			ps.setString(10, member.getIntro());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}

}
