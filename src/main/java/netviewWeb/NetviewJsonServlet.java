package netviewWeb;

import netview.Netview;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by cellargalaxy on 2017/4/25.
 */
public class NetviewJsonServlet extends HttpServlet {
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/plain;charset=utf-8");
		req.setCharacterEncoding("utf-8");
		
		PrintWriter out = resp.getWriter();
		String building = req.getParameter("building");
		String floor = req.getParameter("floor");
		String name = req.getParameter("name");
		String address = req.getParameter("address");
		JSONObject jsonObject = new JSONObject();
		if (building != null && floor != null && name != null && address != null) {
			Netview netview = Netview.getNetview();
			try {
				jsonObject.put("result", netview.addHost(building, new Integer(floor), name, address));
			} catch (Exception e) {
				jsonObject.put("result", false);
			}
			jsonObject.put("ip", address);
		} else {
			jsonObject.put("result", false);
		}
		out.println(jsonObject);
		out.flush();
		out.close();
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/plain;charset=utf-8");
		req.setCharacterEncoding("utf-8");
		
		PrintWriter out = resp.getWriter();
		String address = req.getParameter("address");
		JSONObject jsonObject = new JSONObject();
		if (address != null) {
			Netview netview = Netview.getNetview();
			jsonObject.put("result", netview.deleteHost(address));
			jsonObject.put("ip", address);
		} else {
			jsonObject.put("result", false);
		}
		out.println(jsonObject);
		out.flush();
		out.close();
	}
}
