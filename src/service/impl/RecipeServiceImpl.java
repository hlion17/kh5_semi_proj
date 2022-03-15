package service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Recipe;
import dto.RecipeFile;
import service.face.RecipeService;
import util.Paging;

public class RecipeServiceImpl implements RecipeService {

	@Override
	public List<Recipe> recipeBoard() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Recipe> getList(Paging paging) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Paging getPaging(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Recipe getRecipeIdx(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Recipe recipeContent(Recipe recipe) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void recipeInsert(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getNick(Recipe viewRecipe) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecipeFile viewFile(Recipe viewRecipe) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void recipeUpdate(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void recipeDelete(Recipe recipe) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Recipe> recipeRank() {
		// TODO Auto-generated method stub
		return null;
	}

}
