package com.mvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.mvc.repository.WxPayRepository;

public class WxPayServiceImpl {
	
	@Autowired
	WxPayRepository wxPayRepository;
	
	boolean updateTradeState(String trade_num){
		return wxPayRepository.updateTradeState(trade_num);
	}
}
