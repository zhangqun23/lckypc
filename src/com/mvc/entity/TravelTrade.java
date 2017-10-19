package com.mvc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/*
 * 用户旅游交易表
 * zq
 * */
@Entity
@Table(name="travel_trade")
public class TravelTrade {
private Integer trtr_id;//交易ID
private String trtr_num;//商户订单号
private String trtr_tel;//客户联系方式
private Integer trtr_price;//交易额
private Integer trtr_mnum;//购买的成人票数
private Integer trtr_cnum;//购买的儿童票数
private Travel travel;//外键
private Integer is_state;//付款状态
private String open_id;//外键
private Float trade_discount;//折扣
private String trade_time;//交易时间
private String refund_time;//退款时间
private String transaction_id;//支付时微信生成的订单号，String(32)
private String refund_id;//退款时微信生成的单号，String(32)
private Integer refund_fee;//退款金额
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
public Integer getTrtr_id() {
	return trtr_id;
}
public void setTrtr_id(Integer trtr_id) {
	this.trtr_id = trtr_id;
}
@Column(length=16)
public String getTrtr_tel() {
	return trtr_tel;
}
public void setTrtr_tel(String trtr_tel) {
	this.trtr_tel = trtr_tel;
}
public Integer getTrtr_price() {
	return trtr_price;
}
public void setTrtr_price(Integer trtr_price) {
	this.trtr_price = trtr_price;
}
@Column(columnDefinition = "INT not null default 0")
public Integer getTrtr_mnum() {
	return trtr_mnum;
}
public void setTrtr_mnum(Integer trtr_mnum) {
	this.trtr_mnum = trtr_mnum;
}
@Column(columnDefinition = "INT not null default 0")
public Integer getTrtr_cnum() {
	return trtr_cnum;
}
public void setTrtr_cnum(Integer trtr_cnum) {
	this.trtr_cnum = trtr_cnum;
}
@ManyToOne
@JoinColumn(name="travel")
public Travel getTravel() {
	return travel;
}
public void setTravel(Travel travel) {
	this.travel = travel;
}
public String getOpen_id() {
	return open_id;
}
public void setOpen_id(String open_id) {
	this.open_id = open_id;
}
public String getTrtr_num() {
	return trtr_num;
}
public void setTrtr_num(String trtr_num) {
	this.trtr_num = trtr_num;
}
public Integer getIs_state() {
	return is_state;
}
public void setIs_state(Integer is_state) {
	this.is_state = is_state;
}
@Column(columnDefinition = "float(10,2)")
public Float getTrade_discount() {
	return trade_discount;
}
public void setTrade_discount(Float trade_discount) {
	this.trade_discount = trade_discount;
}
public String getTrade_time() {
	return trade_time;
}
public void setTrade_time(String trade_time) {
	this.trade_time = trade_time;
}
public String getRefund_time() {
	return refund_time;
}
public void setRefund_time(String refund_time) {
	this.refund_time = refund_time;
}
public String getTransaction_id() {
	return transaction_id;
}
public void setTransaction_id(String transaction_id) {
	this.transaction_id = transaction_id;
}
public Integer getRefund_fee() {
	return refund_fee;
}
public void setRefund_fee(Integer refund_fee) {
	this.refund_fee = refund_fee;
}
public String getRefund_id() {
	return refund_id;
}
public void setRefund_id(String refund_id) {
	this.refund_id = refund_id;
}
}
