package controller.store;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import service.face.StoreService;
import service.impl.StoreServiceImpl;

@WebServlet("/payment")
public class StorePaymentControlelr extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private StoreService storeService = new StoreServiceImpl();

}
