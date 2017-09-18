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
private String trtr_tel;//客户联系方式
private Float trtr_price;//交易额
private Integer trtr_mnum;//购买的成人票数
private Integer trtr_cnum;//购买的儿童票数
private Travel travel_id;//外键
private Integer is_state;//付款状态
private String open_id;//外键
private Float travel_discount;//折扣
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
@Column(columnDefinition = "float(10,1) not null default '0.0'")
public Float getTrtr_price() {
	return trtr_price;
}
public void setTrtr_price(Float trtr_price) {
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
@JoinColumn(name="travel_id")
public Travel getTravel_id() {
	return travel_id;
}
public void setTravel_id(Travel travel_id) {
	this.travel_id = travel_id;
}
public Integer getIs_state() {
	return is_state;
}
public void setIs_state(Integer is_state) {
	this.is_state = is_state;
}
public String getOpen_id() {
	return open_id;
}
public void setOpen_id(String open_id) {
	this.open_id = open_id;
}
@Column(columnDefinition = "float(10,2) not null default '0.00'")
public Float getTravel_discount() {
	return travel_discount;
}
public void setTravel_discount(Float travel_discount) {
	this.travel_discount = travel_discount;
}
}
