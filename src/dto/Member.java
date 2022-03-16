package dto;

public class Member {
	
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
	
	@Override
	public String toString() {
		return "Member [memberno=" + memberno + ", memberid=" + memberid + ", memberpw=" + memberpw + ", membername="
				+ membername + ", nick=" + nick + ", gender=" + gender + ", email=" + email + ", phone=" + phone
				+ ", zipcode=" + zipcode + ", address=" + address + ", intro=" + intro + ", my_ref_code=" + my_ref_code
				+ "]";
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
	
	
	
	
}

