package com.mvc.service;

import java.text.ParseException;
import java.util.List;

import com.mvc.entity.Ad;
import com.mvc.entity.User;

import net.sf.json.JSONObject;

/**
 * 
 * @ClassName: AdService
 * @Description: TODO
 * @author ycj
 * @date 2017年8月30日 上午10:46:32 
 * 
 *
 */
public interface AdService {
	
	//根据id删除ad
	boolean deleteIsdelete(Integer ad_id);
	
	//根据id筛选ad
	Ad selectAdById(String adId);

	//根据state选择ad
	Ad selectAdByState(String adState);
	
	//根据id修改ad
	Boolean updateAdBase(Integer ad_id, JSONObject jsonObject, User user) throws ParseException;

	

	
	
		
		//boolean save(Ad ad);
		//Long isExist(String adTitle);
		//List<Ad> findAdByTitle(String adTitle);
		//List<Ad> findAdByType(String adType);
		//List<Ad> findAdByState(String adState);
		//List<Ad> findAdAlls();
		//Ad findAdById(Integer ad_id);
		//	List<Ad> findAdByTitle(String adTitle, Pager pager);
			
		
		
			
		
		
		
		
			
	}
