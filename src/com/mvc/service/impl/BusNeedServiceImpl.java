package com.mvc.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.dao.BusNeedDao;
import com.mvc.entity.BusNeed;
import com.mvc.entity.BusTrade;
import com.mvc.repository.BusNeedRepository;
import com.mvc.repository.BusTradeRepository;
import com.mvc.service.BusNeedService;

import net.sf.json.JSONObject;
import com.utils.JSONUtil;
/**
 * BusNeed相关Service层接口实现
 * 
 * @author wdh
 * @date 2017年9月5日
 */

@Service("busNeedServiceImpl")
public  class BusNeedServiceImpl implements BusNeedService {
	@Autowired
	BusNeedDao busNeedDao;
	@Autowired
	BusNeedRepository busNeedRepository;
	@Autowired
	BusTradeRepository busTradeRepository;

	/**
	 * 添加信息
	 */
	public boolean saveBune(BusNeed busNeed) {
		BusNeed result = busNeedRepository.saveAndFlush(busNeed);
		if (result.getBune_id() != null)
			return true;
		else
			return false;
	}
	// 查询总条数
		@Override
		public Integer countBuneTotal(String searchKey) {
				return busNeedDao.countBuneTotal(searchKey);
			}

	// 根据id删除
		@Override
		public boolean deleteBuneIsdelete(Integer bune_id) {
			return busNeedDao.updateBuneState(bune_id);
		}
	// 根据ID获取
		@Override
		public BusNeed selectBusNeedById(Integer bune_id) {
				return busNeedRepository.selectBusNeedById(bune_id);
					}
	// 根据页数筛选全部信息
		@Override
		public List<BusNeed> findBusNeedByPage(String searchKey, Integer offset, Integer end) {
			return busNeedDao.findBusNeedByPage(searchKey, offset, end);
		}
	
	
		// 添加
		@Override
		public BusNeed addBusNeed(JSONObject jsonObject) {
			
			long time = System.currentTimeMillis();
			Date date = new Date(time);
			BusNeed busNeed = new BusNeed();
			busNeed = (BusNeed) JSONUtil.JSONToObj(jsonObject.toString(), BusNeed.class);
			
			return busNeed;
		}
		
	//信息补录
		@Override
		public BusNeed updateBusNeed(Integer bune_id, JSONObject jsonObject){
			BusNeed busNeed = busNeedRepository.selectBusNeedById(bune_id);
			if (jsonObject != null) {
				
					if (jsonObject.containsKey("bune_purp")) {
						busNeed.setBune_purp(jsonObject.getString("bune_purp"));
					}
				
				}
						
				
			return busNeed;	
			}
				
	//交易信息补录	
		@Override
		public BusTrade updateBusTrade(Integer butr_id, JSONObject jsonObject){
			BusTrade busTrade = busTradeRepository.selectBusTradeById(butr_id);
			if (jsonObject != null) {
				try{
					if (jsonObject.containsKey("butr_time")) {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						Date date = sdf.parse(jsonObject.getString("butr_time"));
						busTrade.setButr_time(date);}
						if (jsonObject.containsKey("butr_depo")) {
							busTrade.setButr_depo(Float.parseFloat(jsonObject.getString("butr_depo")));
						}
						if (jsonObject.containsKey("butr_money")) {
							busTrade.setButr_money(Float.parseFloat(jsonObject.getString("butr_money")));
						}
						if (jsonObject.containsKey("bune_bus")) {
							busTrade.setBune_bus(jsonObject.getString("bune_bus"));}
				
						if (jsonObject.containsKey("bune_type")) {
							busTrade.setBune_type(Integer.parseInt(jsonObject.getString("bune_type")));
						}
						if (jsonObject.containsKey("invoice_if")) {
							busTrade.setInvoice_if(Integer.parseInt(jsonObject.getString("invoice_if")));
						}						
						if (jsonObject.containsKey("invoice_num")) {
							busTrade.setInvoice_num(jsonObject.getString("invoice_num"));}
				
						if (jsonObject.containsKey("butr_state")) {
							busTrade.setButr_state(Integer.parseInt(jsonObject.getString("butr_state")));
						}					
					
				}catch (ParseException e) {
					e.printStackTrace();
				}
						
				}
			return busTrade;	
			}
}
