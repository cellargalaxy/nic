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

    static {
        try {
            Properties properties = new Properties();
            File confFile = new File(new File(Configuration.class.getResource("").getPath()).getParentFile().getAbsolutePath() + "/netview.properties");
            properties.load(new InputStreamReader(new FileInputStream(confFile)));

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
                pingBufferTime = 100;
                e.printStackTrace();
            }

            try {
                pingOutTime = new Integer(properties.getProperty("pingOutTime"));
            } catch (Exception e) {
                pingOutTime = 1000;
                e.printStackTrace();
            }

            try {
                pingWaitTime = new Integer(properties.getProperty("pingWaitTime"));
            } catch (Exception e) {
                pingWaitTime = 60000;
                e.printStackTrace();
            }

            pingParameter = properties.getProperty("pingParameter");
            if (pingParameter == null) {
                pingParameter = "-n 1 -w 1500";
            }

            pingRegularly = properties.getProperty("pingRegularly");
            if (pingRegularly == null) {
                pingRegularly = "\\d+ms$";
            }
        } catch (IOException e) {
            e.printStackTrace();
            pingTimes = 3;
            pingBufferTime = 1000;
            pingOutTime = 10000;
            pingWaitTime = 600000;
            pingParameter = "-n 1 -w 1500";
            pingRegularly = "\\d+ms$";
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
}
