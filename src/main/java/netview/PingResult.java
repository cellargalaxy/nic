package netview;

import java.util.Date;

/**
 * Created by cellargalaxy on 2017/4/20.
 */
public class PingResult {
	
	private Date date;
	private boolean isConn;
	
	
	public PingResult() {
		date = new Date();
		isConn = true;
	}
	
	public PingResult(boolean isConn) {
		this.isConn = isConn;
		date = new Date();
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public boolean isConn() {
		return isConn;
	}
	
	public void setConn(boolean conn) {
		isConn = conn;
	}
	
	@Override
	public String
	toString() {
		return "PingResult{" +
				"date=" + date +
				", isConn=" + isConn +
				'}';
	}
}
