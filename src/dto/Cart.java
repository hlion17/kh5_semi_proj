package dto;

public class Cart {
	
	private int cart_no;
	private int member_no;
	private int pro_no;
	private int quantity;
	private int price;
	
	//상품명 조인
	private String name;

	@Override
	public String toString() {
		return "Cart [cart_no=" + cart_no + ", member_no=" + member_no + ", pro_no=" + pro_no + ", quantity=" + quantity
				+ ", price=" + price + ", name=" + name + "]";
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	

}
