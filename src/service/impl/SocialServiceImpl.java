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
import dao.face.SocialDao;
import dao.impl.SocialDaoImpl;
import dto.Follow;
import dto.Member;
import dto.ProfileFile;
import dto.SocialMember;
import dto.SocialMember;
import dto.SocialMember;
import service.face.SocialService;
import util.Paging;

public class SocialServiceImpl implements SocialService {

	private SocialDao boardDao = new SocialDaoImpl();

	@Override
	public SocialMember getProfileno(HttpServletRequest req) {
		System.out.println("[TEST] SocialServiceImpl - getProfileno(HttpServletRequest req) 호출");
		//전달파라미터 boardno를 저장할 DTO객체 생성
		SocialMember memberno = new SocialMember();
		
//		String param = req.getParameter("memberno"); //0322 1138수정 (세션에서 보드넘버를 받아오므로 수정, 추후 개선여지있음)
		String param = "" + req.getSession().getAttribute("memberno"); //0322 1203 (랭킹에서 링크할때 파라미터에 담겨있는 방식으로 하지 않으면 로그인한사람 페이지로만 이동함 아맞다! 당연하지이게!맞네 다시메모장 ㄱㄱ)
		
		if( param != null && !"".equals( param ) ) {
			memberno.setMemberno( Integer.parseInt(param) );
		} else {
			System.out.println("[WARN] MemberService getMemberno() - memberno값이 null이거나 비어있음");
		}
		
		System.out.println("[TEST] SocialServiceImpl - getProfileno(HttpServletRequest req) 리턴 memberno : " + memberno);
		return memberno;
	}

	@Override
	public SocialMember getMemberno(HttpServletRequest req) {
		System.out.println("[TEST] SocialServiceImpl - getMemberno(HttpServletRequest req) 호출");
		//전달파라미터 boardno를 저장할 DTO객체 생성
		SocialMember memberno = new SocialMember();
		
		String param = req.getParameter("memberno"); 
		
		if( param != null && !"".equals( param ) ) {
			memberno.setMemberno( Integer.parseInt(param) );
		} else {
			System.out.println("[WARN] MemberService getMemberno() - memberno값이 null이거나 비어있음");
		}
		
		System.out.println("[TEST] SocialServiceImpl - getMemberno(HttpServletRequest req) 리턴 memberno : " + memberno);
		return memberno;
	}

	@Override
	public Paging getPaging(HttpServletRequest req) {
		System.out.println("[TEST] SocialMemberServiceImpl - getPaging(HttpServletRequest req) 호출");

		//전달파라미터 curPage 추출하기
		String param = req.getParameter("curPage");
		int curPage = 0;
		if( param != null && !"".equals( param ) ) {
			curPage = Integer.parseInt(param);
		} else {
			System.out.println("[WARN] SocialMemberService getPaging() - curPage값이 null이거나 비어있음");
		}
		
		//총 게시글 수 조회하기
		int totalCount = boardDao.selectCntAll(JDBCTemplate.getConnection());
		
		//Paging 객체 생성 - 페이징 계산
		Paging paging = new Paging(totalCount, curPage);
		
		System.out.println("[TEST] SocialMemberServiceImpl - getPaging(HttpServletRequest req) 리턴 - paging : " + paging);
		return paging;
	}

	@Override
	public List<SocialMember> getList(Paging paging) {
		System.out.println("[TEST] SocialServiceImpl - getList(Paging paging) 호출");
		
		//페이징 적용해서 조회 결과 반환
		System.out.println("[TEST] SocialServiceImpl - getList(Paging paging) 리턴 boardDao.selectAll( JDBCTemplate.getConnection(), paging ) : " + boardDao.selectAll( JDBCTemplate.getConnection(), paging ));
		return boardDao.selectAll( JDBCTemplate.getConnection(), paging );
	}

	@Override
	public List<Follow> getListFollow(Paging paging, HttpServletRequest req) {
		System.out.println("[TEST] SocialServiceImpl - getListFollow() 호출");
		
		//페이징 적용해서 조회 결과 반환
		System.out.println("[TEST] SocialServiceImpl - getListFollow() 리턴 boardDao.selectAll() : " + boardDao.selectAllFollow( JDBCTemplate.getConnection(), paging, req ));
		return boardDao.selectAllFollow( JDBCTemplate.getConnection(), paging, req );
	}

