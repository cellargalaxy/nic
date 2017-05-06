package netview;

import java.util.Date;
import java.util.LinkedList;

/**
 * Created by cellargalaxy on 2017/4/20.
 */
public class Host {
	private String building;
	private int floor;
	private String name;
	private String address;
	private int times;
	private LinkedList<PingResult> results;
	private boolean conn;
	private Date date;
	
	
	public Host(String building, int floor, String name, String address, int times) {
		this.building = building;
		this.floor = floor;
		this.name = name;
		this.address = address;
		this.times = times;
		results = new LinkedList<PingResult>();
		for (int i = 0; i < times; i++) results.add(new PingResult());
		conn = true;
		date = new Date();
	}
	
	public Host(int times) {
		this.times = times;
		results = new LinkedList<PingResult>();
		for (int i = 0; i < times; i++) results.add(new PingResult());
		conn = true;
		date = new Date();
	}
	
	/**
	 * @return true表示连通状态改变了，否则没改变
	 */
	private boolean check() {
		if (conn) {
			for (PingResult result : results) {
				if (result.isConn()) {
					return false;
				}
			}
			conn = false;
			date = results.getFirst().getDate();
			return true;
		} else {
			for (PingResult result : results) {
				if (result.isConn()) {
					conn = true;
					date = result.getDate();
					return true;
				}
			}
			return false;
		}
	}
	
	protected void addResult(PingResult result) {
		results.poll();
		results.add(result);
		if (check()) {
			Netview.getNetview().addHappen(this);
		}
	}
	
	public String getBuilding() {
		return building;
	}
	
	public void setBuilding(String building) {
		this.building = building;
	}
	
	public int getFloor() {
		return floor;
	}
	
	public void setFloor(int floor) {
		this.floor = floor;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public LinkedList<PingResult> GetResults() {
		return results;
	}
	
	public void SetResults(LinkedList<PingResult> results) {
		this.results = results;
	}
	
	public boolean IsConn() {
		return conn;
	}
	
	public void SetConn(boolean conn) {
		this.conn = conn;
	}
	
	public Date GetDate() {
		return date;
	}
	
	public void SetDate(Date date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return "Host{" +
				"Building='" + building + '\'' +
				", floor=" + floor +
				", name='" + name + '\'' +
				", address='" + address + '\'' +
				", times=" + times +
				", results=" + results +
				", conn=" + conn +
				", date=" + date +
				'}';
	}
}
