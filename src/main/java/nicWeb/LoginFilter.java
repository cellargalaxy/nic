package nicWeb;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by cellargalaxy on 2017/4/27.
 */
public class LoginFilter implements Filter {
    private String errorJsp = "/error.jsp";
    private FilterConfig config;

    public void init(FilterConfig filterConfig) throws ServletException {
        config = filterConfig;
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        request.setCharacterEncoding(config.getInitParameter("coding"));
        response.setContentType("text/plain;charset=utf-8");

        String disableLoginFilter = config.getInitParameter("disableLoginFilter");
        if (disableLoginFilter.toUpperCase().equals("Y")) {
            filterChain.doFilter(request, response);
            return;
        }

        String requestPath = request.getRequestURI();
        String separator = config.getInitParameter("separator");
        String exceptionPath = config.getInitParameter("exceptionPath");
        String[] exceptionPaths = exceptionPath.split(separator);
        for (String path : exceptionPaths) {
            if (requestPath.contains(path)) {
                filterChain.doFilter(request, response);
                return;
            }
        }

        HttpSession session = request.getSession();
        if (session.getAttribute("youNicer") != null) {
            filterChain.doFilter(request, response);
        } else {
            request.setAttribute("error", "请登录！");
            request.getRequestDispatcher(errorJsp).forward(request, response);
        }
    }

    public void destroy() {
        this.config = null;
    }
}
