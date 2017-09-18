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
@Table(name="truck_send")
public class TruckSend {
	private Integer trse_id;//主键
	private Float trse_left_load;//剩余载重
	private String trse_splace;//始发地（默认洛川）
	private String trse_eplace;//目的地
	private String trse_price;//价格
	private Date trse_time;//出发时间
	private Truck truck_id;//外键
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "trtr_id",unique = true, nullable = false, length = 11)
	public Integer getTrse_id() {
		return trse_id;
	}
	public void setTrse_id(Integer trse_id) {
		this.trse_id = trse_id;
	}
	
	@Column(columnDefinition = "float(10,2) not null default '0.00'")
	public Float getTrse_left_load() {
		return trse_left_load;
	}
	public void setTrse_left_load(Float trse_left_load) {
		this.trse_left_load = trse_left_load;
	}
	
	@Column(name = "trse_splace", length = 32)
	public String getTrse_splace() {
		return trse_splace;
	}
	public void setTrse_splace(String trse_splace) {
		this.trse_splace = trse_splace;
	}
	
	@Column(name = "trse_eplace", length = 32)
	public String getTrse_eplace() {
		return trse_eplace;
	}
	public void setTrse_eplace(String trse_eplace) {
		this.trse_eplace = trse_eplace;
	}
	
	@Column(name = "trse_price", length = 32)
	public String getTrse_price() {
		return trse_price;
	}
	public void setTrse_price(String trse_price) {
		this.trse_price = trse_price;
	}
	
	
	public Date getTrse_time() {
		return trse_time;
	}
	public void setTrse_time(Date trse_time) {
		this.trse_time = trse_time;
	}
	
	@ManyToOne
	@JoinColumn(name="truck_id")
	public Truck getTruck_id() {
		return truck_id;
	}
	public void setTruck_id(Truck truck_id) {
		this.truck_id = truck_id;
	}
	
}
