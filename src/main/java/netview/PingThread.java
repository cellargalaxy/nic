package netview;

import java.util.LinkedList;

/**
 * Created by cellargalaxy on 2017/4/20.
 */
public class PingThread implements Runnable{
	private boolean run;
	private LinkedList<Host> hosts;
	private int waitTime;
	
	public static void main(String[] args) {
		LinkedList<Host> hosts=new LinkedList<Host>();
		hosts.add(new Host("gbk","202","202.116.150.40",3));
		hosts.add(new Host("gbk","222","10.31.108.222",3));
		hosts.add(new Host("gbk","DNS","114.114.114.114",3));
		hosts.add(new Host("gbk","百度","baidu.com",3));
		PingThread pingThread=new PingThread(hosts,1000);
		new Thread(pingThread,"ping线程").start();
	}
	
	public PingThread(LinkedList<Host> hosts, int waitTime) {
		this.hosts = hosts;
		this.waitTime = waitTime;
		run=true;
	}
	
	private void pingAllHosts(){
		while (run) {
			for (Host host : hosts) {
				host.ping();
				if (!run) break;
			}
		}
	}
	
	public void stop(){
		run=false;
	}
	
	public void run() {
		pingAllHosts();
	}
}
