package com.mvc.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mvc.entity.BusNeed;


	/**
	 * BusNeed相关Dao层接口
	 * 
	 * @author wdh
	 * @date 2017年9月4日
	 */
@Repository
	public interface BusNeedDao {

		// 删除信息
			Boolean updateBuneState(Integer bune_id);
			
		// 根据id修改信息
			Boolean updateBusNeedById(Integer bune_id, BusNeed busNeed);

		// 根据页数筛选全部信息
		 List<BusNeed> findBusNeedByPage(String searchKey, Integer offset, Integer end);
		
		// 查询信息总条数
		Integer countBuneTotal(String searchKey);
		
		/////////////////////
		
		// 根据id修改信息
			Boolean updateBusTradeById(Integer butr_id, BusNeed busNeed );

		// 根据页数筛选全部信息
			List<BusNeed> findBusTradeByPage(String searchKey, Integer offset, Integer end);
				
		// 查询信息总条数
			Integer countTotal(String searchKey);
				
	}
