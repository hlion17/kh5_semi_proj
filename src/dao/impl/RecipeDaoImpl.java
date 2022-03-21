package dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.JDBCTemplate;
import dao.face.RecipeDao;
import dto.Recipe;
import dto.RecipeFile;
import oracle.sql.DATE;
import util.Paging;

public class RecipeDaoImpl implements RecipeDao {

	private PreparedStatement ps = null; //SQL수행 객체
	private ResultSet rs = null; //SQL조회 결과 객체
	
	@Override
	public List<Recipe> selectAll(Connection conn) {
		System.out.println("[TEST] RecipeDaoImpl - selectAll(Connection conn) 호출");
		
		//SQL 작성
		String sql = "";
		sql += "SELECT";
		sql += "	board_no";
		sql += "	, member_no";
		sql += "	, title";
//		sql += "	, content";
		sql += "	, updated_date";
		sql += "	, hit";
		sql += "	, board_like";
//		sql += "	, intro";
		sql += " FROM recipe";
		sql += " ORDER BY board_no DESC";
		
//		System.out.println("[TEST] RecipeDaoImpl - selectAll(Connection conn) - sql : " + sql);
		
		//결과 저장할 List
		List<Recipe> boardList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			rs = ps.executeQuery(); //SQL수행 및 결과집합 저장

//			System.out.println( "[TEST] RecipeDaoImpl - selectAll(Connection conn) - rs.next() : " + rs.next() );
			
			while( rs.next() ) {
				Recipe b = new Recipe(); //결과값 저장 객체
				
				//결과값 한 행 처리
				b.setBoardno( rs.getInt("board_no") );			//게시글번호
//				System.out.println( rs.getInt("board_no") );	
				b.setTitle( rs.getString("title") );			//게시글 제목
				b.setUserid( rs.getInt("member_no") );			//글쓴이
//				b.setContent( rs.getString("content") );		//내용
				b.setWriteDate( rs.getDate("updated_date") );	//등록일
				b.setHit( rs.getInt("hit") );					//조회수
				b.setLike( rs.getInt("board_like") );			//추천수
//				b.setIntro( rs.getString("intro") );			//소개글
				
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
		System.out.println("[TEST] RecipeDaoImpl - selectAll(Connection conn) - boardList 리턴 : " + boardList);
		return boardList;
	}

	@Override
	public List<Recipe> selectAll(Connection conn, Paging paging) {
		System.out.println("[TEST] RecipeDaoImpl - selectAll(Connection conn, Paging paging) 호출");
		
		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	SELECT rownum rnum, B.* FROM (";
		sql += " 		SELECT";
//		sql += "			board_no, member_no, title";
//		sql += "			, updated_date, hit, board_like";
		sql += "			RECIPE.*, MEMBER.NICK";
		sql += "		FROM recipe, MEMBER";
		sql += "		WHERE MEMBER.MEMBER_NO = RECIPE.MEMBER_NO";
		sql += "		ORDER BY board_no DESC";
		sql += " 	) B";
		sql += " ) recipe";
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
				b.setBoardno( rs.getInt("board_no") );
				b.setTitle( rs.getString("title") );
				b.setUserid( rs.getInt("member_no") );
//				b.setContent( rs.getString("content") );
				b.setHit( rs.getInt("hit") );
				b.setWriteDate( rs.getDate("updated_date") );
				b.setLike( rs.getInt("board_like") );
				b.setNick( rs.getString("nick"));
				
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
		System.out.println("[TEST] RecipeDaoImpl - selectAll(Connection conn, Paging paging) - boardList 리턴 : " + boardList);
		return boardList;
	}
	
	@Override
	public List<Recipe> selectAllRank(Connection conn) {
		System.out.println("[TEST] RecipeDaoImpl - selectAll(Connection conn) 호출");
		
		//SQL 작성
		String sql = "";
		sql += "SELECT";
		sql += "	board_no";
		sql += "	, member_no";
		sql += "	, title";
//		sql += "	, content";
		sql += "	, updated_date";
		sql += "	, hit";
		sql += "	, board_like";
		sql += "	, intro";
		sql += " FROM recipe";
		sql += " ORDER BY board_like DESC";
		
//		System.out.println("[TEST] RecipeDaoImpl - selectAll(Connection conn) - sql : " + sql);
		
		//결과 저장할 List
		List<Recipe> boardList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			rs = ps.executeQuery(); //SQL수행 및 결과집합 저장

//			System.out.println( "[TEST] RecipeDaoImpl - selectAll(Connection conn) - rs.next() : " + rs.next() );
			
			while( rs.next() ) {
				Recipe b = new Recipe(); //결과값 저장 객체
				
				//결과값 한 행 처리
				b.setBoardno( rs.getInt("board_no") );			//게시글번호
//				System.out.println( rs.getInt("board_no") );	
				b.setTitle( rs.getString("title") );			//게시글 제목
				b.setUserid( rs.getInt("member_no") );			//글쓴이
//				b.setContent( rs.getString("content") );		//내용
				b.setWriteDate( rs.getDate("updated_date") );	//등록일
				b.setHit( rs.getInt("hit") );					//조회수
				b.setLike( rs.getInt("board_like") );			//추천수
				b.setIntro( rs.getString("intro") );			//소개글
				
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
		System.out.println("[TEST] RecipeDaoImpl - selectAll(Connection conn) - boardList 리턴 : " + boardList);
		return boardList;
	}
	
	@Override
	public int selectCntAll(Connection conn) {
		System.out.println("[TEST] RecipeDaoImpl - selectCntAll(Connection conn) 호출");
		
		//SQL 작성
		String sql = "";
		sql += "SELECT count(*) cnt FROM recipe";
		
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
		
		System.out.println("[TEST] RecipeDaoImpl - selectCntAll(Connection conn) - count 리턴 : " + count);
		return count;
	}
	
	@Override
	public int updateHit(Connection conn, Recipe boardno) {
		System.out.println("[TEST] RecipeDaoImpl - updateHit(Connection conn, Recipe boardno) 호출");
		
		String sql = "";
		sql += "UPDATE recipe";
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
		
		System.out.println("[TEST] RecipeDaoImpl - updateHit(Connection conn, Recipe boardno) - res 리턴 : " + res);
		return res;
	}
	

	@Override
	public int downHit(Connection conn, int boardno) {
		System.out.println("[TEST] RecipeDaoImpl - downHit(conn, int) 호출");
		
		String sql = "";
		sql += "UPDATE recipe";
		sql += " SET hit = hit - 1";
		sql += " WHERE board_no = ?";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, boardno);
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		System.out.println("[TEST] RecipeDaoImpl - downHit(conn, int) - res 리턴 : " + res);
		return res;
	}
	
	@Override
	public int updateLike(Connection conn, Recipe boardno, HttpServletRequest req) {
		System.out.println("[TEST] RecipeDaoImpl - updateLike(Connection conn, Recipe boardno) 호출");
		
		String sql = "";
		sql += "UPDATE recipe";
		sql += " SET board_like = board_like + 1";
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
		
		System.out.println("[TEST] RecipeDaoImpl - updateLike(Connection conn, Recipe boardno) - res 리턴 : " + res);
		return res;
	}
	
	@Override
	public Recipe selectBoardByBoardno(Connection conn, Recipe boardno) {
		System.out.println("[TEST] RecipeDaoImpl - selectBoardByBoardno(Connection conn, Recipe boardno) 호출");
		
		//SQL 작성
		String sql = "";
		sql += "SELECT";
		sql += "	board_no";
		sql += "	, member_no";
		sql += "	, title";
		sql += "	, content";
		sql += "	, updated_date";
		sql += "	, hit";
		sql += "	, board_like";
		sql += "	, intro";
		sql += " FROM recipe";
		sql += " WHERE board_no = ?";
		
		//결과 저장할 DTO객체
		Recipe board = null;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setInt(1, boardno.getBoardno());
			
			rs = ps.executeQuery(); //SQL수행 및 결과집합 저장

			while( rs.next() ) {
				board = new Recipe(); //결과값 저장 객체
				
				//결과값 행 처리
				board.setBoardno( rs.getInt("board_no") );
				board.setTitle( rs.getString("title") );
				board.setUserid( rs.getInt("member_no") );
				board.setContent( rs.getString("content") );
				board.setHit( rs.getInt("hit") );
				board.setLike( rs.getInt("board_like") );
				board.setWriteDate( rs.getDate("updated_date") );
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//JDBC객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 조회 결과 반환
		System.out.println("[TEST] RecipeDaoImpl - selectBoardByBoardno(Connection conn, Recipe boardno) - board 리턴 : " + board);
		return board;
	}

	@Override
	public int insert(Connection conn, Recipe board) {
		System.out.println("[TEST] RecipeDaoImpl - insert(Connection conn, Recipe board) 호출");
		
		String sql = "";
		sql += "INSERT INTO recipe(board_no, member_no, TITLE, CONTENT, HIT, BOARD_LIKE, intro)";
		sql += " VALUES (?, ?, ?, ?, 0, 0, ?)";
		
		int res = 0;
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);

			System.out.println("getBoardno : " + board.getBoardno());
			System.out.println("getUserid : " + board.getUserid());
			System.out.println("getTitle : " + board.getTitle());
			System.out.println("getContent : " + board.getContent());
			System.out.println("getIntro : " + board.getIntro());
			
			ps.setInt(1, board.getBoardno());
			ps.setInt(2, board.getUserid());
			ps.setString(3, board.getTitle());
			ps.setString(4, board.getContent());
			ps.setString(5, board.getIntro());

			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		System.out.println("[TEST] RecipeDaoImpl - insert(Connection conn, Recipe board) 리턴 res : " + res);
		return res;
	}

	@Override
	public String selectNickByUserid(Connection conn, Recipe viewBoard) {
		System.out.println("[TEST] RecipeDaoImpl - selectNickByUserid(Connection conn, Recipe viewBoard) 호출");
		
		//SQL 작성
		String sql = "";
		sql += "SELECT nick FROM member";
		sql += " WHERE member_no = ?";
		
		//결과 저장할 String 변수
		String usernick = null;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setInt(1, viewBoard.getUserid()); //조회할 id 적용
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				usernick = rs.getString("nick");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 결과 반환
		System.out.println("[TEST] RecipeDaoImpl - selectNickByUserid(Connection conn, Recipe viewBoard) 리턴 usernick : " + usernick);
		return usernick;
		
	}
	
	@Override
	public int selectBoardno(Connection conn) {
		System.out.println("[TEST] RecipeDaoImpl - selectBoardno(Connection conn) 호출");
		
		//SQL 작성
		String sql = "";
		sql += "SELECT recipe_seq.nextval FROM dual";
		
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
		System.out.println("[TEST] RecipeDaoImpl - selectBoardno(Connection conn) 리턴 nextBoardno : " + nextBoardno);
		return nextBoardno;
	}
	
	@Override
	public int insertFile(Connection conn, RecipeFile boardFile) {
		System.out.println("[TEST] RecipeDaoImpl -  insertFile(Connection conn, RecipeFile boardFile) 호출");
		
		String sql = "";
		sql += "INSERT INTO recipeimg( img_no, BOARD_NO, ORIGIN_NAME, STORED_NAME, PATH, FILESIZE, WRITE_DATE)";
		sql += " VALUES (recipeimg_seq.nextval, ?, ?, ?, ?, ?, ?)";
		
		int res = 0;
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, boardFile.getBoardno());
			ps.setString(2, boardFile.getOriginname());
			ps.setString(3, boardFile.getStoredname());
			ps.setString(4, boardFile.getPath());
			ps.setInt(5, boardFile.getFilesize());
			ps.setDate(6, (Date) boardFile.getWriteDate());
//			new Date(refItem.getExpireDate().getTime())
//			new java.sql.Date(new java.util.Date().getTime())

			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		System.out.println("[TEST] RecipeDaoImpl -  insertFile(Connection conn, RecipeFile boardFile) 리턴 res : " + res);
		return res;
	}
	
	@Override
	public RecipeFile selectFile(Connection conn, Recipe viewBoard) {
		System.out.println("[TEST] RecipeDaoImpl -  selectFile(Connection conn, Recipe viewBoard) 호출");
		
		//SQL 작성
		String sql = "";
		sql += "SELECT";
		sql += "	img_no";
		sql += "	, board_no";
		sql += "	, origin_name";
		sql += "	, stored_name";
		sql += "	, filesize";
		sql += "	, write_date";
		sql += " FROM recipeimg";
		sql += " WHERE board_no = ?";
		
		//결과 저장할 DTO객체
		RecipeFile boardFile = null;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setInt(1, viewBoard.getBoardno());
			
			rs = ps.executeQuery(); //SQL수행 및 결과집합 저장

			while( rs.next() ) {
				boardFile = new RecipeFile(); //결과값 저장 객체
				
				//결과값 행 처리
				boardFile.setFileno( rs.getInt("img_no") );
				boardFile.setBoardno( rs.getInt("board_no") );
				boardFile.setOriginname( rs.getString("origin_name") );
				boardFile.setStoredname( rs.getString("stored_name") );
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
		System.out.println("[TEST] RecipeDaoImpl -  selectFile(Connection conn, Recipe viewBoard) 리턴 boardFile : " + boardFile);
		return boardFile;
	}
	
	@Override
	public int update(Connection conn, Recipe board) {
		System.out.println("[TEST] RecipeDaoImpl -  update(Connection conn, Recipe board) 호출");
		
		String sql = "";
		sql += "UPDATE recipe";
		sql += " SET title = ?,";
		sql += " 	content = ?";
		sql += " WHERE board_no = ?";
		
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
		
		System.out.println("[TEST] RecipeDaoImpl -  update(Connection conn, Recipe board) 리턴 res" + res);
		return res;
	}

	@Override
	public int delete(Connection conn, Recipe board) {
		System.out.println("[TEST] RecipeDaoImpl -  delete(Connection conn, Recipe board) 호출");
		
		String sql = "";
		sql += "DELETE recipe";
		sql += " WHERE board_no = ?";
		
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
		
		System.out.println("[TEST] RecipeDaoImpl -  delete(Connection conn, Recipe board) 리턴 res : " + res);
		return res;
	}
	
	@Override
	public int deleteFile(Connection conn, Recipe board) {
		System.out.println("[TEST] RecipeDaoImpl -  deleteFile(Connection conn, Recipe board) 호출");
		
		String sql = "";
		sql += "DELETE recipeimg";
		sql += " WHERE board_no = ?";
		
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
		
		System.out.println("[TEST] RecipeDaoImpl -  deleteFile(Connection conn, Recipe board) 리턴 res : " + res);
		return res;
	}

	@Override
	public int setFollow(Connection conn, int followee_memberno, int follower_memberno) {
		System.out.println("[TEST] RecipeDaoImpl -  setFollow(conn, int, int)  호출");
		
		String sql = "";
		sql += "INSERT INTO follow( followee, follower )";
		sql += " VALUES (?, ?)";
		
		int res = 0;
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, followee_memberno);
			ps.setInt(2, follower_memberno);

			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		System.out.println("[TEST] RecipeDaoImpl -  setFollow(conn, int, int)  리턴 res : " + res);
		return res;
	}

}
