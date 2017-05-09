package netviewWeb;

import netview.Netview;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by cellargalaxy on 2017/4/25.
 */
public class NetviewJsonServlet extends HttpServlet {
	
	/**
	 * 添加一个Host
	 *
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
		
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		out.println(jsonObject);
		out.close();
		
	}
	
	/**
	 * 删除一个Host
	 *
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String address = req.getParameter("address");
		JSONObject jsonObject = new JSONObject();
		if (address != null) {
			Netview netview = Netview.getNetview();
			jsonObject.put("result", netview.deleteHost(address));
		} else {
			jsonObject.put("result", false);
		}
		jsonObject.put("ip", address);
		
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		out.println(jsonObject);
		out.close();
	}
}
