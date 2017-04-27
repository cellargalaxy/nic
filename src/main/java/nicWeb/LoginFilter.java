package nicWeb;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by cellargalaxy on 2017/4/27.
 */
public class LoginFilter implements Filter{
	
	public void init(FilterConfig filterConfig) throws ServletException {
	
	}
	
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletResponse response=(HttpServletResponse)servletResponse;
		HttpServletRequest request = (HttpServletRequest)servletRequest;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/plain;charset=utf-8");
		
		HttpSession session=request.getSession();
		if (session.getAttribute("isLogin")!=null||request.getRequestURI().equals("/nic/")) {
			filterChain.doFilter(request,response);
		}else {
			response.sendRedirect("/nic/");
		}
	}
	
	public void destroy() {
	
	}
}
