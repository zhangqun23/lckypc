package com.mvc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mvc.entity.Ad;

<<<<<<< HEAD
public interface AdRepository extends JpaRepository<Ad, Integer>{

	/*//根据title查询ad信息是否存在，返回1存在
	@Query("select count(ad_id) from Ad tr where ad_title = :ad_title and is_delete=0")
	Long countByAdTitle(String adTitle);
*/
	//根据id获取ad信息
	@Query("select tr from Ad tr where ad_id=:ad_id ")
	Ad selectAdById(int ad_id);

/*	//根据state筛选ad信息
	@Query("select t from Ad t where ad_state = :ad_state and is_delete=0")
	List<Ad> findAdByState(@Param("ad_state")Integer adState);
*/
	//state为null时返回全部ad信息
	@Query("select a from Ad a where is_delete=0")
	List<Ad> findAlls();

}
=======
/**
 * 
 * @ClassName: AdRepository
 * @Description: TODO
 * @author ycj
 * @date 2017年8月31日 下午3:55:27 
 * 
 *
 */
public interface AdRepository extends JpaRepository<Ad, Integer> {
	
	//返回相应类型广告
	@Query("select a from Ad a where ad_type = :ad_type and is_delete=0")
	public List<Ad> findAdByType(@Param("ad_type")Integer ad_type);

	//返回相应状态ad
	@Query("select a from Ad a where ad_state =:ad_state and is_delete=0")
	public List<Ad> findAdByState(@Param("ad_state")Integer ad_state);

	//类型为空返回全部广告
	@Query("select a from Ad a where is_delete=0")
	public List<Ad> findAlls();
	
	// 根据id查询ad
    @Query("select a from Ad a where ad_id = :ad_id")
	public Ad findAdById(@Param("ad_id") Integer adId);
	
	
	
	
	
	
	
	
    
    
	
	// 根据标题查询旅游信息是否存在,返回1存在，返回0不存在
//	@Query("select count(ad_id) from Ad a where ad_title = :ad_title and is_delete=0")
//	public Long countByAdTitle(@Param("ad_title") String ad_title);
	
	//根据ID获取旅游信息
//	@Query("select a from Ad a where ad_id=:ad_id ")
//	Ad selectAdById(@Param("ad_id") Integer ad_id);
	
    // 根据ID查询全部旅游信息
// 	@Query("select a from Ad a where is_delete=0 ")
// 	public List<Ad> findAdAlls();
	
	// 根据id删除
//	@Query("update Ad set is_delete=1 where ad_id = :ad_id")
//	public boolean deleteByAdId(@Param("ad_id") Integer ad_id);
	
	// 根据标题查询旅游信息
//	@Query("select a from Ad a where ad_title = :ad_title and is_delete=0")
//	public Ad selectAdByTitle(@Param("ad_title") String ad_title);
	// 查询总条数
//	@Query("select count(travel_id) from travel tr where is_delete=0")
//		Long countTotal(@Param("creator_id") Integer creator_id);	

}
>>>>>>> 95c2cc36851a97c50c66e34ffdd019500e70fbd0
