package service.impl;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.RefDao;
import dao.impl.RefDaoImpl;
import dto.Ref;
import dto.RefItem;
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

	@Override
	public void getRefItemList(HttpServletRequest req) {
		// 쿼리파라미터 분석
		int refCode = Integer.parseInt(req.getParameter("refCode"));
		logger.info("쿼리파라미터로 전달된 냉장고 코드: " + refCode);
		
		// DB Connection 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// DB 에서 냉장고 코드에 해당하는 품목들을 조회하여 리스트로 반환한다.
		List<RefItem> list = refDao.getItemListByRefCode(conn, refCode);
		logger.info("조회된 냉장고 품목의 목록: " + list);
		// View 에 전달할 아이템 목록 저장
		req.setAttribute("itemList", list);
	}

	@Override
	public void getFilteredRefItemList(HttpServletRequest req) {
		// 쿼리파라미터 분석
		int refCode = Integer.parseInt(req.getParameter("refCode"));
		int status = Integer.parseInt(req.getParameter("status"));
		logger.info("쿼리파라미터로 전달된 상태코드: " + status);
		logger.info("쿼리파라미터로 전달된 냉장고 코드: " + refCode);
				
		// DB Connection 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// DB에서 냉장고품목을 상태코드로 필터링한 품목을 조회하여 리스트로 반환한다.
		List<RefItem> list = refDao.findAllByFiltering(conn, refCode, status);
		logger.info("상태코드로 필터링된 냉장고 품목의 목록: " + list);
		
		// View에 전달할 아이템 목록 저장
		req.setAttribute("filterdItemList", list);
	}

	@Override
	public void getOrderedRefItemList(HttpServletRequest req) {
		// 쿼리파라미터 분석
		int refCode = Integer.parseInt(req.getParameter("refCode"));
		int status = Integer.parseInt(req.getParameter("status"));
		String orderBy = req.getParameter("orderBy");
		logger.info("쿼리파라미터로 전달된 상태코드: " + status);
		logger.info("쿼리파라미터로 전달된 냉장고 코드: " + refCode);
		logger.info("쿼리파라미터로 전달된 정렬기준: " + orderBy);
		
		
		// DB Connection 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// DB에서 냉장고품목을 상태코드로 필터링한 품목을 조회하여 리스트로 반환한다.
		List<RefItem> list = refDao.findAllByFilteringAndOrdering(conn, refCode, status, orderBy);
		logger.info("정렬과 필터링된 냉장고 품목의 목록: " + list);
		
		// View에 전달할 아이템 목록 저장
		req.setAttribute("orderedAndFilteredList", list);
		
	}

	@Override
	public void addRefItem(HttpServletRequest req) {
		// 쿼리파라미터 분석
		int refCode = Integer.parseInt(req.getParameter("refCode"));
		int ingrCtyCode = Integer.parseInt(req.getParameter("ingrCtyCode"));
		String itemName = req.getParameter("itemName");
		String itemQty = req.getParameter("itemQty");
		int status = Integer.parseInt(req.getParameter("status"));
		String expireDate = req.getParameter("expireDate");  // 입력받은 날짜 형식 어떻게 변환할 껀지 생각해보기
		String note = req.getParameter("note");
		
		// 유통기한 날짜 String -> Data 변환
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date exDate = null;
		try {
			exDate = sdf.parse(expireDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		// 냉장고 품목 DTO 생성
		RefItem refItem = new RefItem();
		
		// DTO에 정보 저장
		refItem.setIngrCtyCode(ingrCtyCode);
		refItem.setItemName(itemName);
		refItem.setItemQty(itemQty);
		refItem.setStatus(status);
		refItem.setExpireDate(exDate);
		refItem.setNote(note);
		
		// DB Connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// DTO 정보로 냉장고 아이템 DB에 추가
		int result = refDao.insert(conn, refCode, refItem);
		
		// DB 결과에 따라 트랜잭션 처리
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		// View에 전달 할 결과 저장
		
	}

	@Override
	public void deleteRefItem(HttpServletRequest req) {
		// 쿼리 파라미터 분석
		int itemNo = Integer.parseInt(req.getParameter("item_no"));
		
		// Connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// DB에서 해당 번호에 해당하는 냉장고 품목 삭제
		int result = refDao.delete(conn, itemNo);
		
		// DB 결과에 따라 트랜잭션 처리
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		// View에 전달 할 결과 저장
		
	}

	@Override
	public void updateRefItem(HttpServletRequest req) {
		// 쿼리 파라미터 분석
		int ingrCtyCode = Integer.parseInt(req.getParameter("ingrCtyCode"));
		String itemName = req.getParameter("itemName");
		String itemQty = req.getParameter("itemQty");
		int status = Integer.parseInt(req.getParameter("status"));
		String expireDate = req.getParameter("expireDate");
		String note = req.getParameter("note");
		
		// 유통기한 날짜 String -> Data 변환
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date exDate = null;
		try {
			exDate = sdf.parse(expireDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		// 냉장고 품목 DTO 생성
		RefItem refItem = new RefItem();
		
		// DTO에 정보 저장
		refItem.setIngrCtyCode(ingrCtyCode);
		refItem.setItemName(itemName);
		refItem.setItemQty(itemQty);
		refItem.setStatus(status);
		refItem.setExpireDate(exDate);
		refItem.setNote(note);
		
		// Connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// DB에서 DTO 정보에 해당하는 냉장고 품목 수정
		int result = refDao.update(conn, refItem);
		
		// DB 결과에 따른 트랜잭션 처리
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		// View에 전달할 결과 저장
		
	}
	
}
