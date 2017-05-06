package me.biezhi.wechat;

import com.blade.kit.base.Config;
import me.biezhi.wechat.service.WechatService;

public class Application {
	
	public static void startWechar(WechatService wechatService) throws InterruptedException {
		Constant.config = Config.load("classpath:config.properties");
		
		WechatRobot wechatRobot = new WechatRobot(wechatService);
		wechatRobot.showQrCode();
		while (!Constant.HTTP_OK.equals(wechatRobot.waitForLogin())) {
			Thread.sleep(2000);
		}
		wechatRobot.closeQrWindow();
		wechatRobot.start();
	}
	
}