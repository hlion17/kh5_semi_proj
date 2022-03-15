package dto;

import java.util.Date;

public class RefItem {
	private int itemNo;
	private int ingrCtyCode;
	private String itemName;
	private String itemQty;
	private int status; // 0 - 냉장, 1 - 냉동, 2 - 실온
	private String regDate;
	private String expireDate;
	private String note;
	
	@Override
	public String toString() {
		return "RefItem [itemNo=" + itemNo + ", ingrCtyCode=" + ingrCtyCode + ", itemName=" + itemName + ", itemQty="
				+ itemQty + ", status=" + status + ", regDate=" + regDate + ", expireDate=" + expireDate + ", note="
				+ note + "]";
	}

	public int getItemNo() {
		return itemNo;
	}

	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
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

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getIngrCtyCode() {
		return ingrCtyCode;
	}

	public void setIngrCtyCode(int ingrCtyCode) {
		this.ingrCtyCode = ingrCtyCode;
	}
	
	
}
