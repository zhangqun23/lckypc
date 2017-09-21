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

	//初始化
	Integer countTotal();
	List<SmallGoods> findSmgoByPage(int offset, int limit);
	
	//sego限制
	Integer countSegoTotal(String smgoSego);
	List<SmallGoods> findSmgoBySego(String smgoSego, int offset, int limit);

	//time限制
	Integer countTimeTotal(Date date1, Date date2);
	List<SmallGoods> findSmgoByTime(Date date1, Date date2, int offset, int limit);

	//sego、time限制
	Integer countTotalSG(String smgoSego, Date date1, Date date2);
	List<SmallGoods> findSmgoBySG(String smgoSego, Date date1, Date date2, int offset, int limit);
	
	//根据id删除smgo信息
	boolean deleteIsdelete(Integer smgoid);
	
	//补录
	boolean update(Date edittime, float editprice, Integer smgoid);
	
}