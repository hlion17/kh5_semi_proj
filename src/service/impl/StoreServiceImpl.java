package service.impl;

import java.util.List;

import common.JDBCTemplate;
import dao.face.StoreDao;
import dao.impl.StoreDaoImpl;
import dto.Product;
import service.face.StoreService;

public class StoreServiceImpl implements StoreService {
	
	private StoreDao storeDao = new StoreDaoImpl();
	
	@Override
	public List<Product> getList() {
	
		return storeDao.selectAll(JDBCTemplate.getConnection());
		
	}

}
