package com.mvc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mvc.entity.Travel;


/**
 * 旅游信息管理
 * 
 * @author wdh
 * @date 2017年8月11日
 */
public interface TravelRepository extends JpaRepository<Travel, Integer> {
	
	// 根据ID查询旅游信息
	@Query("select tr from Travel tr where travel_id = :travel_id")
	public Travel findTravelById(@Param("travel_id") Integer travel_id);

	// 根据ID查询全部旅游信息
	@Query("select tr from Travel tr where is_delete=0 ")
	public List<Travel> findTravelAlls();

	// 根据id删除
	@Query("update Travel set is_delete=1 where travel_id = :travel_id")
	public boolean deleteByTravelId(@Param("travel_id") Integer travel_id);
	
	// 根据标题查询旅游信息是否存在,返回1存在，返回0不存在
	@Query("select count(travel_id) from Travel tr where travel_title = :travel_title and is_delete=0")
	public Long countByTravelTitle(@Param("travel_title") String travel_title);

	// 根据标题查询旅游信息
	@Query("select tr from Travel tr where travel_title = :travel_title and is_delete=0")
	public Travel findTravelByTitle(@Param("travel_title") String travel_title);
	// 查询总条数
//	@Query("select count(travel_id) from travel tr where is_delete=0")
//		Long countTotal(@Param("creator_id") Integer creator_id);

	// 根据公司查询旅游信息
	@Query("select tr from Travel tr where travel_firm = :travel_firm")
	public List<Travel> findTravelByFirm(@Param("travel_firm") Integer travelFirm);
	
	

}
