package netview;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by cellargalaxy on 2017/4/20.
 */
public class PingResult {
	private static final Pattern lostPattern=Pattern.compile("\\(\\d+%");
	private static final Pattern delayPattern=Pattern.compile("\\d+ms$");
	
	private Date date;
	private int lost;
	private int delay;
	
	public PingResult() {
	}
	
	public PingResult(String pingResult) {
		lost=-1;
		delay=-1;
		date=new Date();
		if (pingResult!=null) {
			Matcher lostMatcher=lostPattern.matcher(pingResult);
			Matcher delayMatcher=delayPattern.matcher(pingResult);
			if (lostMatcher.find()) {
				String s=lostMatcher.group();
				lost=new Integer(s.substring(1,s.length()-1));
			}
			if (delayMatcher.find()) {
				String s=delayMatcher.group();
				delay=new Integer(s.substring(0,s.length()-2));
			}
		}
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public int getLost() {
		return lost;
	}
	
	public void setLost(int lost) {
		this.lost = lost;
	}
	
	public int getDelay() {
		return delay;
	}
	
	public void setDelay(int delay) {
		this.delay = delay;
	}
	
	@Override
	public String toString() {
		return "PingResult{" +
				"date=" + date +
				", lost=" + lost +
				", delay=" + delay +
				'}';
	}
}
