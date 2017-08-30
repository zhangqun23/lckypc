package com.mvc.dao;

import java.util.List;


import com.mvc.entity.Travel;
import com.mvc.entity.TravelTrade;

	/**
	 * Travel相关Dao层接口
	 * 
	 * @author wdh
	 * @date 2017年8月11日
	 */
	public interface TravelDao {

		// 根据id修改
			Boolean updateState(Integer travel_id);
		// 根据id修改旅游信息
			Boolean updateTravelById(Integer travel_id, Travel travel);

		// 根据页数筛选全部旅游信息列表
		 List<Travel> findTravelByPage(String searchKey, Integer offset, Integer end);
		
		 // 根据标题获取旅游信息
		List<Travel> findTravelByTitle(String travel_title, Integer offset, Integer end);

		// 查询信息总条数
		Integer countTotal(String searchKey);
		
		// 根据页数筛选全部旅游交易信息列表
		List<TravelTrade> findTravelTradeByPage(String searchKey, Integer offset, Integer end);
		// 查询信息总条数
				Integer countTrTotal(String searchKey);
				
	}
