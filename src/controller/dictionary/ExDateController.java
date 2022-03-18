package controller.dictionary;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.ExpireDate;
import service.face.ExpireDateService;
import service.impl.ExpireServiceImpl;

@WebServlet("/expireDate/list")
public class ExDateController extends HttpServlet {
    
	private ExpireDateService expireDateService = new ExpireServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/dictionary/ExDate/expireDateSearch.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String itemName = req.getParameter("item");

        // 제품 유통기한 검색 서비스
        List<ExpireDate> result = expireDateService.getList(itemName);

        // 요청 속성에 결과 저장
        req.setAttribute("list", result);

        req.getRequestDispatcher("/WEB-INF/views/dictionary/ExDate/expireDateList.jsp").forward(req, resp);
    }

}
