package netview;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.LinkedList;

/**
 * Created by cellargalaxy on 2017/4/20.
 */
public class PingThread implements Runnable {
	private boolean run;
	private Netview netview;
	private int waitTime;
	private int outTime;
	
	
	public PingThread(Netview netview, int waitTime, int outTime) {
		this.netview = netview;
		this.waitTime = waitTime;
		this.outTime = outTime;
		run = true;
	}
	
	private void pingAllHosts() {
		while (run) {
			String[] addresses = netview.createAddresses();
			for (String address : addresses) {
				try {
					netview.addPingResult(address, new PingResult(InetAddress.getByName(address).isReachable(outTime)));
					if (!run) return;
					Thread.sleep(waitTime);
					if (!run) return;
				} catch (Exception e) {
					e.printStackTrace();
					netview.addPingResult(address, new PingResult(false));
				}
			}
			System.out.println("ping完一遍");
		}
	}
	
	public void stop() {
		run = false;
	}
	
	public void run() {
		pingAllHosts();
	}
}
