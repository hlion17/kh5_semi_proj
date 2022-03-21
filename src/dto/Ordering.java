package dto;

import java.util.Date;

public class Ordering {
	
	private int order_no;
	private int mumber_no;
	private int deli_no;
	private Date order_date;
	private int order_qty;
	private int total;
	private String status;
	
	@Override
	public String toString() {
		return "Orderling [order_no=" + order_no + ", mumber_no=" + mumber_no + ", deli_no=" + deli_no + ", order_date="
				+ order_date + ", order_qty=" + order_qty + ", total=" + total + ", status=" + status + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	public int getOrder_no() {
		return order_no;
	}
	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}
	public int getMumber_no() {
		return mumber_no;
	}
	public void setMumber_no(int mumber_no) {
		this.mumber_no = mumber_no;
	}
	public int getDeli_no() {
		return deli_no;
	}
	public void setDeli_no(int deli_no) {
		this.deli_no = deli_no;
	}
	public Date getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}
	public int getOrder_qty() {
		return order_qty;
	}
	public void setOrder_qty(int order_qty) {
		this.order_qty = order_qty;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	

}
