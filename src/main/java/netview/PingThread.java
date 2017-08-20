package netview;


import org.apache.commons.exec.*;
import org.apache.log4j.Logger;

import java.io.ByteArrayOutputStream;

/**
 * Created by cellargalaxy on 2017/4/20.
 */
public class PingThread implements Runnable {
	private static final Logger LOGGER = Logger.getLogger(PingThread.class.getName());
	private volatile boolean runAble;
	private final Netview netview;
	
	
	public PingThread(Netview netview) {
		this.netview = netview;
		runAble = true;
	}
	
	private void pingAllHosts() {
		while (runAble) {
			Host[] hosts = netview.createAddresses();
			ByteArrayOutputStream[] byteArrayOutputStreams = pings(hosts);
			try {
				Thread.sleep(Configuration.getPingOutTime());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (int i = 0; i < byteArrayOutputStreams.length; i++) {
				if (hosts[i].addResult(new PingResult(byteArrayOutputStreams[i].toString().trim()))) {
					netview.dealChangeStatus(hosts[i]);
				}
			}
			try {
				Thread.sleep(Configuration.getPingWaitTime());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			LOGGER.debug("ping完一遍");
		}
	}
	
	private ByteArrayOutputStream[] pings(Host[] hosts) {
		ByteArrayOutputStream[] byteArrayOutputStreams = new ByteArrayOutputStream[hosts.length];
		for (int i = 0; i < byteArrayOutputStreams.length; i++) {
			try {
				byteArrayOutputStreams[i] = new ByteArrayOutputStream();
				CommandLine commandLine = CommandLine.parse("ping " + hosts[i].getAddress() + " " + Configuration.getPingParameter());
				DefaultExecutor executor = new DaemonExecutor();
				executor.setStreamHandler(new PumpStreamHandler(byteArrayOutputStreams[i], byteArrayOutputStreams[i]));
				ExecuteWatchdog watchdog = new ExecuteWatchdog(Configuration.getPingOutTime());
				executor.setWatchdog(watchdog);
				executor.setExitValue(0);
				DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
				executor.execute(commandLine, resultHandler);
				Thread.sleep(Configuration.getPingBufferTime());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return byteArrayOutputStreams;
	}
	
	public void stop() {
		runAble = false;
	}
	
	public void run() {
		pingAllHosts();
	}
}