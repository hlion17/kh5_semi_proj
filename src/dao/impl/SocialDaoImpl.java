package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.face.SocialDao;
import dto.ProfileFile;
import dto.SocialMember;
import dto.ProfileFile;
import dto.SocialMember;
import util.Paging;

public class SocialDaoImpl implements SocialDao {

	private PreparedStatement ps = null; //SQL수행 객체
	private ResultSet rs = null; //SQL조회 결과 객체
	
	@Override
	public int selectCntAll(Connection conn) {
		System.out.println("[TEST] SocialMemberDaoImpl - selectCntAll(Connection conn) 호출");
		
		//SQL 작성
		String sql = "";
		sql += "SELECT count(*) cnt FROM Member";
		
		//총 게시글 수
		int count = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				count = rs.getInt("cnt");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		System.out.println("[TEST] SocialMemberDaoImpl - selectCntAll(Connection conn) - count 리턴 : " + count);
		return count;
	}
	
	@Override
	public List<SocialMember> selectAll(Connection conn, Paging paging) {
		System.out.println("[TEST] SocialMemberDaoImpl - selectAll(Connection conn, Paging paging) 호출");
		
		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	SELECT rownum rnum, B.* FROM (";
		sql += " 		SELECT M.*, IMAGE_NO, ORIGIN_NAME, STORED_NAME, FILESIZE";
		sql += "		FROM MEMBER M, PRFIMG P";
		sql += "		WHERE M.MEMBER_NO = P.MEMBER_NO";
		sql += "		ORDER BY M.member_no DESC";
		sql += " 	) B";
		sql += " ) MEMBER";
		sql += " WHERE rnum BETWEEN ? AND ?";
		
		//결과 저장할 List
		List<SocialMember> boardList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());
			
			rs = ps.executeQuery(); //SQL수행 및 결과집합 저장
			
			while( rs.next() ) {
				SocialMember b = new SocialMember(); //결과값 저장 객체
				
				//결과값 한 행 처리
				b.setMemberno( rs.getInt("member_no"));
				b.setMemberid( rs.getString("id"));
				b.setMemberpw( rs.getString("pw"));
				b.setMembername( rs.getString("name"));
				b.setNick( rs.getString("nick"));
				b.setGender( rs.getString("gender"));
				b.setEmail( rs.getString("email"));
				b.setPhone( rs.getString("phone"));
				b.setAddress( rs.getString("address"));
				b.setIntro( rs.getString("intro"));
				b.setMy_ref_code( rs.getInt("my_ref_code"));
				b.setZipcode( rs.getString("zipcode"));
				
				b.setImage_no( rs.getInt("image_no"));
				b.setMember_no( rs.getInt("member_no"));
				b.setOrigin_name( rs.getString("origin_name"));
				b.setStored_name( rs.getString("stored_name"));
				b.setFilesize( rs.getInt("filesize"));
				
				//리스트객체에 조회한 행 객체 저장
				boardList.add(b);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//JDBC객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 조회 결과 반환
		System.out.println("[TEST] SocialMemberDaoImpl - selectAll(Connection conn, Paging paging) - boardList 리턴 : " + boardList);
		return boardList;
	}
	
	@Override
	public ProfileFile selectFile(Connection conn, SocialMember viewBoard) {
		System.out.println("[TEST] SocialMemberDaoImpl -  selectFile(Connection conn, SocialMember viewBoard) 호출");
		
		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM PRFIMG";
		sql += " WHERE member_no = ?";
		
		//결과 저장할 DTO객체
		ProfileFile b = null;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setInt(1, viewBoard.getMemberno());
			
			rs = ps.executeQuery(); //SQL수행 및 결과집합 저장

			while( rs.next() ) {
				b = new ProfileFile(); //결과값 저장 객체
				
				//결과값 행 처리
				b.setFileno( rs.getInt("image_no"));
				b.setMemberno( rs.getInt("member_no"));
				b.setOriginname( rs.getString("origin_name"));
				b.setStoredname( rs.getString("stored_name"));
				b.setFilesize( rs.getInt("filesize"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//JDBC객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 조회 결과 반환
		System.out.println("[TEST] SocialMemberDaoImpl -  selectFile(Connection conn, SocialMember viewBoard) 리턴 boardFile : " + b);
		return b;
	}
	
	@Override
	public SocialMember selectBoardByBoardno(Connection conn, SocialMember boardno) {
		System.out.println("[TEST] SocialMemberDaoImpl - selectBoardByBoardno(Connection conn, SocialMember boardno) 호출");
		
		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM MEMBER";
		sql += " WHERE member_no = ?";
		
		//결과 저장할 DTO객체
		SocialMember b = null;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setInt(1, boardno.getMemberno());
			
			rs = ps.executeQuery(); //SQL수행 및 결과집합 저장

			while( rs.next() ) {
				b = new SocialMember(); //결과값 저장 객체
				
				//결과값 한 행 처리
				b.setMemberno( rs.getInt("member_no"));
				b.setMemberid( rs.getString("id"));
				b.setMemberpw( rs.getString("pw"));
				b.setMembername( rs.getString("name"));
				b.setNick( rs.getString("nick"));
				b.setGender( rs.getString("gender"));
				b.setEmail( rs.getString("email"));
				b.setPhone( rs.getString("phone"));
				b.setAddress( rs.getString("address"));
				b.setIntro( rs.getString("intro"));
				b.setMy_ref_code( rs.getInt("my_ref_code"));
				b.setZipcode( rs.getString("zipcode"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//JDBC객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 조회 결과 반환
		System.out.println("[TEST] SocialMemberDaoImpl - selectBoardByBoardno(Connection conn, SocialMember boardno) - b 리턴 : " + b);
		return b;
	}

}
