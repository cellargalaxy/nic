package wechatWeb;

import netview.Netview;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by cellargalaxy on 17-8-20.
 */
public class StatusChangeInfoServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter printWriter = resp.getWriter();
		printWriter.write(Netview.getNETVIEW().getChangeStatusInfo().toString());
		printWriter.close();
		Netview.getNETVIEW().clearChangeStatusInfo();
	}
}
