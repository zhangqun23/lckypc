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
	
	//初始化
	@RequestMapping("/getAdListByPage.do")
	public @ResponseBody String getAdsByPrarm(HttpServletRequest request, HttpSession session) {
		List<Ad> list;
		JSONObject jsonObject = new JSONObject();
		Integer totalRow = adService.countTotal();
		Pager pager = new Pager();
		pager.setPage(Integer.valueOf(request.getParameter("page")));
		pager.setTotalRow(Integer.parseInt(totalRow.toString()));
		list = adService.findAdByPage(pager.getOffset(), pager.getLimit());
		jsonObject.put("totalPage", pager.getTotalPage());
		System.out.println("totalPage:" + pager.getTotalPage());
		jsonObject.put("list", list);
		return jsonObject.toString();
	}
	
	//根据state筛选ad信息
	@RequestMapping("/getAdListByState.do")
	public @ResponseBody String getAdsByState(HttpServletRequest request, HttpSession session) {
		String adState;
		List<Ad> list;
		if(request.getParameter("adState") != null){
			adState = JSONObject.fromObject(request.getParameter("adState")).getString("ad_state");
		}else{
			adState = null;
		}
		JSONObject jsonObject = new JSONObject();
		Integer totalRow = adService.countTotalS(adState);
		Pager pager = new Pager();
		pager.setPage(Integer.valueOf(request.getParameter("page")));
		pager.setTotalRow(Integer.parseInt(totalRow.toString()));
		list = adService.findAdByState(adState, pager.getOffset(), pager.getLimit());
		jsonObject.put("totalPage", pager.getTotalPage());
		System.out.println("totalPage:" + pager.getTotalPage());
		jsonObject.put("list", list);
		return jsonObject.toString();
	}
	
	//根据type筛选ad信息
	@RequestMapping("/getAdListByType.do")
	public @ResponseBody String getAdsByType(HttpServletRequest request, HttpSession session) {
		String adType;
		List<Ad> list;
		if(request.getParameter("adType") != null){
			adType = JSONObject.fromObject(request.getParameter("adType")).getString("ad_type");
		}else{
			adType = null;
		}
		JSONObject jsonObject = new JSONObject();
		Integer totalRow = adService.countTotalT(adType);
		Pager pager = new Pager();
		pager.setPage(Integer.valueOf(request.getParameter("page")));
		pager.setTotalRow(Integer.parseInt(totalRow.toString()));
		list = adService.findAdByType(adType, pager.getOffset(), pager.getLimit());
		jsonObject.put("totalPage", pager.getTotalPage());
		System.out.println("totalPage:" + pager.getTotalPage());
		jsonObject.put("list", list);
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
