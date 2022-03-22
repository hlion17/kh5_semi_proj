package dto;

public class RankMember {
	
	private int memberno;
	private String memberid;
	private String memberpw;
	private String membername;
	private String nick;
	private String gender;
	private String email;
	private String phone;
	private String zipcode;
	private String address;
	private String intro;
	private int my_ref_code;
	
	private int recipeCnt;
	private int followCnt;
	private int dense_rank;
	
	//prfimg테이블
	private int image_no;		//image_no		NUMBER			[PK]
	private int member_no;		//member_no		NUMBER			[FK]
	private String origin_name;	//origin_name	VARCHAR2(500)
	private String stored_name;	//stored_name	VARCHAR2(500)
	private int filesize;		//filesize		NUMBER
	
	@Override
	public String toString() {
		return "RankMember [memberno=" + memberno + ", memberid=" + memberid + ", memberpw=" + memberpw
				+ ", membername=" + membername + ", nick=" + nick + ", gender=" + gender + ", email=" + email
				+ ", phone=" + phone + ", zipcode=" + zipcode + ", address=" + address + ", intro=" + intro
				+ ", my_ref_code=" + my_ref_code + ", recipeCnt=" + recipeCnt + ", followCnt=" + followCnt
				+ ", dense_rank=" + dense_rank + ", image_no=" + image_no + ", member_no=" + member_no
				+ ", origin_name=" + origin_name + ", stored_name=" + stored_name + ", filesize=" + filesize + "]";
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
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
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
	public int getDense_rank() {
		return dense_rank;
	}
	public void setDense_rank(int dense_rank) {
		this.dense_rank = dense_rank;
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
	
		
}