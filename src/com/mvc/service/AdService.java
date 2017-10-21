package com.mvc.service;

import java.util.List;

import com.mvc.entity.Ad;

/**
 * 
 * @ClassName: AdService
 * @Description: ad
 * @author ycj
 * @date 2017年9月6日 上午9:43:28 
 * 
 *
 */
public interface AdService {

	//根据限制条件筛选信息
	Integer countTotal(String adState, String adType);
	List<Ad> findAdByPage(String adState, String adType, Integer offset, Integer limit);
	
	//根据id删除ad信息
	boolean deleteIsdelete(Integer adid);
	
	//审核
	boolean editState(Integer adid , String adState);
	
}
