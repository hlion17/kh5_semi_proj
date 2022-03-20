package controller.store;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Product;
import service.face.StoreService;
import service.impl.StoreServiceImpl;
import util.Paging;


@WebServlet("/store")
public class StoreViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private StoreService storeService = new StoreServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	System.out.println("/store/main [GET]");
	
	//상품 전체 조회 - BoardService이용
	List<Product> productList = storeService.getList();
	
	//조회결과 MODEL값 전달 - req.setAttribute
	req.setAttribute("ProductList", productList);

	//VIEW 지정 및 응답 - forward
	req.getRequestDispatcher("/WEB-INF/views/store/store.jsp").forward(req, resp);
	
	}

}
