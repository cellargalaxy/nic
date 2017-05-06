package me.biezhi.wechat.service;

import com.blade.kit.DateKit;
import com.blade.kit.FileKit;
import com.blade.kit.StringKit;
import com.blade.kit.http.HttpRequest;
import com.blade.kit.json.JSONArray;
import com.blade.kit.json.JSONKit;
import com.blade.kit.json.JSONObject;
import me.biezhi.wechat.Constant;
import me.biezhi.wechat.exception.WechatException;
import me.biezhi.wechat.model.WechatContact;
import me.biezhi.wechat.model.WechatMeta;
import me.biezhi.wechat.util.Matchers;
import me.biezhi.wechat.util.PingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.concurrent.TimeUnit;

public abstract class WechatServiceImpl implements WechatService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WechatService.class);
	
	
	/**
	 * 获取联系人
	 */
	@Override
	public WechatContact getContact(WechatMeta wechatMeta) {
		String url = wechatMeta.getBase_uri() + "/webwxgetcontact?pass_ticket=" + wechatMeta.getPass_ticket() + "&skey="
				+ wechatMeta.getSkey() + "&r=" + DateKit.getCurrentUnixTime();
		
		JSONObject body = new JSONObject();
		body.put("BaseRequest", wechatMeta.getBaseRequest());
		
		HttpRequest request = HttpRequest.post(url).contentType("application/json;charset=utf-8")
				.header("Cookie", wechatMeta.getCookie()).send(body.toString());
		
		LOGGER.debug(request.toString());
		String res = request.body();
		request.disconnect();
		
		if (StringKit.isBlank(res)) {
			throw new WechatException("获取联系人失败");
		}
		
		LOGGER.debug(res);
		
		WechatContact wechatContact = new WechatContact();
		try {
			JSONObject jsonObject = JSONKit.parseObject(res);
			JSONObject BaseResponse = jsonObject.get("BaseResponse").asJSONObject();
			if (null != BaseResponse) {
				int ret = BaseResponse.getInt("Ret", -1);
				if (ret == 0) {
					JSONArray memberList = jsonObject.get("MemberList").asArray();
					JSONArray contactList = new JSONArray();
					
					if (null != memberList) {
						for (int i = 0, len = memberList.size(); i < len; i++) {
							JSONObject contact = memberList.get(i).asJSONObject();
							// 公众号/服务号
							if (contact.getInt("VerifyFlag", 0) == 8) {
								continue;
							}
							// 特殊联系人
							if (Constant.FILTER_USERS.contains(contact.getString("UserName"))) {
								continue;
							}
							// 群聊
							if (contact.getString("UserName").indexOf("@@") != -1) {
								continue;
							}
							// 自己
							if (contact.getString("UserName").equals(wechatMeta.getUser().getString("UserName"))) {
								continue;
							}
							contactList.add(contact);
						}
						
						wechatContact.setContactList(contactList);
						wechatContact.setMemberList(memberList);
						
						this.getGroup(wechatMeta, wechatContact);
						
						return wechatContact;
					}
				}
			}
		} catch (Exception e) {
			throw new WechatException(e);
		}
		return null;
	}
	
	private void getGroup(WechatMeta wechatMeta, WechatContact wechatContact) {
		String url = wechatMeta.getBase_uri() + "/webwxbatchgetcontact?type=ex&pass_ticket=" + wechatMeta.getPass_ticket() + "&skey="
				+ wechatMeta.getSkey() + "&r=" + DateKit.getCurrentUnixTime();
		
		JSONObject body = new JSONObject();
		body.put("BaseRequest", wechatMeta.getBaseRequest());
		
		HttpRequest request = HttpRequest.post(url).contentType("application/json;charset=utf-8")
				.header("Cookie", wechatMeta.getCookie()).send(body.toString());
		
		LOGGER.debug(request.toString());
		String res = request.body();
		request.disconnect();
		
		if (StringKit.isBlank(res)) {
			throw new WechatException("获取群信息失败");
		}
		
		LOGGER.debug(res);
		
		try {
			JSONObject jsonObject = JSONKit.parseObject(res);
			JSONObject BaseResponse = jsonObject.get("BaseResponse").asJSONObject();
			if (null != BaseResponse) {
				int ret = BaseResponse.getInt("Ret", -1);
				if (ret == 0) {
					JSONArray memberList = jsonObject.get("MemberList").asArray();
					JSONArray contactList = new JSONArray();
					
					if (null != memberList) {
						for (int i = 0, len = memberList.size(); i < len; i++) {
							JSONObject contact = memberList.get(i).asJSONObject();
							// 公众号/服务号
							if (contact.getInt("VerifyFlag", 0) == 8) {
								continue;
							}
							// 特殊联系人
							if (Constant.FILTER_USERS.contains(contact.getString("UserName"))) {
								continue;
							}
							// 群聊
							if (contact.getString("UserName").indexOf("@@") != -1) {
								continue;
							}
							// 自己
							if (contact.getString("UserName").equals(wechatMeta.getUser().getString("UserName"))) {
								continue;
							}
							contactList.add(contact);
						}
						
						wechatContact.setContactList(contactList);
						wechatContact.setMemberList(memberList);
					}
				}
			}
		} catch (Exception e) {
			throw new WechatException(e);
		}
	}
	
	/**
	 * 获取UUID
	 */
	@Override
	public String getUUID() {
		HttpRequest request = HttpRequest.get(Constant.JS_LOGIN_URL, true, "appid", "wx782c26e4c19acffb", "fun", "new",
				"lang", "zh_CN", "_", DateKit.getCurrentUnixTime());
		
		LOGGER.debug(request.toString());
		
		String res = request.body();
		request.disconnect();
		
		if (StringKit.isNotBlank(res)) {
			String code = Matchers.match("window.QRLogin.code = (\\d+);", res);
			if (null != code) {
				if (code.equals("200")) {
					return Matchers.match("window.QRLogin.uuid = \"(.*)\";", res);
				} else {
					throw new WechatException("错误的状态码: " + code);
				}
			}
		}
		throw new WechatException("获取UUID失败");
	}
	
	/**
	 * 打开状态提醒
	 */
	@Override
	public void openStatusNotify(WechatMeta wechatMeta) {
		
		String url = wechatMeta.getBase_uri() + "/webwxstatusnotify?lang=zh_CN&pass_ticket=" + wechatMeta.getPass_ticket();
		
		JSONObject body = new JSONObject();
		body.put("BaseRequest", wechatMeta.getBaseRequest());
		body.put("Code", 3);
		body.put("FromUserName", wechatMeta.getUser().getString("UserName"));
		body.put("ToUserName", wechatMeta.getUser().getString("UserName"));
		body.put("ClientMsgId", DateKit.getCurrentUnixTime());
		
		HttpRequest request = HttpRequest.post(url).contentType("application/json;charset=utf-8")
				.header("Cookie", wechatMeta.getCookie()).send(body.toString());
		
		LOGGER.debug("" + request);
		String res = request.body();
		request.disconnect();
		
		if (StringKit.isBlank(res)) {
			throw new WechatException("状态通知开启失败");
		}
		
		try {
			JSONObject jsonObject = JSONKit.parseObject(res);
			JSONObject BaseResponse = jsonObject.get("BaseResponse").asJSONObject();
			if (null != BaseResponse) {
				int ret = BaseResponse.getInt("Ret", -1);
				if (ret != 0) {
					throw new WechatException("状态通知开启失败，ret：" + ret);
				}
			}
		} catch (Exception e) {
			throw new WechatException(e);
		}
	}
	
	/**
	 * 微信初始化
	 */
	@Override
	public void wxInit(WechatMeta wechatMeta) {
		String url = wechatMeta.getBase_uri() + "/webwxinit?r=" + DateKit.getCurrentUnixTime() + "&pass_ticket="
				+ wechatMeta.getPass_ticket() + "&skey=" + wechatMeta.getSkey();
		
		JSONObject body = new JSONObject();
		body.put("BaseRequest", wechatMeta.getBaseRequest());
		
		HttpRequest request = HttpRequest.post(url).contentType("application/json;charset=utf-8")
				.header("Cookie", wechatMeta.getCookie()).send(body.toString());
		
		LOGGER.debug("" + request);
		String res = request.body();
		request.disconnect();
		
		if (StringKit.isBlank(res)) {
			throw new WechatException("微信初始化失败");
		}
		
		try {
			JSONObject jsonObject = JSONKit.parseObject(res);
			if (null != jsonObject) {
				JSONObject BaseResponse = jsonObject.get("BaseResponse").asJSONObject();
				if (null != BaseResponse) {
					int ret = BaseResponse.getInt("Ret", -1);
					if (ret == 0) {
						wechatMeta.setSyncKey(jsonObject.get("SyncKey").asJSONObject());
						wechatMeta.setUser(jsonObject.get("User").asJSONObject());
						
						StringBuffer synckey = new StringBuffer();
						JSONArray list = wechatMeta.getSyncKey().get("List").asArray();
						for (int i = 0, len = list.size(); i < len; i++) {
							JSONObject item = list.get(i).asJSONObject();
							synckey.append("|" + item.getInt("Key", 0) + "_" + item.getInt("Val", 0));
						}
						wechatMeta.setSynckey(synckey.substring(1));
					}
				}
			}
		} catch (Exception e) {
		}
	}
	
	/**
	 * 选择同步线路
	 */
	@Override
	public void choiceSyncLine(WechatMeta wechatMeta) {
		boolean enabled = false;
		for (String syncUrl : Constant.SYNC_HOST) {
			int[] res = this.syncCheck(syncUrl, wechatMeta);
			if (res[0] == 0) {
				String url = "https://" + syncUrl + "/cgi-bin/mmwebwx-bin";
				wechatMeta.setWebpush_url(url);
				LOGGER.info("选择线路：[{}]", syncUrl);
				enabled = true;
				break;
			}
		}
		if (!enabled) {
			throw new WechatException("同步线路不通畅");
		}
	}
	
	/**
	 * 检测心跳
	 */
	@Override
	public int[] syncCheck(WechatMeta wechatMeta) {
		return this.syncCheck(null, wechatMeta);
	}
	
	/**
	 * 检测心跳
	 */
	private int[] syncCheck(String url, WechatMeta meta) {
		
		// 如果网络中断，休息10秒
		if (PingUtil.netIsOver()) {
			try {
				TimeUnit.SECONDS.sleep(10);
			} catch (Exception e) {
				LOGGER.error("", e);
			}
		}
		
		if (null == url) {
			url = meta.getWebpush_url() + "/synccheck";
		} else {
			url = "https://" + url + "/cgi-bin/mmwebwx-bin/synccheck";
		}
		
		JSONObject body = new JSONObject();
		body.put("BaseRequest", meta.getBaseRequest());
		
		HttpRequest request = HttpRequest
				.get(url, true, "r", DateKit.getCurrentUnixTime() + StringKit.getRandomNumber(5), "skey",
						meta.getSkey(), "uin", meta.getWxuin(), "sid", meta.getWxsid(), "deviceid",
						meta.getDeviceId(), "synckey", meta.getSynckey(), "_", System.currentTimeMillis())
				.header("Cookie", meta.getCookie());
		
		LOGGER.debug(request.toString());
		
		String res = request.body();
		request.disconnect();
		
		int[] arr = new int[]{-1, -1};
		if (StringKit.isBlank(res)) {
			return arr;
		}
		
		String retcode = Matchers.match("retcode:\"(\\d+)\",", res);
		String selector = Matchers.match("selector:\"(\\d+)\"}", res);
		if (null != retcode && null != selector) {
			arr[0] = Integer.parseInt(retcode);
			arr[1] = Integer.parseInt(selector);
			return arr;
		}
		return arr;
	}
	
	/**
	 * 处理消息
	 */
	@Override
	public void handleMsg(WechatMeta wechatMeta, JSONObject data) {
		if (null == data) {
			return;
		}
		
		JSONArray AddMsgList = data.get("AddMsgList").asArray();
		
		for (int i = 0, len = AddMsgList.size(); i < len; i++) {
			JSONObject msg = AddMsgList.get(i).asJSONObject();
			int msgType = msg.getInt("MsgType", 0);
			String content = msg.getString("Content");
			
			if (msgType == 51) {
//				LOGGER.info("成功截获微信初始化消息");
			} else if (msgType == 1) {
				if (Constant.FILTER_USERS.contains(msg.getString("FromUserName"))) {
					//接收文件助手之类特殊账号的信息
					dealSpecialMsg(wechatMeta, msg, msg.getString("FromUserName"), msg.getString("ToUserName"), msg.getString("UserName"), content);
				} else if (msg.getString("FromUserName").equals(wechatMeta.getUser().getString("UserName"))) {
					//接收自己发出来的信息
					dealMyselfMsg(wechatMeta, msg, msg.getString("FromUserName"), msg.getString("ToUserName"), msg.getString("UserName"), content);
				} else if (msg.getString("FromUserName").indexOf("@@") != -1) {
					//群聊信息
					dealGroupChatMsg(wechatMeta, msg, msg.getString("FromUserName"), msg.getString("ToUserName"), msg.getString("UserName"), content);
				} else {
					//私聊信息
					dealPrivateChatMsg(wechatMeta, msg, msg.getString("FromUserName"), msg.getString("ToUserName"), msg.getString("UserName"), content);
				}
			} else if (msgType == 3) {
				dealPictureMsg(wechatMeta, msg, msg.getString("FromUserName"), msg.getString("ToUserName"), msg.getString("UserName"), content);
			} else if (msgType == 34) {
//				LOGGER.info(getUserRemarkName(msg.getString("FromUserName")) + ":给你发送了一段语音");
			} else if (msgType == 42) {
//				LOGGER.info(getUserRemarkName(msg.getString("FromUserName")) + ":给你发送了一张名片");
			} else {
//				LOGGER.info(getUserRemarkName(msg.getString("FromUserName"))+ ":你有新的消息，请注意查收");
			}
		}
	}
	
	/**
	 * 处理特殊用户（例如文件助手）的信息
	 */
	public abstract void dealSpecialMsg(WechatMeta wechatMeta, JSONObject msg, String fromUserName, String toUserName, String userName, String content);
	
	/**
	 * 处理自己的信息，要注意，不管是自己对自己聊天还是群聊，自己说的话都会调用这个方法
	 */
	public abstract void dealMyselfMsg(WechatMeta wechatMeta, JSONObject msg, String fromUserName, String toUserName, String userName, String content);
	
	/**
	 * 处理私聊的信息
	 */
	public abstract void dealPrivateChatMsg(WechatMeta wechatMeta, JSONObject msg, String fromUserName, String toUserName, String userName, String content);
	
	/**
	 * 处理群聊别人的信息
	 */
	public abstract void dealGroupChatMsg(WechatMeta wechatMeta, JSONObject msg, String fromUserName, String toUserName, String userName, String content);
	
	/**
	 * 处理图片的信息
	 */
	public abstract void dealPictureMsg(WechatMeta wechatMeta, JSONObject msg, String fromUserName, String toUserName, String userName, String content);
	
	/**
	 * 发送消息
	 */
	public void webwxsendmsg(WechatMeta meta, String content, String to) {
		String url = meta.getBase_uri() + "/webwxsendmsg?lang=zh_CN&pass_ticket=" + meta.getPass_ticket();
		JSONObject body = new JSONObject();
		
		String clientMsgId = DateKit.getCurrentUnixTime() + StringKit.getRandomNumber(5);
		JSONObject Msg = new JSONObject();
		Msg.put("Type", 1);
		Msg.put("Content", content);
		Msg.put("FromUserName", meta.getUser().getString("UserName"));
		Msg.put("ToUserName", to);
		Msg.put("LocalID", clientMsgId);
		Msg.put("ClientMsgId", clientMsgId);
		
		body.put("BaseRequest", meta.getBaseRequest());
		body.put("Msg", Msg);
		
		HttpRequest request = HttpRequest.post(url).contentType("application/json;charset=utf-8")
				.header("Cookie", meta.getCookie()).send(body.toString());
		
		LOGGER.info("发送消息...");
		LOGGER.debug("" + request);
		request.body();
		request.disconnect();
	}
	
	/**
	 * 保存图片
	 *
	 * @param wechatMeta
	 * @param msg
	 * @param file       如果是null，自动设置文件名保存
	 * @return 图片文件
	 */
	public File savePicture(WechatMeta wechatMeta, JSONObject msg, File file) {
		String imgDir = Constant.config.get("app.img_path");
		String msgId = msg.getString("MsgId");
		FileKit.createDir(imgDir, false);
		String imgUrl = wechatMeta.getBase_uri() + "/webwxgetmsgimg?MsgID=" + msgId + "&skey=" + wechatMeta.getSkey();
		if (file == null) file = new File(imgDir + "/" + msgId + ".jpg");
		HttpRequest.get(imgUrl).header("Cookie", wechatMeta.getCookie()).receive(file);
		return file;
	}
	
	public String getUserRemarkName(String id) {
		String name = null;
		for (int i = 0, len = Constant.CONTACT.getMemberList().size(); i < len; i++) {
			JSONObject member = Constant.CONTACT.getMemberList().get(i).asJSONObject();
			if (member.getString("UserName").equals(id)) {
				if (StringKit.isNotBlank(member.getString("RemarkName"))) {
					name = member.getString("RemarkName");
				} else {
					name = member.getString("NickName");
				}
				return name;
			}
		}
		return name;
	}
	
	@Override
	public JSONObject webwxsync(WechatMeta meta) {
		
		String url = meta.getBase_uri() + "/webwxsync?skey=" + meta.getSkey() + "&sid=" + meta.getWxsid();
		
		JSONObject body = new JSONObject();
		body.put("BaseRequest", meta.getBaseRequest());
		body.put("SyncKey", meta.getSyncKey());
		body.put("rr", DateKit.getCurrentUnixTime());
		
		HttpRequest request = HttpRequest.post(url).contentType("application/json;charset=utf-8")
				.header("Cookie", meta.getCookie()).send(body.toString());
		
		LOGGER.debug(request.toString());
		String res = request.body();
		request.disconnect();
		
		if (StringKit.isBlank(res)) {
			throw new WechatException("同步syncKey失败");
		}
		
		JSONObject jsonObject = JSONKit.parseObject(res);
		JSONObject BaseResponse = jsonObject.get("BaseResponse").asJSONObject();
		if (null != BaseResponse) {
			int ret = BaseResponse.getInt("Ret", -1);
			if (ret == 0) {
				meta.setSyncKey(jsonObject.get("SyncKey").asJSONObject());
				StringBuffer synckey = new StringBuffer();
				JSONArray list = meta.getSyncKey().get("List").asArray();
				for (int i = 0, len = list.size(); i < len; i++) {
					JSONObject item = list.get(i).asJSONObject();
					synckey.append("|" + item.getInt("Key", 0) + "_" + item.getInt("Val", 0));
				}
				meta.setSynckey(synckey.substring(1));
				return jsonObject;
			}
		}
		return null;
	}
	
}
