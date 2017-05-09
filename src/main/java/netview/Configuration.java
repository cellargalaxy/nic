package netview;

import java.io.*;
import java.util.Properties;

/**
 * Created by cellargalaxy on 2017/4/21.
 */
public class Configuration {
	private static int times;
	private static int waitTime;
	private static int outTime;
	private static String ipTableName;
	private static int wechatWaitTime;
	private static String wechatInterClassName;
	
	static {
		try {
			Properties properties = new Properties();
			File confFile = new File(new File(Configuration.class.getResource("").getPath()).getParentFile().getAbsolutePath() + "/netview.properties");
//			System.out.println(confFile.getAbsolutePath());
			properties.load(new BufferedReader(new InputStreamReader(new FileInputStream(confFile), "utf-8")));
			ipTableName = properties.getProperty("ipTableName");
			
			wechatInterClassName = properties.getProperty("wechatInterClassName");
			if (wechatInterClassName == null) wechatInterClassName = "netview.Wechat";
			
			
			try {
				times = new Integer(properties.getProperty("times"));
			} catch (Exception e) {
				times = 5;
				e.printStackTrace();
			}
			try {
				waitTime = new Integer(properties.getProperty("waitTime"));
			} catch (Exception e) {
				waitTime = 1000;
				e.printStackTrace();
			}
			try {
				outTime = new Integer(properties.getProperty("outTime"));
			} catch (Exception e) {
				outTime = 1500;
				e.printStackTrace();
			}
			try {
				wechatWaitTime = new Integer(properties.getProperty("wechatWaitTime"));
			} catch (Exception e) {
				wechatWaitTime = 1000 * 60 * 15;
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
			times = 5;
			waitTime = 1000;
			outTime = 1500;
			wechatWaitTime = 1000 * 60 * 15;
			wechatInterClassName = "netview.Wechat";
		}
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
	
	public static int getOutTime() {
		return outTime;
	}
	
	public static void setOutTime(int outTime) {
		Configuration.outTime = outTime;
	}
	
	public static String getIpTableName() {
		return ipTableName;
	}
	
	public static void setIpTableName(String ipTableName) {
		Configuration.ipTableName = ipTableName;
	}
	
	
	public static int getWechatWaitTime() {
		return wechatWaitTime;
	}
	
	public static void setWechatWaitTime(int wechatWaitTime) {
		Configuration.wechatWaitTime = wechatWaitTime;
	}
	
	public static String getWechatInterClassName() {
		return wechatInterClassName;
	}
	
	public static void setWechatInterClassName(String wechatInterClassName) {
		Configuration.wechatInterClassName = wechatInterClassName;
	}
}
