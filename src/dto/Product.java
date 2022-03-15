package dto;

public class Product {
	
	private int pro_no;
	private int	cty_no;
	private String name;
	private String img_path;
	private int price;
	private String description;
	
	@Override
	public String toString() {
		return "Product [pro_no=" + pro_no + ", cty_no=" + cty_no + ", name=" + name + ", img_path=" + img_path
				+ ", price=" + price + ", description=" + description + "]";
	}

	public int getPro_no() {
		return pro_no;
	}

	public void setPro_no(int pro_no) {
		this.pro_no = pro_no;
	}

	public int getCty_no() {
		return cty_no;
	}

	public void setCty_no(int cty_no) {
		this.cty_no = cty_no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImg_path() {
		return img_path;
	}

	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
