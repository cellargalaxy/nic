package nic;

import java.io.*;
import java.util.Properties;

/**
 * Created by cellargalaxy on 2017/5/2.
 */
public class Configuration {
	private static String nicerTableName;
	private static String pictureRootPath;
	private static String pictureKind;
	
	static {
		try {
			Properties properties = new Properties();
			File confFile = new File(new File(netview.Configuration.class.getResource("").getPath()).getParentFile().getAbsolutePath() + "/netview.properties");
//			System.out.println(confFile.getAbsolutePath());
			properties.load(new BufferedReader(new InputStreamReader(new FileInputStream(confFile), "utf-8")));
			nicerTableName = properties.getProperty("nicerTableName");
			if (nicerTableName == null) {
				nicerTableName = "nicer";
			}
			pictureRootPath = properties.getProperty("pictureRootPath");
			if (pictureRootPath == null) {
				pictureRootPath = "D:/照片&视频";
			}
			pictureKind = properties.getProperty("pictureKind");
			if (pictureKind == null) {
				pictureKind = "jpg";
			}
		} catch (IOException e) {
			e.printStackTrace();
			nicerTableName = "nicer";
			pictureRootPath = "D:/照片&视频";
			pictureKind = "jpg";
		}
	}
	
	protected static String getNicerTableName() {
		return nicerTableName;
	}
	
	protected static void setNicerTableName(String nicerTableName) {
		Configuration.nicerTableName = nicerTableName;
	}
	
	protected static String getPictureKind() {
		return pictureKind;
	}
	
	protected static void setPictureKind(String pictureKind) {
		Configuration.pictureKind = pictureKind;
	}
	
	public static String getPictureRootPath() {
		return pictureRootPath;
	}
	
	public static void setPictureRootPath(String pictureRootPath) {
		Configuration.pictureRootPath = pictureRootPath;
	}
}
