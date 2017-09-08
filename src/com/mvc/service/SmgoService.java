package com.mvc.service;

import java.text.ParseException;
import java.util.List;

import com.mvc.entity.Smgo;
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
	List<Smgo> findSmgoByPage(String searchKey, int offset, int end);

	//根据id删除smgo信息
	boolean deleteIsdelete(Integer smgoid);

	//根据id查询smgo信息
	Smgo selectSmgoById(int smgo_id);

	//修改smgo信息
	Boolean updateSmgoBase(Integer smgo_id, JSONObject jsonObject, User user) throws ParseException;

	//根据sego筛选smgo信息   总页数
	Integer countSegoTotal(String smgoSego);

	//根据sego、page筛选smgo信息
	List<Smgo> findSmgoBySego(String smgoSego, int offset, int limit);

	//sego为null时返回全部smgo信息
	List<Smgo> findAlls();

}
