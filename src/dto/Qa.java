package dto;

import java.util.Date;

public class Qa {

	private int boardno;
	private String title;
	private String memberid;
	private String content;
	private int hit;
	private Date writeDate;
	private int memberno;
	@Override
	public String toString() {
		return "Qa [boardno=" + boardno + ", title=" + title + ", memberid=" + memberid + ", content=" + content
				+ ", hit=" + hit + ", writeDate=" + writeDate + ", memberno=" + memberno + "]";
	}
	public int getBoardno() {
		return boardno;
	}
	public void setBoardno(int boardno) {
		this.boardno = boardno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public Date getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}
	public int getMemberno() {
		return memberno;
	}
	public void setMemberno(int memberno) {
		this.memberno = memberno;
	}
	
	
}
