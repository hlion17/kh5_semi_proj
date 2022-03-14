package dto;

import java.util.Date;

public class RefItem {
	private int itemNo;
	private String ingrName;
	private String itemQty;
	private int status; // 0 - 냉장, 1 - 냉동, 2 - 실온
	private Date regDate;
	private Date expireDate;
	private String note;
	
	@Override
	public String toString() {
		return "RefItem [itemNo=" + itemNo + ", ingrName=" + ingrName + ", itemQty=" + itemQty + ", status=" + status
				+ ", regDate=" + regDate + ", expireDate=" + expireDate + ", note=" + note + "]";
	}

	public int getItemNo() {
		return itemNo;
	}

	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}

	public String getIngrName() {
		return ingrName;
	}

	public void setIngrName(String ingrName) {
		this.ingrName = ingrName;
	}

	public String getItemQty() {
		return itemQty;
	}

	public void setItemQty(String itemQty) {
		this.itemQty = itemQty;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	
}
