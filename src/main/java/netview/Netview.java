package netview;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by cellargalaxy on 2017/4/22.
 */
public class Netview {
	private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("MM-dd HH:mm:ss");
	private static final Netview netview = new Netview();
	
	private String happens;
	
	private LinkedList<Host> hosts;
	private PingThread pingThread;
	
	private Netview() {
		hosts = Sql.findAllHosts(Configuration.getTimes());
		pingThread = new PingThread(this, Configuration.getWaitTime(), Configuration.getOutTime());
	}
	
	public static Netview getNetview() {
		return netview;
	}
	
	public void start() {
		new Thread(pingThread, "ping线程").start();
	}
	
	public synchronized void stop() {
		pingThread.stop();
	}
	
	public void addHappen(Host host) {
		if (happens == null) {
			if (host.IsConn()) {
				happens = "恢复：" + host.getBuilding() + "-" + host.getName() + "：" + SIMPLE_DATE_FORMAT.format(host.GetDate());
			} else {
				happens = "断开：" + host.getBuilding() + "-" + host.getName() + "：" + SIMPLE_DATE_FORMAT.format(host.GetDate());
			}
		} else {
			if (host.IsConn()) {
				happens += "\r\n恢复：" + host.getBuilding() + "-" + host.getName() + "：" + SIMPLE_DATE_FORMAT.format(host.GetDate());
			} else {
				happens += "\r\n断开：" + host.getBuilding() + "-" + host.getName() + "：" + SIMPLE_DATE_FORMAT.format(host.GetDate());
			}
		}
	}
	
	public String getHappens() {
		String string = happens;
		happens = null;
		return string;
	}
	
	public synchronized boolean addHost(String building, int floor, String name, String address) {
		Host host = new Host(building, floor, name, address, Configuration.getTimes());
		if (Sql.addAddress(host)) {
			hosts.add(host);
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
				if (Sql.addAddress(host)) {
					this.hosts.add(host);
				} else {
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
			}
		}
	}
	
	
}
