package wechatWeb;


import org.json.JSONArray;
import wechat.JsonHost;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by 孵化种子 on 2017/8/6.
 */
public class JsonHostServlet extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token=req.getParameter("token");
        JSONArray jsonArray=null;
        if (token!=null && token.equals(JsonHost.jsonHostToken())) {
            jsonArray=JsonHost.jsonHosts();
        }else {
            jsonArray=new JSONArray();
        }

        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.println(jsonArray);
        out.close();
    }
}
