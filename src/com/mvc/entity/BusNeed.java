package com.mvc.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author wanghuimin
 * @date 2017年8月9日
 */
@Entity
@Table(name = "bus_need")
public class BusNeed implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer bune_id;// 主键
	private String bune_tel;// 联系电话
	private Integer bune_num;// 乘车人数
	private Float bune_time;// 用车时长
	private Date bune_gath_time;// 集合时间
	private String bune_gath_pla;// 集合地点
	private String bune_goal_pla;// 目的地点
	private String bune_purp;// 包车用途
	private Date bune_insert_time;// 需求添加时间
	private String bune_remark;// 备注
	private Boolean is_delete;// 是否删除0表示未删除，1表示删除
	private Date butr_time;// 交易创建时间
	private Float butr_depo;// 押金
	private Float butr_money;// 交易金额
	private String bune_bus;// 车牌号
	private Integer bune_type;// 交易类型，0表示线上，1表示线下
	private Integer invoice_if;// 是否开发票，0:未开1:已开
	private String invoice_num;// 发票号
	private Integer butr_state;// 交易状态，0表示待交易，1表示已交易
	private String open_id;// 微信用户

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getBune_id() {
		return bune_id;
	}

	public void setBune_id(Integer bune_id) {
		this.bune_id = bune_id;
	}

	@Column(length = 16)
	public String getBune_tel() {
		return bune_tel;
	}

	public void setBune_tel(String bune_tel) {
		this.bune_tel = bune_tel;
	}

	@Column(columnDefinition = "INT not null default 0")
	public Integer getBune_num() {
		return bune_num;
	}

	public void setBune_num(Integer bune_num) {
		this.bune_num = bune_num;
	}

	@Column(columnDefinition = "float(10,1) not null default '0.0'")
	public Float getBune_time() {
		return bune_time;
	}

	public void setBune_time(Float bune_time) {
		this.bune_time = bune_time;
	}

	public Date getBune_gath_time() {
		return bune_gath_time;
	}

	public void setBune_gath_time(Date bune_gath_time) {
		this.bune_gath_time = bune_gath_time;
	}

	@Column(length = 32)
	public String getBune_gath_pla() {
		return bune_gath_pla;
	}

	public void setBune_gath_pla(String bune_gath_pla) {
		this.bune_gath_pla = bune_gath_pla;
	}

	@Column(length = 32)
	public String getBune_goal_pla() {
		return bune_goal_pla;
	}

	public void setBune_goal_pla(String bune_goal_pla) {
		this.bune_goal_pla = bune_goal_pla;
	}

	@Column(length = 255)
	public String getBune_purp() {
		return bune_purp;
	}

	public void setBune_purp(String bune_purp) {
		this.bune_purp = bune_purp;
	}

	@Column(length = 255)
	public String getBune_remark() {
		return bune_remark;
	}

	public void setBune_remark(String bune_remark) {
		this.bune_remark = bune_remark;
	}
	@Column(columnDefinition = "INT not null default 0")
	public Boolean getIs_delete() {
		return is_delete;
	}

	public void setIs_delete(Boolean is_delete) {
		this.is_delete = is_delete;
	}

	@Column(length = 128)
	public String getOpen_id() {
		return open_id;
	}

	public void setOpen_id(String open_id) {
		this.open_id = open_id;
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

	@Column(length = 64)
	public String getBune_bus() {
		return bune_bus;
	}

	public void setBune_bus(String bune_bus) {
		this.bune_bus = bune_bus;
	}

	@Column(columnDefinition = "INT not null default 1")
	public Integer getBune_type() {
		return bune_type;
	}

	public void setBune_type(Integer bune_type) {
		this.bune_type = bune_type;
	}

	@Column(columnDefinition = "INT not null default 0")
	public Integer getInvoice_if() {
		return invoice_if;
	}

	public void setInvoice_if(Integer invoice_if) {
		this.invoice_if = invoice_if;
	}

	@Column(length = 32)
	public String getInvoice_num() {
		return invoice_num;
	}

	public void setInvoice_num(String invoice_num) {
		this.invoice_num = invoice_num;
	}

	@Column(columnDefinition = "INT not null default 0")
	public Integer getButr_state() {
		return butr_state;
	}

	public void setButr_state(Integer butr_state) {
		this.butr_state = butr_state;
	}

	public Date getBune_insert_time() {
		return bune_insert_time;
	}

	public void setBune_insert_time(Date bune_insert_time) {
		this.bune_insert_time = bune_insert_time;
	}
}
