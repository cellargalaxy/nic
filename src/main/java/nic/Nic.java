package nic;


import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.Map;

/**
 * Created by cellargalaxy on 2017/5/2.
 */
public class Nic {
	private static final Logger LOGGER=Logger.getLogger(Nic.class.getName());
	private static final Nic nic = new Nic();
	
	private Nic() {
	}
	
	public boolean turnRightNicer(long id) {
		Nicer nicer = Sql.findNicer(id);
		if (nicer != null) {
			nicer.setStatus(Nicer.pass);
			boolean b=Sql.changeNicer(nicer);
			if (b) {
				LOGGER.info("审核通过用户："+id);
			}
			return b;
		} else {
			return false;
		}
	}
	
	
	public boolean addNicer(Map map) {
		try {
			Nicer nicer = new Nicer();
			BeanUtils.populate(nicer, map);
			boolean b=Sql.addNicer(nicer);
			if (b) {
				LOGGER.info("注册："+map.get("id"));
			}
			return b;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean changeNicer(Map map) {
		try {
			Nicer nicer = new Nicer();
			BeanUtils.populate(nicer, map);
			boolean b=Sql.changeNicer(nicer);
			if (b) {
				LOGGER.info("修改用户："+nicer.getId());
			}
			return b;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean deleteNicer(long id) {
		boolean b=Sql.deleteNicer(id);
		if (b) {
			LOGGER.info("删除用户："+id);
		}
		return b;
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
