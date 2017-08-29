package com.mvc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="bus_trade")
public class BusTrade {
	
private Integer butr_id;//主键
private Date butr_time;//交易创建时间
private Float butr_depo;//押金
private Float butr_money;//交易金额
private String bune_bus;//车牌号
private Integer bune_type;//交易类型，0表示线上，1表示线下
private Integer invoice_if;//是否开发票，0:未开1:已开
private String invoice_num;//发票号
private  Integer butr_state;//交易状态，0表示交易中，1表示交易结束
private BusNeed busNeed;//班车需求fk
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
public Integer getButr_id() {
	return butr_id;
}
public void setButr_id(Integer butr_id) {
	this.butr_id = butr_id;
}
public Date getButr_time() {
	return butr_time;
}
public void setButr_time(Date butr_time) {
	this.butr_time = butr_time;
}
@Column(columnDefinition = "float(10,1) not null default '0.0'")
public Float getButr_depo() {
	return butr_depo;
}
public void setButr_depo(Float butr_depo) {
	this.butr_depo = butr_depo;
}
@Column(columnDefinition = "float(10,1) not null default '0.0'")
public Float getButr_money() {
	return butr_money;
}
public void setButr_money(Float butr_money) {
	this.butr_money = butr_money;
}
@Column(length=64)
public String getBune_bus() {
	return bune_bus;
}
public void setBune_bus(String bune_bus) {
	this.bune_bus = bune_bus;
}
@Column(columnDefinition="INT not null default 0")
public Integer getBune_type() {
	return bune_type;
}
public void setBune_type(Integer bune_type) {
	this.bune_type = bune_type;
}
@Column(columnDefinition="INT not null default 0")
public Integer getInvoice_if() {
	return invoice_if;
}
public void setInvoice_if(Integer invoice_if) {
	this.invoice_if = invoice_if;
}

@Column(length=32)
public String getInvoice_num() {
	return invoice_num;
}
public void setInvoice_num(String invoice_num) {
	this.invoice_num = invoice_num;
}
@Column(columnDefinition="INT not null default 0")
public Integer getButr_state() {
	return butr_state;
}
public void setButr_state(Integer butr_state) {
	this.butr_state = butr_state;
}
@ManyToOne
@JoinColumn(name="bune_id")
public BusNeed getBusNeed() {
	return busNeed;
}
public void setBusNeed(BusNeed busNeed) {
	this.busNeed = busNeed;
}
}
