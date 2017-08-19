package netview;


import org.apache.log4j.Logger;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by cellargalaxy on 2017/4/22.
 */
public class Netview {
	private static final Logger LOGGER = Logger.getLogger(Netview.class.getName());
	private static final Netview NETVIEW = new Netview();
	private static final DateFormat DATE_FORMAT=new SimpleDateFormat("hh:mm:ss");
	
	private final LinkedList<Host> hosts;
	private final PingThread pingThread;
	private final StringBuffer changeStatusInfo;
	
	private Netview() {
		hosts = Sql.findAllHosts(Configuration.getPingTimes());
		sortHost();
		pingThread = new PingThread(this);
		changeStatusInfo = new StringBuffer();
		LOGGER.info("初始化监控主类");
	}
	
	private void sortHost() {
		Map<String, Host> map = new TreeMap<String, Host>();
		for (Host host : hosts) {
			map.put(host.getBuilding() + host.getFloor() + host.getAddress(), host);
		}
		hosts.clear();
		for (Map.Entry<String, Host> entry : map.entrySet()) {
			hosts.add(entry.getValue());
		}
	}
	
	public static Netview getNETVIEW() {
		return NETVIEW;
	}
	
	public void start() {
		new Thread(pingThread, "ping线程").start();
		LOGGER.info("启动ping线程");
	}
	
	public void stop() {
		pingThread.stop();
		LOGGER.info("结束ping线程");
	}
	
	public boolean addHost(String address, String building, String floor, String model, String name) {
		Host host = new Host(address, building, floor, model, name);
		return addHost(host);
	}
	
	private synchronized boolean addHost(Host host) {
		if (Sql.addAddress(host)) {
			hosts.add(host);
			LOGGER.info("添加一个主机; address:" + host.getAddress() + " 地址:" + host.getBuilding() + "-" + host.getFloor() + "-" + host.getModel() + "-" + host.getName());
			return true;
		} else {
			return false;
		}
	}
	
	public Building addHosts(File file) {
		LinkedList<Host> hosts = ExcelReadIp.readExcelIps(file);
		if (hosts == null) {
			return new Building("文件格式异常，添加失败！");
		} else {
			Building building = new Building("下列Host添加失败");
			synchronized (this) {
				for (Host host : hosts) {
					if (!addHost(host)) {
						building.addHost(host);
					}
				}
			}
			return building;
		}
	}
	
	
	public synchronized boolean deleteHost(String address) {
		if (Sql.delleteAddress(address)) {
			Iterator<Host> iterator = hosts.iterator();
			while (iterator.hasNext()) {
				if (iterator.next().getAddress().equals(address)) {
					iterator.remove();
					LOGGER.info("删除主机：" + address);
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
			if (!host.isConn()) {
				building.addHost(host);
			}
		}
		return building;
	}
	
	public synchronized Building createDemandKeyBuilding(String demandKey) {
		Building building = new Building("查询：" + demandKey);
		for (Host host : hosts) {
			if (host.getAddress().contains(demandKey) || host.getBuilding().contains(demandKey) ||
					host.getFloor().contains(demandKey) || host.getModel().contains(demandKey) ||
					demandKey.contains(host.getAddress()) || demandKey.contains(host.getBuilding()) ||
					demandKey.contains(host.getFloor()) || demandKey.contains(host.getModel())) {
				building.addHost(host);
			}
		}
		return building;
	}
	
	
	public synchronized LinkedList<Building> createAllBuilding() {
		LinkedList<Building> buildings = new LinkedList<Building>();
		main:
		for (Host host : hosts) {
			for (Building building : buildings) {
				if (building.getBuildingName().equals(host.getBuilding().toUpperCase())) {
					building.addHost(host);
					continue main;
				}
			}
			Building building = new Building(host.getBuilding().toUpperCase());
			building.addHost(host);
			buildings.add(building);
		}
		return buildings;
	}
	
	public synchronized Host[] createAddresses() {
		Host[] hs = new Host[hosts.size()];
		int i = 0;
		for (Host host : hosts) {
			hs[i] = host;
			i++;
		}
		return hs;
	}
	
	public void clearChangeStatusInfo(){
		changeStatusInfo.setLength(0);
	}
	
	protected void dealChangeStatus(Host host) {
		if (changeStatusInfo.length() > 1000) {
			clearChangeStatusInfo();
		}
		
		if (host.isConn()) {
			changeStatusInfo.append("通:" + DATE_FORMAT.format(host.getDate()) + host.getBuilding() + "-" + host.getFloor() + "-" + host.getModel());
		} else {
			changeStatusInfo.append("挂:" + DATE_FORMAT.format(host.getDate()) + host.getBuilding() + "-" + host.getFloor() + "-" + host.getModel());
		}
		if (host.getName() != null && host.getName().length() > 0) {
			changeStatusInfo.append("-" + host.getName());
		}
		changeStatusInfo.append("\r\n");
		LOGGER.info("是否连通：" + host.isConn() + " address:" + host.getAddress() + " 地址:" + host.getBuilding() + "-" + host.getFloor() + "-" + host.getModel() + "-" + host.getName());
	}
	
	public StringBuffer getChangeStatusInfo() {
		return changeStatusInfo;
	}
}
