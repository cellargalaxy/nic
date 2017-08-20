package onDutyWeb;

import nic.Nicer;
import onDuty.DutyScheduleFactory;
import org.apache.log4j.Logger;
import util.FileServletMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * Created by cellargalaxy on 17-8-20.
 */
public class CreateClassScheduleServlet extends HttpServlet {
	private static final Logger LOGGER = Logger.getLogger(CreateClassScheduleServlet.class.getName());
	private static final String JSP = "/jsp/createClassSchedule.jsp";
	private static final String MESSAGE_JSP = "/message.jsp";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String year = req.getParameter("year");
		String semester = req.getParameter("semester");
		if (year != null && semester != null) {
			String path = getServletContext().getRealPath("/upload");
			File file = new File(path + "/classSchedule/" + year + semester);
			if (file.exists()) {
				Nicer[] nicers = DutyScheduleFactory.createNicers(file.listFiles());
				req.setAttribute("nicers", nicers);
				req.setAttribute("year", year);
				req.setAttribute("semester", semester);
				req.getRequestDispatcher(JSP).forward(req, resp);
			} else {
				req.setAttribute("message", "未有人上传" + year + "学年第" + semester + "学期的课程表");
				req.setAttribute("url", "/nic/onDuty/createClassSchedule");
				req.getRequestDispatcher(MESSAGE_JSP).forward(req, resp);
			}
		} else {
			req.getRequestDispatcher(JSP).forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String year = req.getParameter("year");
		String semester = req.getParameter("semester");
		String startWeekString = req.getParameter("startWeek");
		String endWeekString = req.getParameter("endWeek");
		String nicerId = req.getParameter("nicerId");
		
		if (nicerId != null) {
			File file = new File(getServletContext().getRealPath("/upload") + "/" + year + semester + "/" + nicerId);
			FileServletMethod.sendFile(resp, file, true, file.getName() + ".xls");
			return;
		}
		
		File excel = null;
		try {
			int startWeek;
			int endWeek;
			if (year != null && semester != null && (startWeek = new Integer(startWeekString)) > 0 && (endWeek = new Integer(endWeekString)) > 0) {
				String path = getServletContext().getRealPath("/upload");
				File file = new File(path + "/classSchedule/" + year + semester);
				if (file.exists()) {
					excel = new File(path + "/classSchedule/" + year + semester + ":" + startWeek + "-" + endWeek + ".xls");
					if (DutyScheduleFactory.createDutyScheduleExcel(file.listFiles(), startWeek, endWeek, excel)) {
						FileServletMethod.sendFile(resp, excel, true, excel.getName());
					} else {
						req.setAttribute("message", "课余空闲表生成失败，请联系管理员");
						req.setAttribute("url", "/nic/onDuty/createClassSchedule");
						req.getRequestDispatcher(MESSAGE_JSP).forward(req, resp);
					}
				} else {
					req.setAttribute("message", "未有人上传" + year + "学年第" + semester + "学期的课程表");
					req.setAttribute("url", "/nic/onDuty/createClassSchedule");
					req.getRequestDispatcher(MESSAGE_JSP).forward(req, resp);
				}
			} else {
				req.setAttribute("message", "生成非法课程表: " + year + "学年 " + semester + "学期 第" + startWeekString + "-" + endWeekString + "周");
				req.setAttribute("url", "/nic/onDuty/createClassSchedule");
				req.getRequestDispatcher(MESSAGE_JSP).forward(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("message", "生成非法课程表: " + year + "学年 " + semester + "学期 第" + startWeekString + "-" + endWeekString + "周");
			req.setAttribute("url", "/nic/onDuty/createClassSchedule");
			req.getRequestDispatcher(MESSAGE_JSP).forward(req, resp);
		} finally {
			if (excel != null) {
				excel.delete();
			}
		}
		
		Nicer youNicer = (Nicer) req.getSession().getAttribute("youNicer");
		LOGGER.info(youNicer.getId() + "创建" + year + "学年第" + semester + "学期,第" + startWeekString + "-" + endWeekString + "周的课余空闲表");
	}
}
