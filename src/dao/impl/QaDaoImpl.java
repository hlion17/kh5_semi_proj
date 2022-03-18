package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.face.QaDao;
import dto.Qa;
import dto.QaFile;
import util.Paging;

public class QaDaoImpl implements QaDao {

	private PreparedStatement ps = null; // SQL수행 객체
	private ResultSet rs = null; // SQL조회 결과 객체

	public List<Qa> selectAll(Connection conn) {

		// SQL 작성
		String sql = "";
		sql += "SELECT";
		sql += "	qa_no";
		sql += "	, id";
		sql += "	, title";
//			sql += "	, content";
		sql += "	, updated_date";
		sql += "	, hit";
		sql += " FROM qa, member";
		sql += " WHERE qa.member_no = member.member_no";
		sql += " ORDER BY qa_no DESC";

		// 결과 저장할 List
		List<Qa> qaList = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql); // SQL수행 객체

			rs = ps.executeQuery(); // SQL수행 및 결과집합 저장

			while (rs.next()) {
				Qa n = new Qa(); // 결과값 저장 객체

				// 결과값 한 행 처리
				n.setBoardno(rs.getInt("qa_no")); // 게시글번호
				n.setMemberid(rs.getString("id")); // 글쓴이
				n.setTitle(rs.getString("title")); // 게시글 제목
//					n.setContent( rs.getString("content") );		//내용
				n.setWriteDate(rs.getDate("updated_date")); // 등록일
				n.setHit(rs.getInt("hit")); // 조회수

				// 리스트객체에 조회한 행 객체 저장
				qaList.add(n);
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// JDBC객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		
		// 최종 조회 결과 반환
		return qaList;
	}

	public List<Qa> selectAll(Connection conn, Paging paging) {

		// SQL 작성
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	SELECT rownum rnum, B.* FROM (";
		sql += " 		SELECT";
		sql += "			qa_no, title, id";
		sql += "			, hit, updated_date";
		sql += "		FROM qa, member";
		sql += " 		WHERE qa.member_no = member.member_no";
		sql += "		ORDER BY qa_no DESC";
		sql += " 	) B";
		sql += " ) BOARD";
		sql += " WHERE rnum BETWEEN ? AND ?";
		
//		System.out.println(sql);

		// 결과 저장할 List
		List<Qa> qaList = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql); // SQL수행 객체

			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());

			rs = ps.executeQuery(); // SQL수행 및 결과집합 저장

			while (rs.next()) {
				Qa n = new Qa(); // 결과값 저장 객체

				// 결과값 한 행 처리
				n.setBoardno(rs.getInt("qa_no"));
				n.setTitle(rs.getString("title"));
				n.setMemberid(rs.getString("id"));
//					n.setContent( rs.getString("content") );
				n.setHit(rs.getInt("hit"));
				n.setWriteDate(rs.getDate("updated_date"));

				// 리스트객체에 조회한 행 객체 저장
				qaList.add(n);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// JDBC객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		// 최종 조회 결과 반환
		return qaList;
	}

	public int selectCntAll(Connection conn) {

		// SQL 작성
		String sql = "";
		sql += "SELECT count(*) cnt FROM qa";

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

	public int updateHit(Connection conn, Qa boardno) {

		String sql = "";
		sql += "UPDATE qa";
		sql += " SET hit = hit + 1";
		sql += " WHERE qa_no = ?";

		int res = 0;

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, boardno.getBoardno());

			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

		return res;
	}

