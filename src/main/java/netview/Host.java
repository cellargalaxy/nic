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
	
	private void check() {
		for (PingResult result : results) {
			if (result.getDelay() >= 0) {
				if (!conn) {
					conn = true;
					date = result.getDate();
				}
				return;
			}
		}
		if (conn) {
			conn = false;
			date = results.getFirst().getDate();
		}
	}
	
	protected void addResult(PingResult result) {
		results.poll();
		results.add(result);
		check();
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
	
	public int getTimes() {
		return times;
	}
	
	public void setTimes(int times) {
		this.times = times;
	}
	
	public LinkedList<PingResult> getResults() {
		return results;
	}
	
	public void setResults(LinkedList<PingResult> results) {
		this.results = results;
	}
	
	public boolean isConn() {
		return conn;
	}
	
	public void setConn(boolean conn) {
		this.conn = conn;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
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
