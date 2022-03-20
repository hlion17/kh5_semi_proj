package dto;


public class ProfileFile {

	private int fileno;
	private int memberno;
	private String originname;
	private String storedname;
	private int filesize;
	@Override
	public String toString() {
		return "ProfileFile [fileno=" + fileno + ", memberno=" + memberno + ", originname=" + originname
				+ ", storedname=" + storedname + ", filesize=" + filesize + "]";
	}
	public int getFileno() {
		return fileno;
	}
	public void setFileno(int fileno) {
		this.fileno = fileno;
	}
	public int getMemberno() {
		return memberno;
	}
	public void setMemberno(int memberno) {
		this.memberno = memberno;
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
	
	
}
