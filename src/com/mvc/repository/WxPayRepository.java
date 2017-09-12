package com.mvc.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WxPayRepository {

	@Modifying
	@Query("update trade set trade_state = 1 where trade_num = :trade_num")
	public boolean updateTradeState(@Param("trade_num") String trade_num);
}
