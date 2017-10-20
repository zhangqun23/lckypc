package com.mvc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mvc.entity.BusNeed;


/**
 * 班车信息管理
 * 
 * @author wdh
 * @date 2017年9月4日
 */
public interface BusNeedRepository extends JpaRepository<BusNeed, Integer> {
	
	// 根据ID查询班车信息
	@Query("select bn from BusNeed bn where bune_id = :bune_id")
	public BusNeed findBusNeedById(@Param("bune_id") Integer bune_id);

	// 根据ID查询全部信息
	@Query("select bn from BusNeed bn where is_delete=0 ")
	public List<BusNeed> findBusNeedAlls();

	// 根据id删除
	@Query("update BusNeed set is_delete=1 where bune_id = :bune_id")
	public boolean deleteByBusNeedId(@Param("bune_id") Integer bune_id);
	
	//根据ID获取信息
	@Query("select bn from BusNeed bn where bune_id=:bune_id and is_delete=0")
	BusNeed selectBusNeedById(@Param("bune_id") Integer bune_id);
	

}
