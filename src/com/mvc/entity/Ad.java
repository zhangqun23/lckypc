package com.mvc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ad")
public class Ad {
private Integer ad_id;//广告id，主键
private Integer ad_type;//0招工，1旅游、2其他
private Integer ad_state;//0代表未审核，1代表已审核
private Integer user_id;//外键
private String ad_name;//联系人
private String ad_tel;//联系方式
private String ad_title;//广告名称
private String ad_pic_path;//广告图片路径
private String ad_remark;//广告备注
private String ad_content;//广告内容

@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column(name = "ad_id",unique = true, nullable = false, length = 10)
public Integer getAd_id() {
	return ad_id;
}
public void setAd_id(Integer ad_id) {
	this.ad_id = ad_id;
}

@Column(name = "ad_type", length = 10)
public Integer getAd_type() {
	return ad_type;
}
public void setAd_type(Integer ad_type) {
	this.ad_type = ad_type;
}

@Column(name = "ad_state", length = 11)
public Integer getAd_state() {
	return ad_state;
}
public void setAd_state(Integer ad_state) {
	this.ad_state = ad_state;
}

@Column(name = "user_id")
public Integer getUser_id() {
	return user_id;
}
public void setUser_id(Integer user_id) {
	this.user_id = user_id;
}

@Column(name = "ad_name", length = 32)
public String getAd_name() {
	return ad_name;
}
public void setAd_name(String ad_name) {
	this.ad_name = ad_name;
}

@Column(name = "ad_tel", length = 32)
public String getAd_tel() {
	return ad_tel;
}
public void setAd_tel(String ad_tel) {
	this.ad_tel = ad_tel;
}

@Column(name = "ad_title", length = 255)
public String getAd_title() {
	return ad_title;
}
public void setAd_title(String ad_title) {
	this.ad_title = ad_title;
}

@Column(name = "ad_pic_path", length = 255)
public String getAd_pic_path() {
	return ad_pic_path;
}
public void setAd_pic_path(String ad_pic_path) {
	this.ad_pic_path = ad_pic_path;
}

@Column(name = "ad_remark", length = 255)
public String getAd_remark() {
	return ad_remark;
}
public void setAd_remark(String ad_remark) {
	this.ad_remark = ad_remark;
}

@Column(name = "ad_content", length = 255)
public String getAd_content() {
	return ad_content;
}
public void setAd_content(String ad_content) {
	this.ad_content = ad_content;
}
}
