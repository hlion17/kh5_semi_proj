package service.impl;

import java.sql.Connection;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import common.JDBCTemplate;
import dao.face.CartDao;
import dao.face.DeliveryDao;
import dao.face.OrderDao;
import dao.face.ProductDao;
import dao.impl.CartDaoImpl;
import dao.impl.DeliveryDaoImpl;
import dao.impl.OrderDaoImpl;
import dao.impl.ProductDaoImple;
import dto.Cart;
import dto.Delivery;
import dto.OrderResult;
import dto.Ordering;
import dto.Product;
import service.face.OrderingService;

public class OrderingServiceImpl implements OrderingService {

	private OrderDao orderDao = new OrderDaoImpl();
	private DeliveryDao deliveryDao = new DeliveryDaoImpl();
	private ProductDao productDao = new ProductDaoImple();
	private CartDao cartDao = new CartDaoImpl();
	private Logger logger = Logger.getLogger(OrderingServiceImpl.class.getName());
	
	@Override
	public void getProList(HttpServletRequest req) {
		
		// 요청 파라미터 분석
		HttpSession session = req.getSession();
		int memberNo = (Integer) session.getAttribute("memberno");
		logger.info("세션에 저장된 로그인 회원 번호" + memberNo);

		// 접속 객체 생성
		Connection conn = JDBCTemplate.getConnection();
		
		if (req.getParameter("proNo") != null) {
			int proNo = Integer.parseInt(req.getParameter("proNo"));
			logger.info("/order 요청 파라미터 - 상품번호: " + proNo);
			int proQty = Integer.parseInt(req.getParameter("proQty"));
			logger.info("/order 요청 파라미터 - 주문수량: " + proQty);
			Product product = productDao.findByNo(conn, proNo);
			logger.info("주문페이지에 표시할 상품 정보: " + product);
			req.setAttribute("proQty", proQty);
			req.setAttribute("product", product);
		} else {
			List<Cart> list = cartDao.findAllByMemberNo(conn, memberNo);
			logger.info("주문페이지에 표시할 상품 정보 리스트: " + list);
			req.setAttribute("list", list);
		}
		
		
	}

	@Override
	public void addOrder(HttpServletRequest req) {
		// 요청 파라미터 분석
		Map<String, String[]> proNos = new HashMap<>();
        Map<String, String[]> proQtys = new HashMap<>();

        Enumeration<String> names = req.getParameterNames();

        while (names.hasMoreElements()) {
            String name = names.nextElement();
            if ("proNo".equals(name)) {
                String[] values = req.getParameterValues(name);
                proNos.put("proNos", values);
            }
            if ("proQty".equals(name)) {
                String[] values = req.getParameterValues(name);
                proQtys.put("proQtys", values);
            }
        }

        String[] proNoArr = proNos.get("proNos");
        logger.info("/order/add 요청 파라미터 - proNos: " + Arrays.toString(proNoArr));
        String[] proQtyArr = proQtys.get("proQtys");
        logger.info("/order/add 요청 파라미터 - proQtys: " + Arrays.toString(proQtyArr));
        
        Map<String, String> proMap = new HashMap<String, String>();
        
        for (int i = 0; i < proNoArr.length; i++) {
        	proMap.put(proNoArr[i], proQtyArr[i]);
        }
        
        // 회원번호
        HttpSession session = req.getSession();
        int memberNo = (Integer) session.getAttribute("memberno");
        logger.info("세션에 저장된 로그인 회원 번호: " + memberNo);
        
        // 주문 총 금액
        int total = Integer.parseInt(req.getParameter("total"));
        logger.info("/order/add 요청 파라미터 - 주문금액 합계: " + total);
        
        // 배송정보
        String address = req.getParameter("address");
        logger.info("/order/add 요청 파라미터 - 배송주소: " + address);
        String phone = req.getParameter("phone");
        logger.info("/order/add 요청 파라미터 - 주문자 핸드폰 번호: " + phone);
        String reciever = req.getParameter("receiver");
        logger.info("/order/add 요청 파라미터 - 수취인 이름: " + reciever);
        
		// DB Connection 생성
        Connection conn = JDBCTemplate.getConnection();
		
		// DTO 생성 및 세팅
        Ordering order = new Ordering();
        Delivery deli = new Delivery();
		
        int orderNo = orderDao.findOrderNo(conn);
        logger.info("시퀀스로 생성한 주문번호 값: " + orderNo);
        order.setOrder_no(orderNo);
        order.setMumber_no(memberNo);
        order.setTotal(total);
        logger.info("SQL 수행전 주문 DTO: " + order);
        
        deli.setOrder_no(orderNo);
        deli.setAddress(address);
        deli.setPhone(phone);
        deli.setReciever(reciever);
        logger.info("SQL 수행전 배송 DTO: " + deli);

		// DAO 이용
        int result = orderDao.insert(conn, order);
        logger.info("주문 정보 insert 결과: " + result);
        int resultDeli = deliveryDao.insert(conn, deli);
        logger.info("배송 정보 insert 결과: " + resultDeli);
        
        Set<Map.Entry<String, String>> entrySet = proMap.entrySet();
        Iterator iter = entrySet.iterator();
        
        int resultOP = 0;
        while (iter.hasNext()) {
        	Map.Entry<String, String> e = (Map.Entry<String, String>)iter.next();
        	int proNo = Integer.parseInt(e.getKey());
        	int proQty = Integer.parseInt(e.getValue());
        	resultOP += orderDao.insertOrderAndProduct(conn, orderNo, proNo, proQty);
        }
        logger.info("주문_상품 매핑 테이블 insert 결과: " + resultOP);
        
        // 트랜잭션 처리
        if (result == 1 && resultDeli == 1) {
        	if (proNoArr.length == resultOP) {
        		// 주문결과 조회하는 DAO
                // 주문테이블, 배송테이블, 상품 테이블 조인
                // 주문번호, 주문일자, 주문총금액, 상품명, 상품수량, 배송주소, 연락처, 수취인
        		OrderResult orderResult = orderDao.getOrderResutl(conn, orderNo);
        		logger.info("주문 추가 전체 결과: " + orderResult);
        		// View에 전달할 데이터
                req.setAttribute("orderResult", orderResult);
                JDBCTemplate.commit(conn);
                logger.info("주문 추가 전체 결과 성공 커밋 됨");
        	} else {
        		JDBCTemplate.rollback(conn);
                logger.warning("주문 추가 전체 결과 실패 롤백 됨");
        	}
        } else {
        	JDBCTemplate.rollback(conn);
        	logger.warning("주문 추가 전체 결과 실패 롤백 됨");
		}
		
		
	}

	@Override
	public void getOrders(HttpServletRequest req) {
		// 세션에서 로그인 한 회원의 회원번호 가져오기
		HttpSession session = req.getSession();
		int memberNo = (Integer) session.getAttribute("memberno");
		logger.info("세션에 저장된 로그인 회원 번호: " + memberNo);
		
		// DB Connection 객체
		Connection conn = JDBCTemplate.getConnection();
		
		// DAO
		List<OrderResult> list = orderDao.getOrderResutlByMemberNo(conn, memberNo);
		
		// View에 전달 할 데이터 저장
		req.setAttribute("list", list);
	}

}
