package netview;

import com.blade.kit.json.JSONObject;
import me.biezhi.wechat.Application;
import me.biezhi.wechat.model.WechatMeta;
import me.biezhi.wechat.service.WechatServiceImpl;

/**
 * Created by cellargalaxy on 2017/4/30.
 */
public class MyWechatServiceImpl extends WechatServiceImpl {
	private WechatMeta wechatMeta;
	private String xiaobingId;
	private String nicId;
	
	private String xiaobingName = Configuration.getXiaobingName();
	private String nicName = Configuration.getNicName();
	private String jjrName = Configuration.getJjrName();
	
	public static void main(String[] args) throws InterruptedException {
		MyWechatServiceImpl wechatService = new MyWechatServiceImpl();
		Application.startWechar(wechatService);
	}
	
	public void sendHappens(String string) {
		if (wechatMeta != null && nicId != null) {
			webwxsendmsg(wechatMeta, string, nicId);
		}
	}
	
	@Override
	public void dealSpecialMsg(WechatMeta wechatMeta, JSONObject msg, String fromUserName, String toUserName, String userName, String content) {
//		String fromName=getUserRemarkName(fromUserName);
//		String toName=getUserRemarkName(toUserName);
//		String name=getUserRemarkName(userName);
//		System.out.println("特殊；fromName:"+fromName+",toName:"+toName+",name:"+name+"；"+content);
	}
	
	@Override
	public void dealMyselfMsg(WechatMeta wechatMeta, JSONObject msg, String fromUserName, String toUserName, String userName, String content) {
//		String fromName=getUserRemarkName(fromUserName);//null
//		String toName=getUserRemarkName(toUserName);//对方名字，如果是特殊用户会null
//		String name=getUserRemarkName(userName);//null
//		System.out.println("自己；fromName:"+fromName+",toName:"+toName+",name:"+name+"；"+content);
	}
	
	@Override
	public void dealPrivateChatMsg(WechatMeta wechatMeta, JSONObject msg, String fromUserName, String toUserName, String userName, String content) {
		String fromName = getUserRemarkName(fromUserName);//对方名字
		String toName = getUserRemarkName(toUserName);//null
		String name = getUserRemarkName(userName);//null
//		System.out.println("私聊；fromName:"+fromName+",toName:"+toName+",name:"+name+"；"+content);
		if (this.wechatMeta == null) {
			this.wechatMeta = wechatMeta;
		}
		if (xiaobingId == null && fromName != null && fromName.contains(xiaobingName)) {
			xiaobingId = fromUserName;
			System.out.println();
			System.out.println("添加小冰id：" + xiaobingId);
			System.out.println();
		}
		if (nicId != null && fromName != null && fromName.contains(xiaobingName)) {
			webwxsendmsg(wechatMeta,  content.replaceAll(xiaobingName, jjrName), nicId);
		}
	}
	
	@Override
	public void dealGroupChatMsg(WechatMeta wechatMeta, JSONObject msg, String fromUserName, String toUserName, String userName, String content) {
		String fromName = getUserRemarkName(fromUserName);//群名
		String toName = getUserRemarkName(toUserName);//null
		String name = getUserRemarkName(userName);//null
		String[] contents = content.split(":<br/>");
//		if(contents.length>1) System.out.println("群聊；fromName:"+fromName+",toName:"+toName+",name:"+name+"；"+getUserRemarkName(contents[0])+":"+contents[1]);
//		else System.out.println("群聊；fromName:"+fromName+",toName:"+toName+",name:"+name+"；"+content);
		if (nicId == null && fromName != null && fromName.contains(nicName)) {
			nicId = fromUserName;
			System.out.println();
			System.out.println("添加网管id:" + nicId);
			System.out.println();
		}
		if (fromName != null && fromName.contains(nicName)) {
			if (contents[1].trim().startsWith(jjrName) && xiaobingId != null) {
				System.out.println("群聊；"+fromName+":"+getUserRemarkName(contents[0])+":"+contents[1]);
				webwxsendmsg(wechatMeta, contents[1].substring(3), xiaobingId);
			}
		}
	}
	
	@Override
	public void dealPictureMsg(WechatMeta wechatMeta, JSONObject msg, String fromUserName, String toUserName, String userName, String content) {
		String fromName = getUserRemarkName(fromUserName);//对方名字
		String toName = getUserRemarkName(toUserName);//null
		String name = getUserRemarkName(userName);//null
//		System.out.println("文件；fromName:"+fromName+",toName:"+toName+",name:"+name);
		savePicture(wechatMeta, msg, null);
	}
}
