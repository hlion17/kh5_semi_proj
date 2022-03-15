package controller.dictionary;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Ingredient;
import service.face.IngrService;
import service.impl.IngrServiceImpl;

@WebServlet(value = "/ingr/list")
public class IngrListController extends HttpServlet {
	
    private IngrService ingrService = new IngrServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 재료리스트 가져오는 서비스 이용
        List<Ingredient> ingrs = ingrService.getAllIngrs();

        request.setAttribute("ingrs", ingrs);

        // 재료리스트 목록페이지로 이동
        request.getRequestDispatcher("/WEB-INF/views/dictionary/ingrList.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
