package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.JDBCTemplate;
import dao.face.ProductDao;
import dto.Product;

public class ProductDaoImple implements ProductDao {
	
	private PreparedStatement ps;
	private ResultSet rs;

	@Override
	public Product findByNo(Connection conn, int proNo) {
		Product product = new Product();
		String sql = "";
		sql = "SELECT pro_no, cty_no, name, img_path, price, description "
			+ "FROM product "
			+ "WHERE pro_no = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, proNo);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				product.setPro_no(proNo);
				product.setCty_no(rs.getInt("cty_no"));
				product.setName(rs.getString("name"));
				product.setImg_path(rs.getString("img_path"));
				product.setPrice(rs.getInt("price"));
				product.setDescription(rs.getString("description"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}
		
		return product;
	}

}
