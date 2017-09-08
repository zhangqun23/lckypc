package com.base.enums;

/**
 * 
 * @ClassName: EditState
 * @Description: 变更state
 * @author ycj
 * @date 2017年9月7日 下午8:17:40 
 * 
 *
 */
public enum EditState {

	// 0:未审核；1：已审核
	NO(0), YES(1);
	
	public int value;
	
	private EditState(int value){
		this.value = value;
	}
}
