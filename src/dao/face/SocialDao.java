package dao.face;

import java.sql.Connection;
import java.util.List;

import dto.ProfileFile;
import dto.SocialMember;
import util.Paging;

public interface SocialDao {

	int selectCntAll(Connection connection);

	List<SocialMember> selectAll(Connection connection, Paging paging);

	ProfileFile selectFile(Connection connection, SocialMember viewBoard);

	SocialMember selectBoardByBoardno(Connection conn, SocialMember memberno);

	int update(Connection conn, SocialMember board);

	int insertFile(Connection conn, SocialMember board);

}
