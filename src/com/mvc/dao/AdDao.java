package com.mvc.dao;

import java.util.List;

import com.mvc.entity.Ad;

/**
 * 
 * @ClassName: AdDao
 * @Description: ad
 * @author ycj
 * @date 2017年9月6日 上午9:59:08 
 * 
 *
 */
public interface AdDao {

	//根据限制条件筛选信息
	List<Ad> findAdByPage(String adState, String adType, int offset, int limit);
	Integer countTotal(String adState, String adType);
	
	//根据id变更state
	boolean editState(Integer ad_id);

	//根据id删除ad信息
	boolean updateState(Integer ad_id);
	
	
}
