package com.mvc.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.dao.SmgoDao;
import com.mvc.entity.SmallGoods;
import com.mvc.service.SmgoService;

import net.sf.json.JSONObject;

@Service("/smgoServiceImpl")
public class SmgoServiceImpl implements SmgoService{
	
	@Autowired
	SmgoDao smgoDao;
	
	//初始化
	@Override
	public Integer countTotal() {
		// TODO 自动生成的方法存根
		return smgoDao.countTotal();
	}
	@Override
	public List<SmallGoods> findSmgoByPage(int offset, int limit) {
		// TODO 自动生成的方法存根
		return smgoDao.findSmgoByPage(offset,limit);
	}
	
	//sego限制
	@Override
	public Integer countSegoTotal(String smgoSego) {
		// TODO 自动生成的方法存根
		return smgoDao.countSegoTotal(smgoSego);
	}
	@Override
	public List<SmallGoods> findSmgoBySego(String smgoSego, int offset, int limit) {
		// TODO 自动生成的方法存根
		return smgoDao.findSmgoBySego(smgoSego,offset,limit);
	}
	
	//time限制
	@Override
	public Integer countTimeTotal(Date date1, Date date2) {
		// TODO 自动生成的方法存根
		return smgoDao.countTimeTotal(date1,date2);
	}
	@Override
	public List<SmallGoods> findSmgoByTime(Date date1, Date date2, int offset, int limit) {
		// TODO 自动生成的方法存根
		return smgoDao.findSmgoByTime( date1,  date2,  offset,  limit);
	}
	//sego、time限制
	@Override
	public Integer countTotalSG(String smgoSego, Date date1, Date date2) {
		// TODO 自动生成的方法存根
		return smgoDao.countTotalSG( smgoSego,  date1,  date2);
	}
	@Override
	public List<SmallGoods> findSmgoBySG(String smgoSego, Date date1, Date date2, int offset, int limit) {
		// TODO 自动生成的方法存根
		return smgoDao.findSmgoBySG( smgoSego,  date1,  date2,  offset,  limit);
	}
	//根据id删除smgo信息
	@Override
	public boolean deleteIsdelete(Integer smgo_id) {
		// TODO 自动生成的方法存根
		return smgoDao.updateState(smgo_id);
	}

	//补录edit信息
	@Override
	public boolean update(Date edittime, float editprice, Integer smgoid) {
		// TODO 自动生成的方法存根
		return smgoDao.updateEdit(edittime, editprice,smgoid);
	}
	
}
