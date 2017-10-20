package com.mvc.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mvc.entity.Travel;
import com.mvc.entity.TravelTrade;

/**
 * 旅游信息管理
 * 
 * @author wdh
 * @date 2017年8月11日
 */
public interface TravelTradeRepository extends JpaRepository<TravelTrade, Integer> 
{
	//根据ID获取旅游交易信息
			@Query("select trtr from TravelTrade trtr where trtr_id=:trtr_id ")
			TravelTrade selectTravelTradeById(@Param("trtr_id") Integer trtr_id);
	
}
