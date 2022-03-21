package dto;

public class Follow {
	//follow 테이블
	private int followee;	//followee	[PK]	팔로우 당하는 사람
	private int follower;	//follower	[PK]	팔로우 하는 사람
	private int followRes;	//메소드 조건판단 결과 전달용
	private int dbRes;		//메소드 DB수행 결과 전달용
	
	@Override
	public String toString() {
		return "Follow [followee=" + followee + ", follower=" + follower + ", followRes=" + followRes + ", dbRes="
				+ dbRes + "]";
	}

	public int getFollowee() {
		return followee;
	}

	public void setFollowee(int followee) {
		this.followee = followee;
	}

	public int getFollower() {
		return follower;
	}

	public void setFollower(int follower) {
		this.follower = follower;
	}

	public int getFollowRes() {
		return followRes;
	}

	public void setFollowRes(int followRes) {
		this.followRes = followRes;
	}

	public int getDbRes() {
		return dbRes;
	}

	public void setDbRes(int dbRes) {
		this.dbRes = dbRes;
	}
	
	
}
