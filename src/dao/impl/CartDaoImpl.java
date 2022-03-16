package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.face.CartDao;
import dto.Cart;

public class CartDaoImpl implements CartDao {

	private PreparedStatement ps = null; //SQL수행 객체
	private ResultSet rs = null; //SQL조회 결과 객체
	
	@Override
	public List<Cart> selectAll(Connection conn) {

		String sql="";
		sql += "SELECT * FROM cart";
		
		//결과 저장할 List
			List<Cart> cartList = new ArrayList<>();
			
			try {
				ps = conn.prepareStatement(sql); //SQL수행 객체
				
				rs = ps.executeQuery(); //SQL수행 및 결과집합 저장

				while( rs.next() ) {
					Cart cart = new Cart(); //결과값 저장 객체
					
					//결과값 한 행 처리
					cart.setCart_no(rs.getInt("cart_no"));
					cart.setMember_no(rs.getInt("member_no"));
					cart.setPro_no(rs.getInt("pro_no"));
					cart.setQuantity(rs.getInt("quantity"));
					cart.setPrice(rs.getInt("price"));
					
					//리스트객체에 조회한 행 객체 저장
					cartList.add(cart);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				//JDBC객체 닫기
				JDBCTemplate.close(rs);
				JDBCTemplate.close(ps);
			}
			
			//최종 조회 결과 반환
			return cartList;
	}
	
	
	@Override
	public int delete(Connection conn, Cart cart) {

		String sql = "";
		sql += "DELETE cart";
		sql += " WHERE cartno = ?";
		
		int res = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, cart.getCart_no());

			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}
	


}
