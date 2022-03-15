package dto;

<<<<<<< HEAD
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
	
	

=======
public class Recipe {
	private int recipeIdx;
	private String recipeTitle;
	private String memberId;
	private String recipeTime;
	private int recipeLike;
	private int recipeHit;
	private String recipeMainImage;
	private String recipeIntro;
	private String recipeImage;
	private String recipeSource;
	private String recipeExplain;
	
	@Override
	public String toString() {
		return "Recipe [recipeIdx=" + recipeIdx + ", recipeTitle=" + recipeTitle + ", memberId=" + memberId
				+ ", recipeTime=" + recipeTime + ", recipeLike=" + recipeLike + ", recipeHit=" + recipeHit
				+ ", recipeMainImage=" + recipeMainImage + ", recipeIntro=" + recipeIntro + ", recipeImage="
				+ recipeImage + ", recipeSource=" + recipeSource + ", recipeExplain=" + recipeExplain + "]";
	}

	public int getRecipeIdx() {
		return recipeIdx;
	}

	public void setRecipeIdx(int recipeIdx) {
		this.recipeIdx = recipeIdx;
	}

	public String getRecipeTitle() {
		return recipeTitle;
	}

	public void setRecipeTitle(String recipeTitle) {
		this.recipeTitle = recipeTitle;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getRecipeTime() {
		return recipeTime;
	}

	public void setRecipeTime(String recipeTime) {
		this.recipeTime = recipeTime;
	}

	public int getRecipeLike() {
		return recipeLike;
	}

	public void setRecipeLike(int recipeLike) {
		this.recipeLike = recipeLike;
	}

	public int getRecipeHit() {
		return recipeHit;
	}

	public void setRecipeHit(int recipeHit) {
		this.recipeHit = recipeHit;
	}

	public String getRecipeMainImage() {
		return recipeMainImage;
	}

	public void setRecipeMainImage(String recipeMainImage) {
		this.recipeMainImage = recipeMainImage;
	}

	public String getRecipeIntro() {
		return recipeIntro;
	}

	public void setRecipeIntro(String recipeIntro) {
		this.recipeIntro = recipeIntro;
	}

	public String getRecipeImage() {
		return recipeImage;
	}

	public void setRecipeImage(String recipeImage) {
		this.recipeImage = recipeImage;
	}

	public String getRecipeSource() {
		return recipeSource;
	}

	public void setRecipeSource(String recipeSource) {
		this.recipeSource = recipeSource;
	}

	public String getRecipeExplain() {
		return recipeExplain;
	}

	public void setRecipeExplain(String recipeExplain) {
		this.recipeExplain = recipeExplain;
	}
	
	
>>>>>>> branch 'master' of https://github.com/hlion17/kh5_semi_proj.git
}
