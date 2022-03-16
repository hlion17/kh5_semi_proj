package controller.store;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Product;
import service.face.StoreService;
import service.impl.StoreServiceImpl;


@WebServlet("/productInfo")
public class ProductInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private StoreService storeService = new StoreServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		//전달파라미터 얻기 - pro_no(상품번호)
		Product productNo = storeService.getProno(req);

		//상세보기 결과 조회
		Product viewProduct = storeService.view(productNo);
		
		//조회결과 MODEL값 전달
		req.setAttribute("viewProduct", viewProduct);

		//VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/store/productinfo.jsp").forward(req, resp);
		
	}
	

}
