package dto;

import java.util.Date;

public class Recipe {
	
	//<DTO 수정사항 - 3월16일 00:05 기준>
	//클래스다이어그램에 기반한 기존 DTO가 DB를 고려하지 않고 제작되어 전면 수정되었습니다
	//최대한 강사님 코드가 유지되는 것에 초점을 맞췄습니다
	
	//recipe 테이블
//	private int recipeIdx; 			//게시글번호		board_no		[PK]
//	private String memberId;		//작성자			member_no		[FK]
//	private String recipeTitle; 	//게시글제목		title		
//	private String recipeExplain;	//게시글내용		content
//									//게시글등록일		클래스다이어그램에 안넣음
//	private String recipeTime;		//게시글수정일		update_date
//	private int recipeHit;			//조회수			hit
//	private int recipeLike;			//추천수			board_like
//	private String recipeIntro;		//ERD에 없음 - 속성추가 or 게시글 내용 일부 발췌
//	
//	//recipe_ingr 테이블
//	private String recipeSource;	//레시피_재료번호	recipe_ingr_no	[PK] 테이블에선 NUMBER
//	
//	//recipeImg 테이블
//	private String recipeImage;		//레시피이미지 하나로 퉁칠게아니라 dto 분리해야함, recipeFile dto로 이동
//	private String recipeMainImage;	//ERD에 없음 - 속성 or 기존 이미지중에 고르기 or 첫번째 이미지를 디폴트
//									//String -> Boolean요망
	
	//recipe 테이블
	private int boardno;			//게시글번호		board_no	[PK]
	private String title; 			//게시글제목		title		
	private int userid;				//작성자			member_no	[FK]//DB는 NUMBER
	private String content;			//게시글내용		content
	private int hit;				//조회수			hit
//	private String reg_date;		//게시글등록일		reg_date		//DB컬럼 제거요망	
	private Date writeDate;			//게시글수정일		update_date
	private int like;				//추천수			board_like
	private String intro;			//게시글소개글		intro		//DB컬럼 추가요망
	
	@Override
	public String toString() {
		return "Recipe [boardno=" + boardno + ", title=" + title + ", userid=" + userid + ", content=" + content
				+ ", hit=" + hit + ", writeDate=" + writeDate + ", like=" + like + ", intro=" + intro + "]";
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

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
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

	public int getLike() {
		return like;
	}

	public void setLike(int like) {
		this.like = like;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}
	
	
}
