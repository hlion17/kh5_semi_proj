package dto;

import java.util.Date;

public class Review {
	//Review DTO객체			//REVIEW 테이블
	private int review_no;		//review_no	NUMBER			
	private int pro_no;			//pro_no	NUMBER			상품번호
	private int member_no;		//MEMBER_NO	NUMBER
	private String title;		//TITLE		VARCHAR2(100)
	private String content;		//CONTENT	VARCHAR2(4000)
	private Date regdate;		//REGDATE	DATE
	private int hit;			//HIT		NUMBER
	
								//MEMBER 테이블
	private String nick;		//NICK		VARCHAR2(100)
	private String name;		//NAME		VARCHAR2(100)	멤버이름
						
								//PRODUCT 테이블
	private String pro_name;	//NAME		VARCHAR2(100)	상품이름
	@Override
	public String toString() {
		return "Review [review_no=" + review_no + ", pro_no=" + pro_no + ", member_no=" + member_no + ", title=" + title
				+ ", content=" + content + ", regdate=" + regdate + ", hit=" + hit + ", nick=" + nick + ", name=" + name
				+ ", pro_name=" + pro_name + "]";
	}
	public int getReview_no() {
		return review_no;
	}
	public void setReview_no(int review_no) {
		this.review_no = review_no;
	}
	public int getPro_no() {
		return pro_no;
	}
	public void setPro_no(int pro_no) {
		this.pro_no = pro_no;
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
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPro_name() {
		return pro_name;
	}
	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}
	
}
