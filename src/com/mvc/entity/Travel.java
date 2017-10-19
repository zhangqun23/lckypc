package com.mvc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



/*
 * 旅游信息表
 * zq
 * */
@Entity
@Table(name="travel")
public class Travel{
private Integer travel_id;//旅游信息Id
private String travel_title;//标题
private String travel_content;//活动描述
private String travel_route;//路线
private Float travel_mprice;//成人票价格
private Float travel_cprice;//儿童票价格
private Float travel_insurance;//保险费
private Float travel_discount;//折扣
private Date travel_stime;//出发时间
private String travel_location;//出发地点
private Float travel_days;//游玩天数
private String travel_tel;//联系电话
private Integer travel_total_num;//总人数
private Integer travel_left_num;//剩余人数
private String travel_firm;//旅游承办公司
private boolean is_delete;//信息是否删除:0表示未删除，1表示删除
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
public Integer getTravel_id() {
	return travel_id;
}
public void setTravel_id(Integer travel_id) {
	this.travel_id = travel_id;
}
@Column(length=125)
public String getTravel_title() {
	return travel_title;
}
public void setTravel_title(String travel_title) {
	this.travel_title = travel_title;
}
@Column(name="travel_content")
public String getTravel_content() {
	return travel_content;
}
public void setTravel_content(String travel_content) {
	this.travel_content = travel_content;
}
@Column(length=64)
public String getTravel_route() {
	return travel_route;
}
public void setTravel_route(String travel_route) {
	this.travel_route = travel_route;
}
@Column(columnDefinition = "float(10,2) not null default '0.00'")
public Float getTravel_mprice() {
	return travel_mprice;
}
public void setTravel_mprice(Float travel_mprice) {
	this.travel_mprice = travel_mprice;
}
@Column(columnDefinition = "float(10,2) not null default '0.00'")
public Float getTravel_cprice() {
	return travel_cprice;
}
public void setTravel_cprice(Float travel_cprice) {
	this.travel_cprice = travel_cprice;
}
@Column(columnDefinition = "float(10,2) not null default '0.00'")
public Float getTravel_insurance() {
	return travel_insurance;
}
public void setTravel_insurance(Float travel_insurance) {
	this.travel_insurance = travel_insurance;
}
@Column(columnDefinition = "float(10,2) not null default '0.00'")
public Float getTravel_discount() {
	return travel_discount;
}
public void setTravel_discount(Float travel_discount) {
	this.travel_discount = travel_discount;
}

public Date getTravel_stime() {
	return travel_stime;
}
public void setTravel_stime(Date travel_stime) {
	this.travel_stime = travel_stime;
}
@Column(length=32)
public String getTravel_location() {
	return travel_location;
}
public void setTravel_location(String travel_location) {
	this.travel_location = travel_location;
}
@Column(columnDefinition = "float(10,1) not null default '0.0'")
public Float getTravel_days() {
	return travel_days;
}
public void setTravel_days(Float travel_days) {
	this.travel_days = travel_days;
}
@Column(length=32)
public String getTravel_tel() {
	return travel_tel;
}
public void setTravel_tel(String travel_tel) {
	this.travel_tel = travel_tel;
}
@Column(columnDefinition = "INT not null default 0")
public Integer getTravel_total_num() {
	return travel_total_num;
}
public void setTravel_total_num(Integer travel_total_num) {
	this.travel_total_num = travel_total_num;
}
@Column(columnDefinition = "INT not null default 0")
public Integer getTravel_left_num() {
	return travel_left_num;
}
public void setTravel_left_num(Integer travel_left_num) {
	this.travel_left_num = travel_left_num;
}
@Column(length=32)
public String getTravel_firm() {
	return travel_firm;
}
public void setTravel_firm(String travel_firm) {
	this.travel_firm = travel_firm;
}

public Boolean getIs_delete() {
	return is_delete;
}
public void setIs_delete(Boolean is_delete) {
	this.is_delete = is_delete;
}
}