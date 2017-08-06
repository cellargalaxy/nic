package nicWeb;

import nic.Nic;
import nic.Nicer;
import org.json.JSONObject;
import util.BeanMapJson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created by cellargalaxy on 2017/5/4.
 */
public class ChangeNicerServlet extends HttpServlet {
    private String jsp = "/jsp/changeNicer.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idString = req.getParameter("id");
        try {
            long id = new Long(idString);

            HttpSession session = req.getSession();
            Nicer youNicer = (Nicer) session.getAttribute("youNicer");
            if (youNicer.getStatus() != youNicer.getAdminStatus() && youNicer.getId() != id) {
                req.getSession().setAttribute("error", "非法修改个人信息，请重新登录！");
                resp.sendRedirect("/nic/login");
                return;
            }

            Nicer nicer = Nic.getNic().findNicer(id);
            if (nicer != null) {
                req.setAttribute("changeNicer", nicer);
            } else {
                req.setAttribute("error", "账号：" + idString + "不存在！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "账号：" + idString + "不存在！");
        }
        req.getRequestDispatcher(jsp).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String string = req.getParameter("nicer");
        JSONObject jsonObject = new JSONObject();
        try {
            Map map = BeanMapJson.jsonToMap(new JSONObject(string));

            HttpSession session = req.getSession();
            Nicer youNicer = (Nicer) session.getAttribute("youNicer");
            if (youNicer.getStatus() != youNicer.getAdminStatus() && !(youNicer.getId() + "").equals(map.get("id"))) {
                jsonObject.put("result", false);
                jsonObject.put("info", "非法修改个人信息，请重新登录！");
            } else if (Nic.getNic().changeNicer(map)) {
                jsonObject.put("result", true);
                jsonObject.put("info", "修改成功");
            } else {
                jsonObject.put("result", false);
                jsonObject.put("info", "缺少所需信息");
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("result", false);
            jsonObject.put("info", "提交失败");
        }

        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.println(jsonObject);
        out.close();
    }
}
