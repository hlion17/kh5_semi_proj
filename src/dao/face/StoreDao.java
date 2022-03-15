package dao.face;

import java.sql.Connection;
import java.util.List;

import dto.Product;
import dto.Recipe;

public interface StoreDao {

	/* Product 테이블 전체 조회 */
	List<Product> selectAll(Connection conn);
	

}
