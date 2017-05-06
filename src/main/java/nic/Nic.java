package nic;


import org.apache.commons.beanutils.BeanUtils;

import java.util.LinkedList;
import java.util.Map;

/**
 * Created by cellargalaxy on 2017/5/2.
 */
public class Nic {
	private static final Nic nic = new Nic();
	
	public boolean turnRightNicer(long id) {
		Nicer nicer = Sql.findNicer(id);
		if (nicer != null) {
			nicer.setStatus(Nicer.pass);
			return Sql.changeNicer(nicer);
		} else {
			return false;
		}
	}
	
	
	public boolean addNicer(Map map) {
		try {
			Nicer nicer = new Nicer();
			BeanUtils.populate(nicer, map);
			return Sql.addNicer(nicer);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean changeNicer(Map map) {
		try {
			Nicer nicer = new Nicer();
			BeanUtils.populate(nicer, map);
			return Sql.changeNicer(nicer);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean deleteNicer(long id) {
		return Sql.deleteNicer(id);
	}
	
	public Nicer findNicer(long id) {
		return Sql.findNicer(id);
	}
	
	public LinkedList<Nicer> findAllNicer() {
		return Sql.findAllNicer();
	}
	
	public LinkedList<Nicer> findAllPassNicer() {
		LinkedList<Nicer> passNicers = new LinkedList<Nicer>();
		LinkedList<Nicer> nicers = findAllNicer();
		for (Nicer nicer : nicers) {
			if (nicer.getStatus() == Nicer.pass) {
				passNicers.add(nicer);
			}
		}
		return passNicers;
	}
	
	public LinkedList<Nicer> findAllTempNicer() {
		LinkedList<Nicer> tempNicers = new LinkedList<Nicer>();
		LinkedList<Nicer> nicers = findAllNicer();
		for (Nicer nicer : nicers) {
			if (nicer.getStatus() == Nicer.temp) {
				tempNicers.add(nicer);
			}
		}
		return tempNicers;
	}
	
	
	public static Nic getNic() {
		return nic;
	}
}
