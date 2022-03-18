package dao.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.face.NoticeDao;
import dto.Notice;
import dto.NoticeFile;
import util.Paging;

public class NoticeDaoImpl implements NoticeDao {

	private PreparedStatement ps = null; // SQL수행 객체
	private ResultSet rs = null; // SQL조회 결과 객체

	public List<Notice> selectAll(Connection conn) {

		// SQL 작성
		String sql = "";
		sql += "SELECT";
		sql += "	board_no";
		sql += "	, id";
		sql += "	, title";
//			sql += "	, content";
		sql += "	, updated_date";
		sql += "	, hit";
		sql += " FROM notice, member";
		sql += " WHERE notice.member_no = member.member_no";
		sql += " ORDER BY board_no DESC";

		// 결과 저장할 List
		List<Notice> NoticeList = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql); // SQL수행 객체

			rs = ps.executeQuery(); // SQL수행 및 결과집합 저장

			while (rs.next()) {
				Notice n = new Notice(); // 결과값 저장 객체

				// 결과값 한 행 처리
				n.setBoardno(rs.getInt("board_no")); // 게시글번호
				n.setMemberid(rs.getString("id")); // 글쓴이
				n.setTitle(rs.getString("title")); // 게시글 제목
//					n.setContent( rs.getString("content") );		//내용
				n.setWriteDate(rs.getDate("updated_date")); // 등록일
				n.setHit(rs.getInt("hit")); // 조회수

				// 리스트객체에 조회한 행 객체 저장
				NoticeList.add(n);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// JDBC객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		// 최종 조회 결과 반환
		return NoticeList;
	}

	public List<Notice> selectAll(Connection conn, Paging paging) {

		// SQL 작성
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	SELECT rownum rnum, B.* FROM (";
		sql += " 		SELECT";
		sql += "			board_no, title, id";
		sql += "			, hit, updated_date";
		sql += "		FROM notice, member";
		sql += " 		WHERE notice.member_no = member.member_no";
		sql += "		ORDER BY board_no DESC";
		sql += " 	) B";
		sql += " ) notice, member";		
		sql += " WHERE notice.member_no = member.member_no";
		sql += " 	AND (rnum BETWEEN ? AND ?)";

		// 결과 저장할 List
		List<Notice> NoticeList = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql); // SQL수행 객체

			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());

			rs = ps.executeQuery(); // SQL수행 및 결과집합 저장

			while (rs.next()) {
				Notice n = new Notice(); // 결과값 저장 객체

				// 결과값 한 행 처리
				n.setBoardno(rs.getInt("board_no"));
				n.setTitle(rs.getString("title"));
				n.setMemberid(rs.getString("id"));
//					n.setContent( rs.getString("content") );
				n.setHit(rs.getInt("hit"));
				n.setWriteDate(rs.getDate("updated_date"));

				// 리스트객체에 조회한 행 객체 저장
				NoticeList.add(n);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// JDBC객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		// 최종 조회 결과 반환
		return NoticeList;
	}

