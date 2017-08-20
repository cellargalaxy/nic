package nicWeb;

import nic.Nic;
import nic.Nicer;
import org.apache.log4j.Logger;
import util.MD5;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by cellargalaxy on 2017/4/27.
 */
public class LoginServlet extends HttpServlet {
	private static final Logger LOGGER = Logger.getLogger(LoginServlet.class.getName());
	private static final String SUCCESS_JSP = "/jsp/login.jsp";
	private static final String FAIL_JSP = "/error.jsp";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(SUCCESS_JSP).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idString = req.getParameter("id");
		String pw = req.getParameter("pw");
		try {
			Nicer nicer = Nic.getNic().findNicer(new Long(idString));
			if (nicer.getStatus() == nicer.getTempStatus()) {
				req.setAttribute("error", "账号审核未通过,请联系管理员");
				req.getRequestDispatcher(FAIL_JSP).forward(req, resp);
			} else if (pw != null && nicer.getPassword().equals(MD5.encryption(pw))) {
				HttpSession session = req.getSession();
				session.setAttribute("youNicer", nicer);
				resp.sendRedirect("/nic");
				LOGGER.info("用户登录：" + idString);
			} else {
				req.setAttribute("error", "账号或密码错误，请重新登录！");
				req.getRequestDispatcher(FAIL_JSP).forward(req, resp);
			}
		} catch (Exception e) {
			req.setAttribute("error", "账号或密码错误，请重新登录！");
			req.getRequestDispatcher(FAIL_JSP).forward(req, resp);
		}
	}
}