	@Override
	public List<Follow> getListFollower(Paging paging, HttpServletRequest req) {
		System.out.println("[TEST] SocialServiceImpl - getListFollower() 호출");
		
		//페이징 적용해서 조회 결과 반환
		System.out.println("[TEST] SocialServiceImpl - getListFollower() 리턴 boardDao.selectAll() : " + boardDao.selectAllFollow( JDBCTemplate.getConnection(), paging, req ));
		return boardDao.selectAllFollower( JDBCTemplate.getConnection(), paging, req );
	}
	
	@Override
	public SocialMember view(SocialMember socialMember) {
		System.out.println("[TEST] SocialServiceImpl - view() 호출");
		Connection conn = JDBCTemplate.getConnection();
		
		//게시글 조회
		SocialMember board = boardDao.selectBoardByBoardno(conn, socialMember);
		
		System.out.println("[TEST] SocialServiceImpl - view() 리턴 board : " + board);
		return board;
	}

	@Override
	public SocialMember viewFile(SocialMember viewBoard) {
		System.out.println("[TEST] SocialServiceImpl - viewFile() 호출");
		
		SocialMember sm = boardDao.selectFile(JDBCTemplate.getConnection(), viewBoard);
		
		System.out.println("[TEST] SocialServiceImpl - viewFile() 리턴 boardDao.selectFile() : " + sm);
		return sm;
	}

	@Override
	public void update(HttpServletRequest req) {
		System.out.println("[TEST] SocialMemberServiceImpl - update(HttpServletRequest req) 호출");
		
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
		SocialMember board = new SocialMember();
		
//		//첨부파일 정보 DTO객체
//		SocialMember boardFile = new SocialMember();
	

		
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
				if( "memberno".equals(key) ) {
					board.setMemberno( Integer.parseInt(value) );
					System.out.println("key value memberno 삽입 : " + board.getMemberno());
				}
				
			} //if( item.isFormField() ) end
			
			
			
			//--- 3) 파일에 대한 처리 ---
			if( !item.isFormField() ) {
				
				//UUID생성
				String uid = UUID.randomUUID().toString().split("-")[0]; //8자리 UUID
				
				//파일 업로드 폴더
				File uploadFolder = new File( context.getRealPath("/resources/img/social") );
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
				board.setOrigin_name(origin);
				board.setStored_name(stored);
				board.setFilesize( (int)item.getSize() );
				
			} //if( !item.isFormField() ) end
			
		} //while( iter.hasNext() ) end
		
		
		
		//DB연결 객체
		Connection conn = JDBCTemplate.getConnection();
		
		
		
		//게시글 정보 삽입
//		if(board.getTitle()==null || "".equals(board.getTitle())) {
//			board.setTitle("(제목없음)");
//		}
//		if( boardDao.update(conn, board) > 0 ) {
//			JDBCTemplate.commit(conn);
//		} else {
//			JDBCTemplate.rollback(conn);
//		}
		
		
		//첨부파일 정보 삽입
		if( board.getFilesize() != 0 ) {
//			board.setMemberno(board.getMemberno());
			System.out.println("파일정보삽입 : " + board.getMemberno());
			
			if( boardDao.insertFile(conn, board) > 0 ) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
		
		System.out.println("[TEST] SocialMemberServiceImpl - update(HttpServletRequest req) 리턴");
		return;
	}

	@Override
	public int checkFollowPK(int followee, int follower) {
		System.out.println("[TEST] RecipeServiceImpl - checkFollowPK(int, int) 호출");
		
		int res = 0;
		int a = 0;
		Connection conn = JDBCTemplate.getConnection();
		Follow dbValue = boardDao.checkFollowPK(conn, followee, follower);
		//dvValue에 들어있는 멤버값이 양수면 중복

		try {
			a = dbValue.getFollowee(); 
		} catch (NullPointerException e) {
			res = 1;
		}
		
		System.out.println("[TEST] RecipeServiceImpl - checkFollowPK(int, int) 리턴 res : " + res);
		return res; //1이면 통과
	}

	@Override
	public void setFollow(int followee, int follower, HttpServletRequest req) {
		System.out.println("[TEST] RecipeServiceImpl - setFollow() 호출");
		
		Connection conn = JDBCTemplate.getConnection();
		
		if( boardDao.setFollow(conn, followee, follower, req) > 0 ) {
			req.getSession().setAttribute("follow_success_flag", true);
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		System.out.println("[TEST] RecipeServiceImpl - setFollow() 리턴");
		return;
	}

	
}
