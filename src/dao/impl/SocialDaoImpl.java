package dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.SocialDao;
import dto.Follow;
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
		sql += " 		SELECT C.*, DENSE_RANK() OVER (ORDER BY CNT DESC NULLS LAST) DENSE_RANK";
		sql += " 		FROM (";
		sql += " 		select * from member m";
		sql += "		left join (SELECT * FROM (SELECT prfimg.*, ROW_NUMBER() OVER(PARTITION BY member_no ORDER BY image_no DESC) as a FROM prfimg) WHERE a = 1) x";
		sql += "		on m.member_no = x.member_no";
		sql += "		LEFT OUTER JOIN (SELECT FOLLOWEE, COUNT(*) cnt FROM FOLLOW GROUP BY FOLLOWEE) F";
		sql += "		ON (M.MEMBER_NO = f.followee)";
		sql += " 	) C";
		sql += " 	) B";
		sql += " ) Member";
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
				
				b.setFollowCnt( rs.getInt("cnt"));
				b.setDense_rank( rs.getInt("dense_rank"));
//				b.setFollowee( rs.getInt("followee"));
//				b.setFollower( rs.getInt("follower"));
				
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
	public List<Follow> selectAllFollow(Connection conn, Paging paging, HttpServletRequest req) {
		System.out.println("[TEST] SocialMemberDaoImpl - selectAllFollow() 호출");
		
		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	SELECT rownum rnum, B.* FROM (";
		
		sql += " 		select * from follow";
		sql += "		WHERE follower = ?";
		
		sql += " 	) B";
		sql += " ) MEMBER";
		sql += " WHERE rnum BETWEEN ? AND ?";
		
		//결과 저장할 List
		List<Follow> boardList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setInt(1, Integer.parseInt(req.getSession().getAttribute("memberno").toString()) );
			ps.setInt(2, paging.getStartNo());
			ps.setInt(3, paging.getEndNo());
			
			rs = ps.executeQuery(); //SQL수행 및 결과집합 저장
			
			while( rs.next() ) {
				Follow b = new Follow(); //결과값 저장 객체
				
				//결과값 한 행 처리
				b.setFollowee( rs.getInt("followee"));
				b.setFollower( rs.getInt("follower"));
				
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
	public List<Follow> selectAllFollower(Connection conn, Paging paging, HttpServletRequest req) {
		System.out.println("[TEST] SocialMemberDaoImpl - selectAllFollow() 호출");
		
		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	SELECT rownum rnum, B.* FROM (";
		
		sql += " 		select * from follow";
		sql += "		WHERE followee = ?";
		
		sql += " 	) B";
		sql += " ) MEMBER";
		sql += " WHERE rnum BETWEEN ? AND ?";
		
		//결과 저장할 List
		List<Follow> boardList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			System.out.println("memberno : " + Integer.parseInt(req.getSession().getAttribute("memberno").toString()));
			
			ps.setInt(1, Integer.parseInt(req.getSession().getAttribute("memberno").toString()) );
			ps.setInt(2, paging.getStartNo());
			ps.setInt(3, paging.getEndNo());
			
			rs = ps.executeQuery(); //SQL수행 및 결과집합 저장
			
			while( rs.next() ) {
				Follow b = new Follow(); //결과값 저장 객체
				
				//결과값 한 행 처리
				b.setFollowee( rs.getInt("followee"));
				b.setFollower( rs.getInt("follower"));
				
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
	public SocialMember selectFile(Connection conn, SocialMember viewBoard) {
		System.out.println("[TEST] SocialMemberDaoImpl -  selectFile(Connection conn, SocialMember viewBoard) 호출");
		
		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM PRFIMG";
		sql += " WHERE member_no = ?";
		
		//결과 저장할 DTO객체
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setInt(1, viewBoard.getMemberno());
			
			rs = ps.executeQuery(); //SQL수행 및 결과집합 저장

			while( rs.next() ) {
				
				//결과값 행 처리
				viewBoard.setImage_no( rs.getInt("image_no"));
				viewBoard.setMemberno( rs.getInt("member_no"));
				viewBoard.setOrigin_name( rs.getString("origin_name"));
				viewBoard.setStored_name( rs.getString("stored_name"));
				viewBoard.setFilesize( rs.getInt("filesize"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//JDBC객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 조회 결과 반환
		System.out.println("[TEST] SocialMemberDaoImpl -  selectFile(Connection conn, SocialMember viewBoard) 리턴 boardFile : " + viewBoard);
		return viewBoard;
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

	@Override
	public int update(Connection conn, SocialMember board) {
		System.out.println("[TEST] SocialMemberDaoImpl -  update(Connection conn, Recipe board) 호출");
		
		String sql = "";
		sql += "UPDATE member";
		sql += " SET title = ?,";
		sql += " 	content = ?";
		sql += " WHERE board_no = ?";
		
		int res = -1;
		
		try {
			ps = conn.prepareStatement(sql);
//			ps.setString(1, board.getTitle());
//			ps.setString(2, board.getContent());
			ps.setInt(3, board.getMemberno());

			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCTemplate.close(ps);
		}
		
		System.out.println("[TEST] SocialMemberDaoImpl -  update(Connection conn, Recipe board) 리턴 res" + res);
		return res;
	}

	@Override
	public int insertFile(Connection conn, SocialMember board) {
		System.out.println("[TEST] SocialMemberDaoImpl -  insertFile(Connection conn, RecipeFile boardFile) 호출");
		
		String sql = "";
		sql += "INSERT INTO PRFIMG( IMAGE_NO, MEMBER_NO, ORIGIN_NAME, STORED_NAME, FILESIZE )";
		sql += " VALUES (PRFIMG_seq.nextval, ?, ?, ?, ?)";
		
		int res = 0;
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, board.getMemberno());
			ps.setString(2, board.getOrigin_name());
			ps.setString(3, board.getStored_name());
			ps.setInt(4, board.getFilesize());

			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		System.out.println("[TEST] SocialMemberDaoImpl -  insertFile(Connection conn, RecipeFile boardFile) 리턴 res : " + res);
		return res;
	}

}
