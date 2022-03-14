package test;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * Servlet implementation class HelloController
 */
@WebServlet({ "/HelloController", "/hello" })
public class HelloController extends HttpServlet {
	private static final long serialVersionUID = 1L;

}
