package netview;

import java.io.*;
import java.util.Properties;

/**
 * Created by cellargalaxy on 2017/4/21.
 */
public class Configuration {
	private static String ipTableName;
	private static int pingTimes;
	private static int pingBufferTime;
	private static int pingOutTime;
	private static int pingWaitTime;
	private static String pingParameter;
	private static String pingRegularly;
	private static int wechatWaitTime;
	private static String wechatInterClassName;
	private static String wechatHost;
	private static int wechatPort;
	private static String wechatCoding;
	
	static {
		try {
			Properties properties = new Properties();
			File confFile = new File(new File(Configuration.class.getResource("").getPath()).getParentFile().getAbsolutePath() + "/netview.properties");
			properties.load(new BufferedReader(new InputStreamReader(new FileInputStream(confFile), "utf-8")));
			
			ipTableName = properties.getProperty("ipTableName");
			
			try {
				pingTimes = new Integer(properties.getProperty("pingTimes"));
			} catch (Exception e) {
				pingTimes = 3;
				e.printStackTrace();
			}
			
			try {
				pingBufferTime = new Integer(properties.getProperty("pingBufferTime"));
			} catch (Exception e) {
				pingBufferTime = 1000;
				e.printStackTrace();
			}
			
			try {
				pingOutTime = new Integer(properties.getProperty("pingOutTime"));
			} catch (Exception e) {
				pingOutTime = 10000;
				e.printStackTrace();
			}
			
			try {
				pingWaitTime = new Integer(properties.getProperty("pingWaitTime"));
			} catch (Exception e) {
				pingWaitTime = 600000;
				e.printStackTrace();
			}
			
			pingParameter = properties.getProperty("pingParameter");
			if(pingParameter==null) pingParameter="";
			
			pingRegularly = properties.getProperty("pingRegularly");
			
			try {
				wechatWaitTime = new Integer(properties.getProperty("wechatWaitTime"));
			} catch (Exception e) {
				wechatWaitTime = 900000;
				e.printStackTrace();
			}
			
			wechatInterClassName = properties.getProperty("wechatInterClassName");
			if (wechatInterClassName == null) wechatInterClassName = "netview.Wechat";
			
			wechatHost = properties.getProperty("wechatHost");
			if (wechatHost == null) wechatHost = "127.0.0.1";
			
			try {
				wechatPort = new Integer(properties.getProperty("wechatPort"));
			} catch (Exception e) {
				wechatPort = 12345;
				e.printStackTrace();
			}
			
			wechatCoding = properties.getProperty("wechatCoding");
			if (wechatCoding == null) wechatCoding = "utf-8";
			
		} catch (IOException e) {
			e.printStackTrace();
			pingTimes=3;
			pingBufferTime=1000;
			pingOutTime=10000;
			pingWaitTime=600000;
			pingParameter="";
			wechatWaitTime=900000;
			wechatInterClassName="netview.Wechat";
			wechatInterClassName = "127.0.0.1";
			wechatPort = 12345;
			wechatCoding = "utf-8";
		}
	}
	
	
	public static String getIpTableName() {
		return ipTableName;
	}
	
	public static void setIpTableName(String ipTableName) {
		Configuration.ipTableName = ipTableName;
	}
	
	public static int getPingTimes() {
		return pingTimes;
	}
	
	public static void setPingTimes(int pingTimes) {
		Configuration.pingTimes = pingTimes;
	}
	
	public static int getPingBufferTime() {
		return pingBufferTime;
	}
	
	public static void setPingBufferTime(int pingBufferTime) {
		Configuration.pingBufferTime = pingBufferTime;
	}
	
	public static int getPingOutTime() {
		return pingOutTime;
	}
	
	public static void setPingOutTime(int pingOutTime) {
		Configuration.pingOutTime = pingOutTime;
	}
	
	public static int getPingWaitTime() {
		return pingWaitTime;
	}
	
	public static void setPingWaitTime(int pingWaitTime) {
		Configuration.pingWaitTime = pingWaitTime;
	}
	
	public static String getPingParameter() {
		return pingParameter;
	}
	
	public static void setPingParameter(String pingParameter) {
		Configuration.pingParameter = pingParameter;
	}
	
	public static String getPingRegularly() {
		return pingRegularly;
	}
	
	public static void setPingRegularly(String pingRegularly) {
		Configuration.pingRegularly = pingRegularly;
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
	
	public static String getWechatHost() {
		return wechatHost;
	}
	
	public static void setWechatHost(String wechatHost) {
		Configuration.wechatHost = wechatHost;
	}
	
	public static int getWechatPort() {
		return wechatPort;
	}
	
	public static void setWechatPort(int wechatPort) {
		Configuration.wechatPort = wechatPort;
	}
	
	public static String getWechatCoding() {
		return wechatCoding;
	}
	
	public static void setWechatCoding(String wechatCoding) {
		Configuration.wechatCoding = wechatCoding;
	}
}
