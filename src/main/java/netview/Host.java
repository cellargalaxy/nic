package netview;

import java.util.LinkedList;

/**
 * Created by cellargalaxy on 2017/4/20.
 */
public class Host {
	private String coding;
	private String name;
	private String address;
	private int times;
	private LinkedList<PingResult> results;
	private boolean conn;
	
	public Host(String coding, String name, String address, int times) {
		this.coding = coding;
		this.name = name;
		this.address = address;
		this.times = times;
		results = new LinkedList<PingResult>();
		for (int i = 0; i < times; i++) results.add(new PingResult());
		conn = true;
	}
	
	public void ping() {
		addResult(new PingResult(CMD.ping(address, coding)));
	}
	
	private void addResult(PingResult result) {
		results.poll();
		results.add(result);
		System.out.println("ping:" + name + "," + address + ";result:" + result);
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
	
	@Override
	public String toString() {
		return "Host{" +
				"coding='" + coding + '\'' +
				", name='" + name + '\'' +
				", address='" + address + '\'' +
				", times=" + times +
				", results=" + results +
				", conn=" + conn +
				'}';
	}
}
