package com.mvc.dao;

import java.util.List;

import com.mvc.entity.Ad;

/**
<<<<<<< HEAD
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
=======
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
>>>>>>> 95c2cc36851a97c50c66e34ffdd019500e70fbd0
