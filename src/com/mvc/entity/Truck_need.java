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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="truck_need")
public class Truck_need {
private Integer trne_id;//货车需求id，主键
private String trne_name;//联系人
private String trne_tel;//联系电话
private String trne_type;//货物类型
private Float trne_weight;//货物重量
private String trne_splace;//始发地
private String trne_eplace;//目的地
private Date trne_time;//需求时间
private Integer trne_check;//0代表未审核，1代表已审核
private String trne_remark;//备注
private Integer is_freeze;//0代表未冰冻，1代表冰冻
private User user;//外键

@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column(name = "trne_id",unique = true, nullable = false, length = 11)
public Integer getTrne_id() {
	return trne_id;
}
public void setTrne_id(Integer trne_id) {
	this.trne_id = trne_id;
}

@Column(name = "trne_name", length = 32)
public String getTrne_name() {
	return trne_name;
}
public void setTrne_name(String trne_name) {
	this.trne_name = trne_name;
}

@Column(name = "trne_tel", length = 32)
public String getTrne_tel() {
	return trne_tel;
}
public void setTrne_tel(String trne_tel) {
	this.trne_tel = trne_tel;
}

@Column(name = "trne_type", length = 16)
public String getTrne_type() {
	return trne_type;
}
public void setTrne_type(String trne_type) {
	this.trne_type = trne_type;
}

@Column(columnDefinition = "float(10,2) not null default '0.00'")
public Float getTrne_weight() {
	return trne_weight;
}
public void setTrne_weight(Float trne_weight) {
	this.trne_weight = trne_weight;
}

@Column(name = "trne_splace", length = 32)
public String getTrne_splace() {
	return trne_splace;
}
public void setTrne_splace(String trne_splace) {
	this.trne_splace = trne_splace;
}

@Column(name = "trne_eplace", length = 32)
public String getTrne_eplace() {
	return trne_eplace;
}
public void setTrne_eplace(String trne_eplace) {
	this.trne_eplace = trne_eplace;
}

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "trck_time")
public Date trne_time() {
	return trne_time;
}
public void setTrne_time(Date trne_time) {
	this.trne_time = trne_time;
}

@Column(columnDefinition = "INT not null default 0")
public Integer getTrne_check() {
	return trne_check;
}
public void setTrne_check(Integer trne_check) {
	this.trne_check = trne_check;
}

@Column(name = "trne_remark", length = 255)
public String getTrne_remark() {
	return trne_remark;
}
public void setTrne_remark(String trne_remark) {
	this.trne_remark = trne_remark;
}

@Column(name = "is_freeze", nullable = false)
public Integer getIs_freeze() {
	return is_freeze;
}
public void setIs_freeze(Integer is_freeze) {
	this.is_freeze = is_freeze;
}

@ManyToOne
@JoinColumn(name="user_id")
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
}
