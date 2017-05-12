package netview;


import org.apache.log4j.Logger;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by cellargalaxy on 2017/4/22.
 */
public class Netview {
	private static final Logger LOGGER=Logger.getLogger(Netview.class.getName());
	private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("MM-dd HH:mm:ss");
	private static final Netview netview = new Netview();
	
	private LinkedList<Host> hosts;
	private PingThread pingThread;
	private WecharThread wecharThread;
	
	private Netview() {
		try {
			hosts = Sql.findAllHosts(Configuration.getPingTimes());
			pingThread = new PingThread(this);
			wecharThread = new WecharThread((WecharInter) Class.forName(Configuration.getWechatInterClassName()).newInstance());
			LOGGER.info("初始化监控主类");
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Netview getNetview() {
		return netview;
	}
	
	public void start() {
		new Thread(pingThread, "ping线程").start();
		LOGGER.info("启动ping线程");
		new Thread(wecharThread,"微信发送信息线程").start();
		LOGGER.info("启动微信发送信息线程");
	}
	
	public synchronized void stop() {
		pingThread.stop();
		LOGGER.info("结束ping线程");
		wecharThread.stop();
		LOGGER.info("结束微信发送信息线程");
	}
	
	public void addHappen(Host host) {
		if (host.IsConn()) {
			LOGGER.info("恢复：" + host.getBuilding() + "-" + host.getName() + "：" + SIMPLE_DATE_FORMAT.format(host.GetDate()));
			wecharThread.addHappen("恢复：" + host.getBuilding() + "-" + host.getName() + "：" + SIMPLE_DATE_FORMAT.format(host.GetDate()));
		} else {
			LOGGER.info("断开：" + host.getBuilding() + "-" + host.getName() + "：" + SIMPLE_DATE_FORMAT.format(host.GetDate()));
			wecharThread.addHappen("断开：" + host.getBuilding() + "-" + host.getName() + "：" + SIMPLE_DATE_FORMAT.format(host.GetDate()));
		}
	}
	
	
	public synchronized boolean addHost(String building, int floor, String name, String address) {
		Host host = new Host(building, floor, name, address, Configuration.getPingTimes());
		return addHost(host);
	}
	
	private synchronized boolean addHost(Host host){
		if (Sql.addAddress(host)) {
			hosts.add(host);
			LOGGER.info("添加一个主机；building："+host.getBuilding()+"；name："+host.getName()+"；address："+host.getAddress());
			return true;
		} else {
			return false;
		}
	}
	
	public synchronized Building addHosts(File file) {
		LinkedList<Host> hosts = ExcelReadIp.readExcelIps(file);
		if (hosts == null) {
			return new Building("文件格式异常，添加失败！");
		} else {
			LinkedList<Host> failHosts = new LinkedList<Host>();
			for (Host host : hosts) {
				if (!addHost(host)) {
					failHosts.add(host);
				}
			}
			Building building = new Building("下列IP添加失败");
			for (Host failHost : failHosts) building.addHost(failHost);
			return building;
		}
	}
	
	
	public synchronized boolean deleteHost(String address) {
		if (Sql.delleteAddress(address)) {
			Iterator<Host> iterator = hosts.iterator();
			while (iterator.hasNext()) {
				if (iterator.next().getAddress().equals(address)) {
					iterator.remove();
					LOGGER.info("删除主机："+address);
					return true;
				}
			}
			return true;
		} else {
			return false;
		}
	}
	
	public synchronized Building createOutTimeBuilding() {
		Building building = new Building("超时");
		for (Host host : hosts) {
			if (!host.IsConn()) building.addHost(host);
		}
		return building;
	}
	
	public synchronized Building createDemandKeyBuilding(String demandKey) {
		Building building = new Building("查询：" + demandKey);
		if (demandKey != null) {
			for (Host host : hosts) {
				if (host.getBuilding().contains(demandKey) || host.getName().contains(demandKey) || host.getAddress().contains(demandKey)) {
					building.addHost(host);
				}
			}
		}
		return building;
	}
	
	
	public synchronized LinkedList<Building> createAllBuilding() {
		LinkedList<Building> buildings = new LinkedList<Building>();
		main:
		for (Host host : hosts) {
			for (Building building : buildings) {
				if (building.getBuildingName().equals(host.getBuilding())) {
					building.addHost(host);
					continue main;
				}
			}
			Building building = new Building(host.getBuilding());
			building.addHost(host);
			buildings.add(building);
		}
		return buildings;
	}
	
	protected synchronized String[] createAddresses() {
		String[] addresses = new String[hosts.size()];
		int i = 0;
		for (Host host : hosts) {
			addresses[i] = host.getAddress();
			i++;
		}
		return addresses;
	}
	
	protected synchronized void addPingResult(String address, PingResult result) {
		for (Host host : hosts) {
			if (host.getAddress().equals(address)) {
				host.addResult(result);
				LOGGER.debug("地址："+host.getBuilding()+"-"+host.getName()+"；IP："+host.getAddress()+"；添加一个结果；是否连通："+result.isConn()+"延时："+result.getDelay());
			}
		}
	}
	
	
}
