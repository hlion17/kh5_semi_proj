package dao.impl;

import java.sql.Connection;
import java.util.List;

import dao.face.RecipeDao;
import dto.Recipe;
import dto.RecipeFile;
import util.Paging;

public class RecipeDaoImpl implements RecipeDao {

	@Override
	public List<Recipe> selectAll(Connection conn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Recipe> selectAll(Connection conn, Paging paging) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectCntAll(Connection conn) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateHit(Connection conn, Recipe recipeIdx) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Recipe selectRecipeByRecipeIdx(Connection conn, Recipe recipeIdx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(Connection conn, Recipe recipe) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String selectNickByUserid(Connection conn, Recipe viewRecipe) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectRecipeIdx(Connection conn) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int recipeInsertFile(Connection conn, RecipeFile recipeFile) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public RecipeFile selectFile(Connection conn, Recipe viewRecipe) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int recipeUpdate(Connection conn, Recipe recipe) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int recipeDelete(Connection conn, Recipe recipe) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int recipeDeleteFile(Connection conn, Recipe recipe) {
		// TODO Auto-generated method stub
		return 0;
	}

}
