package com.mvc.service;

import java.text.ParseException;
import java.util.List;

import net.sf.json.JSONObject;

import com.mvc.entity.BusNeed;
import com.mvc.entity.BusTrade;

/**
 * BusNeed相关Service层接口
 * 
 * @author wdh
 * @date 2017年9月4日
 */

public interface BusNeedService {

	// 添加修改信息
	boolean saveBune(BusNeed busNeed);

	// 根据id删除
	boolean deleteBuneIsdelete(Integer bune_id);

	// 查询信息总条数
	Integer countBuneTotal(String searchKey);
	
	// 根据页数筛选全部信息列表
	List<BusNeed> findBusNeedByPage(String searchKey, Integer offset, Integer end);
			
	// 根据合同ID获取合同
	BusNeed selectBusNeedById(Integer bune_id);
	
	// 添加合同
		BusNeed addBusNeed(JSONObject jsonObject);
	// 添加合同
		BusTrade addBusTrade(JSONObject jsonObject);
	// 信息补录
	 Boolean updateBusNeed(Integer bune_id, JSONObject jsonObject)throws ParseException;
	
	// 交易信息补录
	 Boolean updateBusTrade(Integer bune_id, JSONObject jsonObject)throws ParseException;
}
