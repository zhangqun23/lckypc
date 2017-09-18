package com.mvc.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.mvc.entity.SmallGoods;
import com.mvc.entity.User;

import net.sf.json.JSONObject;

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

	//查询smgo全部信息
	Integer countTotal(String searchKey);

	//根据页数晒选smgo信息
	List<SmallGoods> findSmgoByPage(String searchKey, int offset, int end);

	//根据id删除smgo信息
	boolean deleteIsdelete(Integer smgoid);

	//根据sego筛选smgo信息   总页数
	Integer countSegoTotal(String smgoSego);

	//根据sego、page筛选smgo信息
	List<SmallGoods> findSmgoBySego(String smgoSego, int offset, int limit);

	//更新数据库
	boolean update(Date edittime, float editprice, Integer smgoid);

}