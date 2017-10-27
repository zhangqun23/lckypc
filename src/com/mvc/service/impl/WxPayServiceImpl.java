package com.mvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.entity.Travel;
import com.mvc.entity.TravelTrade;
import com.mvc.repository.TravelRepository;
import com.mvc.repository.WxPayRepository;
import com.mvc.service.WxPayService;

@Service("wxPayServiceImpl")
public class WxPayServiceImpl implements WxPayService{
	
	@Autowired
	WxPayRepository wxPayRepository;
	@Autowired
	TravelRepository travelRepository;
	
	@Override	
	public void updateTradeState(String trade_num, String transaction_id, String dateFormat){
		wxPayRepository.updateTradeState(trade_num, transaction_id, dateFormat);
	}

	@Override
	public void getTotalNum(String out_trade_no) {
		TravelTrade traveltrade = wxPayRepository.getTotalNum(out_trade_no);
		Travel travel = traveltrade.getTravel();
		int travel_left_num = travel.getTravel_left_num();
		int mnun = traveltrade.getTrtr_mnum();
		int cnum = traveltrade.getTrtr_cnum();
		int left_num = travel_left_num - mnun - cnum;
		if(left_num <0){
			left_num = 0;
		}
		travelRepository.updateLeftNum(left_num,travel.getTravel_id());
	}
}
