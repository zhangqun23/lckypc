package com.mvc.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.dao.SmgoDao;
import com.mvc.entity.Smgo;
import com.mvc.service.SmgoService;

@Service("/smgoServiceImpl")
public class SmgoServiceImpl implements SmgoService{
	
	@Autowired
	SmgoDao smgoDao;
	
	//查询全部smgo信息
	@Override
	public Integer countTotal(String searchKey) {
		// TODO 自动生成的方法存根
		return smgoDao.countTotal(searchKey);
	}
	
	//根据页数筛选smgo信息
	@Override
	public List<Smgo> findSmgoByPage(String searchKey, int offset, int end) {
		// TODO 自动生成的方法存根
		return smgoDao.findSmgoByPage(searchKey, offset, end);
	}
	
	//根据id删除smgo信息
	@Override
	public boolean deleteIsdelete(Integer smgo_id) {
		// TODO 自动生成的方法存根
		return smgoDao.updateState(smgo_id);
	}

	//根据sego获取smgo总信息
	@Override
	public Integer countSegoTotal(String smgoSego) {
		// TODO 自动生成的方法存根
		return smgoDao.countSegoTotal(smgoSego);
	}

	//根据sego筛选smgo信息
	@Override
	public List<Smgo> findSmgoBySego(String smgoSego, int offset, int limit) {
		// TODO 自动生成的方法存根
		return smgoDao.findSmgoBySego(smgoSego,offset,limit);
	}

	//补录edit信息
	@Override
	public boolean update(Date edittime, float editprice, Integer smgoid) {
		// TODO 自动生成的方法存根
		return smgoDao.updateEdit(edittime, editprice,smgoid);
	}
}
