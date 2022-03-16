package dto;

import java.util.Date;

public class RecipeFile {
	
	//recipeImg 테이블과 매칭
	private int fileno;			//파일번호		img_no
	private int boardno;		//게시글번호	board_no
	private String originname;	//원본파일명	origin_name
	private String storedname;	//저장파일명	stored_name
	private int filesize;		//파일크기		file_size	//DB컬럼 추가요망
	private Date writeDate;		//저장날짜		write_date	//DB컬럼 추가요망
	private String path;		//파일경로		path
	
	@Override
	public String toString() {
		return "RecipeFile [fileno=" + fileno + ", boardno=" + boardno + ", originname=" + originname + ", storedname="
				+ storedname + ", filesize=" + filesize + ", path=" + path + "]";
	}

	public int getFileno() {
		return fileno;
	}

	public void setFileno(int fileno) {
		this.fileno = fileno;
	}

	public int getBoardno() {
		return boardno;
	}

	public void setBoardno(int boardno) {
		this.boardno = boardno;
	}

	public String getOriginname() {
		return originname;
	}

	public void setOriginname(String originname) {
		this.originname = originname;
	}

	public String getStoredname() {
		return storedname;
	}

	public void setStoredname(String storedname) {
		this.storedname = storedname;
	}

	public int getFilesize() {
		return filesize;
	}

	public void setFilesize(int filesize) {
		this.filesize = filesize;
	}

	public Date getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}
