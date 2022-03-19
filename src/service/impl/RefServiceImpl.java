package service.impl;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import dto.Member;
import dto.Ref;
import dto.RefItem;
import service.face.MemberService;
import service.face.RefService;

public class RefServiceImpl implements RefService {
	
	private RefDao refDao = new RefDaoImpl();
	private MemberDao memberDao = new MemberDaoImpl();
	private static Logger logger = Logger.getLogger(RefServiceImpl.class.getName());

	// 냉장고 선택
	@Override
	public void chooseRef(HttpServletRequest req) {
		// 쿼리파리미터 값 받아오기
		HttpSession session = req.getSession();
		int myRefCode = (Integer) session.getAttribute("refCode");
		logger.info("로그인세션에서 가져온 냉장고 코드: " + myRefCode);
		
		// DB Connection 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// DB 에서 냉장고 데이터 리스트로 가져오기 
		List<Ref> list = refDao.findAllMemberByRefCode(conn, myRefCode);
		logger.info("로그인한 회원의 냉장고코드로 조회된 냉장고 목록: " + list);
		
		// View 에 전달할 데이터 저장
		req.setAttribute("list", list);
				
	}
	
	// 특정 냉장고 품목 상세 내용 가져오기
	@Override
	public void getItemDetail(HttpServletRequest req) {
		// 파라미터 분석
		int refCode = Integer.parseInt(req.getParameter("refCode"));
		int itemNo = Integer.parseInt(req.getParameter("itemNo"));
		logger.info("/ref/items/detail 파라미터 - 냉장고 코드:" + refCode);
		logger.info("/ref/items/detail 파라미터 - 냉장고 품목 번호: " + itemNo);
		
		// DB접속 객체 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// 냉장고 품목 번호로 조회
		RefItem refItem = refDao.findByItemNo(conn, itemNo);
		logger.info("품목 상세조회 결과: " + refItem);
		
		// View로 전달할 정보 저장
		req.setAttribute("refCode", refCode);
		req.setAttribute("refItem", refItem);
	}

	// 전체 냉장고 품목 리스트(기본:유통기한 기준 오름차순)
	@Override
	public void getAllItems(HttpServletRequest req) {
		// 쿼리파라미터 분석
		int refCode = Integer.parseInt(req.getParameter("refCode"));
		logger.info("/ref/itemlist 파라미터 - refCode: " + refCode);
		
		// DB Connection 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// DB 에서 냉장고 코드에 해당하는 품목들을 조회하여 리스트로 반환한다.
		List<RefItem> list = refDao.findAllItems(conn, refCode);
		logger.info("아이템 리스트: " + list);
		
		// View에 전달 할 결과 저장
		req.setAttribute("itemList", list);
		req.setAttribute("refCode", refCode);  // 페이지 간 전달 할 냉장고 코드
		req.setAttribute("status", 4);
	}
	
	// 필요없을 듯
	// 전체 냉장고 품목 리스트(등록일 기준 내림차순)
	/*
	@Override
	public void getAllItemsDesc(HttpServletRequest req) {
		// 쿼리파라미터 분석
		int refCode = Integer.parseInt(req.getParameter("refCode"));
		logger.info("/ref/itemlist 파라미터 - refCode: " + refCode);
		
		// DB Connection 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// DB 에서 냉장고 코드에 해당하는 품목들을 조회하여 리스트로 반환한다.
		List<RefItem> list = refDao.findAllItemsDesc(conn, refCode);
		logger.info("DB조회결과 - 아이템 리스트: " + list);
		
		// View에 전달 할 결과 저장
		req.setAttribute("itemList", list);
		req.setAttribute("refCode", refCode);  // 페이지 간 전달 할 냉장고 코드
	}
	*/

	// 보관상태로 필터링 된 냉장고 품목 리스트
	@Override
	public void getFilteredItems(HttpServletRequest req) {
		// 쿼리파라미터 분석
		int refCode = Integer.parseInt(req.getParameter("refCode"));
		int status = Integer.parseInt(req.getParameter("status"));
		logger.info("/ref/itemlist/filterAndSort 파라미터 - status: " + status);
		logger.info("/ref/itemlist/filterAndSort 파라미터 - refCode: " + refCode);

		// DB Connection 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// DB에서 냉장고품목을 상태코드로 필터링한 품목을 조회하여 리스트로 반환한다.
		List<RefItem> list = refDao.findFilteredItems(conn, refCode, status);
		logger.info("DB조회결과 - 아이템 리스트(fitered): " + list);
		
		// View에 전달할 아이템 목록 저장
		req.setAttribute("refCode", refCode); // 페이지 간 전달 할 냉장고 코드 
		req.setAttribute("itemList", list);
		req.setAttribute("status", status);
	}
	
