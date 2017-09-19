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

	//初始化
	Integer countTotal();
	List<Ad> findAdByPage(int offset, int limit);
	
	//state限制
	Integer countTotalS(String adState);
	List<Ad> findAdByState(String adState, int offset, int limit);

	//type限制
	Integer countTotalT(String adType);
	List<Ad> findAdByType(String adType, int offset, int limit);

	//根据id删除ad信息
	boolean deleteIsdelete(Integer adid);
	
	//根据id变更state
	boolean editState(Integer adid);
	
}
