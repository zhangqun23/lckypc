package com.mvc.dao;

import java.util.List;

import com.mvc.entity.Ad;

/**
	 * 
	 * @ClassName: AdDao
	 * @Description: TODO
	 * @author ycj
	 * @date 2017年8月31日 下午3:58:32 
	 * 
	 *
	 */
	public interface AdDao {
		
		//查询信息总条数
		Integer countTotal(String searchKey);
		
		//根据页数筛选全部旅游信息列表
		List<Ad> findAdByPage(String searchKey, Integer offset, Integer end);
		
		//根据id删除ad
		Boolean deleteAd(Integer adId);
		
		
		
		
		
		
		
		
		
		
		
		// 根据id修改
//		Boolean updateState(Integer ad_id);
		
		// 根据标题获取旅游信息
//		List<Ad> selectAdByTitle(String aTitle, Integer offset, Integer end);
	
		// 根据id修改旅游信息
//			Boolean updateAdById(Integer ad_id, Ad ad);			
	}