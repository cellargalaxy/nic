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
public class PictureWallServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        File picture = PicturePool.getPictureFile();
        if (picture == null) {
            picture = new File(getServletContext().getRealPath("/") + "/image/nic.jpg");
        }
        FileServletMethod.sendFile(resp, picture, false, null);
    }


}
