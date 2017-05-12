package netview;

/**
 * Created by cellargalaxy on 2017/5/6.
 */
public class WecharThread implements Runnable {
	private boolean run;
	private WecharInter wecharInter;
	private int wechatWaitTime = Configuration.getWechatWaitTime();
	private String happens;
	
	
	public WecharThread(WecharInter wecharInter) {
		this.wecharInter = wecharInter;
		run = true;
	}
	
	public void addHappen(String string) {
		if (happens == null) {
			happens = null;
		} else {
			happens += "\r\n" + string;
		}
	}
	
	public void stop() {
		run = false;
	}
	
	
	public void run() {
		while (run) {
			try {
				if (happens != null && wecharInter.send(happens)) {
					happens = null;
				}
				Thread.sleep(wechatWaitTime);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
