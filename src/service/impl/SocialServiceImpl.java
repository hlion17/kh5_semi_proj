package service.impl;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.SocialDao;
import dao.impl.SocialDaoImpl;
import dto.Member;
import dto.ProfileFile;
import dto.Recipe;
import dto.SocialMember;
import service.face.SocialService;
import util.Paging;

public class SocialServiceImpl implements SocialService {

	private SocialDao boardDao = new SocialDaoImpl();
	
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
		System.out.println("[TEST] RecipeServiceImpl - getPaging(HttpServletRequest req) 호출");

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
		
		System.out.println("[TEST] RecipeServiceImpl - getPaging(HttpServletRequest req) 리턴 - paging : " + paging);
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
	public SocialMember view(SocialMember memberno) {
		System.out.println("[TEST] SocialServiceImpl - view() 호출");
		Connection conn = JDBCTemplate.getConnection();
		
		//게시글 조회
		SocialMember board = boardDao.selectBoardByBoardno(conn, memberno);
		
		System.out.println("[TEST] SocialServiceImpl - view() 리턴 board : " + board);
		return memberno;
	}

	
	@Override
	public ProfileFile viewFile(SocialMember viewBoard) {
		System.out.println("[TEST] SocialServiceImpl - viewFile() 호출");
		
		System.out.println("[TEST] SocialServiceImpl - viewFile() 리턴 boardDao.selectFile() : " + boardDao.selectFile(JDBCTemplate.getConnection(), viewBoard));
		return boardDao.selectFile(JDBCTemplate.getConnection(), viewBoard);
	}

}
