package netview;

import java.util.LinkedList;

/**
 * Created by cellargalaxy on 2017/4/20.
 */
public class PingThread implements Runnable {
	private boolean run;
	private Netview netview;
	private int waitTime;
	private String coding;
	
	
	public PingThread(Netview netview, int waitTime, String coding) {
		this.netview = netview;
		this.waitTime = waitTime;
		this.coding = coding;
		run = true;
	}
	
	private void pingAllHosts() throws InterruptedException {
		while (run) {
			String[] addresses = netview.createAddresses();
			for (String address : addresses) {
				System.out.println("ping:" + address);
				netview.addPingResult(address, new PingResult(CMD.ping(address, coding)));
				if (!run) return;
			}
			Thread.sleep(waitTime);
		}
	}
	
	public void stop() {
		run = false;
	}
	
	public void run() {
		try {
			pingAllHosts();
		} catch (InterruptedException e) {
			e.printStackTrace();
			stop();
		}
	}
}
