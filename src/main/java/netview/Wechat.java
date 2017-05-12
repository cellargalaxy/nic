package netview;

/**
 * Created by cellargalaxy on 2017/5/9.
 */
public class Wechat implements WecharInter {
	
	
	public boolean send(String string) {
		System.out.println("需要微信发送，但没实现接口：" + string);
		return true;
	}
}
