package nicWeb;

import nic.PicturePool;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by cellargalaxy on 17-8-19.
 */
public class PictureWallServlet extends HttpServlet {
	private static final String JSP = "/jsp/pictureWall.jsp";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("pictureIndex", (int) (PicturePool.pictureCount() * Math.random()));
		req.getRequestDispatcher(JSP).forward(req, resp);
	}
}
