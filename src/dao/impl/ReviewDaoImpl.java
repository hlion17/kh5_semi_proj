package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.face.ReviewDao;
import dto.Review;
import dto.ReviewFile;
import util.Paging;

public class ReviewDaoImpl implements ReviewDao {
	
	private PreparedStatement ps = null; //SQL수행 객체
	private ResultSet rs = null; //SQL조회 결과 객체
	
	@Override
	public List<Review> selectAll(Connection conn) {
		
		//SQL 작성
		String sql = "";
		sql += "SELECT";
		sql += "	review_no";
		sql += "	, pro_no";
		sql += "	, member_no";
		sql += "	, title";
		sql += "	, regdate";
		sql += "	, hit";
		sql += " FROM Review";
		sql += " ORDER BY review_no DESC";
		
		//결과 저장할 List
		List<Review> reviewList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			rs = ps.executeQuery(); //SQL수행 및 결과집합 저장

			while( rs.next() ) {
				Review review = new Review(); //결과값 저장 객체
				
				//결과값 한 행 처리
				review.setReview_no(rs.getInt("review_no"));
				review.setPro_no(rs.getInt("pro_no"));
				review.setMember_no(rs.getInt("member_no"));
				review.setTitle(rs.getNString("title"));
				review.setRegdate(rs.getDate("regdate"));
				review.setHit(rs.getInt("hit"));
				
				//리스트객체에 조회한 행 객체 저장
				reviewList.add(review);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//JDBC객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 조회 결과 반환
		return reviewList;
	}
	
	
	@Override
	public List<Review> selectAll(Connection conn, Paging paging) {	
		//SQL 작성
			String sql = "";
			sql += "SELECT * FROM (";
			sql += "	SELECT rownum rnum, R.* FROM (";
			sql += " 		SELECT";
			sql += "			review_no, pro_no, member_no, title";
			sql += "			,regdate, hit";
			sql += "		FROM Review";
			sql += "		ORDER BY review_no DESC";
			sql += " 	) R";
			sql += " ) REVIEW";
			sql += " WHERE rnum BETWEEN ? AND ?";
			
			//결과 저장할 List
			List<Review> reviewList = new ArrayList<>();
			
			try {
				ps = conn.prepareStatement(sql); //SQL수행 객체
				
				ps.setInt(1, paging.getStartNo());
				ps.setInt(2, paging.getEndNo());
				
				rs = ps.executeQuery(); //SQL수행 및 결과집합 저장
				
				while( rs.next() ) {
					Review review = new Review(); //결과값 저장 객체
					
					//결과값 한 행 처리
					review.setReview_no(rs.getInt("review_no"));
					review.setPro_no(rs.getInt("pro_no"));
					review.setMember_no(rs.getInt("member_no"));
//					review.setTitle(rs.getNString("title"));
					review.setRegdate(rs.getDate("regdate"));
					review.setHit(rs.getInt("hit"));
					
					//리스트객체에 조회한 행 객체 저장
					reviewList.add(review);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				//JDBC객체 닫기
				JDBCTemplate.close(rs);
				JDBCTemplate.close(ps);
			}
			
			//최종 조회 결과 반환
			return reviewList;
		
	}
	
	
	@Override
	public int selectCntAll(Connection conn) {
		
		//SQL 작성
		String sql = "";
		sql += "SELECT count(*) cnt FROM review";
		
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
		
		return count;
	}
	
	@Override
	public Review selectReviewByReviewno(Connection conn, Review Review_no) {
		
		//SQL 작성
		String sql = "";
		sql += "SELECT";
		sql += "	review_no";
		sql += "	, pro_no";
		sql += "	, member_no";
		sql += "	, title";
		sql += "	, regdate";
		sql += "	, hit";
		sql += " FROM Review";
		sql += "WHERE review_no = ?";
		
		//결과 저장할 DTO객체
		Review review = new Review();
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			ps.setInt(1, Review_no.getReview_no());
			rs = ps.executeQuery(); //SQL수행 및 결과집합 저장

			while( rs.next() ) {
//				review = new Review(); //결과값 저장 객체
				
				//결과값 한 행 처리
				review.setReview_no(rs.getInt("review_no"));
				review.setPro_no(rs.getInt("pro_no"));
				review.setMember_no(rs.getInt("member_no"));
				review.setTitle(rs.getNString("title"));
				review.setRegdate(rs.getDate("regdate"));
				review.setHit(rs.getInt("hit"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//JDBC객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 조회 결과 반환
		return review;
	}
	
	
	@Override
	public int selectReviewno(Connection conn) {
		
		//SQL 작성
		String sql = "";
		sql += "SELECT review_seq.nextval FROM dual";
		
		//다음 시퀀스 번호
		int nextReviewno = 0;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				nextReviewno = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 결과 반환
		return nextReviewno;
	}
	
	@Override
	public int updateHit(Connection conn, Review review_no) {
		String sql = "";
		sql += "UPDATE review";
		sql += " SET hit = hit + 1";
		sql += " WHERE review_no = ?";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, review_no.getReview_no());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
				
		return res;
	}
	
	
	@Override
	public int insert(Connection conn, Review review) {
		String sql = "";
		sql += "INSERT INTO reivew(REVIEW_NO, PRO_NO, MEMBER_NO, TITLE, CONTENT, HIT)";
		sql += " VALUES (?, ?, ?, ?, ?,  0)";
		
		int res = 0;
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, review.getReview_no());
			ps.setInt(2, review.getPro_no());
			ps.setInt(3, review.getMember_no());
			ps.setString(4, review.getTitle());
			ps.setString(5, review.getContent());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}
	

	@Override
	public ReviewFile selectFile(Connection conn, Review viewReview) {
		
		//SQL 작성
		String sql = "";
		sql += "SELECT";
		sql += "	img_no";
		sql += "	, origin_name";
		sql += "	, stored_name";
		sql += "	, path";
		sql += "	, filesize";
		sql += "	, review_no";
		sql += " FROM boardfile";
		sql += " WHERE img_no = ?";
		
		//결과 저장할 DTO객체
		ReviewFile reviewFile = null;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setInt(1, viewReview.getReview_no());
			
			rs = ps.executeQuery(); //SQL수행 및 결과집합 저장

			while( rs.next() ) {
				reviewFile = new ReviewFile(); //결과값 저장 객체
					
				//결과값 행 처리
				reviewFile.setImg_no(rs.getInt("img_no"));
				reviewFile.setReview_no(rs.getInt("review_no")); 
				reviewFile.setOrigin_name(rs.getString("orgin_name"));
				reviewFile.setStored_name(rs.getString("stored_name"));
				reviewFile.setPath(rs.getString("path"));
				
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//JDBC객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 조회 결과 반환
		return reviewFile;
	}
	
	@Override
	public Object selectNickByUserid(Connection conn, Review viewReview) {

		//SQL 작성
		String sql = "";
		sql += "SELECT member_id FROM member";
		sql += " WHERE id = ?";
		
		//결과 저장할 String 변수
		String memberid = null;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setInt(1, viewReview.getMember_no()); //조회할 id 적용
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				memberid = rs.getString("member_id");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 결과 반환
		return memberid;
	}
	
	@Override
	public int delete(Connection conn, Review review) {
		String sql = "";
		sql += "DELETE review";
		sql += " WHERE review_no = ?";
		
		int res = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, review.getReview_no());

			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}
	
	@Override
	public int deleteFile(Connection conn, Review review) {

		String sql = "";
		sql += "DELETE reviewFile";
		sql += " WHERE review_no = ?";
		
		int res = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, review.getReview_no());

			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}

	@Override
	public int insertFile(Connection conn, ReviewFile reviewFile) {
		String sql = "";
		sql += "INSERT INTO boardfile( img_no, origin_name, stored_name, path, filesize, review_no,)";
		sql += " VALUES (img_no_seq.nextval, ?, ?, ?, ?, ?)";
		
		int res = 0;
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, reviewFile.getReview_no());
			ps.setString(2, reviewFile.getOrigin_name());
			ps.setString(3, reviewFile.getStored_name());
			ps.setString(4, reviewFile.getPath());
			ps.setInt(5, reviewFile.getFilesize());
			ps.setInt(6, reviewFile.getReview_no());
			
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}
	

}
