package com.mvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.dao.AdDao;
import com.mvc.entity.Ad;
import com.mvc.repository.AdRepository;
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
	
	//根据限制条件筛选信息 
	@Override
	public List<Ad> findAdByPage(String adState, String adType, Integer offset, Integer limit) {
		// TODO 自动生成的方法存根
		return adDao.findAdByPage(adState,  adType,  offset,  limit);
	}
	@Override
	public Integer countTotal(String adState, String adType) {
		// TODO 自动生成的方法存根
		return adDao.countTotal( adState,  adType);
	}	
	
	//根据id删除ad信息
	@Override
	public boolean deleteIsdelete(Integer ad_id) {
		// TODO 自动生成的方法存根
		return adDao.updateState(ad_id);
	}

	//审核
	@Override
	public boolean editState(Integer ad_id , String adState) {
		// TODO 自动生成的方法存根
		return adDao.editState(ad_id , adState);
	}
	
}