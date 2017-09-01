package com.mvc.service;

import java.util.List;

import com.mvc.entity.Ad;

/**
 * 
 * @ClassName: AdService
 * @Description: TODO
 * @author ycj
 * @date 2017年8月31日 下午3:38:28 
 * 
 *
 */
public interface AdService {
	
	//全部信息
	Integer countTotal(String searchKey);
	
	//根据页数筛选全部ad信息列表
	List<Ad> findAdByPage(String searchKey, Integer offset, Integer end);
	
	//保存修改后的ad
	Ad saveAd(Ad ad);
	
	//返回相应类型ad
	List<Ad> findAdByType(Integer adType);
	
	//类型为空返回全部ad;状态为空返回全部ad
	List<Ad> findAdAlls();
	
	//返回相应状态ad
	List<Ad> findAdByState(Integer adState);
	
	//根据id删除ad
	Boolean deleteAd(Integer adId);
	
	//根据id查找ad
	Ad selectAdInfo(String adId);
	
	
	
	
	
	
	
	
	
	// 根据id删除
//	boolean deleteIsdelete(Integer ad_id);
	
	// 根据标题查询旅游信息是否存在,返回1存在，返回0不存在
//	Long isExist(String adTitle);
	
	// 根据合同ID获取合同
//	Ad selectAdById(Integer ad_id);
	
	// 修改旅游基本信息
//   Boolean updateAdBase(Integer ad_id, JSONObject jsonObject, User user) throws ParseException;
    
    // 根据ID查询旅游信息()
// 	Ad findAdById(Integer ad_id);
 	
 	//根据title获取ad
//	List<Ad> selectAdByTitle(String aTitle, Integer offset, Integer end);

//	// 添加旅游信息
//	Travel addTravel(User user,JSONObject jsonObject);	
//	// 根据标题获取列表
//		List<Travel> findTravelByTitle(String travelTitle, Pager pager);	
}