package com.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mvc.entity.TravelTrade;


public interface WxPayRepository extends JpaRepository<TravelTrade, Integer> {

	@Modifying
	@Query("update TravelTrade set is_state = 0 where trtr_num = :trade_num")
	public boolean updateTradeState(@Param("trade_num") String trade_num);
}