	public Qa selectBoardByBoardno(Connection conn, Qa boardno) {

		// SQL 작성
		String sql = "";
		sql += "SELECT";
		sql += "	qa_no";
		sql += "	, title";
		sql += "	, id";
		sql += "	, content";
		sql += "	, hit";
		sql += "	, updated_date";
		sql += " FROM qa, member";
		sql += " WHERE qa.member_no = member.member_no";
		sql += " AND qa_no = ?";
		
//		System.out.println(sql);

		// 결과 저장할 DTO객체
		Qa qa = null;

		try {
			ps = conn.prepareStatement(sql); // SQL수행 객체

			ps.setInt(1, boardno.getBoardno());

			rs = ps.executeQuery(); // SQL수행 및 결과집합 저장

			while (rs.next()) {
				qa = new Qa(); // 결과값 저장 객체

				// 결과값 행 처리
				qa.setBoardno(rs.getInt("qa_no"));
				qa.setTitle(rs.getString("title"));
				qa.setMemberid(rs.getString("id"));
				qa.setContent(rs.getString("content"));
				qa.setHit(rs.getInt("hit"));
				qa.setWriteDate(rs.getDate("updated_date"));
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// JDBC객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		// 최종 조회 결과 반환
		return qa;
	}

	public int insert(Connection conn, Qa qa) {

		String sql = "";
		sql += "INSERT INTO qa(qa_no, TITLE, member_no, CONTENT, HIT)";
		sql += " VALUES (?, ?, ?, ?, 0)";

		int res = 0;

		try {
			// DB작업
			ps = conn.prepareStatement(sql);

			ps.setInt(1, qa.getBoardno());
			ps.setString(2, qa.getTitle());
			ps.setInt(3, qa.getMemberno()); 
			ps.setString(4, qa.getContent());

			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

		return res;
	}

	public String selectNickByMemberid(Connection conn, Qa viewBoard) { 

		// SQL 작성
		String sql = "";
		sql += "SELECT nick FROM member";
		sql += " WHERE id = ?";

		// 결과 저장할 String 변수
		String nick = null;

		try {
			ps = conn.prepareStatement(sql); // SQL수행 객체

			ps.setString(1, viewBoard.getMemberid()); // 조회할 id 적용
			

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
		sql += "SELECT qa_seq.nextval FROM dual";

		// 다음 시퀀스 번호
		int nextqano = 0;

		try {
			ps = conn.prepareStatement(sql); // SQL수행 객체

			rs = ps.executeQuery(); // SQL 수행 및 결과집합 저장

			// 조회 결과 처리
			while (rs.next()) {
				nextqano = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		// 최종 결과 반환
		return nextqano;
	}

	public int insertFile(Connection conn, QaFile qaFile) {

		String sql = "";
		sql += "INSERT INTO qafile( FILE_NO, qa_no, ORIGIN_NAME, STORED_NAME, FILESIZE )";
		sql += " VALUES (qafile_seq.nextval, ?, ?, ?, ?)";

		int res = 0;

		try {
			// DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, qaFile.getBoardno());
			ps.setString(2, qaFile.getOriginname());
			ps.setString(3, qaFile.getStoredname());
			ps.setInt(4, qaFile.getFilesize());

			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

		return res;
	}

	public QaFile selectFile(Connection conn, Qa viewBoard) {

		// SQL 작성
		String sql = "";
		sql += "SELECT";
		sql += "	file_no";
		sql += "	, qafile.qa_no";
		sql += "	, origin_name";
		sql += "	, stored_name";
		sql += "	, filesize";
		sql += "	, updated_date";
		sql += " FROM qafile, qa";
		sql += " WHERE qa.qa_no = qafile.qa_no";
		sql += " AND qafile.qa_no = ?";

		
		
		// 결과 저장할 DTO객체
		QaFile qaFile = null;

		try {
			ps = conn.prepareStatement(sql); // SQL수행 객체

			ps.setInt(1, viewBoard.getBoardno());

			rs = ps.executeQuery(); // SQL수행 및 결과집합 저장

			while (rs.next()) {
				qaFile = new QaFile(); // 결과값 저장 객체

				// 결과값 행 처리
				qaFile.setFileno(rs.getInt("file_no"));
				qaFile.setBoardno(rs.getInt("qa_no"));
				qaFile.setOriginname(rs.getString("origin_name"));
				qaFile.setStoredname(rs.getString("stored_name"));
				qaFile.setFilesize(rs.getInt("filesize"));
				qaFile.setWriteDate(rs.getDate("updated_date"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// JDBC객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		// 최종 조회 결과 반환
		return qaFile;
	}

	public int update(Connection conn, Qa qa) {

		String sql = "";
		sql += "UPDATE qa";
		sql += " SET title = ?,";
		sql += " 	content = ?";
		sql += " WHERE qa_no = ?";

		int res = -1;

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, qa.getTitle());
			ps.setString(2, qa.getContent());
			ps.setInt(3, qa.getBoardno());

			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			JDBCTemplate.close(ps);
		}

		return res;
	}

	public int delete(Connection conn, Qa qa) {

		String sql = "";
		sql += "DELETE qa";
		sql += " WHERE qa_no = ?";

		int res = -1;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, qa.getBoardno());

			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			JDBCTemplate.close(ps);
		}

		return res;
	}

	public int deleteFile(Connection conn, Qa qa) {

		String sql = "";
		sql += "DELETE qafile";
		sql += " WHERE qa_no = ?";

		int res = -1;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, qa.getBoardno());

			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			JDBCTemplate.close(ps);
		}

		return res;
	}
	
}
