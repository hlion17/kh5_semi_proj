package service.impl;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import common.JDBCTemplate;
import dao.face.MemberDao;
import dao.face.RefDao;
import dao.impl.MemberDaoImpl;
import dao.impl.RefDaoImpl;
import dto.Member;
import dto.ProfileFile;
import service.face.MemberService;
import util.Paging;

public class MemberServiceImpl implements MemberService {

	private MemberDao memberDao = new MemberDaoImpl();
	private RefDao refDao = new RefDaoImpl();
	private MemberDao boardDao = new MemberDaoImpl();
	
	@Override
	public Member getLoginMember(HttpServletRequest req) {
		
		Member member = new Member();
		
		member.setMemberid( req.getParameter("memberid") );
		member.setMemberpw( req.getParameter("memberpw") );
		
		
		return member;
	}
	
	@Override
	public boolean login(Member member) {
		
		System.out.println("로그인서비스임플 " + member);
		//로그인 인증 성공
		if( memberDao.selectCntMemberByMemberidMemberpw(JDBCTemplate.getConnection(), member) > 0 ) {
			return true;
		}
		
		//로그인 인증 실패
		return false;
	}

	@Override
	public Member info(Member member) {
		
		return memberDao.selectMemberByMemberid(JDBCTemplate.getConnection(), member);
		
	}
	
	@Override
	public Member getJoinMember(HttpServletRequest req) {
		
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		Member member = new Member();
		
		member.setMemberid( req.getParameter("memberid") );
		member.setMemberpw( req.getParameter("memberpw") );
		member.setMembername( req.getParameter("membername") );
		member.setNick( req.getParameter("nick") );
		member.setGender(req.getParameter("gender"));
		member.setEmail(req.getParameter("email"));
		member.setPhone(req.getParameter("phone"));
		member.setZipcode(req.getParameter("zipcode"));
		member.setAddress(req.getParameter("address"));
		member.setIntro(req.getParameter("intro"));
		
		return member;
	}
	
	@Override
	public Member join(Member member) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		// edited by young
		// 회원가입 처리
		int result = memberDao.insert(conn, member);
		
		// 회원 ID를 얻어와 냉장고와 매핑 테이블에 insert 할 정보를 얻어온다.
		String memberId = member.getMemberid();
		Member foundMember = memberDao.selectrefCodeAndMemberNo(conn, memberId);
		
		// 냉장고, 냉장고_매핑 테이블에 회원정보 insert
		int result2 = refDao.insertRef(conn, foundMember);
		int result3 = refDao.insertRef_Member(conn, foundMember);
		
