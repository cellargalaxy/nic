package netviewWeb;

import netview.Netview;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by cellargalaxy on 2017/4/27.
 */
public class NetviewListener implements ServletContextListener {
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		Netview.getNetview().start();
	}
	
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		Netview.getNetview().stop();
	}
}
