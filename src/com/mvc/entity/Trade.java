package com.mvc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="trade")
public class Trade {
	private Integer trade_id;//交易ID
	private String trade_num;//交易号
	private Float trade_state;//交易状态（0：没有确认支付；1确认支付）
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "trade_id",unique = true, nullable = false, length = 10)
	public Integer getTrade_id() {
		return trade_id;
	}
	public void setTrade_id(Integer trade_id) {
		this.trade_id = trade_id;
	}
	@Column(name = "trade_num", length = 32)
	public String getTrade_num() {
		return trade_num;
	}
	public void setTrade_num(String trade_num) {
		this.trade_num = trade_num;
	}
	@Column(name = "trade_state", length = 11)
	public Float getTrade_state() {
		return trade_state;
	}
	public void setTrade_state(Float trade_state) {
		this.trade_state = trade_state;
	}
}
