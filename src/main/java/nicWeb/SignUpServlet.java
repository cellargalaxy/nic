package nicWeb;

import nic.Nic;
import org.json.JSONObject;
import util.BeanMapJson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;

/**
 * Created by cellargalaxy on 2017/5/3.
 */
public class SignUpServlet extends HttpServlet {
	private String jsp = "/jsp/signUp.jsp";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(jsp).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String string = req.getParameter("nicer");
		JSONObject jsonObject = new JSONObject();
		try {
			Map map = BeanMapJson.jsonToMap(new JSONObject(string));
			if (Nic.getNic().addNicer(map)) {
				jsonObject.put("result", true);
				jsonObject.put("info", "注册成功，请等待管理员审核");
			} else {
				jsonObject.put("result", false);
				jsonObject.put("info", "缺少所需信息");
			}
		} catch (Exception e) {
			jsonObject.put("result", false);
			jsonObject.put("info", "提交失败");
		}
		
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		out.println(jsonObject);
		out.close();
	}
	
	
}
