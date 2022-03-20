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
		List<Cart> cartList = new ArrayList<>();
		
		String sql="";
		sql += "SELECT * FROM cart ORDER BY cart_no";
		
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
	
	
	

	// 새로작성
	// 세션에 로그인 한 회원의 장바구니 품목 조회
	@Override
	public List<Cart> findAllByMemberNo(Connection conn, int memberNo) {
		List<Cart> list = new ArrayList<Cart>();
		String sql = "";
		sql = "SELECT cart_no, member_no, pro_no, quantity, price "
				+ "FROM cart "
				+ "WHERE member_no = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, memberNo);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Cart cart = new Cart();
				
				cart.setCart_no(rs.getInt("cart_no"));
				cart.setMember_no(rs.getInt("member_no"));
				cart.setPro_no(rs.getInt("pro_no"));
				cart.setQuantity(rs.getInt("quantity"));
				cart.setPrice(rs.getInt("price"));
				
				list.add(cart);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}
		
		
		return list;
	}


	// 장바구니 품목 추가
	@Override
	public int insert(Connection conn, Cart cart) {
		int result = -1;
		String sql = "";
		sql = "INSERT INTO cart (cart_no, member_no, pro_no, quantity, price) "
				+ "VALUES (CART_SEQ.NEXTVAL, ?, ?, ?, ?)";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, cart.getMember_no());
			ps.setInt(2, cart.getPro_no());
			ps.setInt(3, cart.getQuantity());
			ps.setInt(4, cart.getPrice());
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return result;
	}

	// 장바구니 정보 수정
	@Override
	public int update(Connection conn, Cart cart) {
		int result = -1;
		String sql = "";
		sql = "UPDATE cart "
				+ "SET quantity = ? "
				+ "WHERE member_no = ? AND pro_no = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, cart.getQuantity());
			ps.setInt(2, cart.getMember_no());
			ps.setInt(3, cart.getPro_no());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return result;
	}
	
	// 장바구니 품목 삭제
	@Override
	public int delete(Connection conn, Cart cart) {
		int result = -1;
		String sql = "";
		sql = "DELETE cart "
				+ "WHERE member_no = ? AND pro_no = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, cart.getMember_no());
			ps.setInt(2, cart.getPro_no());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return result;
	}
	

}
