package onDuty;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by cellargalaxy on 17-8-19.
 */
public class ClassSchedule {
	private static final int WEEK_COUNT=25;
	private static final int DAY_COUNT=7;
	private static final int CLASS_COUNT=5;
	
	private static final String OLD_CLASS_SCHEDULE_MARK="时间";
	private static final String SEPARATOR1 =",";
	private static final String SEPARATOR2 ="-";
	private static final Pattern CLASS_PATTERN = Pattern.compile("\\d+");
	private static final Pattern NEW_WEEK_PATTERN = Pattern.compile("[\\d\\-,]+");
	private static final Pattern OLD_WEEK_PATTERN = Pattern.compile("\\{第[\\d\\-,]+周}");
	//                                   -1,1,2,3,4,5,6,7,8,9,10,11,12,13,14
	private static final int[] clazzs=  {-1,1,1,2,2,3,3,3,4,4,5 ,5 ,5 ,5 ,5};
	
	private final boolean[][][] classSchedule;
	
	public static ClassSchedule createClassSchedule(File ClassScheduleFile){
		Workbook workbook = null;
		try {
			workbook = Workbook.getWorkbook(ClassScheduleFile);
			Sheet sheet = workbook.getSheet(0);
			if (sheet.getRow(0)[0].getContents().trim().equals(OLD_CLASS_SCHEDULE_MARK)) {
				return excel2OldClassSchedule(sheet);
			}else {
				return excel2NewClassSchedule(sheet);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (workbook != null) {
				workbook.close();
			}
		}
	}
	
	private static ClassSchedule excel2OldClassSchedule(Sheet sheet){
		ClassSchedule classSchedule=new ClassSchedule();
		int clazz=-1;
		for (int i = 1; i < sheet.getRows(); i++) {
			Cell[] cells = sheet.getRow(i);
			Matcher classMatcher = CLASS_PATTERN.matcher(cells[1].getContents());
			if (classMatcher.find()) {
				clazz=clazzs[new Integer(classMatcher.group())];
			}
			for (int day = 2; day < cells.length; day++) {
				Matcher weekMatcher=OLD_WEEK_PATTERN.matcher(cells[day].getContents());
				if (weekMatcher.find()) {
					String weekString=weekMatcher.group();
					if (weekString.contains(SEPARATOR2)) {
						String[] strings=weekMatcher.group().replaceAll("\\{第","").replaceAll("周}","").split(SEPARATOR2);
						int startWeek=new Integer(strings[0]);
						int endWeek=new Integer(strings[1]);
						for (int week = startWeek; week <= endWeek; week++) {
							classSchedule.setHasClass(week,day-1,clazz);
						}
					}else {
						classSchedule.setHasClass(new Integer(weekString),day-1,clazz);
					}
				}
			}
		}
		return classSchedule;
	}
	
	private static ClassSchedule excel2NewClassSchedule(Sheet sheet){
		ClassSchedule classSchedule=new ClassSchedule();
		for (int i = 1; i < sheet.getRows(); i++) {
			Cell[] cells = sheet.getRow(i);
			Matcher classMatcher = CLASS_PATTERN.matcher(cells[0].getContents());
			if (classMatcher.find()) {
				int clazz=clazzs[new Integer(classMatcher.group())];
				for (int day = 1; day < cells.length; day++) {
					Matcher weekMatcher=NEW_WEEK_PATTERN.matcher(cells[day].getContents());
					if (weekMatcher.find()) {
						String[] weekStrings=weekMatcher.group().split(SEPARATOR1);
						for (String weekString : weekStrings) {
							if (weekString.contains(SEPARATOR2)) {
								String[] strings=weekString.split(SEPARATOR2);
								int startWeek=new Integer(strings[0]);
								int endWeek=new Integer(strings[1]);
								for (int week = startWeek; week <= endWeek; week++) {
									classSchedule.setHasClass(week,day,clazz);
								}
							}else {
								classSchedule.setHasClass(new Integer(weekString),day,clazz);
							}
						}
					}
				}
			}
		}
		return classSchedule;
	}
	
	private ClassSchedule() {
		classSchedule=new boolean[WEEK_COUNT][CLASS_COUNT][DAY_COUNT];
	}
	
	public void setHasClass(int week,int day,int clazz){
		classSchedule[week-1][clazz-1][day-1]=true;
	}
	
	public boolean siHasClass(int week,int day,int clazz){
		return classSchedule[week-1][clazz-1][day-1];
	}
}
