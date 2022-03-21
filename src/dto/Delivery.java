package dto;

public class Delivery {
	private int deli_no;
	private String address;
	private String phone;
	private String reciever;
	private int order_no;
	
	@Override
	public String toString() {
		return "Delivery [deli_no=" + deli_no + ", address=" + address + ", phone=" + phone + ", reciever=" + reciever
				+ ", order_no=" + order_no + "]";
	}

	public int getDeli_no() {
		return deli_no;
	}

	public void setDeli_no(int deli_no) {
		this.deli_no = deli_no;
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

	public String getReciever() {
		return reciever;
	}

	public void setReciever(String reciever) {
		this.reciever = reciever;
	}

	public int getOrder_no() {
		return order_no;
	}

	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}
	
	
	
}
