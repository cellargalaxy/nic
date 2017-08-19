package nicWeb;

import nic.PicturePool;
import util.FileServletMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * Created by cellargalaxy on 2017/5/4.
 */
public class PictureServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String indexString = req.getParameter("index");
		File picture;
		try {
			double d = Math.abs(new Double(indexString));
			if (d < 1) {
				picture = PicturePool.getPictureFile(d);
			} else {
				picture = PicturePool.getPictureFile((int) d);
			}
			if (picture == null) {
				picture = new File(getServletContext().getRealPath("/") + "/image/nic.jpg");
			}
		} catch (Exception e) {
			picture = new File(getServletContext().getRealPath("/") + "/image/nic.jpg");
		}
		FileServletMethod.sendFile(resp, picture, false, null);
	}
	
	
}
