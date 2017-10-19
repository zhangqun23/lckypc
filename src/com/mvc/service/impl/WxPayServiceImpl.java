package com.mvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.repository.WxPayRepository;
import com.mvc.service.WxPayService;

@Service("wxPayServiceImpl")
public class WxPayServiceImpl implements WxPayService{
	
	@Autowired
	WxPayRepository wxPayRepository;
	
	@Override	
	public boolean updateTradeState(String trade_num){
		return wxPayRepository.updateTradeState(trade_num);
	}
}
