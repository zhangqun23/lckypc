package com.utils;

/**
 * 字符串工具类
 * 
 * @author wangrui
 * @date 2016-11-17
 */
public class StringUtil {

	/**
	 * 判读字符串是否为空
	 * 
	 * @param s
	 * @return
	 */
	public static Boolean strIsNotEmpty(String s) {
		Boolean flag = true;
		if (s == null || s.length() <= 0) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 4舍5入保留2位小数
	 * 
	 * @param fl
	 * @return
	 */
	public static Float save2Float(Float fl) {
		Float result = 0.0f;
		if (fl != null) {
			String str = String.format("%.2f", fl);
			result = Float.valueOf(str);
		}
		return result;
	}

	/**
	 * 判断对象为空或者为0
	 * 
	 * @param obj
	 * @return
	 */
	public static Boolean isNullOrZero(Object obj) {
		Boolean flag = false;
		if (obj == null || obj.toString().equals("0")) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 当天0时刻
	 * 
	 * @param str
	 * @return yyyy-MM-dd 00:00:00
	 */
	public static String dayFirstTime(String str) {
		// TODO 自动生成的方法存根
		return str + " 00:00:00";
	}
	
	/**
	 * 当天最后时间
	 * 
	 * @param str
	 * @return yyyy-MM-dd 23:59:59
	 */
	public static String dayLastTime(String str) {
		// TODO 自动生成的方法存根
		return str + " 23:59:59";
	}
}
