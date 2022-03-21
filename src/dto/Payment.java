package dto;

import java.util.Date;

public class Payment {
	
	private int pay_no;
	private int order_no;
	private Date pay_date;
	private String method;
	private int pay_amount;
	private int apply_num;
	private int member_no;
	private String name; //주문자이름
	private String email;
	private int phone;
	private String address;
	private int zipcode;
	@Override
	public String toString() {
		return "Payment [pay_no=" + pay_no + ", order_no=" + order_no + ", pay_date=" + pay_date + ", method=" + method
				+ ", pay_amount=" + pay_amount + ", apply_num=" + apply_num + ", member_no=" + member_no + ", name="
				+ name + ", email=" + email + ", phone=" + phone + ", address=" + address + ", zipcode=" + zipcode
				+ "]";
	}
	public int getPay_no() {
		return pay_no;
	}
	public void setPay_no(int pay_no) {
		this.pay_no = pay_no;
	}
	public int getOrder_no() {
		return order_no;
	}
	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}
	public Date getPay_date() {
		return pay_date;
	}
	public void setPay_date(Date pay_date) {
		this.pay_date = pay_date;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public int getPay_amount() {
		return pay_amount;
	}
	public void setPay_amount(int pay_amount) {
		this.pay_amount = pay_amount;
	}
	public int getApply_num() {
		return apply_num;
	}
	public void setApply_num(int apply_num) {
		this.apply_num = apply_num;
	}
	public int getMember_no() {
		return member_no;
	}
	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getZipcode() {
		return zipcode;
	}
	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}
	
	
	
	
}
