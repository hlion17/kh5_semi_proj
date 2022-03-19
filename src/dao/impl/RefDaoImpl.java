package dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.face.RefDao;
import dto.Member;
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
	
	// 특정 냉장고 품목의 상세 내용 조회
	@Override
	public RefItem findByItemNo(Connection conn, int itemNo) {
		RefItem refItem = new RefItem();
		
		String sql = "";
		sql = "SELECT "
				+ "item_no, "
				+ "ingr_cty_code, "
				+ "item_name, "
				+ "item_qty, "
				+ "status, "
				+ "regdate, "
				+ "expire_date, "
				+ "note "
			+ "FROM item "
			+ "WHERE item_no = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, itemNo);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				refItem.setItemNo(rs.getInt("item_no"));
				refItem.setIngrCtyCode(rs.getInt("ingr_cty_code"));
				refItem.setItemName(rs.getString("item_name"));
				refItem.setItemQty(rs.getString("item_qty"));
				refItem.setStatus(rs.getInt("status"));
				refItem.setRegDate(rs.getDate("regdate"));
				refItem.setExpireDate(rs.getDate("expire_date"));
				refItem.setNote(rs.getString("note"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}
		
		return refItem;
	}

	// 전체 아이템 조회(기본정렬: 유통기한 오름차순)
	@Override
	public List<RefItem> findAllItems(Connection conn, int refCode) {
		
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
			+ "WHERE ri.ref_no = ? "
			+ "ORDER BY expire_date";
		
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
				refItem.setRegDate(rs.getDate("regdate"));
				refItem.setExpireDate(rs.getDate("expire_date"));
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
	
	// 전체 아이템 등록일 내림차순 조회
	/*
	@Override
	public List<RefItem> findAllItemsDesc(Connection conn, int refCode) {
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
			+ "WHERE ri.ref_no = ? "
			+ "ORDER BY refDate desc";
		
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
				refItem.setRegDate(rs.getDate("regdate"));
				refItem.setExpireDate(rs.getDate("expire_date"));
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
	*/

	// 삭제 예정
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
				refItem.setRegDate(rs.getDate("regdate"));
				refItem.setExpireDate(rs.getDate("expire_date"));
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

	// 상태코드로 필터링한 냉장고 아이템 조회(기본정렬: 유통기한 오름차순)
	@Override
	public List<RefItem> findFilteredItems(Connection conn, int refCode, int status) {
		List<RefItem> list = new ArrayList<RefItem>();
		
		String subSql = "";
		if (status >= 0 && status <= 2) {
			subSql = "AND i.status = ? ";
		}
		
		// prepareStatement 로는 order by 절에 ?으로 쓸수 없다고 한다.
		// dataType 을 가지는 데이터만 파라미터로 사용할 수 있음
		// 테이블명, 컬럼명 같은 것 사용불가
		// https://stackoverflow.com/questions/12430208/using-a-prepared-statement-and-variable-bind-order-by-in-java-with-jdbc-driver

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
			+ "ORDER BY expire_date";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, refCode);
			
			if (status >= 0 && status <= 2) {
				ps.setInt(2, status);				
			}

			rs = ps.executeQuery();
			
			while (rs.next()) {
				RefItem refItem = new RefItem();
				
				refItem.setItemNo(rs.getInt("item_no"));
				refItem.setItemName(rs.getString("item_name"));
				refItem.setIngrCtyCode(rs.getInt("ingr_cty_code"));
				refItem.setItemQty(rs.getString("item_qty"));
				refItem.setStatus(rs.getInt("status"));
				refItem.setRegDate(rs.getDate("regdate"));
				refItem.setExpireDate(rs.getDate("expire_date"));
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
	
	// 상태코드로 필터링된 냉장고 품목 조회(등록일 기준 내림차순 정렬)
	@Override
	public List<RefItem> findFileredItemsOrderByRegDateDesc(Connection conn, int refCode, int status) {
		List<RefItem> list = new ArrayList<RefItem>();
		
		String subSql = "";
		if (status >= 0 && status <= 2) {
			subSql = "AND i.status = ? ";
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
			+ "ORDER BY regDate DESC";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, refCode);
			
			if (status >= 0 && status <= 2) {
				ps.setInt(2, status);				
			}

			rs = ps.executeQuery();
			
			while (rs.next()) {
				RefItem refItem = new RefItem();
				
				refItem.setItemNo(rs.getInt("item_no"));
				refItem.setItemName(rs.getString("item_name"));
				refItem.setIngrCtyCode(rs.getInt("ingr_cty_code"));
				refItem.setItemQty(rs.getString("item_qty"));
				refItem.setStatus(rs.getInt("status"));
				refItem.setRegDate(rs.getDate("regdate"));
				refItem.setExpireDate(rs.getDate("expire_date"));
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
			ps.setDate(6, new Date(refItem.getExpireDate().getTime()));  // 날짜형식을 어떻게 할지
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
	
	// 냉장고_품목 매핑 테이블 품목 삭제
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

	// 품목 내용 수정
	@Override
	public int update(Connection conn, RefItem refItem) {
		int result = -1;
		String sql = "";
		sql = "UPDATE item "
				+ "SET "
				+ "item_name = ?, "
				+ "item_qty = ?, "
				+ "status = ?, "
				+ "expire_date = ?, "
				+ "note = ? "
				+ "WHERE item_no = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, refItem.getItemName());
			ps.setString(2, refItem.getItemQty());
			ps.setInt(3, refItem.getStatus());
			ps.setDate(4, new Date(refItem.getExpireDate().getTime()));
			ps.setString(5, refItem.getNote());
			ps.setInt(6, refItem.getItemNo());
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

	@Override
	public int insertSharingMember(Connection conn, int refCode, int memberNo) {
		int result = -1;
		String sql = "";
		sql = "INSERT INTO ref_member ("
				+ "ref_member_no, "
				+ "ref_code, "
				+ "member_no) "
			+ "Values (REF_MEBER_SEQ.NEXTVAL, ?, ?)";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, refCode);
			ps.setInt(2, memberNo);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		return result;
	}

	@Override
	public int insertRef(Connection conn, Member member) {
		int result = -1;
		String sql = "";
		sql = "INSERT INTO ref (ref_code, ref_name) "
				+ "VALUES (?,?)";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, member.getMy_ref_code());
			ps.setString(2, member.getNick() + "의 냉장고");
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return result;
	}

	@Override
	public int insertRef_Member(Connection conn, Member member) {
		int result = -1;
		String sql = "";
		sql = "INSERT INTO ref_member (ref_member_no, ref_code, member_no) "
				+ "VALUES (REF_MEMBER_SEQ.NEXTVAL, ?, ?)";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, rs.getInt(member.getMy_ref_code()));
			ps.setInt(2, rs.getInt(member.getMemberno()));
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
