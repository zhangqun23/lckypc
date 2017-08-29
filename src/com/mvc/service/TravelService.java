package com.mvc.service;

import java.text.ParseException;
import java.util.List;



import net.sf.json.JSONObject;

import com.mvc.entity.Travel;
import com.mvc.entity.TravelTrade;
import com.mvc.entity.User;
import com.utils.Pager;

/**
 * Travel相关Service层接口
 * 
 * @author wdh
 * @date 2017年8月14日
 */

public interface TravelService {

	// 添加修改旅游信息
	boolean save(Travel travel);
//	// 添加旅游信息
//	Travel addTravel(User user,JSONObject jsonObject);

	// 根据标题查询旅游信息是否存在,返回1存在，返回0不存在
	Long isExist(String travelTitle);

	// 根据标题查询
	List<Travel> findtravelByTitle(String travelTitle);

	// 根据id删除
	boolean deleteIsdelete(Integer travel_id);

	// 查询同公司总条数
	Integer countTotal(String searchKey);
	// 根据页数筛选全部旅游信息列表
	List<Travel> findTravelByPage(String searchKey, Integer offset, Integer end);

	// 获取旅游信息列表，无翻页功能
	List<Travel> findTravelAlls();

	// 根据ID查询旅游信息
	Travel findTravelById(Integer travel_id);
	
	// 根据公司筛选旅游信息列表
		List<Travel> findTravelByFirm(Integer travelFrim);
	// 根据标题获取列表
		List<Travel> findTravelByTitle(String travelTitle, Pager pager);
		
	// 根据页数筛选全部旅游交易信息列表
		List<TravelTrade> findTravelTradeByPage(String searchKey, Integer offset, Integer end);
	// 查询总条数
		Integer countTrTotal(String searchKey);	
		
	// 根据合同ID获取合同
		Travel selectTravelById(Integer travel_id);
	
	// 修改旅游基本信息
		Boolean updateTravelBase(Integer travel_id, JSONObject jsonObject, User user) throws ParseException;
	
}
