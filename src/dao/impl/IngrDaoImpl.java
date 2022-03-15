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
}
