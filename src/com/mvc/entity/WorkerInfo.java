package com.mvc.entity;
/*
 * 工作人员信息表
 * zq
 * */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="worker_info")
public class WorkerInfo {
private Integer woin_id;//工作人员id
private String woin_num;//工作人员登录账号
private String woin_pwd;//密码
private String woin_name;//姓名
private Integer woin_permission;//角色权限
private Integer woin_state;//是否删除
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
public Integer getWoin_id() {
	return woin_id;
}
public void setWoin_id(Integer woin_id) {
	this.woin_id = woin_id;
}
@Column(columnDefinition = "INT not null default 0")
public Integer getWoin_permission() {
	return woin_permission;
}
public void setWoin_permission(Integer woin_permission) {
	this.woin_permission = woin_permission;
}
@Column(length=16)
public String getWoin_num() {
	return woin_num;
}
public void setWoin_num(String woin_num) {
	this.woin_num = woin_num;
}
@Column(length=16)
public String getWoin_pwd() {
	return woin_pwd;
}
public void setWoin_pwd(String woin_pwd) {
	this.woin_pwd = woin_pwd;
}
@Column(length=64)
public String getWoin_name() {
	return woin_name;
}
public void setWoin_name(String woin_name) {
	this.woin_name = woin_name;
}
@Column(columnDefinition = "INT not null default 0")
public Integer getWoin_state() {
	return woin_state;
}
public void setWoin_state(Integer woin_state) {
	this.woin_state = woin_state;
}

}
