package dto;

import java.util.Date;

public class OrderResult {
	private int orderNo;
	private Date orderDate;
	private int total;
	private String proName;
	private int proQty;
	private String address;
	private String phone;
	private String receiver;
	
	@Override
	public String toString() {
		return "OrderResult [orderNo=" + orderNo + ", orderDate=" + orderDate + ", total=" + total + ", proName="
				+ proName + ", proQty=" + proQty + ", address=" + address + ", phone=" + phone + ", receiver="
				+ receiver + "]";
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public int getProQty() {
		return proQty;
	}

	public void setProQty(int proQty) {
		this.proQty = proQty;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	
	

}
