package wechatWeb;

import netview.Building;
import netview.Netview;
import wechat.JsonHost;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by cellargalaxy on 17-8-14.
 */
public class WXNetviewServlet extends HttpServlet {
    private String jsp = "/jsp/netview.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getParameter("token");
        if (token != null && token.equals(JsonHost.jsonHostToken())) {
            Netview netview = Netview.getNETVIEW();
            String demandKey = req.getParameter("demandKey");
            if (demandKey == null) {
                LinkedList<Building> buildings = netview.createAllBuilding();
                buildings.add(0, netview.createOutTimeBuilding());
                req.setAttribute("buildings", buildings);
            } else {
                Building[] buildings = {netview.createDemandKeyBuilding(demandKey)};
                req.setAttribute("buildings", buildings);
            }
            req.getRequestDispatcher(jsp).forward(req, resp);
        }
    }
}