		// '회원가입, 냉장고 생성, 냉장고_회원 매핑 테이블에 관련 정보 삽입'이 하나의 트랜잭션으로 관리되도록 처리
		if(result == 1 && result2 == 1 && result3 == 1) {
			JDBCTemplate.commit(conn);
			return member;
		} else {
			JDBCTemplate.rollback(conn);
			return null;
		}
		
	}

	@Override
	public boolean checkIdDup(Member member) {
		//아이디 중복이라 사용 불가능
		if( memberDao.idCheck(JDBCTemplate.getConnection(), member) > 0 ) {
//			System.out.println("checkIdDup에서 false");
			return false;
		}
		
		//아이디 중복이 아니라 사용 가능!
//		System.out.println("checkIdDup에서 true");
		return true;
	}

	@Override
	public Member getIdMember(HttpServletRequest req) {
		Member member = new Member();
		
		member.setMemberid( req.getParameter("memberid") );
		
		
		return member;
	}

	@Override
	public String checkEmailPhone(Member member) {
		
		String Findedid = null;
		// 일치하는 정보가 있음(아이디 찾기 가능)
		if (memberDao.idFind(JDBCTemplate.getConnection(), member) != null) {
			Findedid = memberDao.idFind(JDBCTemplate.getConnection(), member);
		} else {
			System.out.println("Service에서 실패함");
		}
		return Findedid;

	}
	
	@Override
	public Member getIdFindMember(HttpServletRequest req) {
		Member member = new Member();
		
		member.setEmail( req.getParameter("email") );
		member.setPhone( req.getParameter("phone") );
		
		
		return member;
	}

	@Override
	public String checkIdEmailPhone(Member member) {
		String Findedpw = null;
		// 일치하는 정보가 있음(비밀번호 찾기 가능)
		if (memberDao.pwFind(JDBCTemplate.getConnection(), member) != null) {
			Findedpw = memberDao.pwFind(JDBCTemplate.getConnection(), member);
		} else {
			System.out.println("Service에서 실패함");
		}
		return Findedpw;
	}

	@Override
	public Member getPwFindMember(HttpServletRequest req) {
		Member member = new Member();

		member.setMemberid(req.getParameter("memberid"));
		member.setEmail(req.getParameter("email"));
		member.setPhone(req.getParameter("phone"));

		return member;
	}

	@Override
	public Member updateMember(Member member) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		// 일치하는 정보가 있음
		if (memberDao.updateInfo(conn, member) > 0) {
			JDBCTemplate.commit(conn);
			return member;
		} else {
			JDBCTemplate.rollback(conn);
			return null;
		}
	}
	
	@Override
	public Member getUpdateInfoMember(HttpServletRequest req) {
		
		
		Member member = new Member();

		HttpSession session = req.getSession();
		
		
		member.setMemberpw(req.getParameter("memberpw"));
		member.setMembername(req.getParameter("membername"));
		member.setNick(req.getParameter("nick"));
		member.setEmail(req.getParameter("email"));
		member.setGender(req.getParameter("gender"));
		member.setPhone(req.getParameter("phone"));
		member.setZipcode(req.getParameter("zipcode"));
		member.setAddress(req.getParameter("address"));
		member.setIntro(req.getParameter("intro"));
		member.setMemberid((String)session.getAttribute("memberid"));
		
		
		return member;
		
	}


	
	public Member getMemberInfoBySession(HttpServletRequest req) {

		System.out.println("getMemberInfoSession 메소드 진입");
		Member member = new Member();

		HttpSession session = req.getSession();

		member.setMemberid((String) session.getAttribute("memberid"));
		member.setMemberpw((String) session.getAttribute("memberpw"));
		member.setMembername((String) session.getAttribute("membername"));
		member.setNick((String) session.getAttribute("nick"));
		member.setEmail((String) session.getAttribute("email"));
		member.setGender((String) session.getAttribute("gender"));
		member.setPhone((String) session.getAttribute("phone"));
		member.setZipcode((String) session.getAttribute("zipcode"));
		member.setAddress((String) session.getAttribute("address"));
		member.setIntro((String) session.getAttribute("intro"));
		
		System.out.println("getMemberInfoSession 메소드 리턴값" + member);
		return member;
		
	}

	@Override
	public List<Member> getList(Paging paging) {
		System.out.println("[TEST] MemberServiceImpl - getList(Paging paging) 호출");
		
		//페이징 적용해서 조회 결과 반환
		System.out.println("[TEST] MemberServiceImpl - getList(Paging paging) 리턴 boardDao.selectAll( JDBCTemplate.getConnection(), paging ) : " + boardDao.selectAll( JDBCTemplate.getConnection(), paging ));
		return boardDao.selectAll( JDBCTemplate.getConnection(), paging );
	}

	@Override
	public void uploadProfil(HttpServletRequest req) {
		// 파일업로드형식 인코딩이 맞는지 검사
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);

		// multipart/form-data 형식이 아닐 경우 파일업로드 처리 중단
		if (!isMultipart) {
			System.out.println("[ERROR] 파일 업로드 형식 데이터가 아님");
			return;
		}

		DiskFileItemFactory factory = new DiskFileItemFactory();

		// 메모리에서 처리 사이즈 설정
		int maxMem = 1 * 1024 * 1024; // 1MB == 1048576B
		factory.setSizeThreshold(maxMem);

		// 서블릿컨텍스트 객체
		ServletContext context = req.getServletContext();

		// 임시파일 폴더
		String path = context.getRealPath("tmp");
		File tmpRepository = new File(path);
		tmpRepository.mkdir();
		factory.setRepository(tmpRepository);

		// 파일업로드 수행 객체
		ServletFileUpload upload = new ServletFileUpload(factory);

		// 파일 업로드 용량 제한 사이즈 설정
		int maxFile = 10 * 1024 * 1024; // 10MB
		upload.setFileSizeMax(maxFile);

		// 파일 업로드된 데이터 파싱
		List<FileItem> items = null;
		try {
			items = upload.parseRequest(req);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		//

		// 게시글 정보 DTO객체
		Member memberno = new Member();

		// 첨부파일 정보 DTO객체
		ProfileFile boardFile = new ProfileFile();

		// 파일아이템 반복자
		Iterator<FileItem> iter = items.iterator();

		while (iter.hasNext()) {
			FileItem item = iter.next();

			// --- 1) 빈 파일에 대한 처리 ---
			if (item.getSize() <= 0) {

				// 빈 파일은 무시하고 다음 FileItem처리로 넘어간다
				continue;
			}

			// --- 2) 폼 필드에 대한 처리 ---
			if (item.isFormField()) {

				// 키 추출하기
				String key = item.getFieldName();

				// 값 추출하기
				String value = null;
				try {
					value = item.getString("UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}


			} // if( item.isFormField() ) end

			// --- 3) 파일에 대한 처리 ---
			if (!item.isFormField()) {

				// UUID생성
				String uid = UUID.randomUUID().toString().split("-")[0]; // 8자리 UUID

				// 파일 업로드 폴더
				File uploadFolder = new File(context.getRealPath("upload"));
				uploadFolder.mkdir();

				// 파일명 처리
				String origin = item.getName();
				String stored = uid;

				// 업로드할 파일 객체 생성하기
				File up = new File(uploadFolder, stored);

				try {
					item.write(up); // 임시파일 -> 실제 업로드 파일
					item.delete(); // 임시파일 제거

				} catch (Exception e) {
					e.printStackTrace();
				}

				// 업로드된 파일의 정보를 DTO객체에 저장하기
				boardFile.setOriginname(origin);
				boardFile.setStoredname(stored);
				boardFile.setFilesize((int) item.getSize());

			} // if( !item.isFormField() ) end

		} // while( iter.hasNext() ) end

		// DB연결 객체
		Connection conn = JDBCTemplate.getConnection();



		// 첨부파일 정보 삽입
		if (boardFile.getFilesize() != 0) {
			boardFile.setMemberno(memberno.getMemberno());

			if (memberDao.insertProFile(conn, boardFile) > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}

	}

	

	

}
