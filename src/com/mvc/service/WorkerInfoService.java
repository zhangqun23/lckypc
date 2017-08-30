package com.mvc.service;

import java.util.List;
import com.mvc.entity.WorkerInfo;

/**
 * WorkerInfo相关Service层接口
 * 
 * @author wdh
 * @date 2017年8月28日
 */

public interface WorkerInfoService {

	// 添加用户,修改用户信息
	boolean save(WorkerInfo woin);

	// 根据woNum查询用户账号是否存在,返回1存在，返回0不存在
	Long isExist(String woinNum);

	
	// 根据id删除
	boolean deleteIsdelete(Integer woin_id);

	// 查询总条数
	Integer countTotal(String searchKey);

	// 根据页数筛选全部用户列表

	List<WorkerInfo> findWoinAllByPage(String searchKey, Integer offset, Integer end);

	// 根据ID查询用户信息
	WorkerInfo findWoinById(Integer woin_id);

	
}
