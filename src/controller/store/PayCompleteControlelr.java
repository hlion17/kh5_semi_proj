package controller.store;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.StoreService;
import service.impl.StoreServiceImpl;

@WebServlet("/PayComplete")
public class PayCompleteControlelr extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private StoreService storeService = new StoreServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}


}

