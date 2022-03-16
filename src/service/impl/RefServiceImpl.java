package service.impl;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import common.JDBCTemplate;
import dao.face.MemberDao;
import dao.face.RefDao;
import dao.impl.MemberDaoImpl;
import dao.impl.RefDaoImpl;
import dto.Ref;
import dto.RefItem;
import service.face.MemberService;
import service.face.RefService;

public class RefServiceImpl implements RefService {
	
	private RefDao refDao = new RefDaoImpl();
	private MemberDao memberDao = new MemberDaoImpl();
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
		req.setAttribute("refCode", refCode);
	}

	// 미완성 - 밑에 메서드로 대처 가능할것 같다.
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

	// 미완성
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
		req.setAttribute("itemList", list);
		
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
		logger.info("품목 추가 파라미터 정보: ");
		logger.info("refCode: " + refCode);
		logger.info("ingrCytCode: " + ingrCtyCode);
		logger.info("itemName: " + itemName);
		logger.info("itemQty: " + itemQty);
		logger.info("status: " + status);
		logger.info("expireDate: " + expireDate);
		logger.info("note: " + note);
		
		// 유통기한 날짜 String -> Data 변환
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
//		Date exDate = null;
//		try {
//			exDate = sdf.parse(expireDate);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		
		// 냉장고 품목 DTO 생성
		RefItem refItem = new RefItem();
		
		// DTO에 정보 저장
		refItem.setIngrCtyCode(ingrCtyCode);
		refItem.setItemName(itemName);
		refItem.setItemQty(itemQty);
		refItem.setStatus(status);
		refItem.setExpireDate(expireDate);
		refItem.setNote(note);
		
		// DB Connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// DB에서 아이템 식별값 생성해서 가져오기
		refDao.getRefItemNo(conn, refItem);
		
		// DTO 정보로 냉장고 아이템 DB에 추가
		int result = refDao.insertItem(conn, refCode, refItem);
		int result2 = refDao.insertRef_Item(conn, refCode, refItem);
		logger.info("DB 품목 삽입 처리 결과 : " + result);
		logger.info("DB 냉장고_품목 삽입 처리 결과 : " + result2);
		
		// DB 결과에 따라 트랜잭션 처리
		if (result > 0 && result2 > 0) {
			JDBCTemplate.commit(conn);
			logger.info("냉장고 품목 insert 결과 commit 됨");
		} else {
			JDBCTemplate.rollback(conn);
			logger.info("냉장고 품목 insert 결과 rollback 됨 ");
		}
		
		// View에 전달 할 결과 저장
		
	}

	@Override
	public void deleteRefItem(HttpServletRequest req) {
		// 쿼리 파라미터 분석
		int itemNo = Integer.parseInt(req.getParameter("itemNo"));
		
		// Connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// DB에서 해당 번호에 해당하는 냉장고 품목 삭제
		int result = refDao.deleteRef_Item(conn, itemNo);
		int result2 = refDao.deleteItem(conn, itemNo);
		logger.info("냉장고 품목 맵핑테이블 삭제 결과: " + result);
		logger.info("냉장고 품목 삭제 결과: " + result2);
		
		// DB 결과에 따라 트랜잭션 처리
		if (result > 0 && result2 > 0) {
			JDBCTemplate.commit(conn);
			logger.info("냉장고 품목 삭제 커밋 됨");
		} else {
			JDBCTemplate.rollback(conn);
			logger.info("냉장고 품목 삭제 롤백 됨");
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
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
//		Date exDate = null;
//		try {
//			exDate = sdf.parse(expireDate);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		
		// 냉장고 품목 DTO 생성
		RefItem refItem = new RefItem();
		
		// DTO에 정보 저장
		refItem.setIngrCtyCode(ingrCtyCode);
		refItem.setItemName(itemName);
		refItem.setItemQty(itemQty);
		refItem.setStatus(status);
		refItem.setExpireDate(expireDate);
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

	@Override
	public void shareRef(HttpServletRequest req) {
		// 요청 파라미터 분석 - 공유 대상의 냉장고 코드
		int refCode = Integer.parseInt(req.getParameter("refCode"));
		logger.info("refService - 요청파라미터: " + refCode);
		
		// 세션분석 - 로그인한 회원의 아이디
		HttpSession session = req.getSession();
		String memberId = (String) session.getAttribute("memberId");
		logger.info("refService - 세션아이디: " + memberId);
		
		// DB Connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// DB에서 회원아이디에 해당하는 회원번호 조회 - MemberService 이용
		int memberNo = memberDao.findMemberNoById(conn, memberId);
		logger.info("refService - DB 회원번호조회 결과: " + memberNo);
		
		// 공유 대상 냉장고에 로그인 한 회원번호 추가
		int result = refDao.insertSharingMember(conn, refCode, memberNo);
		logger.info("refService - insert 결과: " + result);
		
		// insert 결과 트랜잭션 처리
		if (result > 0) {
			JDBCTemplate.commit(conn);
			logger.info("refService - insert 트랜잭션 처리: 커밋됨");
		} else {
			logger.warning("refService - insert 트랜잭션 처리: 롤백됨");
		}
		
		// View에 전달할 데이터 저장
		req.setAttribute("memberId", memberId);
	}
	
}
