package dto;

public class Cart {
	
	private int cart_no;
	private int member_no;
	private int pro_no;
	private int quantity;
	private int price;
	
	@Override
	public String toString() {
		return "Cart [cart_no=" + cart_no + ", member_no=" + member_no + ", pro_no=" + pro_no + ", quantity=" + quantity
				+ ", price=" + price + "]";
	}
	public int getCart_no() {
		return cart_no;
	}
	public void setCart_no(int cart_no) {
		this.cart_no = cart_no;
	}
	public int getMember_no() {
		return member_no;
	}
	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}
	public int getPro_no() {
		return pro_no;
	}
	public void setPro_no(int pro_no) {
		this.pro_no = pro_no;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
	

}
