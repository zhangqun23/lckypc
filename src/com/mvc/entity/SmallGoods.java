package com.mvc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="small_goods")
public class SmallGoods {
private Integer smgo_id;//主键
private String  smgo_name;//货物名称
private Float smgo_weight;//重量
private String smgo_start;//始发地
private String smgo_end;//目的地
private String smgo_sender;//发件人
private String smgo_sender_tel;//发件人联系方式
private String smgo_receiver;//收件人
private String smgo_receiver_tel;//收件人联系方式
private Float amgo_money;//交易金额
private Date smgo_deal_time;//交易时间
private Date smgo_send_time;//发件日期
private String smgo_remark;//备注
private Boolean is_delete;//是否删除
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
public Integer getSmgo_id() {
	return smgo_id;
}
public void setSmgo_id(Integer smgo_id) {
	this.smgo_id = smgo_id;
}
@Column(length=32)
public String getSmgo_name() {
	return smgo_name;
}
public void setSmgo_name(String smgo_name) {
	this.smgo_name = smgo_name;
}
@Column(columnDefinition = "float(10,1) not null default '0.0'")
public Float getSmgo_weight() {
	return smgo_weight;
}
public void setSmgo_weight(Float smgo_weight) {
	this.smgo_weight = smgo_weight;
}
@Column(length=32)
public String getSmgo_start() {
	return smgo_start;
}
public void setSmgo_start(String smgo_start) {
	this.smgo_start = smgo_start;
}
@Column(length=32)
public String getSmgo_end() {
	return smgo_end;
}
public void setSmgo_end(String smgo_end) {
	this.smgo_end = smgo_end;
}
@Column(length=32)
public String getSmgo_sender() {
	return smgo_sender;
}
public void setSmgo_sender(String smgo_sender) {
	this.smgo_sender = smgo_sender;
}
@Column(length=16)
public String getSmgo_sender_tel() {
	return smgo_sender_tel;
}
public void setSmgo_sender_tel(String smgo_sender_tel) {
	this.smgo_sender_tel = smgo_sender_tel;
}
@Column(length=32)
public String getSmgo_receiver() {
	return smgo_receiver;
}
public void setSmgo_receiver(String smgo_receiver) {
	this.smgo_receiver = smgo_receiver;
}
@Column(length=16)
public String getSmgo_receiver_tel() {
	return smgo_receiver_tel;
}
public void setSmgo_receiver_tel(String smgo_receiver_tel) {
	this.smgo_receiver_tel = smgo_receiver_tel;
}
@Column(columnDefinition = "float(10,1) not null default '0.0'")
public Float getAmgo_money() {
	return amgo_money;
}
public void setAmgo_money(Float amgo_money) {
	this.amgo_money = amgo_money;
}
public Date getSmgo_deal_time() {
	return smgo_deal_time;
}
public void setSmgo_deal_time(Date smgo_deal_time) {
	this.smgo_deal_time = smgo_deal_time;
}
public Date getSmgo_send_time() {
	return smgo_send_time;
}
public void setSmgo_send_time(Date smgo_send_time) {
	this.smgo_send_time = smgo_send_time;
}
@Column(length=256)
public String getSmgo_remark() {
	return smgo_remark;
}
public void setSmgo_remark(String smgo_remark) {
	this.smgo_remark = smgo_remark;
}
public Boolean getIs_delete() {
	return is_delete;
}
public void setIs_delete(Boolean is_delete) {
	this.is_delete = is_delete;
}


}
