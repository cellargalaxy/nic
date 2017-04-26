package netview;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by cellargalaxy on 2017/4/21.
 */
public class Configuration {
	private static String conding;
	private static int times;
	private static int waitTime;
	
	static {
		try {
			Properties properties = new Properties();
			File confFile = new File(new File(Configuration.class.getResource("").getPath()).getParentFile().getAbsolutePath() + "/netview.conf");
			System.out.println(confFile.getAbsolutePath());
			properties.load(new FileInputStream(confFile));
			conding = properties.getProperty("conding");
			if (conding == null) conding = "utf-8";
			try {
				times = new Integer(properties.getProperty("times"));
			} catch (Exception e) {
				times = 5;
				e.printStackTrace();
			}
			try {
				waitTime = new Integer(properties.getProperty("waitTime"));
			} catch (Exception e) {
				waitTime = 1000 * 3;
				e.printStackTrace();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			conding = "utf-8";
			times = 5;
			waitTime = 1;
		}
	}
	
	public static String getConding() {
		return conding;
	}
	
	public static void setConding(String conding) {
		Configuration.conding = conding;
	}
	
	public static int getTimes() {
		return times;
	}
	
	public static void setTimes(int times) {
		Configuration.times = times;
	}
	
	public static int getWaitTime() {
		return waitTime;
	}
	
	public static void setWaitTime(int waitTime) {
		Configuration.waitTime = waitTime;
	}
}
