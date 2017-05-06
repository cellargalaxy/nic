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
	private static String xiaobingName;
	private static String nicName;
	private static String jjrName;
	private static int wechatWaitTime;
	
	static {
		try {
			Properties properties = new Properties();
			File confFile = new File(new File(Configuration.class.getResource("").getPath()).getParentFile().getAbsolutePath() + "/netview.properties");
//			System.out.println(confFile.getAbsolutePath());
			properties.load(new BufferedReader(new InputStreamReader(new FileInputStream(confFile), "utf-8")));
			ipTableName = properties.getProperty("ipTableName");
			
			xiaobingName = properties.getProperty("xiaobingName");
			if (xiaobingName == null) xiaobingName = "小冰";
			
			nicName = properties.getProperty("nicName");
			if (nicName == null) nicName = "网络";
			
			jjrName = properties.getProperty("jjrName");
			if (jjrName == null) jjrName = "jjr";
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
			xiaobingName = "小冰";
			nicName = "网络";
			jjrName = "jjr";
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
	
	public static String getXiaobingName() {
		return xiaobingName;
	}
	
	public static void setXiaobingName(String xiaobingName) {
		Configuration.xiaobingName = xiaobingName;
	}
	
	public static String getNicName() {
		return nicName;
	}
	
	public static void setNicName(String nicName) {
		Configuration.nicName = nicName;
	}
	
	public static String getJjrName() {
		return jjrName;
	}
	
	public static void setJjrName(String jjrName) {
		Configuration.jjrName = jjrName;
	}
	
	public static int getWechatWaitTime() {
		return wechatWaitTime;
	}
	
	public static void setWechatWaitTime(int wechatWaitTime) {
		Configuration.wechatWaitTime = wechatWaitTime;
	}
}
