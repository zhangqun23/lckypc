package com.mvc.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.mvc.entity.Travel;
import com.mvc.entity.TravelTrade;
import com.mvc.entity.User;
import com.mvc.service.TravelService;
import com.mvc.service.UserService;
import com.utils.Pager;









import net.sf.json.JSONObject;

/**
 * 旅游控制器
 * 
 * @author wdh
 * @date 2017-08-14
 */
@Controller
@RequestMapping("/travel")
public class TravelController {

	@Autowired
	TravelService travelService;
	@Autowired
	UserService userService;
	/**
	 * 返回旅游管理界面
	 * 
	 * @return
	 */
	@RequestMapping("/toTravelPage.do")
	public String travelPage() {
		return "travelInformation/index";
	}
	
	/**
	 * 根据页数筛选旅游信息列表
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getTravelListByPage.do")
	public @ResponseBody String getTravelsByPrarm(HttpServletRequest request, HttpSession session) {
		JSONObject jsonObject = new JSONObject();
		String searchKey = request.getParameter("searchKey");
		Integer totalRow = travelService.countTotal(searchKey);
		Pager pager = new Pager();
		pager.setPage(Integer.valueOf(request.getParameter("page")));
		pager.setTotalRow(Integer.parseInt(totalRow.toString()));
		List<Travel> list = travelService.findTravelByPage(searchKey, pager.getOffset(), pager.getLimit());
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
	 * 获取旅游信息列表，无翻页功能
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getAllTravelList.do")
	public @ResponseBody String getAllTravels(HttpServletRequest request, HttpSession session) {
		List<Travel> result = travelService.findTravelAlls();
		return JSON.toJSONString(result);
	}

	/**
	 * 删除旅游信息
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/deleteTravel.do")
	public @ResponseBody String deleteTravel(HttpServletRequest request, HttpSession session) {
		Integer travelid = Integer.valueOf(request.getParameter("travelId"));
		boolean result = travelService.deleteIsdelete(travelid);
		return JSON.toJSONString(result);
	}

	/**
	 * 添加,修改旅游信息
	 * 
	 * @param request
	 * @param session
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/addTravel.do")
	public @ResponseBody String addTravel(HttpServletRequest request, HttpSession session) throws ParseException {
		JSONObject jsonObject = new JSONObject();
		jsonObject = JSONObject.fromObject(request.getParameter("travel"));
		Travel travel = new Travel();
		if (jsonObject.containsKey("travel_title")) {
		travel.setTravel_title(jsonObject.getString("travel_title"));}
		if (jsonObject.containsKey("travel_content")) {
		travel.setTravel_content(jsonObject.getString("travel_content"));}
		if (jsonObject.containsKey("travel_route")) {
		travel.setTravel_route(jsonObject.getString("travel_route"));}

		if (jsonObject.containsKey("travel_mprice")) {
			travel.setTravel_mprice(Float.parseFloat(jsonObject.getString("travel_mprice")));
		}
		if (jsonObject.containsKey("travel_cprice")) {
			travel.setTravel_cprice(Float.parseFloat(jsonObject.getString("travel_cprice")));
		}
		if (jsonObject.containsKey("travel_insurance")) {
			travel.setTravel_insurance(Float.parseFloat(jsonObject.getString("travel_insurance")));
		}
		if (jsonObject.containsKey("travel_discount")) {
//			DecimalFormat df = new DecimalFormat("#.00");
//			String str = df.format(Float.parseFloat(jsonObject.getString("travel_discount")));
//			travel.setTravel_discount(Float.parseFloat(str));
			travel.setTravel_discount(Float.parseFloat(jsonObject.getString("travel_discount")));
		}
		
		if (jsonObject.containsKey("travel_stime")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(jsonObject.getString("travel_stime"));
			travel.setTravel_stime(date);}
		if (jsonObject.containsKey("travel_location")) {
			travel.setTravel_location(jsonObject.getString("travel_location"));}
		if (jsonObject.containsKey("travel_days")) {
			travel.setTravel_days(Float.parseFloat(jsonObject.getString("travel_days")));
		}
		if (jsonObject.containsKey("tel")) {
			travel.setTel(jsonObject.getString("tel"));}
		if (jsonObject.containsKey("travel_total_num")) {
			travel.setTravel_total_num(Integer.parseInt(jsonObject.getString("travel_total_num")));
		}
		if (jsonObject.containsKey("travel_left_num")) {
			travel.setTravel_left_num(Integer.parseInt(jsonObject.getString("travel_left_num")));
		}
		if (jsonObject.containsKey("travel_firm")) {
			travel.setTravel_firm(jsonObject.getString("travel_firm"));}
		travel.setIs_delete(0);
		boolean result;
		if (jsonObject.containsKey("travel_id")) {
			travel.setTravel_id(Integer.valueOf(jsonObject.getString("travel_id")));
			result = travelService.save(travel);// 修改信息
		} else {
			result = travelService.save(travel);// 添加信息
		}
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
	@RequestMapping("/isTravelTitleExist.do")
	public @ResponseBody Long checkUserName(HttpServletRequest request, HttpSession session, ModelMap map) {
		String travelTitle = request.getParameter("travelTitle");
		Long result = travelService.isExist(travelTitle);
		return result;
	}
	
	/**
	 * 返回旅游管理界面
	 * 
	 * @return
	 */
	@RequestMapping("/toTravelTradePage.do")
	public String travelTradePage() {
		return "travelInformation/index";
	}
	
	/**
	 * 根据ID获取旅游信息
	 * 
	 * @param request
	 * @param session
	 * @return Travel对象
	 */
	@RequestMapping("/selectTravelById.do")
	public @ResponseBody String selectTravelById(HttpServletRequest request, HttpSession session) {
		int travel_id = Integer.parseInt(request.getParameter("travel_id"));
		session.setAttribute("travel_id", travel_id);
		Travel travel = travelService.selectTravelById(travel_id);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("travel", travel);
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
	@RequestMapping("/updateTravelById.do")
	public @ResponseBody Integer updateConById(HttpServletRequest request, HttpSession session) throws ParseException {
		User user = (User) session.getAttribute(SessionKeyConstants.LOGIN);
		JSONObject jsonObject = JSONObject.fromObject(request.getParameter("travel"));
		Integer travel_id = null;
		if (jsonObject.containsKey("travel_id")) {
			travel_id = Integer.parseInt(jsonObject.getString("travel_id"));
		}
		Boolean flag = travelService.updateTravelBase(travel_id, jsonObject, user);
		if (flag == true)
			return 1;
		else
			return 0;
	}
	/**
	 * 根据页数筛选旅游信息列表
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getTravelTradeListByPage.do")
	public @ResponseBody String getTravelTradesByPrarm(HttpServletRequest request, HttpSession session) {
		JSONObject jsonObject = new JSONObject();
		String searchKey = request.getParameter("searchKey");
		Integer totalRow = travelService.countTrTotal(searchKey);
		Pager pager = new Pager();
		pager.setPage(Integer.valueOf(request.getParameter("page")));
		pager.setTotalRow(Integer.parseInt(totalRow.toString()));
		List<TravelTrade> list = travelService.findTravelTradeByPage(searchKey, pager.getOffset(), pager.getLimit());
		jsonObject.put("list", list);
		jsonObject.put("totalPage", pager.getTotalPage());
		System.out.println("totalPage:" + pager.getTotalPage());
		return jsonObject.toString();
	}
}
