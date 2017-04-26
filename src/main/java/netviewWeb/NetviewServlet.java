package netviewWeb;

import netview.Building;
import netview.Netview;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by cellargalaxy on 2017/4/24.
 */
public class NetviewServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/plain;charset=utf-8");
		req.setCharacterEncoding("utf-8");
		
		Netview netview = Netview.getNetview();
		HttpSession session = req.getSession();
		String demandKsy = req.getParameter("demandKsy");
		if (demandKsy == null) {
			LinkedList<Building> buildings = netview.createHostMap();
			buildings.add(0, netview.createOutTimeHostMap());
			session.setAttribute("buildings", buildings);
		} else {
			Building[] buildings = {netview.createDemandKsyHostMap(demandKsy)};
			session.setAttribute("buildings", buildings);
		}
		req.getRequestDispatcher("netview/netview.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/plain;charset=utf-8");
		req.setCharacterEncoding("utf-8");
		
		//获得磁盘文件条目工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//获取文件需要上传到的路径
		String path = req.getRealPath("/upload");
		//设置存储室
		factory.setRepository(new File(path));
		//设置 缓存的大小，当上传文件的容量超过该缓存时，直接放到 暂时存储室
		factory.setSizeThreshold(1024 * 1024);
		//高水平的API文件上传处理
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		File ipFile = null;
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			//可以上传多个文件
			List<FileItem> list = upload.parseRequest(req);
			for (FileItem item : list) {
				if (!item.isFormField()) {
					//获取路径名
					String filename = item.getName();
					//索引到最后一个反斜杠
					int start = filename.lastIndexOf("\\");
					//截取 上传文件的 字符串名字，加1是 去掉反斜杠，
					filename = path + "/" + filename.substring(start + 1);
					ipFile = new File(filename);
					inputStream = item.getInputStream();
					outputStream = new BufferedOutputStream(new FileOutputStream(ipFile));
					int len = -1;
					byte[] bytes = new byte[1024];
					while ((len = inputStream.read(bytes)) != -1) {
						outputStream.write(bytes);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) try {
				inputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (outputStream != null) try {
				outputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		Netview netview = Netview.getNetview();
		HttpSession session = req.getSession();
		Building[] buildings = {netview.addHosts(ipFile)};
		session.setAttribute("buildings", buildings);
		req.getRequestDispatcher("netview/netview.jsp").forward(req, resp);
		
	}
	
	
}
