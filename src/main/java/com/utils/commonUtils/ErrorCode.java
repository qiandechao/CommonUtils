package com.utils.commonUtils;

public enum ErrorCode {
	SUCCESS(1, "操作成功！"), 
	FAILED(2, "服务器未知错误！"), 
	CUSTOM(3, "自定义错误"), 
	INTERNAL_ERROR(1000, "系统内部错误,请联系系统管理员！"), 
	SIGNATURE_ACCREDIT_FAILED(1001, "签名认证失败！"), 
	SERVER_RESPONSE_BUSY(1002, "系统繁忙，请稍后再试！"), 
	SERVER_RESPONSE_TIMEOUT(1003, "请求超时，请稍后再试！"), 
	SERVER_RESPONSE_INVALIDURL(1004, "API地址不存在！"), 
	PARAMTER_REQUIRE(2000, "参数<{}>不允许为空！"), 
	PARAMTER_TOO_SHORT(2001, "参数<{}>长度至少要大于{}个字符！"), 
	PARAMTER_TOO_LONG(2002, "参数<{}>长度不能大于{}个字符！"), 
	PARAMTER_FORMAT_ILLEGAL(2003, "参数<{}>不是合法的<{}>类型！"),
	PARAMTER_FILETYPE_ILLEGAL(2004, "BASE64文件流<{}>未按照约定协议拼接！"),
	OBJECT_NOT_EXIST(2005, "目标对象<{}>不存在！"),
	//以下是自定义错误
	ACCOUNT_MUE_REQUIRE(3000, "参数<mobile、userName、email>必须有一个不能为空！"),
	ACCOUNT_MOBILE_EXIST(3001, "手机号<{}>已经被注册！"),
	ACCOUNT_USERNAME_FORMAT(3002, "用户名必须是字母、数字、“_”组成的4-10位字符！"),
	ACCOUNT_USERNAME_EXIST(3003, "用户名<{}>已经被注册！"),
	ACCOUNT_USERNAME_SENSITIVE(3004, "用户名含有敏感词，请重新输入！"),
	ACCOUNT_SHOWNAME_SENSITIVE(3005, "昵称含有敏感词，请重新输入！"),
	ACCOUNT_EMAIL_EXIST(3006, "邮箱<{}>已经被注册！"),
	ACCOUNT_PASSWORD_FORMAT(3007, "密码必须有字母和数字组合且≥6位≤16位！"),
	ACCOUNT_SOURCE_ERROR(3008, "用户来源<source>错误！"),
	ACCOUNT_USERID_INVALIDURL(3009, "该用户不存在！"),
	ACCOUNT_THIRDTYPE_ILLEGAL(3010, "传入的<type>不是合法的值！"),
	ACCOUNT_USERSHOWNAME_FORMAT(3011, "用户昵称必须是字母、汉字、数字、“_”组成的4-16位字符！"),
	LOGIN_FAILED_INCORRECT(3015, "登录失败，账号或密码错误！"),
	LOGIN_FAILED_AVALIBLE(3016, "您的账号被冻结，请联系客服人员！"),
	ACCOUNT_OLDPASSWORD_ERROR(3017, "旧密码不正确！"),
	CAPTCHA_VALIDATE_INVALID(3020, "短信验证码验证失败！"),
	CAPTCHA_SEND_FAILED(3021, "短信发送失败，请您稍后再试！"),
	CAPTCHA_SEND_OFTEN(3022, "您发送过于频繁，请2分钟后再试！"),
	TOPIC_ALL_EMTPY(3100, "参数<content、audio、images>必须有一个不能为空！"),
	TOPIC_ROB_ANSWER(3101, "抢回答权失败，问题已被抢或其他原因！"),
	TOPIC_REPLY_MAXTIMES(3102, "一个问题最多只能问了3次！"),
	TOPIC_NOT_EXIST(3103, "该问题不存在，可能已被删除了！"),
	APK_NOT_EXIST(3300, "不存在APK版本信息！"),
	FILE_WRITE_FAILED(3200, "Base64文件流<{}>写入失败！"),
	
	API_SUCCESS(1,"SUCCESS"),
	COURSE_FIND_FAILED(2,"课程为空或者已删除"),
	COURSE_GONE_OFF_SHELVES(2,"该课程已下架"),
	COURSE_LIVE_OFF(2,"平台暂不支持直播功能"),
	COURSE_PRICE_ERROR(2,"开通课程金额不能为0"),
	COURSE_COLLECT_ALREADY(2,"该课程您已经收藏过了！"),
	COURSE_BUYED(2,"您已经购买过该课程!"),
	
	SHOPCAR_ALREADY_EXIT(2,"购物车已有此课程，请勿重复添加"),
	MEMBER_DISABLED(2,"该类会员已停用"),
	ORDER_FAILED(2,"下单失败！"),
	OTHER_ID_NULL(2,"otherId不能为空!"),
	ORDER_NOT_EXIT(2,"该订单不存在!"),
	ORDER_STATUS_ERROR(2,"订单状态异常!"),
	NOT_BUY_PAYMENT_TYPE_OF_ALIPAY(2,"您暂未购买【支付宝支付】功能，无法使用"),
	NOT_BUY_PAYMENT_TYPE_OF_YIBAO(2,"您暂未购买【易宝支付】功能，无法使用"),
	NOT_BUY_PAYMENT_TYPE_OF_WEIXIN(2,"您暂未购买【微信支付】功能，无法使用"),
	PAY_SUCCESS(3,"支付成功");
	
	
	private int code;
	private String msg;
	private Object args[];

	// 构造方法
	private ErrorCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public ErrorCode setCode(int code) {
		this.code = code;
		return this;
	}

	public String getMsg() {
		if (args != null && args.length > 0) {
			String msg = this.msg;
			for (Object arg : args) {
				if(msg.indexOf("{}") != -1) {
					msg = msg.substring(0, msg.indexOf("{}")).concat(arg.toString()).concat(msg.substring(msg.indexOf("{}") + 2));
				}
			}
			return msg;
		} else {
			return this.msg;
		}
	}

	public ErrorCode setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	public Object[] getArgs() {
		return args;
	}

	public ErrorCode setArgs(Object... args) {
		this.args = args;
		return this;
	}
}
