package dto;

import oracle.sql.DATE;

public class Recipe {
	
	private int board_no;
	private int member_no;
	private String title;
	private String content;
	private DATE reg_date;
	private DATE updated_date;
	private int hit;
	private int board_like;
	
	@Override
	public String toString() {
		return "recipe [board_no=" + board_no + ", member_no=" + member_no + ", title=" + title + ", content=" + content
				+ ", reg_date=" + reg_date + ", updated_date=" + updated_date + ", hit=" + hit + ", board_like="
				+ board_like + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

	public int getBoard_no() {
		return board_no;
	}

	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}

	public int getMember_no() {
		return member_no;
	}

	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public DATE getReg_date() {
		return reg_date;
	}

	public void setReg_date(DATE reg_date) {
		this.reg_date = reg_date;
	}

	public DATE getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(DATE updated_date) {
		this.updated_date = updated_date;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public int getBoard_like() {
		return board_like;
	}

	public void setBoard_like(int board_like) {
		this.board_like = board_like;
	}
	
	

}
