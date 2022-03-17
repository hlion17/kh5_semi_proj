package dto;

public class ReviewFile {
	
	private int img_no;
	private int riview_no;
	private String origin_name;
	private String stored_name;
	private String path;
	
	@Override
	public String toString() {
		return "ReviewFile [img_no=" + img_no + ", riview_no=" + riview_no + ", origin_name=" + origin_name
				+ ", stored_name=" + stored_name + ", path=" + path + "]";
	}
	
	
	public int getImg_no() {
		return img_no;
	}
	public int getRiview_no() {
		return riview_no;
	}
	public String getOrigin_name() {
		return origin_name;
	}
	public String getStored_name() {
		return stored_name;
	}
	public String getPath() {
		return path;
	}
	
	

}
