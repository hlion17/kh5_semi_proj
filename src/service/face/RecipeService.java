package service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Recipe;
import dto.RecipeFile;
import util.Paging;

public interface RecipeService {
	/**
	 * �Խñ� ��ü ��ȸ
	 * @return 
	 */
	public List<Recipe> recipeBoard();
	
	/**
	 * �Խñ� ����¡ ��� ��ȸ
	 * 
	 * @param paging - ����¡ ���� ��ü
	 * @return List<Board> - ����¡�� �ݿ��� �Խñ� ��ȸ ��� ���
	 */
	public List<Recipe> getList(Paging paging);
	
	/**
	 * ����¡ ��ü ����
	 * 
	 * @param req - ��û ���� ��ü
	 * @return Paging - ����¡ ����� �Ϸ�� Paging��ü
	 */
	public Paging getPaging(HttpServletRequest req);

	/**
	 * ��û �Ķ���� ������
	 * 
	 * @param req - ��û ���� ��ü
	 * @return Recipe - �����Ķ���� recipeIdx���� ������ DTO��ü
	 */
	public Recipe getRecipeIdx(HttpServletRequest req);

	/**
	 * ���޵� recipeIdx�� �̿��Ͽ� �Խñ��� ��ȸ�Ѵ�
	 * 
	 * ��ȸ�� �Խñ��� ��ȸ���� 1 ������Ų��
	 * 
	 * @param boardno - ��ȸ�� recipeIdx�� ������ �ִ� DTO��ü
	 * @return Board - ��ȸ�� �Խñ� ����
	 */
	public Recipe recipeContent(Recipe recipe);

	/**
	 * �Խñ� �ۼ�
	 * 	�Է��� �Խñ� ������ DB�� ����
	 * 
	 * @param req - ��û���� ��ü(�Խñ۳��� + ÷������)
	 * 
	 */
//	public void recipeInsert(Recipe recipe);
	public void recipeInsert(HttpServletRequest req);

	/**
	 * ���޵� Recipe ��ü�� id �� �̿��� �г��� ��ȸ
	 * 
	 * @param viewRecipe - ��ȸ�� �Խñ� ����
	 * @return String - �Խñ� �ۼ����� �г���
	 */
	public String getNick(Recipe viewRecipe);

	/**
	 * ÷������ ���� ��ȸ�ϱ�
	 * 
	 * @param viewRecipe - ÷�����ϰ� ����� �Խñ��� ��ȣ
	 * @return RecipeFile - ÷������ ���� DTO��ü
	 */
	public RecipeFile viewFile(Recipe viewRecipe);

	/**
	 * �Խñ� ����
	 * 
	 * @param req - ��û ���� ��ü
	 */
	public void recipeUpdate(HttpServletRequest req);

	/**
	 * �Խñ� ����
	 * 
	 * @param recipe - ������ �Խñ� ��ȣ�� ���� ��ü
	 */
	public void recipeDelete(Recipe recipe);

	/**
	 * ������ ��ŷ
	 * @return
	 */
	public List<Recipe> recipeRank();
	
	

}
