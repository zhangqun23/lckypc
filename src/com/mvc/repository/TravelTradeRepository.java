package com.mvc.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.mvc.entity.TravelTrade;

/**
 * 旅游信息管理
 * 
 * @author wdh
 * @date 2017年8月11日
 */
public interface TravelTradeRepository extends JpaRepository<TravelTrade, Integer> 
{
	
	
}
