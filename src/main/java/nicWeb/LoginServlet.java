package nicWeb;

import nic.Nic;
import nic.Nicer;

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
	private String jsp = "/jsp/login.jsp";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Object object = session.getAttribute("error");
		if (object != null) {
			session.setAttribute("error", null);
			req.setAttribute("error", object);
		}
		req.getRequestDispatcher(jsp).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idString = req.getParameter("id");
		String pw = req.getParameter("pw");
		try {
			Nicer nicer = Nic.getNic().findNicer(new Long(idString));
			if (nicer.getStatus() == Nicer.temp) {
				req.setAttribute("error", "账号审核未通过");
				req.getRequestDispatcher(jsp).forward(req, resp);
			} else if (pw.equals(nicer.getPassword())) {
				HttpSession session = req.getSession();
				session.setAttribute("youNicer", nicer);
				resp.sendRedirect("/nic");
			} else {
				req.setAttribute("error", "密码错误");
				req.getRequestDispatcher(jsp).forward(req, resp);
			}
		} catch (Exception e) {
			req.setAttribute("error", "账号不存在");
			req.getRequestDispatcher(jsp).forward(req, resp);
		}
	}
}
