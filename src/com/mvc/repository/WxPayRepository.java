package com.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mvc.entity.TravelTrade;


public interface WxPayRepository extends JpaRepository<TravelTrade, Integer> {

	@Modifying
	@Query("update TravelTrade set is_state = 0 , transaction_id= :transaction_id , trade_time = :dateFormat where trtr_num = :trade_num")
	public void updateTradeState(@Param("trade_num") String trade_num,
			@Param("transaction_id") String transaction_id, @Param("dateFormat") String dateFormat);

	@Query("select tt from TravelTrade tt where trtr_num = :out_trade_no")
	public TravelTrade getTotalNum(@Param("out_trade_no") String out_trade_no);

}
