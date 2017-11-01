package com.mvc.service;

import com.mvc.entity.AlarmStatistic;

/**
 * 报警统计业务
 * 
 * @author wangrui
 * @date 2016-10-20
 */
public interface AlarmStatisticService {

	// 报警统计
	AlarmStatistic findAlst();
	//报警统计当天旅游交易
	Integer findTrTrade(String startTime, String endTime);
}
