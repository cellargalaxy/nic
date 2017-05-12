package nicWeb;

import nic.Nic;
import nic.Nicer;
import org.apache.log4j.Logger;

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
	private static final Logger LOGGER=Logger.getLogger(LoginServlet.class.getName());
	private String successJsp="/jsp/login.jsp";
	private String failJsp = "/error.jsp";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Object object = session.getAttribute("error");
		if (object != null) {
			session.setAttribute("error", null);
			req.setAttribute("error", object);
		}
		req.getRequestDispatcher(successJsp).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idString = req.getParameter("id");
		String pw = req.getParameter("pw");
		try {
			Nicer nicer = Nic.getNic().findNicer(new Long(idString));
			if (nicer.getStatus() == Nicer.temp && pw.equals(nicer.getPassword())) {
				req.setAttribute("error", "账号审核未通过!");
				req.getRequestDispatcher(failJsp).forward(req, resp);
			} else if (pw.equals(nicer.getPassword())) {
				HttpSession session = req.getSession();
				session.setAttribute("youNicer", nicer);
				resp.sendRedirect("/nic");
				LOGGER.info("用户登录："+idString);
			} else {
				req.setAttribute("error", "账号或密码错误，请重新登录！");
				req.getRequestDispatcher(failJsp).forward(req, resp);
			}
		} catch (Exception e) {
			req.setAttribute("error", "账号或密码错误，请重新登录！");
			req.getRequestDispatcher(failJsp).forward(req, resp);
		}
	}
}
