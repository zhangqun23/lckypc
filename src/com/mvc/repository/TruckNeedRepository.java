package com.mvc.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mvc.entity.TruckNeed;

/**
 * 货主信息录入
 * @author ghl
 * @date   2017年9月6日
 */
public interface TruckNeedRepository extends JpaRepository<TruckNeed, Integer> {
	//根据truckNeedId查询truckNeed详情
	@Query("select t from TruckNeed t where trne_id = :trne_id  ")
	TruckNeed findTruckNeedInfo(@Param("trne_id")Integer trneId);
}
