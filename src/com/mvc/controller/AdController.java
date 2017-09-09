package com.mvc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.base.constants.SessionKeyConstants;
import com.mvc.entity.Ad;
import com.mvc.entity.User;
import com.mvc.service.AdService;
import com.mvc.service.UserService;
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
	@Autowired
	UserService uservice;
	
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
	
	/**
	 * 
	 * 
	 *@Title: getAdsByPrarm 
	 *@Description: 页码
	 *@param @param request
	 *@param @param session
	 *@param @return
	 *@return String
	 *@throws
	 */
	@RequestMapping("/getAdListByPage.do")
	public @ResponseBody String getAdsByPrarm(HttpServletRequest request, HttpSession session) {
		JSONObject jsonObject = new JSONObject();
		String searchKey = request.getParameter("searchKey");
		Integer totalRow = adService.countTotal(searchKey);
		Pager pager = new Pager();
		pager.setPage(Integer.valueOf(request.getParameter("page")));
		pager.setTotalRow(Integer.parseInt(totalRow.toString()));
		List<Ad> list = adService.findAdByPage(searchKey, pager.getOffset(), pager.getLimit());
		jsonObject.put("list", list);
		jsonObject.put("totalPage", pager.getTotalPage());
		System.out.println("totalPage:" + pager.getTotalPage());
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
	/**
	 * 
	 * 
	 *@Title: selectAdById 
	 *@Description: 根据id获取ad信息
	 *@param @param request
	 *@param @param session
	 *@param @return
	 *@return String
	 *@throws
	 */
	@RequestMapping("/selectAdById.do")
	public @ResponseBody String selectAdById(HttpServletRequest request, HttpSession session) {
		int ad_id = Integer.parseInt(request.getParameter("ad_id"));
		session.setAttribute("ad_id", ad_id);
		Ad ad = adService.selectAdById(ad_id);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("ad", ad);
		return jsonObject.toString();
	}
	
	//根据state筛选ad信息
	@RequestMapping("/selectAdByState.do")	
	public @ResponseBody String selectAdByState(HttpServletRequest request, HttpSession session) {
		String adState;
		List<Ad> list;
		JSONObject jsonObject = new JSONObject();
		if(request.getParameter("adState") != null){
			adState = JSONObject.fromObject(request.getParameter("adState")).getString("ad_state");
			Integer totalRow = adService.countStateTotal(adState);
			Pager pager = new Pager();
			pager.setPage(Integer.valueOf(request.getParameter("page")));
			pager.setTotalRow(Integer.parseInt(totalRow.toString()));
			list = adService.findAdByStatePage(adState, pager.getOffset(), pager.getLimit());
			jsonObject.put("totalPage", pager.getTotalPage());
		}else{
			list = adService.findAlls();
		}
		jsonObject.put("list", list);
		return jsonObject.toString();
	}
}