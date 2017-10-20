package com.mvc.service;

public interface WxPayService {

	void updateTradeState(String trade_num, String transaction_id, String dateFormat);

	void getTotalNum(String out_trade_no);
}
