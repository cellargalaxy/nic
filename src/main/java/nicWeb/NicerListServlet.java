package nicWeb;

import nic.Nic;
import nic.Nicer;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

/**
 * Created by cellargalaxy on 2017/5/3.
 */
public class NicerListServlet extends HttpServlet {
    private String jsp = "/jsp/nicerList.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LinkedList<Nicer> tempNicers = Nic.getNic().findAllTempNicer();
        LinkedList<Nicer> passNicers = Nic.getNic().findAllPassNicer();
        req.setAttribute("tempNicers", tempNicers);
        req.setAttribute("passNicers", passNicers);
        req.getRequestDispatcher(jsp).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idString = req.getParameter("id");
        String resultString = req.getParameter("result");
        JSONObject jsonObject = new JSONObject();
        try {
            long id = new Long(idString);
            int result = new Integer(resultString);
            if (result == 0) {
                if (Nic.getNic().deleteNicer(id)) {
                    jsonObject.put("result", true);
                    jsonObject.put("info", "删除成功");
                } else {
                    jsonObject.put("result", false);
                    jsonObject.put("info", "删除失败");
                }
            } else if (result == 1) {
                if (Nic.getNic().turnRightNicer(id)) {
                    jsonObject.put("result", true);
                    jsonObject.put("info", "提交成功");
                } else {
                    jsonObject.put("result", false);
                    jsonObject.put("info", "提交失败");
                }
            } else {
                jsonObject.put("result", false);
                jsonObject.put("info", "提交信息错误");
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
