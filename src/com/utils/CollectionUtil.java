package com.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 集合工具类
 * 
 * @author wangrui
 * @date 2016-12-05
 */
public class CollectionUtil {

	/**
	 * Object数组转list
	 * 
	 * @param obj
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<String> objToList(Object[] obj) {
		List<String> list = new ArrayList(Arrays.asList(obj));
		return list;
	}

	/**
	 * 初始化指定长度的数组，默认值为0
	 * 
	 * @param len
	 * @return
	 */
	public static Integer[] initInt(int len) {
		Integer[] arr = new Integer[len];
		for (int i = 0; i < len; i++) {
			arr[i] = 0;
		}
		return arr;
	}
}
