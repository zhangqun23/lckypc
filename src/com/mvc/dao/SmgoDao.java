package com.mvc.dao;

import java.util.Date;
import java.util.List;

import com.mvc.entity.SmallGoods;

/**
 * 
 * @ClassName: SmgoDao
 * @Description: smgo
 * @author ycj
 * @date 2017年9月7日 上午11:26:25 
 * 
 *
 */
public interface SmgoDao {

	//根据限制条件筛选信息
	Integer countTotal(String smgoSego, Date startDate, Date endDate);
	List<SmallGoods> findSmgoByPage(String smgoSego, Date startDate, Date endDate, int offset, int limit);
	
	//根据id删除smgo信息
	boolean updateState(Integer smgo_id);

	//添加补录信息
	boolean updateEdit(Date edittime, float editprice, Integer smgoid);
	
}
