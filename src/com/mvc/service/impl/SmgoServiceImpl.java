package com.mvc.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.dao.SmgoDao;
import com.mvc.entity.Smgo;
import com.mvc.entity.User;
import com.mvc.repository.SmgoRepository;
import com.mvc.service.SmgoService;

import net.sf.json.JSONObject;

@Service("/smgoServiceImpl")
public class SmgoServiceImpl implements SmgoService{

	@Autowired
	SmgoRepository smgoRepository;
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
	
	//根据id获取smgo信息
	@Override
	public Smgo selectSmgoById(int smgo_id) {
		// TODO 自动生成的方法存根
		return smgoRepository.selectSmgoById(smgo_id);
	}
	
	//修改smgo信息
	@Override
	public Boolean updateSmgoBase(Integer smgo_id, JSONObject jsonObject, User user) throws ParseException {
		// TODO 自动生成的方法存根
		Smgo smgo = smgoRepository.selectSmgoById(smgo_id);
		if(smgo != null){
			if (jsonObject.containsKey("smgo_name")) {
				smgo.setSmgo_name(jsonObject.getString("smgo_name"));
				}
			if (jsonObject.containsKey("smgo_weight")) {
				smgo.setSmgo_weight(Float.parseFloat(jsonObject.getString("smgo_weight")));
				}
			if (jsonObject.containsKey("smgo_start")) {
				smgo.setSmgo_start(jsonObject.getString("smgo_start"));
				}
			if (jsonObject.containsKey("smgo_end")) {
				smgo.setSmgo_end(jsonObject.getString("smgo_end"));
				}
			if (jsonObject.containsKey("smgo_sender")) {
				smgo.setSmgo_sender(jsonObject.getString("smgo_sender"));
				}
			if (jsonObject.containsKey("smgo_sender_tel")) {
				smgo.setSmgo_sender_tel(jsonObject.getString("smgo_sender_tel"));
				}
			if (jsonObject.containsKey("smgo_receiver")) {
				smgo.setSmgo_receiver(jsonObject.getString("smgo_receiver"));
				}
			if (jsonObject.containsKey("smgo_receiver_tel")) {
				smgo.setSmgo_receiver_tel(jsonObject.getString("smgo_receiver_tel"));
				}
			if (jsonObject.containsKey("amgo_money")) {
				smgo.setAmgo_money(Float.parseFloat(jsonObject.getString("amgo_money")));
				}
			if (jsonObject.containsKey("smgo_deal_time")) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date = sdf.parse(jsonObject.getString("smgo_deal_time"));
				smgo.setSmgo_deal_time(date);
			}
			if (jsonObject.containsKey("smgo_send_time")) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date = sdf.parse(jsonObject.getString("smgo_send_time"));
				smgo.setSmgo_send_time(date);
			}
			if (jsonObject.containsKey("smgo_remark")) {
				smgo.setSmgo_remark(jsonObject.getString("smgo_remark"));
				}
		}
		smgo = smgoRepository.saveAndFlush(smgo);
		if(smgo.getSmgo_id() != null)
			return true;
		else
			return false;
	}

	@Override
	public Integer countSegoTotal(String smgoSego) {
		// TODO 自动生成的方法存根
		return smgoDao.countSegoTotal(smgoSego);
	}

	@Override
	public List<Smgo> findSmgoBySego(String smgoSego, int offset, int limit) {
		// TODO 自动生成的方法存根
		return smgoDao.findSmgoBySego(smgoSego,offset,limit);
	}

	@Override
	public List<Smgo> findAlls() {
		// TODO 自动生成的方法存根
		return smgoRepository.findAlls();
	}
}
