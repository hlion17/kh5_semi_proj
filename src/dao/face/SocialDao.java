package dao.face;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Follow;
import dto.ProfileFile;
import dto.SocialMember;
import util.Paging;

public interface SocialDao {

	int selectCntAll(Connection connection);

	List<SocialMember> selectAll(Connection connection, Paging paging);

	List<Follow> selectAllFollow(Connection connection, Paging paging, HttpServletRequest req);

	SocialMember selectFile(Connection connection, SocialMember viewBoard);

	SocialMember selectBoardByBoardno(Connection conn, SocialMember memberno);

	int update(Connection conn, SocialMember board);

	int insertFile(Connection conn, SocialMember board);

}
