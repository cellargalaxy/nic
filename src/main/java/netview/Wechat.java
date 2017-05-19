package netview;

import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by cellargalaxy on 2017/5/9.
 */
public class Wechat implements WecharInter {
	private static StringBuilder stringBuilder=new StringBuilder();
	
	public static void main(String[] args) {
		new Wechat().send("testt");
	}
	
	public boolean send(String string) {
		try {
			Socket socket=new Socket(Configuration.getWechatHost(),Configuration.getWechatPort());
			OutputStream outputStream=socket.getOutputStream();
			outputStream.write(string.getBytes(Configuration.getWechatCoding()));
			outputStream.flush();
			outputStream.close();
			socket.close();
			return true;
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}
}
