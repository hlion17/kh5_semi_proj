package dto;

import java.util.Date;

public class QaFile {

	private int fileno;
	private int boardno;
	private String originname;
	private String storedname;
	private Date writeDate;
	private int filesize;
	@Override
	public String toString() {
		return "QaFile [fileno=" + fileno + ", boardno=" + boardno + ", originname=" + originname + ", storedname="
				+ storedname + ", writeDate=" + writeDate + ", filesize=" + filesize + "]";
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
	public Date getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}
	public int getFilesize() {
		return filesize;
	}
	public void setFilesize(int filesize) {
		this.filesize = filesize;
	}
	
	
}
