package com.mvc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mvc.entity.BusTrade;
import com.mvc.entity.Travel;


/**
 * 班车信息管理
 * 
 * @author wdh
 * @date 2017年9月4日
 */
public interface BusTradeRepository extends JpaRepository<BusTrade, Integer> {
	
//	// 根据ID查询班车信息
//	@Query("select bt from BusTrade bt where bune_id = :bune_id")
//	public BusTrade findBusTradeById(@Param("butr_id") Integer butr_id);
//	
	
	//根据ID获取信息
//	@Query("select br from BusNeed LEFT JOIN BusTrade ON BusTrade.bune_id=BusNeed.bune_id")
	
	@Query("select br from BusTrade br where bune_id=:bune_id ")		
	BusTrade selectBusTradeById(@Param("bune_id") Integer bune_id);

}
