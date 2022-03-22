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

	List<Follow> selectAllFollower(Connection connection, Paging paging, HttpServletRequest req);

	SocialMember selectFile(Connection connection, SocialMember viewBoard);

	SocialMember selectBoardByBoardno(Connection conn, SocialMember memberno);

	int update(Connection conn, SocialMember board);

	int insertFile(Connection conn, SocialMember board);
	
	/**
	 * 팔로우
	 * 
	 * @param conn - DB연결 객체
	 * @param followee - 팔로우당하는 사람
	 * @param follower - 팔로우하는 사람
	 * @return DB 수행 결과
	 */
	public int setFollow(Connection conn, int followee, int follower, HttpServletRequest req);

	/**
	 * 팔로우 검사조건 - 이미 팔로우 한적이 있는지 검사(PK 위반 방지)
	 * 
	 * @param conn - DB연결 객체
	 * @param followee - 팔로우당하는 사람
	 * @param follower - 팔로우하는 사람
	 * @return DB 수행 결과
	 */
	public Follow checkFollowPK(Connection conn, int followee, int follower);

}
