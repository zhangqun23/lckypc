package com.mvc.controller;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
 * @Description: TODO
 * @author ycj
 * @date 2017年8月31日 下午3:32:05 
 * 
 *
 */
@Controller
@RequestMapping("/ad")
public class AdController {

	@Autowired
	AdService adService;
	@Autowired
	UserService userService;
	
	/**
	 * 
	 * 
	 *@Title: selectAdInfo 
	 *@Description: TODO
	 *@param @param request
	 *@param @return
	 *@return String
	 *@throws
	 */
	@RequestMapping("/selectAdInfo.do")
	public @ResponseBody String selectAdInfo (HttpServletRequest request){
		Ad list;
		String adId = request.getParameter("ad_id");
		list = adService.selectAdInfo(adId);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("list", list);
		return jsonObject.toString();
		
	}
	
	/**
	 * 
	 * 
	 *@Title: selectAdById 
	 *@Description: TODO
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
	
	/**
	 * 返回旅游管理界面
	 * 
	 * @return
	 */
	@RequestMapping("/toAdPage.do")
	public String adPage() {
		return "adInformation/index";
	}
	
	/**
	 * 根据页数筛选旅游信息列表
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getAdListByPage.do")
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
	 * 获取指定页面的十条旅游信息，总页数
	 * 
	 * @param request
	 * @return
	 */

	

	/**
	 * 删除旅游信息
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/deleteAd.do")
	public @ResponseBody String deleteAd(HttpServletRequest request, HttpSession session) {
		Integer adid = Integer.valueOf(request.getParameter("adId"));
		boolean result = adService.deleteIsdelete(adid);
		return JSON.toJSONString(result);
	}

	/**
	 * 检查旅游信息是否已经存在:返回1存在，返回0不存在
	 * 
	 * @param request
	 * @param session
	 * @param map
	 * @return
	 */
	@RequestMapping("/isAdTitleExist.do")
	public @ResponseBody Long checkUserName(HttpServletRequest request, HttpSession session, ModelMap map) {
		String adTitle = request.getParameter("adTitle");
		Long result = adService.isExist(adTitle);
		return result;
	}
	
	
	
	/**
	 * 
	 * 
	 *@Title: selectAdByTitle 
	 *@Description: TODO
	 *@param @param request
	 *@param @param session
	 *@param @return
	 *@return String
	 *@throws
	 */
	@RequestMapping("/selectAdByTitle.do")
	public @ResponseBody String selectAdByTitle(HttpServletRequest request, HttpSession session) {
		String aTitle = request.getParameter("ad_title");
		session.setAttribute("ad_title", aTitle);
		List<Ad> ad = adService.selectAdByTitle(aTitle, null, null);//有问题
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("ad", ad);
		return jsonObject.toString();
	}
	
	/**
	 * 根据合同ID修改旅游信息
	 * 
	 * @param request
	 * @param session
	 * @return 成功返回1，失败返回0
	 * @throws ParseException 
	 */
	@RequestMapping("/updateAdById.do")
	public @ResponseBody Integer updateAdById(HttpServletRequest request, HttpSession session) throws ParseException {
		User user = (User) session.getAttribute(SessionKeyConstants.LOGIN);
		JSONObject jsonObject = JSONObject.fromObject(request.getParameter("ad"));
		Integer ad_id = null;
		if (jsonObject.containsKey("ad_id")) {
			ad_id = Integer.parseInt(jsonObject.getString("ad_id"));
		}
		Boolean flag = adService.updateAdBase(ad_id, jsonObject, user);
		if (flag == true)
			return 1;
		else
			return 0;
	}
}