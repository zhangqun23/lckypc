package com.mvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.dao.WorkerInfoDao;
import com.mvc.entity.WorkerInfo;
import com.mvc.repository.WorkerInfoRepository;
import com.mvc.service.WorkerInfoService;

/**
 * WorkerInfo相关Service层接口实现
 * 
 * @author wdh
 * @date 2017年8月28日
 */

@Service("workerInfoServiceImpl")
public class WorkerInfoServiceImpl implements WorkerInfoService {
	@Autowired
	WorkerInfoRepository workerInfoRepository;
	@Autowired
	WorkerInfoDao workerInfoDao;

	/**
	 * 添加用户, 修改用户信息
	 */
	public boolean save(WorkerInfo workerInfo) {
		WorkerInfo result = workerInfoRepository.saveAndFlush(workerInfo);
		if (result.getWoin_id() != null)
			return true;
		else
			return false;
	}

	// 根据Num查询用户账号是否存在,返回1存在，返回0不存在
	public Long isExist(String userNum) {
		Long result = workerInfoRepository.countByWoinNum(userNum);
		return result;
	}

	// 根据Num查询用户信息
	public WorkerInfo findByUserNum(String woin_num) {
		return workerInfoRepository.findByWoinNum(woin_num);
	}

	// 查询所有用户列表
	public List<WorkerInfo> findAll() {
		return workerInfoRepository.findWoinAlls();
	}

	// 查询总条数
	@Override
	public Integer countTotal(String searchKey) {
		return workerInfoDao.countTotal(searchKey);
	}

	// 根据页数筛选全部用户列表
	@Override
	public List<WorkerInfo> findWoinAllByPage(String searchKey, Integer offset, Integer end) {
		return workerInfoDao.findWoinAllByPage(searchKey, offset, end);
	}

	
	// 根据id删除
	@Override
	public boolean deleteIsdelete(Integer woin_id) {
		return workerInfoDao.updateState(woin_id);
	}

	// 根据ID查询用户信息
	@Override
	public WorkerInfo findWoinById(Integer woin_id) {
		return workerInfoRepository.findWoinById(woin_id);
	}

	

}
