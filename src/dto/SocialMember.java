package dto;

public class SocialMember {
	
								//member테이블
	private int memberno;		//member_no		NUMBER			[PK]
	private String memberid;	//id			VARCHAR2(100)
	private String memberpw;	//pw			VARCHAR2(100)
	private String membername;	//name			VARCHAR2(100)
	private String nick;		//nick			VARCHAR2(100)
	private String gender;		//gender		VARCHAR2(1)
	private String email;		//email			VARCHAR2(100)
	private String phone;		//phone			VARCHAR2(11)
	private String address;		//address		VARCHAR2(100)
	private String intro;		//intro			VARCHAR2(1000)
	private int my_ref_code;	//my_ref_code	NUMBER
	private String zipcode;		//zipcode		VARCHAR2(6)
	
								//prfimg테이블
	private int image_no;		//image_no		NUMBER			[PK]
	private int member_no;		//member_no		NUMBER			[FK]
	private String origin_name;	//origin_name	VARCHAR2(500)
	private String stored_name;	//stored_name	VARCHAR2(500)
	private int filesize;		//filesize		NUMBER
	
	private int recipeCnt;
	private int followCnt;
	private int dense_rank;
	
								//follow 테이블
	private int followee;		//followee	[PK]	팔로우 당하는 사람
	private int follower;		//follower	[PK]	팔로우 하는 사람
	
	@Override
	public String toString() {
		return "SocialMember [memberno=" + memberno + ", memberid=" + memberid + ", memberpw=" + memberpw
				+ ", membername=" + membername + ", nick=" + nick + ", gender=" + gender + ", email=" + email
				+ ", phone=" + phone + ", address=" + address + ", intro=" + intro + ", my_ref_code=" + my_ref_code
				+ ", zipcode=" + zipcode + ", image_no=" + image_no + ", member_no=" + member_no + ", origin_name="
				+ origin_name + ", stored_name=" + stored_name + ", filesize=" + filesize + ", recipeCnt=" + recipeCnt
				+ ", followCnt=" + followCnt + ", followee=" + followee + ", follower=" + follower + "]";
	}

	public int getMemberno() {
		return memberno;
	}

	public void setMemberno(int memberno) {
		this.memberno = memberno;
	}

	public String getMemberid() {
		return memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

	public String getMemberpw() {
		return memberpw;
	}

	public void setMemberpw(String memberpw) {
		this.memberpw = memberpw;
	}

	public String getMembername() {
		return membername;
	}

	public void setMembername(String membername) {
		this.membername = membername;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public int getMy_ref_code() {
		return my_ref_code;
	}

	public void setMy_ref_code(int my_ref_code) {
		this.my_ref_code = my_ref_code;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public int getImage_no() {
		return image_no;
	}

	public void setImage_no(int image_no) {
		this.image_no = image_no;
	}

	public int getMember_no() {
		return member_no;
	}

	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}

	public String getOrigin_name() {
		return origin_name;
	}

	public void setOrigin_name(String origin_name) {
		this.origin_name = origin_name;
	}

	public String getStored_name() {
		return stored_name;
	}

	public void setStored_name(String stored_name) {
		this.stored_name = stored_name;
	}

	public int getFilesize() {
		return filesize;
	}

	public void setFilesize(int filesize) {
		this.filesize = filesize;
	}

	public int getRecipeCnt() {
		return recipeCnt;
	}

	public void setRecipeCnt(int recipeCnt) {
		this.recipeCnt = recipeCnt;
	}

	public int getFollowCnt() {
		return followCnt;
	}

	public void setFollowCnt(int followCnt) {
		this.followCnt = followCnt;
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

	public int getDense_rank() {
		return dense_rank;
	}

	public void setDense_rank(int dense_rank) {
		this.dense_rank = dense_rank;
	}
	
	
	
}