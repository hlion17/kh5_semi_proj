package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.face.RecipeDao;
import dto.Recipe;
import dto.RecipeFile;
import util.Paging;

public class RecipeDaoImpl implements RecipeDao {

	private PreparedStatement ps = null; //SQL수행 객체
	private ResultSet rs = null; //SQL조회 결과 객체
	
	@Override
	public List<Recipe> selectAll(Connection conn) {
		
		//SQL 작성
		String sql = "";
		sql += "SELECT";
		sql += "	board_no";
		sql += "	, member_no";
		sql += "	, title";
//		sql += "	, content";
		sql += "	, updated_date";
		sql += "	, hit";
//		sql += "	, board_like";
//		sql += "	, intro";
		sql += " FROM board";
		sql += " ORDER BY board_no DESC";
		
		//결과 저장할 List
		List<Recipe> boardList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			rs = ps.executeQuery(); //SQL수행 및 결과집합 저장

			while( rs.next() ) {
				Recipe b = new Recipe(); //결과값 저장 객체
				
				//결과값 한 행 처리
				b.setBoardno( rs.getInt("board_no") );
				b.setTitle( rs.getString("title") );
				b.setUserid( rs.getString("member_no") );
//				b.setContent( rs.getString("content") );
				b.setHit( rs.getInt("hit") );
				b.setWriteDate( rs.getDate("updated_date") );
				
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
		return boardList;
	}

	@Override
	public List<Recipe> selectAll(Connection conn, Paging paging) {
		
		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	SELECT rownum rnum, B.* FROM (";
		sql += " 		SELECT";
		sql += "			boardno, title, userid";
		sql += "			, hit, write_date";
		sql += "		FROM board";
		sql += "		ORDER BY boardno DESC";
		sql += " 	) B";
		sql += " ) BOARD";
		sql += " WHERE rnum BETWEEN ? AND ?";
		
		//결과 저장할 List
		List<Recipe> boardList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());
			
			rs = ps.executeQuery(); //SQL수행 및 결과집합 저장
			
			while( rs.next() ) {
				Recipe b = new Recipe(); //결과값 저장 객체
				
				//결과값 한 행 처리
				b.setBoardno( rs.getInt("boardno") );
				b.setTitle( rs.getString("title") );
				b.setUserid( rs.getString("userid") );
//				b.setContent( rs.getString("content") );
				b.setHit( rs.getInt("hit") );
				b.setWriteDate( rs.getDate("write_date") );
				
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
		return boardList;
	}
	
	@Override
	public int selectCntAll(Connection conn) {
		
		//SQL 작성
		String sql = "";
		sql += "SELECT count(*) cnt FROM board";
		
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
	public int updateHit(Connection conn, Recipe boardno) {
		
		String sql = "";
		sql += "UPDATE board";
		sql += " SET hit = hit + 1";
		sql += " WHERE boardno = ?";
		
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
	
	@Override
	public Recipe selectBoardByBoardno(Connection conn, Recipe boardno) {
		
		//SQL 작성
		String sql = "";
		sql += "SELECT";
		sql += "	boardno";
		sql += "	, title";
		sql += "	, userid";
		sql += "	, content";
		sql += "	, hit";
		sql += "	, write_date";
		sql += " FROM board";
		sql += " WHERE boardno = ?";
		
		//결과 저장할 DTO객체
		Recipe board = null;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setInt(1, boardno.getBoardno());
			
			rs = ps.executeQuery(); //SQL수행 및 결과집합 저장

			while( rs.next() ) {
				board = new Recipe(); //결과값 저장 객체
				
				//결과값 행 처리
				board.setBoardno( rs.getInt("boardno") );
				board.setTitle( rs.getString("title") );
				board.setUserid( rs.getString("userid") );
				board.setContent( rs.getString("content") );
				board.setHit( rs.getInt("hit") );
				board.setWriteDate( rs.getDate("write_date") );
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//JDBC객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 조회 결과 반환
		return board;
	}

	@Override
	public int insert(Connection conn, Recipe board) {
		
		String sql = "";
		sql += "INSERT INTO board(BOARDNO, TITLE, USERID, CONTENT, HIT)";
		sql += " VALUES (?, ?, ?, ?, 0)";
		
		int res = 0;
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);

			ps.setInt(1, board.getBoardno());
			ps.setString(2, board.getTitle());
			ps.setString(3, board.getUserid());
			ps.setString(4, board.getContent());

			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}

	@Override
	public String selectNickByUserid(Connection conn, Recipe viewBoard) {
		
		//SQL 작성
		String sql = "";
		sql += "SELECT usernick FROM member";
		sql += " WHERE userid = ?";
		
		//결과 저장할 String 변수
		String usernick = null;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setString(1, viewBoard.getUserid()); //조회할 id 적용
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				usernick = rs.getString("usernick");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 결과 반환
		return usernick;
		
	}
	
	@Override
	public int selectBoardno(Connection conn) {
		
		//SQL 작성
		String sql = "";
		sql += "SELECT board_seq.nextval FROM dual";
		
		//다음 시퀀스 번호
		int nextBoardno = 0;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				nextBoardno = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 결과 반환
		return nextBoardno;
	}
	
	@Override
	public int insertFile(Connection conn, RecipeFile boardFile) {
		
		String sql = "";
		sql += "INSERT INTO boardfile( FILENO, BOARDNO, ORIGINNAME, STOREDNAME, FILESIZE )";
		sql += " VALUES (boardfile_seq.nextval, ?, ?, ?, ?)";
		
		int res = 0;
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, boardFile.getBoardno());
			ps.setString(2, boardFile.getOriginname());
			ps.setString(3, boardFile.getStoredname());
			ps.setInt(4, boardFile.getFilesize());

			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}
	
	@Override
	public RecipeFile selectFile(Connection conn, Recipe viewBoard) {
		
		//SQL 작성
		String sql = "";
		sql += "SELECT";
		sql += "	fileno";
		sql += "	, boardno";
		sql += "	, originname";
		sql += "	, storedname";
		sql += "	, filesize";
		sql += "	, write_date";
		sql += " FROM boardfile";
		sql += " WHERE boardno = ?";
		
		//결과 저장할 DTO객체
		RecipeFile boardFile = null;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setInt(1, viewBoard.getBoardno());
			
			rs = ps.executeQuery(); //SQL수행 및 결과집합 저장

			while( rs.next() ) {
				boardFile = new RecipeFile(); //결과값 저장 객체
				
				//결과값 행 처리
				boardFile.setFileno( rs.getInt("fileno") );
				boardFile.setBoardno( rs.getInt("boardno") );
				boardFile.setOriginname( rs.getString("originname") );
				boardFile.setStoredname( rs.getString("storedname") );
				boardFile.setFilesize( rs.getInt("filesize") );
				boardFile.setWriteDate( rs.getDate("write_date") );
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//JDBC객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 조회 결과 반환
		return boardFile;
	}
	
	@Override
	public int update(Connection conn, Recipe board) {
		
		String sql = "";
		sql += "UPDATE board";
		sql += " SET title = ?,";
		sql += " 	content = ?";
		sql += " WHERE boardno = ?";
		
		int res = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, board.getTitle());
			ps.setString(2, board.getContent());
			ps.setInt(3, board.getBoardno());

			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}

	@Override
	public int delete(Connection conn, Recipe board) {
		
		String sql = "";
		sql += "DELETE board";
		sql += " WHERE boardno = ?";
		
		int res = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, board.getBoardno());

			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}
	
	@Override
	public int deleteFile(Connection conn, Recipe board) {
		
		String sql = "";
		sql += "DELETE boardfile";
		sql += " WHERE boardno = ?";
		
		int res = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, board.getBoardno());

			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}

}
