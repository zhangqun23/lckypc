package com.mvc.dao;

import java.util.List;

import com.mvc.entity.WorkerInfo;

/**
 * WorkerInfo相关Dao层接口
 * 
 * @author wdh
 * @date 2017年8月28日
 */
public interface WorkerInfoDao {

	// 根据用户id修改状态
	boolean updateState(Integer woin_id);

	// 根据页数筛选全部用户列表
	List<WorkerInfo> findWoinAllByPage(String searchKey, Integer offset, Integer end);

	// 查询用户总条数
	Integer countTotal(String searchKey);

}
