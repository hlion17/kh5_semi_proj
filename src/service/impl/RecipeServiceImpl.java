package service.impl;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import common.JDBCTemplate;
import dao.face.RecipeDao;
import dao.impl.RecipeDaoImpl;
import dto.Recipe;
import dto.RecipeFile;
import service.face.RecipeService;
import util.Paging;

public class RecipeServiceImpl implements RecipeService {
	
	private RecipeDao boardDao = new RecipeDaoImpl();
	
	@Override
	public List<Recipe> getList() {
		System.out.println("[TEST] RecipeServiceImpl - getList() 호출");
		
		//게시글 전체 조회 결과 반환
		return boardDao.selectAll( JDBCTemplate.getConnection() );
	}
	
	@Override
	public List<Recipe> getListRank() {
		System.out.println("[TEST] RecipeServiceImpl - getListRank() 호출");
		
		//게시글 전체 조회 결과 반환
		return boardDao.selectAllRank( JDBCTemplate.getConnection() );
	}

	@Override
	public List<Recipe> getList(Paging paging) {

		//페이징 적용해서 조회 결과 반환
		return boardDao.selectAll( JDBCTemplate.getConnection(), paging );
	}

	@Override
	public Paging getPaging(HttpServletRequest req) {
		System.out.println("[TEST] RecipeDaoImpl - getPaging(HttpServletRequest req) 호출");

		//전달파라미터 curPage 추출하기
		String param = req.getParameter("curPage");
		int curPage = 0;
		if( param != null && !"".equals( param ) ) {
			curPage = Integer.parseInt(param);
		} else {
			System.out.println("[WARN] RecipeService getPaging() - curPage값이 null이거나 비어있음");
		}
		
		//총 게시글 수 조회하기
		int totalCount = boardDao.selectCntAll(JDBCTemplate.getConnection());
		
		//Paging 객체 생성 - 페이징 계산
		Paging paging = new Paging(totalCount, curPage);
		
		System.out.println("[TEST] RecipeDaoImpl - getPaging(HttpServletRequest req) 리턴 - paging : " + paging);
		return paging;
	}

	@Override
	public Recipe getBoardno(HttpServletRequest req) {

		//전달파라미터 boardno를 저장할 DTO객체 생성
		Recipe boardno = new Recipe();
		
		String param = req.getParameter("boardno");
		if( param != null && !"".equals( param ) ) {
			boardno.setBoardno( Integer.parseInt(param) );
		} else {
			System.out.println("[WARN] RecipeService getBoardno() - boardno값이 null이거나 비어있음");
		}

		return boardno;
	}

//	@Override
//	public Recipe getLike(HttpServletRequest req) {
//		
//		//전달파라미터 boardno를 저장할 DTO객체 생성
//		Recipe boardno = new Recipe();
//		
//		String param = req.getParameter("like");
//		if( param != null && !"".equals( param ) ) {
//			boardno.setLike( Integer.parseInt(param) );
//		} else {
//			System.out.println("[WARN] RecipeService getBoardno() - like값이 null이거나 비어있음");
//		}
//		
//		return boardno;
//	}

	@Override
	public void addLike(Recipe boardno, HttpServletRequest req) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		//조회수 증가
		if( boardDao.updateLike(conn, boardno, req) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
	}
	
	@Override
	public Recipe view(Recipe boardno) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		//조회수 증가
		if( boardDao.updateHit(conn, boardno) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		//게시글 조회
		Recipe board = boardDao.selectBoardByBoardno(conn, boardno);
		
		return board;
	}

