package com.mvc.dao;

import java.util.List;
import java.util.Map;

import com.mvc.entity.Truck;
import com.mvc.entity.TruckNeed;
import com.mvc.entity.TruckSend;

/**
 * 零担货运
 * @author ghl
 * @date   2017年9月9日
 */
public interface TruckDriverDao {
	//查询Truck信息
	List<Truck> findTruck(String trState, Integer offset, Integer limit);
	//删除Truck
	Boolean deleteTruck(Integer trckId);
	//根据条件获取总页数
	Integer countTotal(String trState);
	/**
	 *TruckSend
	 * @param limit 
	 * @param offset 
	 */
	//查询TruckSend信息
	List<TruckSend> getTruckSend(Integer offset, Integer limit);
	//获取总条数用来分页
	Integer countTotalPage();
	/**
	 * TruckNeed
	 * @return
	 */
	//获取总条数用来分页
	Integer TotalPage();
	//查询TruckNeed
	List<TruckNeed> getTruckNeed(Integer offset, Integer limit);

}
