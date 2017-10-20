package com.mvc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.mvc.entity.Ad;
import com.mvc.service.AdService;
import com.utils.Pager;

import net.sf.json.JSONObject;

/**
 * 
 * @ClassName: AdController
 * @Description: ad
 * @author ycj
 * @date 2017年9月6日 上午9:14:39 
 * 
 *
 */
@Controller
@RequestMapping("/ad")
public class AdController {

	@Autowired
	AdService adService;
	
	/**
	 * 
	 * 
	 *@Title: adPage 
	 *@Description: ad首页
	 *@param @return
	 *@return String
	 *@throws
	 */
	@RequestMapping("/toAdPage.do")
	public String adPage() {
		return "adInformation/index";
	}
	
	//根据限制条件state、type筛选ad信息
	@RequestMapping("/getAdListByPage.do")
	public @ResponseBody String getAdsByPrarm(HttpServletRequest request, HttpSession session) {
		String adState = null;
		String adType = null;
		JSONObject jsonObject = new JSONObject();
		if(request.getParameter("adState") != null){
			adState = JSONObject.fromObject(request.getParameter("adState")).getString("ad_state");
		}
		if(request.getParameter("adType") != null){
			adType = JSONObject.fromObject(request.getParameter("adType")).getString("ad_type");
		}
		Integer totalRow = adService.countTotal(adState,adType);
		Pager pager = new Pager();
		pager.setPage(Integer.valueOf(request.getParameter("page")));
		if(totalRow != 0){
			pager.setTotalRow(Integer.parseInt(totalRow.toString()));
		}
		List<Ad> list = adService.findAdByPage(adState,adType,pager.getOffset(), pager.getLimit());
		jsonObject.put("totalPage", pager.getTotalPage());
		jsonObject.put("list", list);
		System.out.println("总行数"+totalRow);
		System.out.println("当前页码"+pager);
		return jsonObject.toString();
	}
	
	/**
	 * 
	 * 
	 *@Title: deleteAd 
	 *@Description: 删除ad
	 *@param @param request
	 *@param @param session
	 *@param @return
	 *@return String
	 *@throws
	 */
	@RequestMapping("/deleteAd.do")
	public @ResponseBody String deleteAd(HttpServletRequest request, HttpSession session) {
		Integer adid = Integer.valueOf(request.getParameter("adId"));
		boolean result = adService.deleteIsdelete(adid);
		return JSON.toJSONString(result);
	}
	
	/**
	 * 
	 * 
	 *@Title: editState 
	 *@Description: 变更state,弹窗
	 *@param @param request
	 *@param @param session
	 *@param @return
	 *@return String
	 *@throws
	 */
	@RequestMapping("/editState.do")	
	public @ResponseBody boolean editState(HttpServletRequest request, HttpSession session) {
		Integer adid = Integer.valueOf(request.getParameter("adId"));
		boolean result = adService.editState(adid);
		return result;
	}
}
