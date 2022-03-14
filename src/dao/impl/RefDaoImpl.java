package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import common.JDBCTemplate;
import dao.face.RefDao;
import dto.Ref;

public class RefDaoImpl implements RefDao{

	private PreparedStatement ps;
	private ResultSet rs;
	
	@Override
	public List<Ref> findByMemberId(Connection conn, String memberId) {
		
		List<Ref> list = new ArrayList<>();
		
		String sql = "";
		sql = "SELECT m.id, r.ref_code, r.ref_name "
				+ "FROM ref_member rm "
				+ "INNER JOIN ref r "
				+ "ON rm.ref_code = r.ref_code "
				+ "INNER JOIN member m "
				+ "ON rm.member_no = m.member_no "
				+ "WHERE m.id = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, memberId);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Ref ref = new Ref();
				ref.setRefCode(rs.getInt("ref_code"));
				ref.setRefName(rs.getString("ref_name"));
				
				list.add(ref);
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
