package netview;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by cellargalaxy on 2017/4/20.
 */
public class CMD {
	
	
	
	public static String ping(String address,String coding) {
		return cmd("ping "+address,coding);
	}
	private static String cmd(String cmd,String coding){
		Process process=null;
		BufferedReader reader=null;
		try {
			process= Runtime.getRuntime().exec(cmd);
			reader=new BufferedReader(new InputStreamReader(process.getInputStream(),coding));
			String string="";
			String s;
			while ((s = reader.readLine()) != null) string+=s+"\r\n";
			return string;
		}catch (IOException e){
			e.printStackTrace();
			return null;
		}finally {
			try { if (reader!=null) reader.close(); }catch (IOException e){ e.printStackTrace(); }
			if (process!=null) process.destroy();
		}
	}
}
