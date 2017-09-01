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
 * @Description: TODO
 * @author ycj
 * @date 2017年8月31日 下午3:44:03 
 * 
 *
 */
@Service("adServiceImpl")
public  class AdServiceImpl implements AdService {
	@Autowired
	AdRepository adRepository;
	@Autowired
	AdDao adDao;
	
	//查询同公司总条数
	@Override
	public Integer countTotal(String searchKey) {
	    return adDao.countTotal(searchKey);
	}
	
	//根据页数筛选全部旅游信息列表
	@Override
	public List<Ad> findAdByPage(String searchKey, Integer offset, Integer end) {
		return adDao.findAdByPage(searchKey, offset, end);
	}
	
	//广告修改(查询)
	@Override
	public Ad saveAd(Ad ad) {
		Ad result = adRepository.saveAndFlush(ad);//这块可能会有问题，没有实现
		if (result.getAd_id() != null)
			return result ;
		else
			return null ;
	}
	
	//返回相应类型广告
	@Override
	public List<Ad> findAdByType(Integer adType) {
		return adRepository.findAdByType(adType);
	}
	
	//返回相应状态ad
	@Override
	public List<Ad> findAdByState(Integer adState){
		return adRepository.findAdByState(adState);
	}
	
	//类型为空返回全部ad
	@Override
	public List<Ad> findAdAlls() {
		return adRepository.findAlls();
	}
	
	//根据id删除ad
	@Override
	public Boolean deleteAd(Integer adId) {
		return adDao.deleteAd(adId);
	}
	
	//根据id查询ad
	@Override
	public Ad selectAdInfo(String adId) {
		int adid = Integer.parseInt(adId);
		return adRepository.findAdById(adid);
	}
	
	
	
	
	
	
	
	
	
	
	
	// 根据id删除
//	@Override
//	public boolean deleteIsdelete(Integer ad_id) {
//		return adDao.updateState(ad_id);
//	}
	
	// 根据标题查询旅游信息是否存在,返回1存在，返回0不存在
//	@Override
//	public Long isExist(String adTitle) {
//		Long result = adRepository.countByAdTitle(adTitle);
//		return result;
//	}
	
	// 根据合同ID获取合同
//	@Override
//	public Ad selectAdById(Integer ad_id) {
//	    return adRepository.selectAdById(ad_id);
//	}
	
	//根据title获取信息
//	@Override
//	public List<Ad> selectAdByTitle(String aTitle, Integer offset, Integer end){
//		return adDao.selectAdByTitle(aTitle, offset, end);
//	}
	
	// 修改旅游基本信息
//	@Override
//	public Boolean updateAdBase(Integer ad_id, JSONObject jsonObject, User user) throws ParseException {
//		Ad ad = adRepository.selectAdById(ad_id);
//		if (ad != null) {
//			if (jsonObject.containsKey("ad_title")) {
//					ad.setAd_title(jsonObject.getString("ad_title"));
//			}
//			if (jsonObject.containsKey("ad_content")) {
//					ad.setAd_content(jsonObject.getString("ad_content"));
//			}
//			if (jsonObject.containsKey("ad_type")) {
//					ad.setAd_type(Integer.getInteger(jsonObject.getString("ad_type")));
//			}
//			if (jsonObject.containsKey("ad_state")) {
//					ad.setAd_state(Integer.getInteger(jsonObject.getString("ad_state")));
//			}
//			if (jsonObject.containsKey("ad_name")) {
//					ad.setAd_name((jsonObject.getString("ad_name")));
//			}
//			if (jsonObject.containsKey("ad_tel")) {
//					ad.setAd_tel(jsonObject.getString("ad_tel"));
//			}
//			if (jsonObject.containsKey("ad_remark")) {
//					ad.setAd_remark((jsonObject.getString("ad_remark")));
//			}
//		}
//		ad = adRepository.saveAndFlush(ad);  //saveAndFlush没找到实现方法
//		if (ad.getAd_id() != null)
//			return true;
//		else
//			return false;
//		}
	
	// 根据ID查询
//	@Override
//	public Ad findAdById(Integer ad_id) {
//		return adRepository.findAdById(ad_id);
//	}
}