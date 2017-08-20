package netviewWeb;

import util.FileServletMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * Created by cellargalaxy on 2017/5/2.
 */
public class IpFileServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		File file = new File(getServletContext().getRealPath("/") + "/otherFile/ip.xls");
		FileServletMethod.sendFile(resp, file, true, file.getName());
	}
	
	
}
