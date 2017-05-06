package netviewWeb;

import me.biezhi.wechat.Application;
import netview.MyWechatServiceImpl;
import netview.Netview;
import netview.WecharThread;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by cellargalaxy on 2017/4/27.
 */
public class NetviewListener implements ServletContextListener {
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		Netview.getNetview().start();
		try {
			MyWechatServiceImpl wechatService = new MyWechatServiceImpl();
			Application.startWechar(wechatService);
			new Thread(new WecharThread(wechatService)).start();
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("微信机器人启动失败");
		}
	}
	
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		Netview.getNetview().stop();
	}
}
