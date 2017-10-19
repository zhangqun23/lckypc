package com.base.constants;

/**
 * 微信支付相关常量，做到开箱即用 
 */

public class wxPayConstants {

	public static final String APPID = "wx3afdb0aec74f693f";//微信公众号appid
	public static final String MCH_ID = "1468721802";//商户号
	public static final String PATERNERKEY = "luochuankeyunqichezhan9113622220";//API密钥
	public static final String SIGNTYPE = "MD5";//编码方式
	public static final String TRAVELBODY = "洛客-旅游";//交易名称，必须是“XX-XX”
	public static final String NOTIFY_URL = "";//微信支付回调URL，支付完成后，微信会把相关和用户信息发送到该URL
	
}
