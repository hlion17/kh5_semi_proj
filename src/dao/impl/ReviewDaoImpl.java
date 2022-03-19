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
	

	private PreparedStatement ps = null; // SQL수행 객체
	private ResultSet rs = null; // SQL조회 결과 객체

	
	
	public List<Review> selectAll(Connection conn) {

		// SQL 작성
		String sql = "";
		sql += " SELECT review_no, product.name, nick, title, regdate, hit ";
		sql += " FROM review, MEMBER, product ";
		sql += " WHERE review.member_no = member.member_no ";
		sql += "	and review.pro_no = product.pro_no ";
		sql += " ORDER BY review_no desc ";
		
		
		System.out.println(sql);


		// 결과 저장할 List
		List<Review> ReviewList = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql); // SQL수행 객체

			rs = ps.executeQuery(); // SQL수행 및 결과집합 저장

			while (rs.next()) {
				Review review = new Review(); // 결과값 저장 객체

				// 결과값 한 행 처리
				review.setReview_no(rs.getInt("review_no"));
				review.setPro_no(rs.getInt("pro_no"));
				review.setMember_no(rs.getInt("member_no"));
				review.setTitle(rs.getString("title"));
				review.setRegdate(rs.getDate("regdate"));
				review.setHit(rs.getInt("hit"));

				// 리스트객체에 조회한 행 객체 저장
				ReviewList.add(review);
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// JDBC객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		
		// 최종 조회 결과 반환
		return ReviewList;
	}
	
	
	

	//페이징 객체와 함께 리뷰list 전부 조회하기
	public List<Review> selectAll(Connection conn, Paging paging) {

		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	SELECT rownum rnum, R.* FROM (";
		sql += " 		SELECT review_no, product.name, nick, title,regdate, hit";
		sql += "		FROM review, MEMBER, product";
		sql += " 		WHERE review.member_no = member.member_no";
		sql += "			    and review.pro_no = product.pro_no";
		sql += " 		ORDER BY review_no desc ";
		sql += " 	) R";
		sql += " ) REVIEW";
		sql += " WHERE rnum BETWEEN ? AND ?";
		
		System.out.println(sql);

		// 결과 저장할 List
		List<Review> ReviewList = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql); // SQL수행 객체

			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());

			rs = ps.executeQuery(); // SQL수행 및 결과집합 저장

			while (rs.next()) {
				Review review = new Review(); // 결과값 저장 객체

				// 결과값 한 행 처리
				review.setReview_no(rs.getInt("review_no"));
				review.setName(rs.getString("name"));
				review.setNick(rs.getString("nick"));
				review.setTitle(rs.getString("title"));
				review.setRegdate(rs.getDate("regdate"));
				review.setHit(rs.getInt("hit"));

				// 리스트객체에 조회한 행 객체 저장
				ReviewList.add(review);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// JDBC객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		// 최종 조회 결과 반환
		return ReviewList;
	}

	public int selectCntAll(Connection conn) {

		// SQL 작성
		String sql = "";
		sql += "SELECT count(*) cnt FROM review";

		// 총 게시글 수
		int count = 0;

		try {
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {
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

	public int updateHit(Connection conn, Review boardno) {

		String sql = "";
		sql += "UPDATE review";
		sql += " SET hit = hit + 1";
		sql += " WHERE board_no = ?";

		int res = 0;

		try {
			ps = conn.prepareStatement(sql);

			//?
			ps.setInt(1, boardno.getMember_no());

			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

		return res;
	}

	public Review selectBoardByBoardno(Connection conn, Review boardno) {

		// SQL 작성
		String sql = "";
		sql += "SELECT";
		sql += "	review_no";
		sql += "	, pro_no";
		sql += "	, member_no";
		sql += "	, title";
		sql += "	, regdate";
		sql += "	, hit";
		sql += " FROM review, member";
		sql += " WHERE review.member_no = member.member_no";
		sql += " AND board_no = ?";
		
//		System.out.println(sql);

		// 결과 저장할 DTO객체
		Review review = null;

		try {
			ps = conn.prepareStatement(sql); // SQL수행 객체

			//?
			ps.setInt(1, boardno.getMember_no());

			rs = ps.executeQuery(); // SQL수행 및 결과집합 저장

			while (rs.next()) {
				review = new Review(); // 결과값 저장 객체

				// 결과값 행 처리
				review.setReview_no(rs.getInt("review_no"));
				review.setPro_no(rs.getInt("pro_no"));
				review.setMember_no(rs.getInt("member_no"));
				review.setTitle(rs.getString("title"));
				review.setRegdate(rs.getDate("regdate"));
				review.setHit(rs.getInt("hit"));
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// JDBC객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		// 최종 조회 결과 반환
		return review;
	}

	public int insert(Connection conn, Review review) {

		String sql = "";
		//regdate 삭제
		sql += "INSERT INTO review(REVIEW_NO, PRO_NO, MEMBER_NO, TITLE, CONTENT, HIT)";
		sql += " VALUES (?, ?, ?, ?, ?, 0)";
		
		int res = 0;

		try {
			// DB작업
			ps = conn.prepareStatement(sql);

			ps.setInt(1, review.getReview_no());
			ps.setInt(2, review.getPro_no());
			ps.setInt(3, review.getMember_no());
			ps.setString(4, review.getTitle());
			ps.setString(5, review.getContent());
			ps.setInt(6, review.getHit());

			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

		return res;
	}

	//상세보기
	public String selectNickByMemberid(Connection conn, Review viewBoard) { 

		// SQL 작성
		String sql = "";
		sql += "SELECT nick FROM member";
		sql += " WHERE id = ?";

		// 결과 저장할 String 변수
		String nick = null;

		try {
			ps = conn.prepareStatement(sql); // SQL수행 객체

			ps.setString(1, viewBoard.getNick());
			rs = ps.executeQuery(); // SQL 수행 및 결과집합 저장

			
			// 조회 결과 처리
			while (rs.next()) {
				nick = rs.getString("nick");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		// 최종 결과 반환
		return nick;

	}

	public int selectBoardno(Connection conn) {

		// SQL 작성
		String sql = "";
		sql += "SELECT review_seq.nextval FROM dual";

		// 다음 시퀀스 번호
		int nextReviewno = 0;

		try {
			ps = conn.prepareStatement(sql); // SQL수행 객체

			rs = ps.executeQuery(); // SQL 수행 및 결과집합 저장

			// 조회 결과 처리
			while (rs.next()) {
				nextReviewno = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		// 최종 결과 반환
		return nextReviewno;
	}

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

	public ReviewFile selectFile(Connection conn, Review viewBoard) {

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
			ps = conn.prepareStatement(sql); // SQL수행 객체

			ps.setInt(1, viewBoard.getReview_no());

			rs = ps.executeQuery(); // SQL수행 및 결과집합 저장

			while (rs.next()) {
				reviewFile = new ReviewFile(); // 결과값 저장 객체

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
			// JDBC객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		// 최종 조회 결과 반환
		return reviewFile;
	}

	public int update(Connection conn, Review review) {

		String sql = "";
		sql += "UPDATE review";
		sql += " SET title = ?,";
		sql += " 	content = ?";
		sql += " WHERE review_no = ?";

		int res = -1;

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, review.getTitle());
			ps.setString(2, review.getContent());
			ps.setInt(3, review.getReview_no());
			
			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			JDBCTemplate.close(ps);
		}

		return res;
	}

	public int delete(Connection conn, Review review) {

		String sql = "";
		sql += "DELETE review";
		sql += " WHERE board_no = ?";

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

	public int deleteFile(Connection conn, Review review) {

		String sql = "";
		sql += "DELETE reviewfile";
		sql += " WHERE board_no = ?";

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

}

