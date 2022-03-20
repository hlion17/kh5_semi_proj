package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(urlPatterns = "/*")
public class EncodingFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		req.setCharacterEncoding("UTF-8");
		
//		//함부로 추천할수없게 기본적으로 막아놓기
//      	req.setAttribute("like_msg_flag", true);
		
		chain.doFilter(req, resp);
		
	}

}
