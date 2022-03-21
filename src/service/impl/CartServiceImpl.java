package service.impl;

import java.sql.Connection;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import common.JDBCTemplate;
import dao.face.CartDao;
import dao.impl.CartDaoImpl;
import dto.Cart;
import service.face.CartService;

public class CartServiceImpl implements CartService {
	
	private CartDao cartDao = new CartDaoImpl();
	private Logger logger = Logger.getLogger(CartServiceImpl.class.getName());

	@Override
	public List<Cart> getList() {
		return cartDao.selectAll(JDBCTemplate.getConnection());
	}
	
	@Override
	public Cart getCartno(HttpServletRequest req) {
		//전달파라미터 cartno를 저장할 DTO객체 생성
		Cart cartno = new Cart();
		
		String param = req.getParameter("cartno");
		if( param != null && !"".equals( param ) ) {
			cartno.setCart_no( Integer.parseInt(param) );
		} else {
			System.out.println("[WARN] CartService getcartno() - cartno값이 null이거나 비어있음");
		}

		return cartno;
	}
	
	// 삭제 예정
	@Override
	public void delete(Cart cart) {
		Connection conn = JDBCTemplate.getConnection();

		if( cartDao.delete(conn, cart) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
	}

	
	// 새로작성
	// 세션에 로그인 한 회원의 장바구니 품목 조회
	@Override
	public void getAllCartItemByMemberId(HttpServletRequest req) {
		// 요청파라미터 분석
		HttpSession session = req.getSession();
		int memberNo = (Integer) session.getAttribute("memberno");
		logger.info("세션 회원번호: " + memberNo);
		
		// DTO 생성
		
		// DB Connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// DAO 이용
		List<Cart> list = cartDao.findAllByMemberNo(conn, memberNo);
		logger.info("로그인한 회원의 장바구니 DB 조회결과: " + list);
		
		// View에 전달할 데이터 저장
		req.setAttribute("list", list);
		
	}
	
	// 장바구니 품목 추가
	@Override
	public void addCartItem(HttpServletRequest req) {
		// 요청파라미터 분석
		// memberNo 가져오는걸 항상 세션으로 가져오는게 맞는 건지 생각해보기
		int memberNo = Integer.parseInt(req.getParameter("memberNo"));
		int proNo = Integer.parseInt(req.getParameter("proNo"));
		int proQty = Integer.parseInt(req.getParameter("proQty"));
		int proPrice = Integer.parseInt(req.getParameter("proPrice"));
		
		// DTO 생성
		Cart cart = new Cart();
		cart.setMember_no(memberNo);
		cart.setPro_no(proNo);
		cart.setQuantity(proQty);
		cart.setPrice(proPrice);
		
		// DB Connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// DAO 이용
		int checkPro_no = cartDao.checkProInCart(conn, cart);
		
		int result = -1; 
		if (checkPro_no > 0) {
			result = cartDao.addProQty(conn, cart);	
			logger.info("기존 장바구니에 존재하는 품목 수량 업데이트 결과: " + result);
		} else if (checkPro_no == 0) {
			result = cartDao.insert(conn, cart);
			logger.info("장바구니 품목 추가 결과: " + result);
		}
		
		// DB 삽입결과 확인 및 트랜잭션 처리
		if (result == 1) {
			JDBCTemplate.commit(conn);
			logger.info("장바구니 품목 삽입 성공 커밋 됨");
		} else {
			JDBCTemplate.rollback(conn);
			logger.warning("장바구니 품목 삽입 실패 롤백 됨");
		}
		
		// View에 전달할 데이터 저장
		
		
	}
	
	// 장바구니 품목 정보 수정

	@Override
	public void updateCartItem(HttpServletRequest req) {
		// 요청파라미터 분석
		HttpSession session = req.getSession();
		int memberNo = (Integer) session.getAttribute("memberno");
		int proNo = Integer.parseInt(req.getParameter("proNo"));
		int proQty = Integer.parseInt(req.getParameter("proQty"));
		logger.info("세션에 저장된 로그인 한 회원번호: " + memberNo);
		logger.info("/cart/update 요청 파라미터 - 상품번호: " + proNo);
		logger.info("/cart/update 요청 파라미터 - 변경된 수량: " + proQty);
		
		// DTO 생성
		Cart cart = new Cart();
		cart.setMember_no(memberNo);
		cart.setPro_no(proNo);
		cart.setQuantity(proQty);
		logger.info("DTO 저장 값: " + cart);
		
		// DB Connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// DAO 이용
		int result = cartDao.update(conn, cart);
		logger.info("장바구니 품목 수정 결과: " + result);
		
		// DB update 결과 확인 및 트랜잭션 처리
		if (result == 1) {
			JDBCTemplate.commit(conn);
			logger.info("DB update 결과 성공 커밋 됨");
		} else {
			JDBCTemplate.rollback(conn);
			logger.warning("DB update 결과 실패 롤백 됨");
		}
		
		// View에 전달할 데이터 저장
		
		
	}
	
	// 장바구니 품목 삭제

	@Override
	public void deleteCartItem(HttpServletRequest req) {
		// 요청파라미터 분석
		HttpSession session = req.getSession();
		int memberNo = (Integer) session.getAttribute("memberno");
		int proNo = Integer.parseInt(req.getParameter("proNo"));
		logger.info("세션에 저장된 회원 번호: " + memberNo);
		logger.info("/cart/delete 요청 파라미터 - 상품 번호: " + proNo);
		
		// DTO 생성
		Cart cart = new Cart();
		cart.setMember_no(memberNo);
		cart.setPro_no(proNo);
		
		// DB Connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// DAO 이용
		int result = cartDao.delete(conn, cart);
		logger.info("장바구니 품목 삭제 결과: " + result);
		
		// DB 삭제 결과 확인 및 트랜잭션 처리
		if (result == 1) {
			JDBCTemplate.commit(conn);
			logger.info("DB 삭제 결과 성공 커밋 됨");
		} else {
			JDBCTemplate.rollback(conn);
			logger.warning("DB 삭제 결과 실패 롤백 됨");
		}
		
		// View에 전달할 데이터 저장
		
		
	}
	


}
