package netview;


import org.apache.commons.exec.*;
import org.apache.log4j.Logger;

import java.io.ByteArrayOutputStream;

/**
 * Created by cellargalaxy on 2017/4/20.
 */
public class PingThread implements Runnable {
	private static final Logger LOGGER=Logger.getLogger(PingThread.class.getName());
	private boolean run;
	private Netview netview;
	
	
	
	public PingThread(Netview netview) {
		this.netview = netview;
		run=true;
	}
	
	private void pingAllHosts() {
		while (run) {
			String[] addresses=netview.createAddresses();
			ByteArrayOutputStream[] byteArrayOutputStreams=pings(addresses);
			try {
				Thread.sleep(Configuration.getPingOutTime());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (int i = 0; i < byteArrayOutputStreams.length; i++) {
				netview.addPingResult(addresses[i],new PingResult(byteArrayOutputStreams[i].toString().trim()));
			}
			try {
				Thread.sleep(Configuration.getPingWaitTime());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			LOGGER.info("ping完一遍");
		}
	}
	
	private ByteArrayOutputStream[] pings(String[] addresses){
		ByteArrayOutputStream[] byteArrayOutputStreams=new ByteArrayOutputStream[addresses.length];
		for (int i = 0; i < byteArrayOutputStreams.length; i++) {
			try {
				byteArrayOutputStreams[i]=new ByteArrayOutputStream();
				CommandLine commandLine = CommandLine.parse("ping "+addresses[i]+" "+Configuration.getPingParameter());
				DefaultExecutor executor=new DaemonExecutor();
				executor.setStreamHandler(new PumpStreamHandler(byteArrayOutputStreams[i], byteArrayOutputStreams[i]));
				ExecuteWatchdog watchdog =new ExecuteWatchdog(Configuration.getPingOutTime());
				executor.setWatchdog(watchdog);
				executor.setExitValue(0);
				DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
				executor.execute(commandLine,resultHandler);
				Thread.sleep(Configuration.getPingBufferTime());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return byteArrayOutputStreams;
	}
	
	public void stop() {
		run = false;
	}
	
	public void run() {
		pingAllHosts();
	}
}
