package com.mvc.service.impl;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.dao.AdDao;
import com.mvc.entity.Ad;
import com.mvc.entity.User;
import com.mvc.repository.AdRepository;
import com.mvc.service.AdService;

import net.sf.json.JSONObject;

/**
 * 
 * @ClassName: AdServiceImpl
 * @Description: TODO
 * @author ycj
 * @date 2017年8月30日 上午10:46:45 
 * 
 *
 */
@Service("adServiceImpl")
public class AdServiceImpl implements AdService {
		@Autowired
		AdRepository adRepository;
		@Autowired
		AdDao adDao;

		// 根据id删除ad
		@Override
		public boolean deleteIsdelete(Integer ad_id) {
			return adDao.updateState(ad_id);
		}
		
		// 根据id获取ad
		@Override
		public Ad selectAdById(String adId) {
			int adid = Integer.parseInt(adId);
			return adRepository.selectAdById(adid);
		}
		
		@Override
		public Ad selectAdByState(String adState) {
			// TODO 自动生成的方法存根
			return adRepository.selectAdByState(adState);
		}
		
		// 根据id修改ad基本信息
		@Override
		public Boolean updateAdBase(Integer ad_id, JSONObject jsonObject, User user) throws ParseException {
			Ad ad = adRepository.selectAdById(ad_id);
			if (ad != null) {
				if (jsonObject.containsKey("ad_title")) {
					ad.setAd_title(jsonObject.getString("ad_title"));}
					if (jsonObject.containsKey("ad_content")) {
					ad.setAd_content(jsonObject.getString("ad_content"));}
					if (jsonObject.containsKey("ad_type")) {
					ad.setAd_type(Integer.parseInt(jsonObject.getString("ad_type")));}
					if (jsonObject.containsKey("ad_state")) {
						ad.setAd_state(Integer.parseInt(jsonObject.getString("ad_state")));
					}
					if (jsonObject.containsKey("ad_name")) {
						ad.setAd_name((jsonObject.getString("ad_name")));
					}
					if (jsonObject.containsKey("ad_tel")) {
						ad.setAd_tel((jsonObject.getString("ad_tel")));
					}
					if (jsonObject.containsKey("ad_remark")) {
						ad.setAd_remark((jsonObject.getString("ad_remark")));
					}
			}
			if (ad.getAd_id() != null)
				return true;
			else
				return false;
			}
		}
		// 根据ID查询
		//@Override
		//public Ad findAdById(Integer ad_id) {
		//return adRepository.findAdById(ad_id);
		//}

		/**
		 * 修改旅游信息
		 */
		//public boolean save(Ad ad) {
			//Ad result = adRepository.saveAndFlush(ad);
			//if (result.getAd_id() != null)
				//return true;
			//else
				//return false;
		//}
		// 根据标题查询旅游信息是否存在,返回1存在，返回0不存在
			//public Long isExist(String adTitle) {
			//Long result = adRepository.countByAdTitle(adTitle);
				//return result;
			//}

		// 根据标题查询旅游信息
			//public List<Ad> findAdByTitle(String adTitle) {
			//return adRepository.findAdByTitle(adTitle);
		//}
			
			// 根据类型查询旅游信息
			//public List<Ad> findAdByType(String adType) {
			//return adRepository.findAdByType(adType);
		//}
			
			// 根据状态查询旅游信息
			//public List<Ad> findAdByState(String adState) {
			//return adRepository.findAdByState(adState);
			//}
			

		// 查询所有旅游信息列表
		//public List<Ad> findAdAlls() {
			//return adRepository.findAdAlls();
		//}

		
		// 获取旅游信息列表，无翻页功能
//		@Override
//		public List<Travel> findAlls() {
//			return travelRepository.findAlls();
//		}
		

		
			

			
			


