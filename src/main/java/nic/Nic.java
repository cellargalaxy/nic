package nic;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import util.MD5;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by cellargalaxy on 2017/5/2.
 */
public class Nic {
	private static final Logger LOGGER = Logger.getLogger(Nic.class.getName());
	private static final Nic nic = new Nic();
	private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	private Nic() {
	}
	
	public boolean turnRightNicer(long id) {
		Nicer nicer = Sql.findNicer(id);
		if (nicer != null) {
			nicer.setStatus(nicer.getNicerStatus());
			boolean b = Sql.changeNicer(nicer);
			if (b) {
				LOGGER.info("审核通过用户：" + id);
			}
			return b;
		} else {
			return false;
		}
	}
	
	public boolean turnAdminNicer(long id) {
		Nicer nicer = Sql.findNicer(id);
		if (nicer != null) {
			nicer.setStatus(nicer.getAdminStatus());
			boolean b = Sql.changeNicer(nicer);
			if (b) {
				LOGGER.info("提权管理员用户：" + id);
			}
			return b;
		} else {
			return false;
		}
	}
	
	
	public boolean addNicer(Map map) {
		try {
			map.put("password", MD5.encryption(map.get("password").toString()));
			boolean b = Sql.addNicer(mapToNicer(map));
			if (b) {
				LOGGER.info("注册：" + map.get("id"));
			}
			return b;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean changeNicer(Map map) {
		try {
			map.put("password", MD5.encryption(map.get("password").toString()));
			boolean b = Sql.changeNicer(mapToNicer(map));
			if (b) {
				LOGGER.info("修改用户：" + map.get("id"));
			}
			return b;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private static Nicer mapToNicer(Map map) throws InvocationTargetException, IllegalAccessException, ParseException {
		Nicer nicer = new Nicer();
		String birthday = map.get("birthday").toString();
		map.remove("birthday");
		BeanUtils.populate(nicer, map);
		nicer.setBirthday(SIMPLE_DATE_FORMAT.parse(birthday));
		return nicer;
	}
	
	public boolean deleteNicer(long id) {
		boolean b = Sql.deleteNicer(id);
		if (b) {
			LOGGER.info("删除用户：" + id);
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
			if (nicer.getStatus() == nicer.getNicerStatus() || nicer.getStatus() == nicer.getAdminStatus()) {
				passNicers.add(nicer);
			}
		}
		return passNicers;
	}
	
	public LinkedList<Nicer> findAllTempNicer() {
		LinkedList<Nicer> tempNicers = new LinkedList<Nicer>();
		LinkedList<Nicer> nicers = findAllNicer();
		for (Nicer nicer : nicers) {
			if (nicer.getStatus() == nicer.getTempStatus()) {
				tempNicers.add(nicer);
			}
		}
		return tempNicers;
	}
	
	
	public static Nic getNic() {
		return nic;
	}
}
