package service.face;

import dto.OpenRecipe;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface OpenRecipeService {
    void getPageList(HttpServletRequest request, String itemName, String curPage);
}
