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
	private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final Netview netview = new Netview();
	
	private LinkedList<Host> hosts;
	private PingThread pingThread;
	
	public Netview() {
		hosts = Sql.findAllHosts(Configuration.getTimes());
		pingThread = new PingThread(this, Configuration.getWaitTime(), Configuration.getConding());
	}
	public void start(){
		new Thread(pingThread, "ping线程").start();
	}
	public synchronized boolean addHost(String building, int floor, String name, String address) {
		Host host = new Host(building, floor, name, address, Configuration.getTimes());
		int i = Sql.addAddress(host);
		if (i > -1) {
			hosts.add(host);
			return true;
		} else {
			return false;
		}
	}
	
	public synchronized Building addHosts(File file) {
		LinkedList<Host> hosts = ExcelReadIp.readExcelIps(file);
		if (hosts == null) {
			LinkedList<Map> failMap = new LinkedList<Map>();
			return new Building("文件格式异常，添加失败！", failMap);
		} else {
			LinkedList<Host> failHosts = new LinkedList<Host>();
			for (Host host : hosts) {
				if (Sql.addAddress(host) < 1) {
					failHosts.add(host);
				} else {
					this.hosts.add(host);
				}
			}
			LinkedList<Map> failMap = new LinkedList<Map>();
			for (Host failHost : failHosts) failMap.add(hostToMap(failHost));
			return new Building("下列ip添加失败：", failMap);
		}
	}

	
	public synchronized boolean deleteHost(String address) {
		int i = Sql.delleteAddress(address);
		if (i > -1) {
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
	
	public synchronized Building createOutTimeHostMap() {
		LinkedList<Map> maps = new LinkedList<Map>();
		for (Host host : hosts) {
			if (!host.isConn()) maps.add(hostToMap(host));
		}
		return new Building("超时", maps);
	}
	
	public synchronized Building createDemandKsyHostMap(String demandKsy) {
		LinkedList<Map> maps = new LinkedList<Map>();
		if (demandKsy != null) {
			for (Host host : hosts) {
				if (host.getBuilding().contains(demandKsy) || host.getName().contains(demandKsy) || host.getAddress().contains(demandKsy)) {
					maps.add(hostToMap(host));
				}
			}
		}
		return new Building("查询：" + demandKsy, maps);
	}
	
	
	public synchronized LinkedList<Building> createHostMap() {
		LinkedList<Building> buildings = new LinkedList<Building>();
		main:
		for (Host host : hosts) {
			for (Building building : buildings) {
				if (building.getBuildingName().equals(host.getBuilding())) {
					building.getMaps().add(hostToMap(host));
					continue main;
				}
			}
			Building building = new Building(host.getBuilding());
			building.getMaps().add(hostToMap(host));
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
	
	public synchronized void stop() {
		pingThread.stop();
	}
	
	private Map hostToMap(Host host) {
		Map map = new HashMap();
		map.put("name", host.getBuilding() + "-" + host.getName());
		map.put("address", host.getAddress());
		
		LinkedList<PingResult> results = host.getResults();
		int[] delay = new int[results.size()];
		int j = 0;
		for (PingResult result : results) {
			delay[j] = result.getDelay();
			j++;
		}
		map.put("delay", delay);
		map.put("conn", host.isConn());
		map.put("date", SIMPLE_DATE_FORMAT.format(host.getDate()));
		return map;
	}
	
	public PingThread getPingThread() {
		return pingThread;
	}
	
	public void setPingThread(PingThread pingThread) {
		this.pingThread = pingThread;
	}
	
	
	public static Netview getNetview() {
		return netview;
	}
}
