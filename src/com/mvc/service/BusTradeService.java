package com.mvc.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.mvc.entity.BusTrade;

/**
 * BusTrade相关Service层接口
 * 
 * @author wdh
 * @date 2017年9月4日
 */
@Service
public interface BusTradeService {


	// 查询信息总条数
	Integer countTotal(String searchKey);
	
	// 根据页数筛选全部信息列表
	List<BusTrade> findBusTradeByPage(String searchKey, Integer offset, Integer end);
			
	// 根据合同ID获取合同
	BusTrade selectBusTradeById(Integer butr_id);
	
	// 信息补录
	BusTrade updateBusTrade(Integer butr_id, JSONObject jsonObject);
}
