package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.face.OrderDao;
import dto.OrderResult;
import dto.Ordering;

public class OrderDaoImpl implements OrderDao {
	
	private PreparedStatement ps;
	private ResultSet rs;

	@Override
	public int findOrderNo(Connection conn) {
		int seq = -1;
		String sql = "";
		sql = "SELECT ORDERING_SEQ.NEXTVAL FROM dual";
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				seq = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}
		
		return seq;
	}
	
	@Override
	public int insert(Connection conn, Ordering order) {
		int result = -1;
		String sql = "";
		sql = "INSERT INTO ordering (order_no, member_no, total) "
			+ "VALUES (?, ?, ?)";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, order.getOrder_no());
			ps.setInt(2, order.getMumber_no());
			ps.setInt(3, order.getTotal());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return result;
	}

	// 주문_상품 매핑 테이블 insert 메서드
	@Override
	public int insertOrderAndProduct(Connection conn, int orderNo, int proNo, int proQty) {
		int result = -1;
		String sql = "";
		sql = "INSERT INTO order_product (order_pro_no, order_no, pro_no, order_qty) "
			+ "VALUES (order_product_seq.nextval, ?, ?, ?)";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, orderNo);
			ps.setInt(2, proNo);
			ps.setInt(3, proQty);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return result;
		
	}

	// 주문결과 조회 메서드
	@Override
	public OrderResult getOrderResutl(Connection conn, int orderNo) {
		OrderResult result = null;
		String sql = "";
		sql = "SELECT "
				+ "O.order_no"
				+ ", O.order_date"
				+ ", O.total"
				+ ", P.name"
				+ ", D.address"
				+ ", D.phone"
				+ ", D.receiver "
			+ "FROM ordering O "
			+ "INNER JOIN delivery D "
				+ "ON O.order_no = D.order_no "
			+ "INNER JOIN order_product OP "
				+ "ON O.order_no = OP.order_no "
			+ "INNER JOIN product P "
				+ "ON OP.pro_no = P.pro_no "
			+ "WHERE O.order_no = ? ";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, orderNo);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				result = new OrderResult();
				
				result.setOrderNo(rs.getInt("order_no"));
				result.setOrderDate(rs.getDate("order_date"));
				result.setTotal(rs.getInt("total"));
				//result.setProName(rs.getString("name")); 이거 필요없는데 왜 넣었지..
				result.setAddress(rs.getString("address"));
				result.setPhone(rs.getString("phone"));
				result.setReceiver(rs.getString("receiver"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public List<OrderResult> getOrderResutlByMemberNo(Connection conn, int memberNo) {
		List<OrderResult> list = new ArrayList<OrderResult>();
		String sql = "";
		sql = "SELECT "
				+ "O.order_no"
				+ ", O.order_date"
				+ ", O.total"
				+ ", P.name"
				+ ", D.address"
				+ ", D.phone"
				+ ", D.receiver "
				+ ", O.member_no "
				+ ", O.status "
			+ "FROM ordering O "
			+ "INNER JOIN delivery D "
				+ "ON O.order_no = D.order_no "
			+ "INNER JOIN order_product OP "
				+ "ON O.order_no = OP.order_no "
			+ "INNER JOIN product P "
				+ "ON OP.pro_no = P.pro_no "
			+ "WHERE O.member_no = ? ";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, memberNo);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				OrderResult result = new OrderResult();
				
				result.setOrderNo(rs.getInt("order_no"));
				result.setOrderDate(rs.getDate("order_date"));
				result.setTotal(rs.getInt("total"));
				result.setProName(rs.getString("name"));
				result.setAddress(rs.getString("address"));
				result.setPhone(rs.getString("phone"));
				result.setReceiver(rs.getString("receiver"));
				result.setStatus(rs.getString("status"));
				
				list.add(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public int deleteOrderAndProduct(Connection conn, int orderNo) {
		int result = -1;
		String sql = "";
		sql = "DELETE order_product WHERE order_no = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, orderNo);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return result;
	}

	@Override
	public int deleteOrder(Connection conn, int orderNo) {
		int result = -1;
		String sql = "";
		sql = "DELETE ordering WHERE order_no = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, orderNo);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return result;
	}

	
	@Override
	public int updateStatusToSuccess(Connection conn, int orderNo, String status) {
		int result = -1;
		String sql = "";
		sql = "UPDATE ordering "
			+ "SET status = ? "
			+ "WHERE order_no = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, status);
			ps.setInt(2, orderNo);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return result;
	}



}
