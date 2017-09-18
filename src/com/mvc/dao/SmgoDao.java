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

	//查询全部smgo信息
	Integer countTotal(String searchKey);

	//根据页数筛选smgo信息
	List<SmallGoods> findSmgoByPage(String searchKey, int offset, int end);

	//根据id删除smgo信息
	boolean updateState(Integer smgo_id);

	//按照smgoSego获得总页数
	Integer countSegoTotal(String smgoSego);

	//根据smgoSego、page筛选smgo信息
	List<SmallGoods> findSmgoBySego(String smgoSego, int offset, int limit);

	//添加补录信息
	boolean updateEdit(Date edittime, float editprice, Integer smgoid);
	
}
