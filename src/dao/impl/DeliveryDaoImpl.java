package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.JDBCTemplate;
import dao.face.DeliveryDao;
import dto.Delivery;

public class DeliveryDaoImpl implements DeliveryDao {

	private PreparedStatement ps;
	private ResultSet rs;
	
	@Override
	public int insert(Connection conn, Delivery deli) {
		int result = -1;
		String sql = "";
		sql = "INSERT INTO delivery (deli_no, address, phone, receiver, order_no) "
			+ "VALUES (delivery_seq.nextval, ?, ?, ?, ? )";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, deli.getAddress());
			ps.setString(2, deli.getPhone());
			ps.setString(3, deli.getReciever());
			ps.setInt(4, deli.getOrder_no());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return result;
	}

}
