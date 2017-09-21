package com.mvc.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.mvc.service.UserService;
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
	@Autowired
	UserService userService;
	
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
	public @ResponseBody String selectSmgoBySego(HttpServletRequest request, HttpSession session) throws ParseException {
		JSONObject jsonObject = new JSONObject();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		String smgoSego = null;
		Integer totalRow = null;
		String startDate = null;
		String endDate = null;
		Date date1 = null ;
		Date date2 = null;
		List<SmallGoods>list = null;
		Pager pager = new Pager();
		pager.setPage(Integer.valueOf(request.getParameter("page")));
		if(request.getParameter("smgoSego") != null){
			if(request.getParameter("gotNeed") != null){
				smgoSego = JSONObject.fromObject(request.getParameter("smgoSego")).getString("smgo_sego");
				if (jsonObject.containsKey("startDate")) 
				startDate = StringUtil.dayFirstTime(jsonObject.getString("startDate"));
				if (jsonObject.containsKey("endDate"))
				endDate = StringUtil.dayLastTime(jsonObject.getString("endDate"));
				date1 = sdf.parse(startDate);
				date2 = sdf.parse(endDate);
				jsonObject = JSONObject.fromObject(request.getParameter("gotNeed"));
				totalRow = smgoService.countTotalSG(smgoSego,date1,date2);
				list = smgoService.findSmgoBySG(smgoSego,date1,date2,pager.getOffset(),pager.getLimit());
				
			}else{
				smgoSego = JSONObject.fromObject(request.getParameter("smgoSego")).getString("smgo_sego");
				totalRow = smgoService.countSegoTotal(smgoSego);
				list = smgoService.findSmgoBySego(smgoSego,pager.getOffset(),pager.getLimit());
			}
		}else if(request.getParameter("gotNeed") != null){
			if (jsonObject.containsKey("startDate")) 
			startDate = StringUtil.dayFirstTime(jsonObject.getString("startDate"));
			if (jsonObject.containsKey("endDate")) 
			endDate = StringUtil.dayLastTime(jsonObject.getString("endDate"));
			if(startDate != null )
			date1 = sdf.parse(startDate);
			if(endDate != null )
			date2 = sdf.parse(endDate);
			jsonObject = JSONObject.fromObject(request.getParameter("gotNeed"));
			totalRow = smgoService.countTimeTotal(date1, date2);
			list = smgoService.findSmgoByTime(date1, date2, pager.getOffset(),pager.getLimit());
		}else{
			totalRow = smgoService.countTotal();
			list = smgoService.findSmgoByPage(pager.getOffset(),pager.getLimit());
		}
		if(totalRow != null )
		pager.setTotalRow(Integer.parseInt(totalRow.toString()));
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
		Date edittime = null;
		float editprice = 0;
		if(jsonObject.containsKey("edit_time")){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(jsonObject.getString("edit_time"));
			smgo.setEdit_time(date);
			edittime = sdf.parse(jsonObject.getString("edit_time"));	
		}
		if(jsonObject.containsKey("edit_price")){
			smgo.setEdit_price(Float.parseFloat(jsonObject.getString("edit_price")));
			editprice = Float.parseFloat(jsonObject.getString("edit_price"));
		}
		smgo.setIs_delete(false);
		boolean result = false;
		if(request.getParameter("smgoid") !=null){
			String temp = request.getParameter("smgoid");
			Integer smgoid = Integer.parseInt(temp);
			result = smgoService.update(edittime, editprice, smgoid);
		}
		return JSON.toJSONString(result);
	}
}
