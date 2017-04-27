package nicWeb;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by cellargalaxy on 2017/4/27.
 */
public class LoginServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/plain;charset=utf-8");
		req.setCharacterEncoding("utf-8");
		
		String pw=req.getParameter("pw");
		System.out.println("ps:"+pw);
		if (pw!=null&&pw.equals("nicview")) {
			HttpSession session=req.getSession();
			session.setAttribute("isLogin",true);
			resp.sendRedirect("/nic/netview");
		}else {
			req.setAttribute("error","密码错误");
			req.getRequestDispatcher("index.jsp").forward(req, resp);
		}
		
	}
}
