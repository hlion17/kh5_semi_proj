package dto;

public class Follow {
	//follow 테이블
	private int followee;	//followee	[PK]	팔로우 당하는 사람
	private int follower;	//follower	[PK]	팔로우 하는 사람
	@Override
	public String toString() {
		return "Follow [followee=" + followee + ", follower=" + follower + "]";
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
	
	
	
}
