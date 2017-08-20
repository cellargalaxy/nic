package onDutyWeb;

import netview.Netview;
import nic.Nicer;
import onDuty.ClassScheduleFactory;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * Created by cellargalaxy on 17-8-19.
 */
public class UploadClassScheduleServlet extends HttpServlet {
	private static final Logger LOGGER = Logger.getLogger(UploadClassScheduleServlet.class.getName());
	private static final String JSP = "/jsp/uploadClassSchedule.jsp";
	private static final String MESSAGE_JSP = "/message.jsp";
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(JSP).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Nicer youNicer = (Nicer) req.getSession().getAttribute("youNicer");
		
		//获得磁盘文件条目工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//获取文件需要上传到的路径
		String path = getServletContext().getRealPath("/upload");
		//设置存储室
		factory.setRepository(new File(path));
		//设置 缓存的大小，当上传文件的容量超过该缓存时，直接放到 暂时存储室
		factory.setSizeThreshold(1024 * 1024);
		//文件上传处理
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		String year = null;
		String semester = null;
		File file = null;
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			//可以上传多个文件
			List<FileItem> list = upload.parseRequest(req);
			for (FileItem item : list) {
				if (item.isFormField()) {
					String name = item.getFieldName();
					String value = item.getString();
					if (name.equals("year")) {
						year = value;
					} else if (name.equals("semester")) {
						semester = value;
					}
				} else {
					file = new File(path + "/" + youNicer.getId());
					file.getParentFile().mkdirs();
					inputStream = item.getInputStream();
					outputStream = new BufferedOutputStream(new FileOutputStream(file));
					int len;
					byte[] bytes = new byte[1024];
					while ((len = inputStream.read(bytes)) != -1) {
						outputStream.write(bytes, 0, len);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		if (ClassScheduleFactory.createClassSchedule(file) != null) {
			File newFile = new File(path + "/classSchedule/" + year + semester + "/" + file.getName());
			newFile.getParentFile().mkdirs();
			file.renameTo(newFile);
			req.setAttribute("message", "课程表上传成功");
			req.setAttribute("url", "/nic");
			req.getRequestDispatcher(MESSAGE_JSP).forward(req, resp);
		} else {
			req.setAttribute("message", "课程表格式异常，请检查！");
			req.setAttribute("url", "/nic/onDuty/uploadClassSchedule");
			req.getRequestDispatcher(MESSAGE_JSP).forward(req, resp);
		}
		file.delete();
		
		LOGGER.info(youNicer.getId() + "上传" + year + "学年第" + semester + "学期的课程表");
	}
}
