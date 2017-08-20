package onDuty;


/**
 * Created by cellargalaxy on 17-8-19.
 */
public class ClassSchedule {
	public static final int WEEK_COUNT = 25;
	public static final int DAY_COUNT = 7;
	public static final int CLASS_COUNT = 5;
	
	private final boolean[][][] classSchedule;
	
	ClassSchedule() {
		classSchedule = new boolean[WEEK_COUNT][CLASS_COUNT][DAY_COUNT];
	}
	
	public void setHasClass(int week, int day, int clazz) {
		classSchedule[week - 1][clazz - 1][day - 1] = true;
	}
	
	public boolean siHasClass(int week, int day, int clazz) {
		return classSchedule[week - 1][clazz - 1][day - 1];
	}
}