	public int selectCntAll(Connection conn) {

		// SQL 작성
		String sql = "";
		sql += "SELECT count(*) cnt FROM notice";

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

	public int updateHit(Connection conn, Notice boardno) {

		String sql = "";
		sql += "UPDATE notice";
		sql += " SET hit = hit + 1";
		sql += " WHERE board_no = ?";

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

	public Notice selectBoardByBoardno(Connection conn, Notice boardno) {

		// SQL 작성
		String sql = "";
		sql += "SELECT";
		sql += "	board_no";
		sql += "	, title";
		sql += "	, id";
		sql += "	, content";
		sql += "	, hit";
		sql += "	, updated_date";
		sql += " FROM notice, member";
		sql += " WHERE notice.member_no = member.member_no";
		sql += " AND board_no = ?";

		// 결과 저장할 DTO객체
		Notice notice = null;

		try {
			ps = conn.prepareStatement(sql); // SQL수행 객체

			ps.setInt(1, boardno.getBoardno());

			rs = ps.executeQuery(); // SQL수행 및 결과집합 저장

			while (rs.next()) {
				notice = new Notice(); // 결과값 저장 객체

				// 결과값 행 처리
				notice.setBoardno(rs.getInt("board_no"));
				notice.setTitle(rs.getString("title"));
				notice.setMemberid(rs.getString("id"));
				notice.setContent(rs.getString("content"));
				notice.setHit(rs.getInt("hit"));
				notice.setWriteDate(rs.getDate("updated_date"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// JDBC객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		// 최종 조회 결과 반환
		return notice;
	}

	public int insert(Connection conn, Notice notice) {

		String sql = "";
		sql += "INSERT INTO notice(board_no, TITLE, member_no, CONTENT, HIT)";
		sql += " VALUES (?, ?, ?, ?, 0)";

		int res = 0;

		try {
			// DB작업
			ps = conn.prepareStatement(sql);

			ps.setInt(1, notice.getBoardno());
			ps.setString(2, notice.getTitle());
			ps.setInt(3, notice.getMemberno()); // 멤버넘버를 넣고 셀렉트할때 조인해서 id 보여주기
			ps.setString(4, notice.getContent());

			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

		return res;
	}

	public String selectNickByMemberid(Connection conn, Notice viewBoard) {

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
		sql += "SELECT notice_seq.nextval FROM dual";

		// 다음 시퀀스 번호
		int nextNoticeno = 0;

		try {
			ps = conn.prepareStatement(sql); // SQL수행 객체

			rs = ps.executeQuery(); // SQL 수행 및 결과집합 저장

			// 조회 결과 처리
			while (rs.next()) {
				nextNoticeno = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		// 최종 결과 반환
		return nextNoticeno;
	}

	public int insertFile(Connection conn, NoticeFile noticeFile) {

		String sql = "";
		sql += "INSERT INTO noticefile( FILENO, board_no, ORIGIN_NAME, STORED_NAME, FILESIZE )";
		sql += " VALUES (noticefile_seq.nextval, ?, ?, ?, ?)";

		int res = 0;

		try {
			// DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, noticeFile.getBoardno());
			ps.setString(2, noticeFile.getOriginname());
			ps.setString(3, noticeFile.getStoredname());
			ps.setInt(4, noticeFile.getFilesize());

			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

		return res;
	}

	public NoticeFile selectFile(Connection conn, Notice viewBoard) {

		// SQL 작성
		String sql = "";
		sql += "SELECT";
		sql += "	fileno";
		sql += "	, board_no";
		sql += "	, origin_name";
		sql += "	, stored_name";
		sql += "	, filesize";
		sql += "	, updated_date";
		sql += " FROM noticefile";

		sql += " WHERE board_no = ?";

		// 결과 저장할 DTO객체
		NoticeFile NoticeFile = null;

		try {
			ps = conn.prepareStatement(sql); // SQL수행 객체

			ps.setInt(1, viewBoard.getBoardno());

			rs = ps.executeQuery(); // SQL수행 및 결과집합 저장

			while (rs.next()) {
				NoticeFile = new NoticeFile(); // 결과값 저장 객체

				// 결과값 행 처리
				NoticeFile.setFileno(rs.getInt("fileno"));
				NoticeFile.setBoardno(rs.getInt("board_no"));
				NoticeFile.setOriginname(rs.getString("origin_name"));
				NoticeFile.setStoredname(rs.getString("stored_name"));
				NoticeFile.setFilesize(rs.getInt("filesize"));
				NoticeFile.setWriteDate(rs.getDate("updated_date"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// JDBC객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		// 최종 조회 결과 반환
		return NoticeFile;
	}

	public int update(Connection conn, Notice notice) {

		String sql = "";
		sql += "UPDATE notice";
		sql += " SET title = ?,";
		sql += " 	content = ?";
		sql += " WHERE board_no = ?";

		int res = -1;

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, notice.getTitle());
			ps.setString(2, notice.getContent());
			ps.setInt(3, notice.getBoardno());

			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			JDBCTemplate.close(ps);
		}

		return res;
	}

	public int delete(Connection conn, Notice notice) {

		String sql = "";
		sql += "DELETE notice";
		sql += " WHERE board_no = ?";

		int res = -1;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, notice.getBoardno());

			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			JDBCTemplate.close(ps);
		}

		return res;
	}

	public int deleteFile(Connection conn, Notice notice) {

		String sql = "";
		sql += "DELETE noticefile";
		sql += " WHERE board_no = ?";

		int res = -1;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, notice.getBoardno());

			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			JDBCTemplate.close(ps);
		}

		return res;
	}

}





















