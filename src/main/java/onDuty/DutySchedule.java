package onDuty;

import nic.Nicer;

import java.util.LinkedList;

/**
 * Created by cellargalaxy on 17-8-20.
 */
public class DutySchedule {
	private final LinkedList<Nicer>[][] dutySchedule;
	
	DutySchedule() {
		dutySchedule = new LinkedList[ClassSchedule.CLASS_COUNT][ClassSchedule.DAY_COUNT];
		for (int i = 0; i < dutySchedule.length; i++) {
			for (int j = 0; j < dutySchedule[i].length; j++) {
				dutySchedule[i][j] = new LinkedList<Nicer>();
			}
		}
	}
	
	public void addNicer(int day, int clazz, Nicer nicer) {
		dutySchedule[clazz - 1][day - 1].add(nicer);
	}
	
	public LinkedList<Nicer> getNicers(int day, int clazz) {
		return dutySchedule[clazz - 1][day - 1];
	}
}
