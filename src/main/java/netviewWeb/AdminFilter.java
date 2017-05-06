package netviewWeb;

import nic.Nicer;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by cellargalaxy on 2017/5/5.
 */
public class AdminFilter implements Filter {
	private FilterConfig config;
	
	public void init(FilterConfig filterConfig) throws ServletException {
		config = filterConfig;
	}
	
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		
		String disableLoginFilter = config.getInitParameter("disableLoginFilter");
		if (disableLoginFilter.toUpperCase().equals("Y")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		String requestPath = request.getRequestURI();
		String separator = config.getInitParameter("separator");
		String loginPath = config.getInitParameter("exceptionPath");
		String[] loginPaths = loginPath.split(separator);
		for (String path : loginPaths) {
			if (requestPath.contains(path)) {
				filterChain.doFilter(request, response);
				return;
			}
		}
		
		HttpSession session = request.getSession();
		try {
			Object object = session.getAttribute("youNicer");
			Nicer nicer = (Nicer) object;
			if (nicer.getAdmin() == Nicer.isAdmin) {
				filterChain.doFilter(request, response);
			} else {
				request.getSession().setAttribute("error", "你没有管理员权限，请用管理员账号登录！");
				response.sendRedirect("/nic/login");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("error", "请登录！");
			response.sendRedirect("/nic/login");
		}
	}
	
	public void destroy() {
		this.config = null;
	}
}
