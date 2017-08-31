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
 * @date 2017年8月30日 上午10:40:54 
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
		
		@RequestMapping("/toAdPage.do")
		public String adPage() {
			return "adInformation/index";
		}
		
		/**
		 * 
		 * 
		 *@Title: deleteAd 
		 *@Description: 根据id删除ad信息
		 *@param @param request
		 *@param @param session
		 *@param @return
		 *@return String
		 *@throws
		 */
		@RequestMapping(value = "/deleteAd.do")
		public @ResponseBody String deleteAd(HttpServletRequest request, HttpSession session) {
			Integer adid = Integer.valueOf(request.getParameter("adId"));
			boolean result = adService.deleteIsdelete(adid);
			return JSON.toJSONString(result);
		}
		
		/**
		 * 
		 * 
		 *@Title: selectAdByState 
		 *@Description: TODO
		 *@param @param request
		 *@param @param session
		 *@param @return
		 *@return String
		 *@throws
		 */
		@RequestMapping("/selectAdByState.do")
		public @ResponseBody String selectAdByState(HttpServletRequest request, HttpSession session){
			Ad list;
			String adState = request.getParameter("ad_state");	
			list = adService.selectAdByState(adState);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("list", list);
			return jsonObject.toString();
		}
		
		/**
		 * 
		 * 
		 *@Title: updateAdById 
		 *@Description: 根据id修改ad信息
		 *@param @param request
		 *@param @param session
		 *@param @return
		 *@param @throws ParseException
		 *@return Integer
		 *@throws
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
