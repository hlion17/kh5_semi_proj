package test;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet({ "/HelloController", "/hello" })
public class HelloController extends HttpServlet {
	private static final long serialVersionUID = 1L;
		List list = new ArrayList();
}
