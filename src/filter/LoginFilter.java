package filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter({"/ref/choose"})
public class LoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

        HttpSession session = ((HttpServletRequest) request).getSession();
        HttpServletResponse resp = ((HttpServletResponse) response);
        HttpServletRequest req = ((HttpServletRequest) request);

        Boolean login = (Boolean) session.getAttribute("login");

        if (login == null || login == false) {
            // 로그인 페이지로 이동
            response.setContentType("text/html;charset=UTF-8");

            PrintWriter out = response.getWriter();
            out.println("<script> " +
                    "alert('로그인이 필요한 서비스 입니다.');location.href='/member/login';</script>");
            out.flush();

        } else {
            chain.doFilter(req, resp);
        }
		
	}

}