	// 보관상태로 필터링 된 냉장고 품목 리스트(등록일 내림차순 정렬)
	@Override
	public void getFilteredItemsOrderByRegDate(HttpServletRequest req) {
		// 쿼리파라미터 분석
		int refCode = Integer.parseInt(req.getParameter("refCode"));
		int status = Integer.parseInt(req.getParameter("status"));
		logger.info("/ref/itemlist/filterAndSort 파라미터 - status: " + status);
		logger.info("/ref/itemlist/filterAndSort 파라미터 - refCode: " + refCode);
		
		// DB Connection 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// DB에서 냉장고품목을 상태코드로 필터링한 품목을 조회하여 리스트로 반환한다.
		List<RefItem> list = refDao.findFileredItemsOrderByRegDateDesc(conn, refCode, status);
		logger.info("DB조회결과 - 아이템 리스트(fitered,OderByregDate): " + list);
		
		// View에 전달할 아이템 목록 저장
		req.setAttribute("refCode", refCode);  // 페이지 간 전달 할 냉장고 코드
		req.setAttribute("itemList", list);
		req.setAttribute("status", status);
	}

	// 냉장고 아이템 추가
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		// 유통기한 날짜 String -> Data 변환
		Date parsedExDate = null;
		try {
			parsedExDate = sdf.parse(expireDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		refItem.setIngrCtyCode(ingrCtyCode);
		refItem.setItemName(itemName);
		refItem.setItemQty(itemQty);
		refItem.setStatus(status);
		refItem.setExpireDate(parsedExDate);
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

	// 냉장고 품목 삭제
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

	// 냉장고 품목 수정
	@Override
	public void updateRefItem(HttpServletRequest req) {
		// 쿼리 파라미터 분석
		int itemNo = Integer.parseInt(req.getParameter("itemNo"));
		String itemName = req.getParameter("itemName");
		String itemQty = req.getParameter("itemQty");
		int status = Integer.parseInt(req.getParameter("status"));
		String expireDate = req.getParameter("expireDate");
		String note = req.getParameter("note");
		
		// 유통기한 날짜 String -> Data 변환
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date parsedExDate = null;
		try {
			parsedExDate = sdf.parse(expireDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		// 냉장고 품목 DTO 생성
		RefItem refItem = new RefItem();
		
		// DTO에 정보 저장
		refItem.setItemNo(itemNo);
		refItem.setItemName(itemName);
		refItem.setItemQty(itemQty);
		refItem.setStatus(status);
		refItem.setExpireDate(parsedExDate);
		refItem.setNote(note);
		logger.info("품목 수정 할 내용: " + refItem);
		
		// Connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// DB에서 DTO 정보에 해당하는 냉장고 품목 수정
		int result = refDao.update(conn, refItem);
		logger.info("품목 수정 결과: " + result);
		
		// DB 결과에 따른 트랜잭션 처리
		if (result == 1) {  
			// 하나의 항목만 수정하니깐 1이 맞음, where절 안넣고 > 0 으로 했다가 피봄
			JDBCTemplate.commit(conn);
			logger.info("품목 수정 커밋 됨");
		} else {
			JDBCTemplate.rollback(conn);
			logger.warning("품목 수정 롤백 됨");
		}
		
		// View에 전달할 결과 저장
		
	}

	// 냉장고 공유
	@Override
	public void shareRef(HttpServletRequest req) {
		// 요청 파라미터 분석 - 공유 대상의 냉장고 코드
		int refCode = Integer.parseInt(req.getParameter("refCode"));
		logger.info("요청파라미터 - 공유 대상의 냉장고 코드: " + refCode);
		
		// DB Connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// 공유 대상의 냉장고 코드로 공유 대상의 회원 번호 조회
		Member targetMember = memberDao.findeByRefCode(conn, refCode);
		logger.info("DB조회결과 - 공유대상 회원정보: " + targetMember);
		
		// 세션분석 - 로그인 한 회원의 냉장고 코드
		HttpSession session = req.getSession();
		int myRefCode = (Integer) session.getAttribute("refCode");
		logger.info("세션에서 가져온 냉장고 코드: " + myRefCode);
		
		// 공유 대상 냉장고에 로그인 한 회원번호 추가
		int result = refDao.insertSharingMember(conn, myRefCode, targetMember.getMemberno());
		logger.info("냉장고 공유 결과: " + result);
		
		// insert 결과 트랜잭션 처리
		if (result == 1) {
			JDBCTemplate.commit(conn);
			logger.info("refService - insert 트랜잭션 처리: 커밋됨");
		} else {
			logger.warning("refService - insert 트랜잭션 처리: 롤백됨");
		}
		
		// View에 전달할 데이터 저장
	}

	@Override
	public void cancelSharingRef(HttpServletRequest req) {
		// 요청 파라미터 분석
		
	}

	
	
	
}
