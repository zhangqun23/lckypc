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
		List<Ad> list = null;
		Integer totalRow = null;
		JSONObject jsonObject = new JSONObject();
		Pager pager = new Pager();
		pager.setPage(Integer.valueOf(request.getParameter("page")));
		if(request.getParameter("adState") != null){
			if(request.getParameter("adType") != null){
				adState = JSONObject.fromObject(request.getParameter("adState")).getString("ad_state");
				adType = JSONObject.fromObject(request.getParameter("adType")).getString("ad_type");
				totalRow = adService.countTotalST(adState,adType);
				list = adService.findAdByST(adState,adType,pager.getOffset(), pager.getLimit());
			}else {
				adState = JSONObject.fromObject(request.getParameter("adState")).getString("ad_state");
				totalRow = adService.countTotalS(adState);
				list = adService.findAdByState(adState,pager.getOffset(), pager.getLimit());
			}
		}else {
			if(request.getParameter("adType") != null){
				adType = JSONObject.fromObject(request.getParameter("adType")).getString("ad_type");
				totalRow = adService.countTotalT(adType);
				list = adService.findAdByType(adType,pager.getOffset(), pager.getLimit());
		    }else{
		    	totalRow = adService.countTotal();
		    	list = adService.findAdByPage(pager.getOffset(), pager.getLimit());
		    }
		}
		pager.setTotalRow(Integer.parseInt(totalRow.toString()));
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
