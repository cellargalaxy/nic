package netview;

/**
 * Created by cellargalaxy on 2017/5/6.
 */
public class WecharThread implements Runnable {
	private boolean run;
	private MyWechatServiceImpl myWechatService;
	private int wechatWaitTime = Configuration.getWechatWaitTime();
	
	
	public WecharThread(MyWechatServiceImpl myWechatService) {
		this.myWechatService = myWechatService;
		run = true;
	}
	
	public void stop() {
		run = false;
	}
	
	@Override
	public void run() {
		while (run) {
			try {
				String string = Netview.getNetview().getHappens();
				if (string != null) {
					myWechatService.sendHappens(string);
				}
				Thread.sleep(wechatWaitTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
