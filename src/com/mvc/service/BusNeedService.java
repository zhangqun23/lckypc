package com.mvc.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.mvc.entity.BusNeed;
import com.mvc.entity.BusTrade;
import com.mvc.entity.Contract;
import com.mvc.entity.User;

/**
 * BusNeed相关Service层接口
 * 
 * @author wdh
 * @date 2017年9月4日
 */
@Service
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
	BusNeed updateBusNeed(Integer bune_id, JSONObject jsonObject);
	
	// 交易信息补录
	BusTrade updateBusTrade(Integer butr_id, JSONObject jsonObject);
}
