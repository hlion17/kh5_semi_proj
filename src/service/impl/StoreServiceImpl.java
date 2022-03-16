package service.impl;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.StoreDao;
import dao.impl.StoreDaoImpl;
import dto.Product;
import service.face.StoreService;

public class StoreServiceImpl implements StoreService {
	
	private StoreDao storeDao = new StoreDaoImpl();
	
	@Override
	public List<Product> getList() {
	
		//상품 전체 조회 결과 반환
		return storeDao.selectAll(JDBCTemplate.getConnection());
		
	}
	
	@Override
	public Product getProno(HttpServletRequest req) {
		
		//전달파라미터 productno를 저장할 DTO객체 생성
		Product productno = new Product();

		String param = req.getParameter("pro_no");
		if( param != null && !"".equals( param ) ) {
			productno.setPro_no( Integer.parseInt(param) );
		} else {
			System.out.println("[WARN] productno값이 null이거나 비어있음");
		}

		return productno;
	}
	
	@Override
	public Product view(Product productNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		//상품 조회
		Product product = storeDao.selectProductByProductno(conn, productNo);
		
		return product;
	}

}
