package com.utils.commonUtils;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.IosNotification.Builder;
import cn.jpush.api.push.model.notification.Notification;

public class JPushUtils {

	private static final Logger logger = LoggerFactory.getLogger(JPushUtils.class);

	private JPushClient client = null;

	public JPushClient getClient() {
		return client;
	}

	public void setClient(JPushClient client) {
		this.client = client;
	}

	private JPushUtils() {
	}

	private static class JPushUtilsLoader {
		private static final JPushUtils instance = new JPushUtils();
	}

	public static JPushUtils getInstance(String masterSecret, String appKey) {
		JPushClient client = new JPushClient(masterSecret, appKey);
		JPushUtils utils = JPushUtilsLoader.instance;
		utils.setClient(client);
		return utils;
	}

	public void androidSendPush(String title, String message, Object obj,Integer code, List<String> alias) {
		try {
			AndroidNotification.Builder builder = AndroidNotification.newBuilder().setAlert(message).setTitle(title).setAlertType(1);
			if (obj != null) {
				builder.addExtra("obj", obj.toString());
			}
			if(code !=null){
				builder.addExtra("code", code);
			}
			AndroidNotification notification = builder.build();

			PushPayload payload = PushPayload.newBuilder().setPlatform(Platform.android()).setAudience(Audience.alias(alias))
					.setNotification(Notification.newBuilder().addPlatformNotification(notification).build()).setMessage(Message.content(message)).build();
			PushResult result = client.sendPush(payload);
			logger.debug("极光android推送返回结果：" + result);

		} catch (APIConnectionException e) {
			logger.error("极光推送android失败，ALIAS为：" + JSON.toJSONString(alias), e);
		} catch (APIRequestException e) {
			logger.debug("极光推送android失败，ALIAS为：" + JSON.toJSONString(alias));
		}
	}

	public void androidSendPush(String title, String message, Object obj,Integer code, String tag) {
		try {
			AndroidNotification.Builder builder = AndroidNotification.newBuilder().setAlert(message).setTitle(title).setAlertType(1);
			if (obj != null) {
				builder.addExtra("obj", obj.toString());
			}
			if(code !=null){
				builder.addExtra("code", code);
			}
			AndroidNotification notification = builder.build();

			PushPayload payload = PushPayload.newBuilder().setPlatform(Platform.android()).setAudience(Audience.tag(tag))
					.setNotification(Notification.newBuilder().addPlatformNotification(notification).build()).setMessage(Message.content(message)).build();
			PushResult result = client.sendPush(payload);
			logger.debug("极光android推送返回结果：" + result);
		} catch (APIConnectionException e) {
			logger.error("极光推送失败，TAG为：" + tag, e);
		} catch (APIRequestException e) {
			logger.debug("极光推送android失败，TAG为：" + tag);
		}
	}

	public void iosSendPush(String message, Object obj,Integer code, List<String> alias) {
		try {
			IosNotification.Builder builder = IosNotification.newBuilder().setAlert(message).setBadge(1).setSound("happy");
			if (obj != null) {
				builder.addExtra("obj", obj.toString());
			}
			if(code !=null){
				builder.addExtra("code", code);
			}
			IosNotification iosNotification = builder.build();

			PushPayload payload = PushPayload.newBuilder().setPlatform(Platform.ios()).setAudience(Audience.alias(alias))
					.setNotification(Notification.newBuilder().addPlatformNotification(iosNotification).build()).setMessage(Message.content(message))
					.setOptions(Options.newBuilder().build()).build();
			PushResult result = client.sendPush(payload);
			logger.debug("极光ios推送返回结果：" + result);
		} catch (APIConnectionException e) {
			logger.error("极光推送iOS失败，TAG为：" + JSON.toJSONString(alias), e);
		} catch (APIRequestException e) {
			logger.debug("极光推送iOS失败，TAG为：" + JSON.toJSONString(alias));
		}
	}

	public void iosSendPush(String message, Object obj,Integer code, String tag) {
		try {
			Builder builder = IosNotification.newBuilder().setAlert(message).setBadge(1).setSound("happy");
			if (obj != null) {
				builder.addExtra("obj", obj.toString());
			}
			if(code !=null){
				builder.addExtra("code", code);
			}
			IosNotification iosNotification = builder.build();

			PushPayload payload = PushPayload.newBuilder().setPlatform(Platform.ios()).setAudience(Audience.tag(tag))
					.setNotification(Notification.newBuilder().addPlatformNotification(iosNotification).build()).setMessage(Message.content(message))
					.setOptions(Options.newBuilder().build()).build();
			PushResult result = client.sendPush(payload);
			logger.debug("极光ios推送返回结果：" + result);
		} catch (APIConnectionException e) {
			logger.error("极光推送iOS失败，TAG为：" + tag, e);
		} catch (APIRequestException e) {
			logger.debug("极光推送iOS失败，TAG为：" + tag);
		}
	}

	public static void main(String[] args) {
		// {"APP_KEY":"37f1c59b29e463feec25bf1c","MASTER_SECRET":"fa16bb7c3b4666b27e759144"}
		JPushUtils jpushUtils = JPushUtils.getInstance("fa16bb7c3b4666b27e759144", "37f1c59b29e463feec25bf1c");
		List<String> alias = new ArrayList<String>();
		alias.add("alias_1592");
//		alias.add("alias_1391");
		// jpushUtils.iosSendPush("message", "111", alias);
		jpushUtils.androidSendPush("title", "message", "111",1, alias);
//		jpushUtils.iosSendPush("message", "1", alias);
	}

}
