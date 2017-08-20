package onDuty;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import nic.Nic;
import nic.Nicer;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by cellargalaxy on 17-8-20.
 */
public class DutyScheduleFactory {
	
	public static Nicer[] createNicers(File[] classScheduleFiles) {
		Nicer[] nicers = new Nicer[classScheduleFiles.length];
		for (int i = 0; i < classScheduleFiles.length; i++) {
			nicers[i] = Nic.getNic().findNicer(new Long(classScheduleFiles[i].getName()));
		}
		return nicers;
	}
	
	public static boolean createDutyScheduleExcel(File[] classScheduleFiles, int startWeek, int endWeek, File excelFile) {
		WritableWorkbook writableworkbook = null;
		try {
			DutySchedule dutySchedule = createDutySchedule(classScheduleFiles, startWeek, endWeek);
			excelFile.getParentFile().mkdirs();
			writableworkbook = Workbook.createWorkbook(excelFile);
			WritableSheet writablesheet = writableworkbook.createSheet("第" + startWeek + "-" + endWeek + "周课余空闲表", 0);
			
			writablesheet.addCell(new Label(0, 0, "第" + startWeek + "-" + endWeek + "周课余空闲表"));
			for (int day = 1; day <= ClassSchedule.DAY_COUNT; day++) {
				Label label = new Label(day, 0, "星期" + day);
				writablesheet.addCell(label);
			}
			for (int clazz = 1; clazz <= ClassSchedule.CLASS_COUNT; clazz++) {
				Label label = new Label(0, clazz, "第" + clazz + "班");
				writablesheet.addCell(label);
			}
			for (int day = 1; day <= ClassSchedule.DAY_COUNT; day++) {
				for (int clazz = 1; clazz <= ClassSchedule.CLASS_COUNT; clazz++) {
					LinkedList<Nicer> nicers = dutySchedule.getNicers(day, clazz);
					StringBuffer stringBuffer = new StringBuffer();
					for (Nicer nicer : nicers) {
						String name = nicer.getName();
						stringBuffer.append(name.substring(name.length() - 2, name.length()) + "，");
					}
					Label label = new Label(day, clazz, stringBuffer.toString());
					writablesheet.addCell(label);
				}
			}
			writableworkbook.write();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {
			if (writableworkbook != null) {
				try {
					writableworkbook.close();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (WriteException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static DutySchedule createDutySchedule(File[] classScheduleFiles, int startWeek, int endWeek) {
		Nicer[] nicers = new Nicer[classScheduleFiles.length];
		for (int i = 0; i < classScheduleFiles.length; i++) {
			nicers[i] = Nic.getNic().findNicer(new Long(classScheduleFiles[i].getName()));
			nicers[i].setClassSchedule(ClassScheduleFactory.createClassSchedule(classScheduleFiles[i]));
		}
		DutySchedule dutySchedule = new DutySchedule();
		for (int day = 1; day <= ClassSchedule.DAY_COUNT; day++) {
			for (int clazz = 1; clazz <= ClassSchedule.CLASS_COUNT; clazz++) {
				nicer:
				for (Nicer nicer : nicers) {
					for (int week = startWeek; week <= endWeek; week++) {
						if (nicer.getClassSchedule().siHasClass(week, day, clazz)) {
							continue nicer;
						}
					}
					dutySchedule.addNicer(day, clazz, nicer);
				}
			}
		}
		return dutySchedule;
	}
	
}
