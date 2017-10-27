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
import com.mvc.entity.BusNeed;
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
		String butrState = null;
		JSONObject jsonObject = new JSONObject();
		String searchKey = request.getParameter("searchKey");
		if(request.getParameter("butrState") != null){
			butrState = request.getParameter("butrState");
		}
		Integer totalRow = busNeedService.countBuneTotal(butrState,searchKey);
		Pager pager = new Pager();
		pager.setPage(Integer.valueOf(request.getParameter("page")));
		if(totalRow != 0){
		pager.setTotalRow(Integer.parseInt(totalRow.toString()));
		}
		List<BusNeed> list = busNeedService.findBusNeedByPage(butrState,searchKey, pager.getOffset(), pager.getLimit());
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
	 * 信息补录
	 * 
	 * @param request
	 * @param session
	 * @return BusNeed对象
	 */
	@RequestMapping("/repeatAddBusNeed.do")
	public @ResponseBody Integer repeatAddBusNeed(HttpServletRequest request, HttpSession session) throws ParseException {
		
		JSONObject jsonObject = JSONObject.fromObject(request.getParameter("busNeed"));
		Integer bune_id = null;
		if (request.getParameter("bune_id") != null) {
			bune_id = Integer.parseInt(request.getParameter("bune_id"));
		}
		Boolean flag = busNeedService.updateBusNeed(bune_id, jsonObject);
		if (flag == true)
			return 1;
		else
			return 0;
	}

}
