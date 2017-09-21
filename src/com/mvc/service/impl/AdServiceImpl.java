package com.mvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.dao.AdDao;
import com.mvc.entity.Ad;
import com.mvc.service.AdService;

/**
 * 
 * @ClassName: AdServiceImpl
 * @Description: ad
 * @author ycj
 * @date 2017年9月6日 上午9:44:22 
 * 
 *
 */
@Service("/adServiceImpl")
public class AdServiceImpl implements AdService{
	@Autowired
	AdDao adDao;

	//初始化
	@Override
	public Integer countTotal() {
		// TODO 自动生成的方法存根
		return adDao.countTotal();
	}
	@Override
	public List<Ad> findAdByPage(int offset, int limit) {
		// TODO 自动生成的方法存根
		return adDao.findAdByPage(offset,limit);
	}
	
	//state限制
	@Override
	public Integer countTotalS(String adState) {
		// TODO 自动生成的方法存根
		return adDao.countTotalS(adState);
	}
	@Override
	public List<Ad> findAdByState(String adState, int offset, int limit) {
		// TODO 自动生成的方法存根
		return adDao.findAdByState(adState,offset,limit);
	}

	//type限制
	@Override
	public Integer countTotalT(String adType) {
		// TODO 自动生成的方法存根
		return adDao.countTotalT(adType);
	}
	@Override
	public List<Ad> findAdByType(String adType, int offset, int limit) {
		// TODO 自动生成的方法存根
		return adDao.findAdByType(adType,offset,limit);
	}
	
	//state、type限制条件 
		@Override
		public List<Ad> findAdByST(String adState, String adType, int offset, int limit) {
			// TODO 自动生成的方法存根
			return adDao.findAdByST(adState,  adType,  offset,  limit);
		}
		@Override
		public Integer countTotal(String adState, String adType) {
			// TODO 自动生成的方法存根
			return adDao.countTotalST( adState,  adType);
		}	
		
	//根据id删除ad信息
	@Override
	public boolean deleteIsdelete(Integer ad_id) {
		// TODO 自动生成的方法存根
		return adDao.updateState(ad_id);
	}

	//根据id变更state
	@Override
	public boolean editState(Integer ad_id) {
		// TODO 自动生成的方法存根
		return adDao.editState(ad_id);
	}
	@Override
	public Integer countTotalST(String adState, String adType) {
		// TODO 自动生成的方法存根
		return null;
	}
	
}