package com.mvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.dao.AdDao;
import com.mvc.entity.Ad;
import com.mvc.repository.AdRepository;
import com.mvc.service.AdService;

import net.sf.json.JSONObject;

/**
 * 
 * @ClassName: AdServiceImpl
 * @Description: ad
 * @author ycj
 * @date 2017年9月6日 上午9:44:22 
 * 
 *
 */

@Service("adServiceImpl")
public  class AdServiceImpl implements AdService {
	@Autowired
	AdRepository adRepository;
	@Autowired
	AdDao adDao;

	//查询全部ad信息
	@Override
	public Integer countTotal(String searchKey) {
		// TODO 自动生成的方法存根
		return adDao.countTotal(searchKey);
	}
	//根据页数筛选全部旅游信息列表
	@Override
	public List<Ad> findAdByPage(String searchKey, int offset, int end) {
		// TODO 自动生成的方法存根
		return adDao.findAdByPage(searchKey, offset, end);
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
	//根据id获取ad信息
	@Override
	public Ad selectAdById(int ad_id) {
		// TODO 自动生成的方法存根
		return adRepository.selectAdById(ad_id);
	}

	//state为null时返回全部ad信息
	@Override
	public List<Ad> findAlls(){
		// TODO 自动生成的方法存根
		return adRepository.findAlls();
	}

	//按照state获得总页数
	@Override
	public Integer countStateTotal(String adState) {
		// TODO 自动生成的方法存根
		return adDao.countStateTotal(adState);
	}

	//根据state、page筛选ad信息
	@Override
	public List<Ad> findAdByStatePage(String adState, int offset, int limit) {
		// TODO 自动生成的方法存根
		return adDao.findAdByStatePage(adState, offset, limit);
	}

}
