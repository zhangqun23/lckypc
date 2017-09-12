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
import com.mvc.entity.BusNeed;
import com.mvc.entity.BusTrade;
import com.mvc.entity.User;
import com.mvc.service.BusNeedService;
import com.mvc.service.UserService;
import com.utils.Pager;







import net.sf.json.JSONObject;

/**
 * BusNeed控制器
 * 
 * @author wdh
 * @date 2017-09-4
 */
@Controller
@RequestMapping("/busNeed")
public class BusNeedController {

	@Autowired
	BusNeedService busNeedService;
	@Autowired
	UserService userService;
	/**
	 * 返回管理界面
	 * 
	 * @return
	 */
	@RequestMapping("/toBusNeedPage.do")
	public String busNeedPage() {
		return "busInformation/index";
	}
	
	/**
	 * 根据页数筛选信息
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/getBusNeedListByPage.do")
	public @ResponseBody String getBusNeedsByPrarm(HttpServletRequest request, HttpSession session) {
		JSONObject jsonObject = new JSONObject();
		String searchKey = request.getParameter("searchKey");
		Integer totalRow = busNeedService.countBuneTotal(searchKey);
		Pager pager = new Pager();
		pager.setPage(Integer.valueOf(request.getParameter("page")));
		pager.setTotalRow(Integer.parseInt(totalRow.toString()));
		List<BusNeed> list = busNeedService.findBusNeedByPage(searchKey, pager.getOffset(), pager.getLimit());
		jsonObject.put("list", list);
		jsonObject.put("totalPage", pager.getTotalPage());
		System.out.println("totalPage:" + pager.getTotalPage());
		return jsonObject.toString();
	}

	/**
	 * 删除信息
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/deleteBusNeed.do")
	public @ResponseBody String deleteBusNeed(HttpServletRequest request, HttpSession session) {
		Integer busNeedid = Integer.valueOf(request.getParameter("busNeedId"));
		boolean result = busNeedService.deleteBuneIsdelete(busNeedid);
		return JSON.toJSONString(result);
	}
	
	/**
	 * 根据ID获得信息
	 * 
	 * @param request
	 * @param session
	 * @return BusNeed对象
	 */
	@RequestMapping("/selectBusNeedById.do")
	public @ResponseBody String selectBusNeedById(HttpServletRequest request, HttpSession session) {
		int bune_id = Integer.parseInt(request.getParameter("bune_id"));
		session.setAttribute("bune_id", bune_id);
		BusNeed busNeed = busNeedService.selectBusNeedById(bune_id);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("busNeed", busNeed);
		return jsonObject.toString();
	}
	
	/**
	 * 添加班车信息
	 * 
	 * @param request
	 * @param session
	 * @return 合同ID
	 */
	@RequestMapping("/addBusNeed.do")
	public @ResponseBody Integer addBusNeed(HttpServletRequest request, HttpSession session) {
		JSONObject jsonObject = JSONObject.fromObject(request.getParameter("busNeed"));
		BusNeed busNeed = busNeedService.addBusNeed(jsonObject);
		int bune_id = busNeed.getBune_id();
		session.setAttribute("bune_id", bune_id);
		return bune_id;
	}
	/**
	 * 添加班车交易信息
	 * 
	 * @param request
	 * @param session
	 * @return 合同ID
	 */
	@RequestMapping("/addBusTrade.do")
	public @ResponseBody Integer addBusTrade(HttpServletRequest request, HttpSession session) {
		JSONObject jsonObject = JSONObject.fromObject(request.getParameter("busTrade"));
		BusTrade busTrade = busNeedService.addBusTrade(jsonObject);
		int butr_id = busTrade.getButr_id();
		session.setAttribute("butr_id", butr_id);
		return butr_id;
	}
	/**
	 * 信息补录
	 * 
	 * @param request
	 * @param session
	 * @return BusNeed对象
	 */
	@RequestMapping("/repeatAddBusNeed.do")
	public @ResponseBody String repeatAddBusNeed(HttpServletRequest request, HttpSession session) {		
		Integer bune_id = Integer.valueOf(request.getParameter("bune_id"));
		JSONObject jsonObject = JSONObject.fromObject(request.getParameter("busNeed"));
		BusNeed busNeed = busNeedService.updateBusNeed(bune_id, jsonObject);
		jsonObject = new JSONObject();
		jsonObject.put("busNeed", busNeed);
		return jsonObject.toString();
	}
//	/**
//	 * 信息补录
//	 * 
//	 * @param request
//	 * @param session
//	 * @return BusNeed对象
//	 */
//	@RequestMapping("/repeatAddBusTrade.do")
//	public @ResponseBody String repeatAddBusTrade(HttpServletRequest request, HttpSession session) {
//		
//		Integer bune_id = Integer.valueOf(request.getParameter("bune_id"));
//		JSONObject jsonObject = JSONObject.fromObject(request.getParameter("busTrade"));
//		BusTrade busTrade = busNeedService.updateBusTrade(bune_id, jsonObject);
//		jsonObject = new JSONObject();
//		jsonObject.put("busNeed", busTrade);
//		return jsonObject.toString();
//	}
}
