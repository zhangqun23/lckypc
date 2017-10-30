package com.mvc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 报警统计
 * 
 * @author wangrui
 * @date 2016-10-20
 */
@Entity
@Table(name = "alarm_statistic")
public class AlarmStatistic{

	private Integer alst_id;// ID
	private Integer busNeed_num;// 班车
	private Integer travel_num;// 旅游
	private Integer truck_num;// 零担
	private Integer ad_num;// 广告
	private Integer smallGoods_num;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getAlst_id() {
		return alst_id;
	}

	public void setAlst_id(Integer alst_id) {
		this.alst_id = alst_id;
	}

	public Integer getBusNeed_num() {
		return busNeed_num;
	}

	public void setBusNeed_num(Integer busNeed_num) {
		this.busNeed_num = busNeed_num;
	}

	public Integer getTravel_num() {
		return travel_num;
	}

	public void setTravel_num(Integer travel_num) {
		this.travel_num = travel_num;
	}

	public Integer getTruck_num() {
		return truck_num;
	}

	public void setTruck_num(Integer truck_num) {
		this.truck_num = truck_num;
	}

	public Integer getAd_num() {
		return ad_num;
	}

	public void setAd_num(Integer ad_num) {
		this.ad_num = ad_num;
	}

	public Integer getSmallGoods_num() {
		return smallGoods_num;
	}

	public void setSmallGoods_num(Integer smallGoods_num) {
		this.smallGoods_num = smallGoods_num;
	}

}
