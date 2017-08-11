/**
 * 
 */
package com.mvc.entity;

/**
 * 项目汇总表
 * 
 * @author zjn
 * @date 2016年12月9日
 */
public class Summary {

	private String order_num;// 序号
	private String province;// 所在省份
	private String stage0;// 0=规划
	private String stage1;// 1=预可研
	private String stage2;// 2=可研
	private String stage3;// 3=项目建议书
	private String stage4;// 4=初步设计
	private String stage5;// 5=发包、招标设计
	private String stage6;// 6=施工详图
	private String stage7;// 7=竣工图
	private String stage8;// 8=其他
	private String summation;// 合计

	public String getOrder_num() {
		return order_num;
	}

	public void setOrder_num(String order_num) {
		this.order_num = order_num;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getStage0() {
		return stage0;
	}

	public void setStage0(String stage0) {
		this.stage0 = stage0;
	}

	public String getStage1() {
		return stage1;
	}

	public void setStage1(String stage1) {
		this.stage1 = stage1;
	}

	public String getStage2() {
		return stage2;
	}

	public void setStage2(String stage2) {
		this.stage2 = stage2;
	}

	public String getStage3() {
		return stage3;
	}

	public void setStage3(String stage3) {
		this.stage3 = stage3;
	}

	public String getStage4() {
		return stage4;
	}

	public void setStage4(String stage4) {
		this.stage4 = stage4;
	}

	public String getStage5() {
		return stage5;
	}

	public void setStage5(String stage5) {
		this.stage5 = stage5;
	}

	public String getStage6() {
		return stage6;
	}

	public void setStage6(String stage6) {
		this.stage6 = stage6;
	}

	public String getStage7() {
		return stage7;
	}

	public void setStage7(String stage7) {
		this.stage7 = stage7;
	}

	public String getStage8() {
		return stage8;
	}

	public void setStage8(String stage8) {
		this.stage8 = stage8;
	}

	public String getSummation() {
		return summation;
	}

	public void setSummation(String summation) {
		this.summation = summation;
	}

}
