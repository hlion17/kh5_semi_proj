package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.face.StoreDao;
import dto.Product;
import dto.Recipe;

public class StoreDaoImpl implements StoreDao {
	
	private PreparedStatement ps = null; //SQL수행 객체
	private ResultSet rs = null; //SQL조회 결과 객체\
	
	@Override
	public List<Product> selectAll(Connection conn) {
		
				String sql = "";
				sql += "SELECT";
				sql += "	pro_no";
				sql += "	, cty_no";
				sql += "	, name";
				sql += "	, img_path";
				sql += "	, price";
				sql += "	, description";
				sql += " FROM product";
				sql += " ORDER BY boardno pro_no";
				
				//결과 저장할 List
				List<Product> productList = new ArrayList<>();
				
				try {
					ps = conn.prepareStatement(sql); //SQL수행 객체
					
					rs = ps.executeQuery(); //SQL수행 및 결과집합 저장

					while( rs.next() ) {
						Product pro = new Product(); //결과값 저장 객체
						
						//결과값 한 행 처리
						pro.setPro_no(rs.getInt("pro_no"));
						pro.setCty_no(rs.getInt("cty_no"));
						pro.setName(rs.getString("name"));
						pro.setImg_path(rs.getString("img_path"));
						pro.setPrice(rs.getInt("price"));
						pro.setDescription(rs.getString("description"));
						
						
						//리스트객체에 조회한 행 객체 저장
						productList.add(pro);
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					//JDBC객체 닫기
					JDBCTemplate.close(rs);
					JDBCTemplate.close(ps);
				}
				
				//최종 조회 결과 반환
				return productList;
	}
	

	
	
	
	


}