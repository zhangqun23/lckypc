package com.mvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.dao.AlarmStatisticDao;
import com.mvc.entity.AlarmStatistic;
import com.mvc.service.AlarmStatisticService;

/**
 * 报警统计业务实现
 * 
 * @author wangrui
 * @date 2016-10-20
 */
@Service("alarmStatisticServiceImpl")
public class AlarmStatisticServiceImpl implements AlarmStatisticService {

	@Autowired
	AlarmStatisticDao alarmStatisticDao;

	@SuppressWarnings("null")
	@Override
	public AlarmStatistic findAlst() {
		AlarmStatistic alarmStatistic = new AlarmStatistic();
		Object[] obj = (Object[]) alarmStatisticDao.findAlst();
		System.out.println("dsfdsf" + Integer.parseInt(obj[0].toString()));
		alarmStatistic.setBusNeed_num(Integer.parseInt(obj[0].toString()));
		alarmStatistic.setAd_num(Integer.parseInt(obj[1].toString()));
		alarmStatistic.setSmallGoods_num(Integer.parseInt(obj[2].toString()));
		alarmStatistic.setTruck_num(Integer.parseInt(obj[3].toString()));
		return alarmStatistic;
	}

	// 报警统计当天旅游交易
	@SuppressWarnings("null")
	@Override
	public Integer findTrTrade(String startTime, String endTime) {
		AlarmStatistic alarmStatistic = new AlarmStatistic();
		Integer num =  alarmStatisticDao.findTrTrade(startTime, endTime);
		return num;
	}

}
