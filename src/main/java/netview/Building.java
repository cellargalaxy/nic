package netview;


import java.util.LinkedList;
import java.util.Map;

/**
 * Created by cellargalaxy on 2017/4/26.
 */
public class Building {
	private String buildingName;
	private LinkedList<Map> maps;
	
	public Building(String buildingName) {
		this.buildingName = buildingName;
		maps = new LinkedList<Map>();
	}
	
	public Building(String buildingName, LinkedList<Map> maps) {
		this.buildingName = buildingName;
		this.maps = maps;
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
