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

	//初始化
	Integer countTotal();
	List<Ad> findAdByPage(int offset, int limit);
	
	//state限制
	Integer countTotalS(String adState);
	List<Ad> findAdByState(String adState, int offset, int limit);

	//type限制
	Integer countTotalT(String adType);
	List<Ad> findAdByType(String adType, int offset, int limit);
	
	//state、type限制
	List<Ad> findAdByST(String adState, String adType, int offset, int limit);
	Integer countTotalST(String adState, String adType);
	
	//根据id变更state
	boolean editState(Integer ad_id);

	//根据id删除ad信息
	boolean updateState(Integer ad_id);
	
	
}
