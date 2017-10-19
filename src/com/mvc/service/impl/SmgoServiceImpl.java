package com.mvc.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.dao.SmgoDao;
import com.mvc.entity.SmallGoods;
import com.mvc.service.SmgoService;

@Service("/smgoServiceImpl")
public class SmgoServiceImpl implements SmgoService{
	@Autowired
	SmgoDao smgoDao;
	
	//根据限制条件筛选信息
	@Override
	public Integer countTotal(String smgoSego, Date startDate, Date endDate) {
		// TODO 自动生成的方法存根
		return smgoDao.countTotal( smgoSego,  startDate,  endDate);
	}
	@Override
	public List<SmallGoods> findSmgoByPage(String smgoSego, Date startDate, Date endDate, int offset, int limit) {
		// TODO 自动生成的方法存根
		return smgoDao.findSmgoByPage( smgoSego,  startDate,  endDate,  offset,  limit);
	}
	//根据id删除smgo信息
	@Override
	public boolean deleteIsdelete(Integer smgo_id) {
		// TODO 自动生成的方法存根
		return smgoDao.updateState(smgo_id);
	}

	//补录edit信息
	@Override
	public boolean update(String edittime, float editprice, Integer smgo_id) {
		// TODO 自动生成的方法存根
		return smgoDao.updateEdit(edittime, editprice,smgo_id);
	}
	
}
