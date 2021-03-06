package controller.dictionary;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.OpenRecipeService;
import service.impl.OpenRecipeServiceImpl;

@WebServlet("/openrecipe/list999999")
public class OpenRecipeListController_deprecated extends HttpServlet {

    private OpenRecipeService openRecipeService = new OpenRecipeServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	System.out.println("확인");
        if ("y".equals(req.getParameter("post"))) {
            doPost(req, resp);
        } else {
            req.getRequestDispatcher("/WEB-INF/views/dictionary/openRecipe/openRecipeSearch.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	System.out.println("포스트 확인");
        // 파리미터 정보
        String itemName = req.getParameter("itemName");
        String paramCurPage = req.getParameter("curPage");

        // 공식레시피 조회 서비스 - 페이지에 표시될 레시피 목록
        openRecipeService.getPageList(req, itemName, paramCurPage);

        // View
        req.getRequestDispatcher("/WEB-INF/views/dictionary/openRecipe/openRecipeList.jsp").forward(req, resp);
    }
}
