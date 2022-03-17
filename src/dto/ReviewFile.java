package dto;

public class ReviewFile {
	
	private int img_no;
	private int review_no;
	private String origin_name;
	private String stored_name;
	private String path;
	private int filesize;
	
	
	@Override
	public String toString() {
		return "ReviewFile [img_no=" + img_no + ", review_no=" + review_no + ", origin_name=" + origin_name
				+ ", stored_name=" + stored_name + ", path=" + path + ", filesize=" + filesize + "]";
	}


	public int getImg_no() {
		return img_no;
	}


	public void setImg_no(int img_no) {
		this.img_no = img_no;
	}


	public int getReview_no() {
		return review_no;
	}


	public void setReview_no(int review_no) {
		this.review_no = review_no;
	}


	public String getOrigin_name() {
		return origin_name;
	}


	public void setOrigin_name(String origin_name) {
		this.origin_name = origin_name;
	}


	public String getStored_name() {
		return stored_name;
	}


	public void setStored_name(String stored_name) {
		this.stored_name = stored_name;
	}


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}


	public int getFilesize() {
		return filesize;
	}


	public void setFilesize(int filesize) {
		this.filesize = filesize;
	}


	
	
	
}
	