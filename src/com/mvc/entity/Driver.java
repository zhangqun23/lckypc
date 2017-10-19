package com.mvc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="driver")
public class Driver {
private Integer driver_id;//司机ID,主键
private String driver_name;//司机姓名
private String driver_job;//司机工作
private String driver_tel;//司机联系方式
private String driver_idcard;//司机身份证号
private String driver_license_number;//司机驾驶证号
private String driver_license;//司机驾驶证照
private String driver_image;//司机照片
private String driver_car;//汽车照片
private Integer is_audit;//0代表未审核，1代表已审核
private String open_id;//openid

@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column(name = "driver_id",unique = true, nullable = false, length = 11)
public Integer getDriver_id() {
	return driver_id;
}
public void setDriver_id(Integer driver_id) {
	this.driver_id = driver_id;
}

@Column(name = "driver_name", length = 36)
public String getDriver_name() {
	return driver_name;
}
public void setDriver_name(String driver_name) {
	this.driver_name = driver_name;
}

@Column(name = "driver_job", length = 36)
public String getDriver_job() {
	return driver_job;
}
public void setDriver_job(String driver_job) {
	this.driver_job = driver_job;
}

@Column(name = "driver_tel", length = 36)
public String getDriver_tel() {
	return driver_tel;
}
public void setDriver_tel(String driver_tel) {
	this.driver_tel = driver_tel;
}

@Column(name = "driver_idcard", length = 36)
public String getDriver_idcard() {
	return driver_idcard;
}
public void setDriver_idcard(String driver_idcard) {
	this.driver_idcard = driver_idcard;
}


@Column(name = "driver_license_number",length = 36)
public String getDriver_license_number() {
	return driver_license_number;
}
public void setDriver_license_number(String driver_license_number) {
	this.driver_license_number = driver_license_number;
}
@Column(name = "driver_license", length = 36)
public String getDriver_license() {
	return driver_license;
}
public void setDriver_license(String driver_license) {
	this.driver_license = driver_license;
}

@Column(name = "driver_image", length = 36)
public String driver_image() {
	return driver_image;
}
public void setDriver_image(String driver_image) {
	this.driver_image = driver_image;
}

@Column(name = "driver_car", length = 36)
public String getDriver_car() {
	return driver_car;
}
public void setDriver_car(String driver_car) {
	this.driver_car = driver_car;
}

@Column(name = "is_audit", nullable = false)
public Integer getIs_audit() {
	return is_audit;
}
public void setIs_audit(Integer is_audit) {
	this.is_audit = is_audit;
}
@Column(name = "open_id",length = 128)
public String getOpen_id() {
	return open_id;
}
public void setOpen_id(String open_id) {
	this.open_id = open_id;
}

}