	@Override
	public void write(HttpServletRequest req) {
		
		//--- 첨부파일 추가하여 게시글 처리하기 ---
		
		//파일업로드형식 인코딩이 맞는지 검사
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		
		//multipart/form-data 형식이 아닐 경우 파일업로드 처리 중단
		if( !isMultipart ) {
			System.out.println("[ERROR] 파일 업로드 형식 데이터가 아님");
			return;
		}
		
		
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		//메모리에서 처리 사이즈 설정
		int maxMem = 1 * 1024 * 1024; // 1MB == 1048576B
		factory.setSizeThreshold(maxMem);
		
		//서블릿컨텍스트 객체
		ServletContext context = req.getServletContext();
		
		//임시파일 폴더
		String path = context.getRealPath("tmp");
		File tmpRepository = new File(path);
		tmpRepository.mkdir();
		factory.setRepository(tmpRepository);
		
		//파일업로드 수행 객체
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		//파일 업로드 용량 제한 사이즈 설정
		int maxFile = 10 * 1024 * 1024; //10MB
		upload.setFileSizeMax(maxFile);

		
		
		//파일 업로드된 데이터 파싱
		List<FileItem> items = null;
		try {
			items = upload.parseRequest(req);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}

		

		//게시글 정보 DTO객체
		Recipe board = new Recipe();
		
		//첨부파일 정보 DTO객체
		RecipeFile boardFile = new RecipeFile();
	

		
		//파일아이템 반복자
		Iterator<FileItem> iter = items.iterator();
		
		while( iter.hasNext() ) {
			FileItem item = iter.next();
			
			//--- 1) 빈 파일에 대한 처리 ---
			if( item.getSize() <= 0 ) {
				
				//빈 파일은 무시하고 다음 FileItem처리로 넘어간다
				continue;
			}
			
			
			//--- 2) 폼 필드에 대한 처리 ---
			if( item.isFormField() ) {
				
				//키 추출하기
				String key = item.getFieldName();
				
				//값 추출하기
				String value = null;
				try {
					 value = item.getString("UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				
				//key에 맞게 value를 DTO에 삽입
				if( "title".equals(key) ) {
					board.setTitle(value);
					
				} else if ( "content".equals(key) ) {
					board.setContent(value);
					
				}
				
			} //if( item.isFormField() ) end
			
			
			
			//--- 3) 파일에 대한 처리 ---
			if( !item.isFormField() ) {
				
				//UUID생성
				String uid = UUID.randomUUID().toString().split("-")[0]; //8자리 UUID
				
				//파일 업로드 폴더
				File uploadFolder = new File( context.getRealPath("upload") );
				uploadFolder.mkdir();

				//파일명 처리
				String origin = item.getName();
				String stored = uid;
				
				//업로드할 파일 객체 생성하기
				File up = new File(uploadFolder, stored);
				
				try {
					item.write(up); // 임시파일 -> 실제 업로드 파일
					item.delete(); // 임시파일 제거
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				//업로드된 파일의 정보를 DTO객체에 저장하기
				boardFile.setOriginname(origin);
				boardFile.setStoredname(stored);
				boardFile.setFilesize( (int)item.getSize() );
				
			} //if( !item.isFormField() ) end
			
		} //while( iter.hasNext() ) end
		
		
		
		//DB연결 객체
		Connection conn = JDBCTemplate.getConnection();
		
		//게시글 번호 생성
		int boardno = boardDao.selectBoardno(conn);
		
		
		//게시글 정보 삽입
		board.setBoardno(boardno);
		if(board.getTitle()==null || "".equals(board.getTitle())) {
			board.setTitle("(제목없음)");
		}
		
		System.out.println("req : " + req.getSession().getAttribute("memberno"));
		System.out.println("int : " + (int)req.getSession().getAttribute("memberno"));
		board.setUserid( (int)req.getSession().getAttribute("memberno") );
//		board.setUserid( "1" );
		
		if( boardDao.insert(conn, board) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		
		//첨부파일 정보 삽입
		if( boardFile.getFilesize() != 0 ) {
			boardFile.setBoardno(boardno);
			
			if( boardDao.insertFile(conn, boardFile) > 0 ) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}	
		
	}

	@Override
	public String getNick(Recipe viewBoard) {
		return boardDao.selectNickByUserid(JDBCTemplate.getConnection(), viewBoard);
	}

	@Override
	public RecipeFile viewFile(Recipe viewBoard) {
		return boardDao.selectFile(JDBCTemplate.getConnection(), viewBoard);
	}

	@Override
	public void update(HttpServletRequest req) {
		
		//파일업로드형식 인코딩이 맞는지 검사
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		
		//multipart/form-data 형식이 아닐 경우 파일업로드 처리 중단
		if( !isMultipart ) {
			System.out.println("[ERROR] 파일 업로드 형식 데이터가 아님");
			return;
		}

		
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		//메모리에서 처리 사이즈 설정
		int maxMem = 1 * 1024 * 1024; // 1MB == 1048576B
		factory.setSizeThreshold(maxMem);
		
		//서블릿컨텍스트 객체
		ServletContext context = req.getServletContext();
		
		//임시파일 폴더
		String path = context.getRealPath("tmp");
		File tmpRepository = new File(path);
		tmpRepository.mkdir();
		factory.setRepository(tmpRepository);
		
		//파일업로드 수행 객체
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		//파일 업로드 용량 제한 사이즈 설정
		int maxFile = 10 * 1024 * 1024; //10MB
		upload.setFileSizeMax(maxFile);
		
		
		
		//파일 업로드된 데이터 파싱
		List<FileItem> items = null;
		try {
			items = upload.parseRequest(req);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}

		

		//게시글 정보 DTO객체
		Recipe board = new Recipe();
		
		//첨부파일 정보 DTO객체
		RecipeFile boardFile = new RecipeFile();
	

		
		//파일아이템 반복자
		Iterator<FileItem> iter = items.iterator();
		
		while( iter.hasNext() ) {
			FileItem item = iter.next();
			
			//--- 1) 빈 파일에 대한 처리 ---
			if( item.getSize() <= 0 ) {
				
				//빈 파일은 무시하고 다음 FileItem처리로 넘어간다
				continue;
			}
			
			
			//--- 2) 폼 필드에 대한 처리 ---
			if( item.isFormField() ) {
				
				//키 추출하기
				String key = item.getFieldName();
				
				//값 추출하기
				String value = null;
				try {
					 value = item.getString("UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				
				//key에 맞게 value를 DTO에 삽입
				if( "boardno".equals(key) ) {
					board.setBoardno( Integer.parseInt(value) );
					
				} else if( "title".equals(key) ) {
					board.setTitle(value);
					
				} else if ( "content".equals(key) ) {
					board.setContent(value);
					
				}
				
			} //if( item.isFormField() ) end
			
			
			
			//--- 3) 파일에 대한 처리 ---
			if( !item.isFormField() ) {
				
				//UUID생성
				String uid = UUID.randomUUID().toString().split("-")[0]; //8자리 UUID
				
				//파일 업로드 폴더
				File uploadFolder = new File( context.getRealPath("upload") );
				uploadFolder.mkdir();

				//파일명 처리
				String origin = item.getName();
				String stored = uid;
				
				//업로드할 파일 객체 생성하기
				File up = new File(uploadFolder, stored);
				
				try {
					item.write(up); // 임시파일 -> 실제 업로드 파일
					item.delete(); // 임시파일 제거
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				//업로드된 파일의 정보를 DTO객체에 저장하기
				boardFile.setOriginname(origin);
				boardFile.setStoredname(stored);
				boardFile.setFilesize( (int)item.getSize() );
				
			} //if( !item.isFormField() ) end
			
		} //while( iter.hasNext() ) end
		
		
		
		//DB연결 객체
		Connection conn = JDBCTemplate.getConnection();
		
		
		
		//게시글 정보 삽입
		if(board.getTitle()==null || "".equals(board.getTitle())) {
			board.setTitle("(제목없음)");
		}
		if( boardDao.update(conn, board) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		
		//첨부파일 정보 삽입
		if( boardFile.getFilesize() != 0 ) {
			boardFile.setBoardno(board.getBoardno());
			
			if( boardDao.insertFile(conn, boardFile) > 0 ) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
		
	}
	
	@Override
	public void delete(Recipe board) {
		Connection conn = JDBCTemplate.getConnection();
		
		if( boardDao.deleteFile(conn, board) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		if( boardDao.delete(conn, board) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
	}
	
}
