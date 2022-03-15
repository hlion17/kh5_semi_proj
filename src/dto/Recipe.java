package dto;

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
	
	
}
