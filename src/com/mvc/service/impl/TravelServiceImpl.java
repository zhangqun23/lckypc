package com.mvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.mvc.dao.TravelDao;
import com.mvc.entity.Travel;
import com.mvc.entity.TravelTrade;
import com.mvc.repository.TravelRepository;
import com.mvc.repository.TravelTradeRepository;
import com.mvc.service.TravelService;
import com.utils.Pager;
/**
 * Travel相关Service层接口实现
 * 
 * @author wdh
 * @date 2017年8月15日
 */

@Service("travelServiceImpl")
public  class TravelServiceImpl implements TravelService {
	@Autowired
	TravelRepository travelRepository;
	@Autowired
	TravelTradeRepository travelTradeRepository;
	@Autowired
	TravelDao travelDao;

	/**
	 * 添加、修改旅游信息
	 */
	public boolean save(Travel travel) {
		Travel result = travelRepository.saveAndFlush(travel);
		if (result.getTravel_id() != null)
			return true;
		else
			return false;
	}
	// 添加旅游信息
//		@Override
//		public Travel addTravel(User user, JSONObject jsonObject) {
//			
//			Travel travel = new Travel();
//			travel = (Travel) JSONUtil.JSONToObj(jsonObject.toString(), Travel.class);// 将json对象转换成实体对象，注意必须和实体类型一致
//			travel = travelRepository.saveAndFlush(travel);
//			return travel;
//		}
	// 根据标题查询旅游信息是否存在,返回1存在，返回0不存在
		public Long isExist(String travelTitle) {
			Long result = travelRepository.countByTravelTitle(travelTitle);
			return result;
		}

	// 根据标题查询旅游信息
//	public List<Travel> findTravelByTitle(String travel_title, Pager pager) {
//		return travelRepository.findTravelByTitle(travel_title);
//	}
//		public Travel findtravelByTitle(String travel_title) {
//			return travelRepository.findtravelByTitle(travel_title);
//		}

	// 查询所有旅游信息列表
	public List<Travel> findTravelAlls() {
		return travelRepository.findTravelAlls();
	}

	// 根据页数筛选全部旅游信息列表
	@Override
	public List<Travel> findTravelByPage(String searchKey, Integer offset, Integer end) {
		return travelDao.findTravelByPage(searchKey, offset, end);
	}

	// 获取旅游信息列表，无翻页功能
//	@Override
//	public List<Travel> findAlls() {
//		return travelRepository.findAlls();
//	}
	// 查询同公司总条数
		@Override
		public Integer countTotal(String searchKey) {
			return travelDao.countTotal(searchKey);
		}

	// 根据id删除
	@Override
	public boolean deleteIsdelete(Integer travel_id) {
		return travelDao.updateState(travel_id);
	}

	// 根据ID查询
	@Override
	public Travel findTravelById(Integer travel_id) {
		return travelRepository.findTravelById(travel_id);
	}

	// 根据公司筛选旅游信息
	@Override
	public List<Travel> findTravelByFirm(Integer travelFirm) {
		return travelRepository.findTravelByFirm(travelFirm);
	}
	@Override
	public List<Travel> findtravelByTitle(String travelTitle) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Travel> findTravelByTitle(String travelTitle, Pager pager) {
		// TODO Auto-generated method stub
		return null;
	}

	// 根据页数筛选全部旅游信息列表
		@Override
		public List<TravelTrade> findTravelTradeByPage(String searchKey, Integer offset, Integer end) {
			return travelDao.findTravelTradeByPage(searchKey, offset, end);
		}

		// 查询旅游交易总条数
			@Override
			public Integer countTrTotal(String searchKey) {
				return travelDao.countTrTotal(searchKey);
			}

		
		
	

}
