package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.face.IngrDao;
import dto.Ingredient;

public class IngrDaoImpl implements IngrDao {

    private PreparedStatement ps;
    private ResultSet rs;

    @Override
    public List<Ingredient> findAll(Connection conn) {

        List<Ingredient> ingrs = new ArrayList<>();

        String sql;
        sql = "SELECT * FROM INGR";

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Ingredient ingr = new Ingredient();

                ingr.setIngrCode(rs.getInt("ingr_code"));
                ingr.setIngrCtyCode(rs.getInt("ingr_cty_no"));
                ingr.setIngrName(rs.getString("ingr_name"));
                ingr.setDetail1(rs.getString("detail1"));
                ingr.setDetail2(rs.getString("detail2"));
                ingr.setDetail3(rs.getString("detail3"));
                ingr.setDetail4(rs.getString("detail4"));
                ingr.setDetail5(rs.getString("detail5"));
                ingr.setDetail6(rs.getString("detail6"));
                ingr.setDetail7(rs.getString("detail7"));
                ingr.setDetail8(rs.getString("detail8"));
                ingr.setDetail9(rs.getString("detail9"));
                ingr.setDetail10(rs.getString("detail10"));

                ingrs.add(ingr);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplate.close(ps);
            JDBCTemplate.close(rs);
        }

        return ingrs;
    }

	@Override
	public Ingredient findById(Connection conn, int ingrCode) {
		Ingredient ingr = null;
		String sql = "";
		sql = "SELECT "
				+ "INGR_CODE, "
				+ "INGR_CTY_NO, "
				+ "INGR_NAME, "
				+ "DETAIL1, "
				+ "DETAIL2, "
				+ "DETAIL3, "
				+ "DETAIL4, "
				+ "DETAIL5, "
				+ "DETAIL6, "
				+ "DETAIL7, "
				+ "DETAIL8, "
				+ "DETAIL9, "
				+ "DETAIL10 "
			+ "FROM INGR "
			+ "WHERE INGR_CODE = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, ingrCode);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				ingr = new Ingredient();
				ingr.setIngrCode(rs.getInt("ingr_code"));
				ingr.setIngrCtyCode(rs.getInt("ingr_cty_no"));
				ingr.setIngrName(rs.getString("ingr_name"));
				ingr.setDetail1(rs.getString("detail1"));
				ingr.setDetail2(rs.getString("detail2"));
				ingr.setDetail3(rs.getString("detail3"));
				ingr.setDetail4(rs.getString("detail4"));
				ingr.setDetail5(rs.getString("detail5"));
				ingr.setDetail6(rs.getString("detail6"));
				ingr.setDetail7(rs.getString("detail7"));
				ingr.setDetail8(rs.getString("detail8"));
				ingr.setDetail9(rs.getString("detail9"));
				ingr.setDetail10(rs.getString("detail10"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}
			
		return ingr;
	}

	// 모든 정보를 다 넣을 필요는 없어보이지만 일단 해놓음
	@Override
	public List<Ingredient> findByName(Connection conn, String ingrName) {
		List<Ingredient> list = new ArrayList<Ingredient>();
		String sql = "";
		sql = "SELECT "
				+ "INGR_CODE, "
				+ "INGR_CTY_NO, "
				+ "INGR_NAME, "
				+ "DETAIL1, "
				+ "DETAIL2, "
				+ "DETAIL3, "
				+ "DETAIL4, "
				+ "DETAIL5, "
				+ "DETAIL6, "
				+ "DETAIL7, "
				+ "DETAIL8, "
				+ "DETAIL9, "
				+ "DETAIL10 "
			+ "FROM INGR "
			+ "WHERE INGR_NAME LIKE ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + ingrName + "%");
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Ingredient ingr = new Ingredient();
				
				ingr = new Ingredient();
				ingr.setIngrCode(rs.getInt("ingr_code"));
				ingr.setIngrCtyCode(rs.getInt("ingr_cty_no"));
				ingr.setIngrName(rs.getString("ingr_name"));
				ingr.setDetail1(rs.getString("detail1"));
				ingr.setDetail2(rs.getString("detail2"));
				ingr.setDetail3(rs.getString("detail3"));
				ingr.setDetail4(rs.getString("detail4"));
				ingr.setDetail5(rs.getString("detail5"));
				ingr.setDetail6(rs.getString("detail6"));
				ingr.setDetail7(rs.getString("detail7"));
				ingr.setDetail8(rs.getString("detail8"));
				ingr.setDetail9(rs.getString("detail9"));
				ingr.setDetail10(rs.getString("detail10"));
				
				list.add(ingr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}
		
		
		return list;
	}
}
