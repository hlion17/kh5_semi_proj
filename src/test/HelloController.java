package test;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({ "/HelloController", "/hello" })
public class HelloController extends HttpServlet {
	private static final long serialVersionUID = 1L;
		List list = new ArrayList();
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			System.out.println("요요췌키췌키");
		}
}
