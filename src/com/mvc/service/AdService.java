package com.mvc.service;

import java.util.List;

import com.mvc.entity.Ad;
import com.mvc.entity.User;

import net.sf.json.JSONObject;

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

	//查询ad全部信息
	Integer countTotal(String searchKey);

	//根据页数晒选ad信息
	List<Ad> findAdByPage(String searchKey, int offset, int limit);

	//根据id删除ad信息
	boolean deleteIsdelete(Integer adid);
	
	//根据id变更state
	boolean editState(Integer adid);

	//根据id查询ad信息
	Ad selectAdById(int ad_id);

	//state为null时返回全部ad信息
	List<Ad> findAlls();

	//根据state筛选ad信息   总页数
	Integer countStateTotal(String adState);

	//根据state、page筛选ad信息
	List<Ad> findAdByStatePage(String adState, int offset, int limit);

}
