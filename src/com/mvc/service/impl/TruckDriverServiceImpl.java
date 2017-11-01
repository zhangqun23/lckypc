package com.mvc.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mvc.dao.TruckDriverDao;
import com.mvc.entity.Truck;
import com.mvc.entity.TruckNeed;
import com.mvc.entity.TruckSend;
import com.mvc.repository.TruckRepository;
import com.mvc.repository.TruckSendRepository;
import com.mvc.service.TruckDriverService;

/**
 * 零担货运
 * @author ghl
 * @date   2017年9月6日
 */
@Service("truckDriverServiceImpl")
public class TruckDriverServiceImpl implements TruckDriverService {
	@Autowired
	TruckDriverDao truckDriverDao;
	@Autowired
	TruckRepository truckRepository;

	/**
	 * Truck
	 */
	//查询Truck信息
	@Override
	public List<Truck> findTruck(String trState,Integer offset, Integer limit) {
		return truckDriverDao.findTruck(trState,offset,  limit);
	}
	//Truck信息模态框显示
	@Override
	public Boolean findTruckInfo(Integer trckId, Integer trState) {
		return truckDriverDao.findTruck(trckId,trState);
	}
	@Override
	public Truck findTruckList(Integer trckId) {
		// TODO Auto-generated method stub
		return truckRepository.findTruckList(trckId);
	}
	
	//删除Truck
	@Override
	public Boolean deleteTruck(Integer trckId) {
		return truckDriverDao.deleteTruck(trckId);
	}
	//根据条件获取总页数
	@Override
	public Integer countTotal(String trState) {
		// TODO Auto-generated method stub
		return truckDriverDao.countTotal( trState);
	}
	/**
	 * TruckSend
	 */
	//查询TruckSend
	@Override
	public List<TruckSend> getTruckSend(Integer offset, Integer limit) {
		return truckDriverDao.getTruckSend(offset, limit);
	}
	//获取总页数用来分页
	@Override
	public Integer countTotalPage() {
		// TODO Auto-generated method stub
		return truckDriverDao.countTotalPage();
	}
	/**
	 * TruckNee
	 */
	//获取总条数用来分页
	@Override
	public Integer TotalPage() {
		// TODO Auto-generated method stub
		return truckDriverDao.TotalPage();
	}
	//查询TruckNeed信息
	@Override
	public List<TruckNeed> getTruckNeed(Integer offset, Integer limit) {
		// TODO Auto-generated method stub
		return truckDriverDao.getTruckNeed(offset, limit);
	}






	
}
