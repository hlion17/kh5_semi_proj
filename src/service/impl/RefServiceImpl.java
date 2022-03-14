package service.impl;

import java.sql.Connection;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.RefDao;
import dao.impl.RefDaoImpl;
import dto.Ref;
import service.face.RefService;

public class RefServiceImpl implements RefService {
	
	private RefDao refDao = new RefDaoImpl();
	private static Logger logger = Logger.getLogger(RefServiceImpl.class.getName());

	@Override
	public void chooseRef(HttpServletRequest req) {
		// 쿼리파리미터 값 받아오기
		// 일단 쿼리파라터로 멤버 아이디 받아 오는 것으로 처리
		// 이후에 세션 처리
		String memberId = req.getParameter("memberId");
		logger.info("쿼리파라미터로 전달된 회원아이디: " + memberId);
		
		// 쿼리파라미터 검증
		if ("".equals(memberId) || memberId == null) {
			// 일단 로그남기는 처리만 해놓음
			logger.warning("회원아이디가 없음");
		}
		
		// DB Connection 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// DB 에서 냉장고 데이터 리스트로 가져오기 
		List<Ref> list = refDao.findByMemberId(conn, memberId);
		logger.info("회원 아이디로 조회된 냉장고 목록: " + list);
		
		// View 에 전달할 데이터 저장
		req.setAttribute("list", list);
				
	}
	
}
