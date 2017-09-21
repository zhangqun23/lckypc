package com.mvc.service;

import java.util.Date;
import java.util.List;

import com.mvc.entity.SmallGoods;

/**
 * 
 * @ClassName: SmgoService
 * @Description: smgo
 * @author ycj
 * @date 2017年9月7日 上午10:26:25 
 * 
 *
 */
public interface SmgoService {

	//根据限制条件筛选信息
	Integer countTotal(String smgoSego, Date startDate, Date endDate);
	List<SmallGoods> findSmgoByPage(String smgoSego, Date startDate, Date endDate, int offset, int limit);
	
	//根据id删除smgo信息
	boolean deleteIsdelete(Integer smgoid);
	
	//补录
	boolean update(Date edittime, float editprice, Integer smgoid);
	
}