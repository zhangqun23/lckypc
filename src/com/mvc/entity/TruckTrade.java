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
@Table(name = "truck_trade")
public class TruckTrade {
	private Integer trtr_id;// 货车交易id，主键
	private Float trtr_money;// 交易金额
	private Date trtr_date;// 交易时间
	private String trtr_evaluate;// 评价
	private Integer is_over;// 0代表交易中，1代表交易结束
	private Integer is_evaluate;// 0代表未评价，1代表已评价
	private Truck truck;// 外键
	private TruckNeed trne;// 外键

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "trtr_id", unique = true, nullable = false, length = 11)
	public Integer getTrtr_id() {
		return trtr_id;
	}

	public void setTrtr_id(Integer trtr_id) {
		this.trtr_id = trtr_id;
	}

	@Column(columnDefinition = "float(10,1) not null default '0.0'")
	public Float getTrtr_money() {
		return trtr_money;
	}

	public void setTrtr_money(Float trtr_money) {
		this.trtr_money = trtr_money;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "trtr_date")
	public Date getTrtr_date() {
		return trtr_date;
	}

	public void setTrtr_date(Date trtr_date) {
		this.trtr_date = trtr_date;
	}

	@Column(name = "trtr_evaluate", length = 256)
	public String getTrtr_evaluate() {
		return trtr_evaluate;
	}

	public void setTrtr_evaluate(String trtr_evaluate) {
		this.trtr_evaluate = trtr_evaluate;
	}

	@Column(name = "is_over", length = 11)
	public Integer getIs_over() {
		return is_over;
	}

	public void setIs_over(Integer is_over) {
		this.is_over = is_over;
	}

	@Column(name = "is_evaluate", length = 11)
	public Integer getIs_evaluate() {
		return is_evaluate;
	}

	public void setIs_evaluate(Integer is_evaluate) {
		this.is_evaluate = is_evaluate;
	}

	@ManyToOne
	@JoinColumn(name = "truck_id")
	public Truck getTruck() {
		return truck;
	}

	public void setTruck(Truck truck) {
		this.truck = truck;
	}

	@ManyToOne
	@JoinColumn(name = "trne_id")
	public TruckNeed getTrne() {
		return trne;
	}

	public void setTrne(TruckNeed trne) {
		this.trne = trne;
	}
}
