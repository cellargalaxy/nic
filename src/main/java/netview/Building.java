package netview;


import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by cellargalaxy on 2017/4/26.
 */
public class Building {
	private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private String buildingName;
	private LinkedList<Map> maps;
	
	public Building(String buildingName) {
		this.buildingName = buildingName;
		maps = new LinkedList<Map>();
	}
	
	
	public void addHost(Host host) {
		Map map = new HashMap();
		map.put("name", host.getBuilding() + "-" + host.getName());
		map.put("address", host.getAddress());
		
		LinkedList<PingResult> results = host.GetResults();
		boolean[] conns = new boolean[results.size()];
		int j = 0;
		for (PingResult result : results) {
			conns[j] = result.isConn();
			j++;
		}
		map.put("conns", conns);
		map.put("conn", host.IsConn());
		map.put("date", SIMPLE_DATE_FORMAT.format(host.GetDate()));
		maps.add(map);
	}
	
	public String getBuildingName() {
		return buildingName;
	}
	
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	
	public LinkedList<Map> getMaps() {
		return maps;
	}
	
	public void setMaps(LinkedList<Map> maps) {
		this.maps = maps;
	}
}
