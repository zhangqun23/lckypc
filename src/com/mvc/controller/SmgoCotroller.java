package com.mvc.controller;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.mvc.entity.SmallGoods;
import com.mvc.service.SmgoService;
import com.utils.Pager;
import com.utils.StringUtil;

import net.sf.json.JSONObject;

/**
 * 
 * @ClassName: SmgoCotroller
 * @Description: smgo信息
 * @author ycj
 * @date 2017年9月6日 下午8:41:53 
 * 
 *
 */
@Controller
@RequestMapping("/smgo")
public class SmgoCotroller {
	@Autowired
	SmgoService smgoService;
	
	/**
	 * 
	 * 
	 *@Title: smgoPage 
	 *@Description: smgo首页
	 *@param @return
	 *@return String
	 *@throws
	 */
	@RequestMapping("/toSmgoPage.do")
	public String smgoPage() {
		return "smgoInformation/index";
	}
	
	/**
	 * @throws ParseException 
	 * 
	 * 
	 *@Title: selectSmgoBySego 
	 *@Description: sego、time限制
	 *@param @param request
	 *@param @param session
	 *@param @return
	 *@return String
	 *@throws
	 */
	@RequestMapping("/getSmgoListByPage.do")
	public @ResponseBody String selectSmgoByPrarm(HttpServletRequest request, HttpSession session) throws ParseException {
		String smgoSego = null;
		String startDate = null;
		String endDate = null;
		JSONObject jsonObject = new JSONObject();
		if(request.getParameter("smgoSego") != null){
			smgoSego = JSONObject.fromObject(request.getParameter("smgoSego")).getString("smgo_sego");
		}
		if(request.getParameter("gotNeed") != null){
			jsonObject = JSONObject.fromObject(request.getParameter("gotNeed"));
				if (jsonObject.containsKey("startDate")){ 
				    startDate = StringUtil.dayFirstTime(jsonObject.getString("startDate"));
				}
				if (jsonObject.containsKey("endDate")){
				    endDate = StringUtil.dayLastTime(jsonObject.getString("endDate"));
				}
		}
		Integer totalRow = smgoService.countTotal(smgoSego,startDate,endDate);//有问题
		Pager pager = new Pager();
		pager.setPage(Integer.valueOf(request.getParameter("page")));
        if(totalRow != null ){
        	pager.setTotalRow(Integer.parseInt(totalRow.toString()));
		}
		List<SmallGoods> list = smgoService.findSmgoByPage(smgoSego,startDate,endDate,pager.getOffset(),pager.getLimit());
		jsonObject.put("totalPage", pager.getTotalPage());
		jsonObject.put("list", list);
	    return jsonObject.toString();
	}
	
	/**
	 * 
	 * 
	 *@Title: deleteSmgo 
	 *@Description: 删除smgo
	 *@param @param request
	 *@param @param session
	 *@param @return
	 *@return String
	 *@throws
	 */
	@RequestMapping("/deleteSmgo.do")
	public @ResponseBody String deleteSmgo(HttpServletRequest request, HttpSession session) {
		Integer smgoid = Integer.valueOf(request.getParameter("smgoId"));
		boolean result = smgoService.deleteIsdelete(smgoid);
		return JSON.toJSONString(result);
	}
	
	/**
	 * 
	 * 
	 *@Title: addEdit 
	 *@Description: 添加补录信息
	 *@param @param request
	 *@param @param session
	 *@param @return
	 *@param @throws ParseException
	 *@return String
	 *@throws
	 */
	@RequestMapping("/addEdit.do")
	public @ResponseBody String addEdit(HttpServletRequest request, HttpSession session) throws ParseException {
		JSONObject jsonObject = new JSONObject();
		jsonObject = JSONObject.fromObject(request.getParameter("smgoNeed"));
		SmallGoods smgo = new SmallGoods();
		String edittime = null;
		float editprice = 0;
		Integer smgoid = null;
		if(request.getParameter("smgoId") != null){
			smgoid = Integer.parseInt(request.getParameter("smgoId"));
		}
		if(jsonObject.containsKey("edit_time")){
			edittime = jsonObject.getString("edit_time");
		}
		if(jsonObject.containsKey("edit_price")){
			editprice = Float.parseFloat(jsonObject.getString("edit_price"));
		}
		smgo.setIs_delete(false);
		boolean result = smgoService.update(edittime, editprice, smgoid);
		return JSON.toJSONString(result);
	}
}
