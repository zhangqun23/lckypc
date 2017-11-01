package com.mvc.dao;

import com.mvc.entity.AlarmStatistic;

/**
 * 报警统计持久层
 * 
 * @author wangrui
 * @date 2016-10-20
 */
public interface AlarmStatisticDao {

	// 报警统计
	Object findAlst();

	// 报警统计当天旅游交易
	Integer findTrTrade(String startTime, String endTime);
}
