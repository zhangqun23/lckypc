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
		Date startDate = null;
		Date endDate = null;
		JSONObject jsonObject = new JSONObject();
		if(request.getParameter("smgoSego") != null){
			smgoSego = JSONObject.fromObject(request.getParameter("smgoSego")).getString("smgo_sego");
		}
		if(request.getParameter("gotNeed") != null){
			jsonObject = JSONObject.fromObject(request.getParameter("gotNeed"));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				if (jsonObject.containsKey("startDate")){ 
				    startDate = sdf.parse(StringUtil.dayFirstTime(jsonObject.getString("startDate")));
				}
				if (jsonObject.containsKey("endDate")){
				    endDate = sdf.parse(StringUtil.dayLastTime(jsonObject.getString("endDate")));
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
		System.out.println("totalPage:" + pager.getTotalPage());
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
