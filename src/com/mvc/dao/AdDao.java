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

	//查询全部ad信息
	Integer countTotal(String searchKey);

	//根据页数筛选ad信息
	List<Ad> findAdByPage(String searchKey, int offset, int end);

	//根据id变更state
	boolean editState(Integer ad_id);

	//根据state获取页数
	Integer countStateTotal(String adState);

	//根据state、page筛选ad信息
	List<Ad> findAdByStatePage(String adState, int offset, int limit);

	//根据id删除ad信息
	boolean updateState(Integer ad_id);

}
