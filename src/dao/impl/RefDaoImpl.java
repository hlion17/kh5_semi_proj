package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.face.RefDao;
import dto.Ref;
import dto.RefItem;

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

	@Override
	public List<RefItem> getItemListByRefCode(Connection conn, int refCode) {
		
		List<RefItem> list = new ArrayList<>();
		
		String sql = "";
		sql = "SELECT "
				+ "ri.item_no, "
				+ "item_name, "
				+ "ingr_cty_code, "
				+ "item_qty, "
				+ "status, "
				+ "regdate, "
				+ "expire_date, "
				+ "note "
			+ "FROM ref_item ri "
			+ "INNER JOIN item i "
				+ "ON ri.item_no = i.item_no "
			+ "WHERE ri.ref_no = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, refCode);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				RefItem refItem = new RefItem();
				
				refItem.setItemNo(rs.getInt("item_no"));
				refItem.setItemName(rs.getString("item_name"));
				refItem.setIngrCtyCode(rs.getInt("ingr_cty_code"));
				refItem.setItemQty(rs.getString("item_qty"));
				refItem.setStatus(rs.getInt("status"));
				refItem.setRegDate(rs.getDate("regdate").toString());
				refItem.setExpireDate(rs.getDate("expire_date").toString());
				refItem.setNote(rs.getString("note"));
				
				list.add(refItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}
		
		
		return list;
	}

	@Override
	public List<RefItem> findAllByFiltering(Connection conn, int refCode, int status) {
		List<RefItem> list = new ArrayList<RefItem>();
		String sql = "";
		sql = "SELECT "
				+ "ri.item_no, "
				+ "item_name, "
				+ "ingr_cty_code, "
				+ "item_qty, "
				+ "status, "
				+ "regdate, "
				+ "expire_date, "
				+ "note "
			+ "FROM ref_item ri "
			+ "INNER JOIN item i "
				+ "ON ri.item_no = i.item_no "
			+ "WHERE ri.ref_no = ? "
				+ "AND i.status = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, refCode);
			ps.setInt(2, status);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				RefItem refItem = new RefItem();
				
				refItem.setItemNo(rs.getInt("item_no"));
				refItem.setItemName(rs.getString("item_name"));
				refItem.setIngrCtyCode(rs.getInt("ingr_cty_code"));
				refItem.setItemQty(rs.getString("item_qty"));
				refItem.setStatus(rs.getInt("status"));
				refItem.setRegDate(rs.getDate("regdate").toString());
				refItem.setExpireDate(rs.getDate("expire_date").toString());
				refItem.setNote(rs.getString("note"));
				
				list.add(refItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}
		return list;
	}

	@Override
	public List<RefItem> findAllByFilteringAndOrdering(Connection conn, int refCode, int status, String orderBy) {
		List<RefItem> list = new ArrayList<RefItem>();
		
		String subSql = "";
		if (status >= 0 && status <= 2) {
			subSql = "AND i.status = ? ";
		} else {
			subSql = "";
		}
		
		String sql = "";
		sql = "SELECT "
				+ "ri.item_no, "
				+ "item_name, "
				+ "ingr_cty_code, "
				+ "item_qty, "
				+ "status, "
				+ "regdate, "
				+ "expire_date, "
				+ "note "
			+ "FROM ref_item ri "
			+ "INNER JOIN item i "
				+ "ON ri.item_no = i.item_no "
			+ "WHERE ri.ref_no = ? "
				+ subSql
			+ "ORDER BY ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, refCode);
			
			if (status >= 0 && status <= 2) {
				ps.setInt(2, status);				
				ps.setString(3, orderBy);
			} else {
				ps.setString(2, orderBy);
			}

			rs = ps.executeQuery();
			
			while (rs.next()) {
				RefItem refItem = new RefItem();
				
				refItem.setItemNo(rs.getInt("item_no"));
				refItem.setItemName(rs.getString("item_name"));
				refItem.setIngrCtyCode(rs.getInt("ingr_cty_code"));
				refItem.setItemQty(rs.getString("item_qty"));
				refItem.setStatus(rs.getInt("status"));
				refItem.setRegDate(rs.getDate("regdate").toString());
				refItem.setExpireDate(rs.getDate("expire_date").toString());
				refItem.setNote(rs.getString("note"));
				
				list.add(refItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}
		return list;
	}

	@Override
	public int insertItem(Connection conn, int refCode, RefItem refItem) {
		int result = -1;
		String sql = "";
		sql = "INSERT INTO ITEM ("
				+ "ITEM_NO, "
				+ "INGR_CTY_CODE, "
				+ "ITEM_NAME, "
				+ "ITEM_QTY, "
				+ "STATUS, "
				+ "EXPIRE_DATE, "
				+ "NOTE )"
			+ "VALUES (?, ?,?,?,?,?,?) ";
			
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, refItem.getItemNo());
			ps.setInt(2, refItem.getIngrCtyCode());
			ps.setString(3, refItem.getItemName());
			ps.setString(4, refItem.getItemQty());
			ps.setInt(5, refItem.getStatus());
			ps.setString(6, refItem.getExpireDate());  // 날짜형식을 어떻게 할지
			ps.setString(7, refItem.getNote());
			
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
				
		return result;
	}
	
	@Override
	public int insertRef_Item(Connection conn, int refCode, RefItem refItem) {
		int result = -1;
		String sql = "";
		sql = "INSERT INTO ref_item ("
				+ "ref_item_no, "
				+ "ref_no, "
				+ "item_no) "
				+ "VALUES (ref_item_seq.nextval, ? ,? )";
			
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, refCode);
			ps.setInt(2, refItem.getItemNo());
			
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
				
		return result;
	}

	@Override
	public int deleteItem(Connection conn, int itemNo) {
		int result = -1;
		String sql ="";
		sql = "DELETE item "
				+ "WHERE item_no = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, itemNo);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return result;
	}
	
	@Override
	public int deleteRef_Item(Connection conn, int itemNo) {
		int result = -1;
		String sql ="";
		sql = "DELETE ref_item "
				+ "WHERE item_no = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, itemNo);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return result;
	}

	@Override
	public int update(Connection conn, RefItem refItem) {
		int result = -1;
		String sql = "";
		sql = "UPDATE item "
				+ "SET "
				+ "ingr_cty_code = ?, "
				+ "item_name = ?, "
				+ "item_qty = ?, "
				+ "status = ?, "
				+ "regdate = ?, "
				+ "expire_date = ?, "
				+ "note = ? ";
		
		try {
			ps = conn.prepareStatement(sql);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		return result;
	}

	@Override
	public RefItem getRefItemNo(Connection conn, RefItem refItem) {
		String sql = "";
		sql = "SELECT ITEM_SEQ.NEXTVAL itemNo FROM dual";
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				refItem.setItemNo(rs.getInt("itemNo"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return refItem;  // 매개변수로 값 저장가능하면 void로 해도 되지 않나?
	}

	
	

	

}
