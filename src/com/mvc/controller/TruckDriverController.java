package com.mvc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mvc.entity.Truck;
import com.mvc.entity.TruckNeed;
import com.mvc.entity.TruckSend;
import com.mvc.service.TruckDriverService;
import com.utils.Pager;

import net.sf.json.JSONObject;

/**
 * 零担货运
 * 
 * @author ghl
 * @date 2017年9月6日
 */
@Controller
@RequestMapping("/truckLoad")
public class TruckDriverController {
	@Autowired
	TruckDriverService truckDriverService;

	
	@RequestMapping("/toTruckLoadPage.do")
	public String TruckLoadPage() {
		return "truckLoad/index";
	}

	/**
	 * Truck
	 */
	//根据truck_check查询车辆信息
	@RequestMapping("/getTruckDriverList.do")
	public @ResponseBody String getTruckDriverList(HttpServletRequest request, HttpSession session) {
		String trState = null;
		if (request.getParameter("trState") != null) {
			trState = JSONObject.fromObject(request.getParameter("trState")).getString("trck_check");
		}
		Integer totalRow = truckDriverService.countTotal(trState);
		Pager pager = new Pager();
		pager.setPage(Integer.valueOf(request.getParameter("page")));
		if(totalRow != 0){
			pager.setTotalRow(Integer.parseInt(totalRow.toString()));
		}
		List<Truck> list = truckDriverService.findTruck(trState,pager.getOffset(), pager.getLimit());
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("totalPage", pager.getTotalPage());
		jsonObject.put("list", list);
		return jsonObject.toString();
	}
	//Truck信息模态框显示
	@RequestMapping("/checkTruck.do")
	public @ResponseBody String checkTruck(HttpServletRequest request , HttpSession session){
		Integer trck_id = Integer.parseInt(request.getParameter("trck_id"));
		session.setAttribute("trck_id", trck_id);
		Truck truck = truckDriverService.findTruckInfo(trck_id);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("truck", truck);
		return jsonObject.toString();
	}
	//删除Truck
	@RequestMapping("/deleteTruck.do")
	public @ResponseBody String deleteTruck(HttpServletRequest request, HttpSession session){
		Integer trckId = Integer.parseInt(request.getParameter("trckId"));
		Boolean flag = truckDriverService.deleteTruck(trckId);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("flag", flag);
		return jsonObject.toString();
	}
	/**
	 * TruckSend
	 */
	//查询Trucksend
	@RequestMapping("/getTruckSend.do")
	public @ResponseBody String getTruckSend(HttpServletRequest request ,HttpSession session){
		Integer totalRow = truckDriverService.countTotalPage();
		Pager pager = new Pager();
		pager.setPage(Integer.valueOf(request.getParameter("page")));		
		if(totalRow != 0){
			pager.setTotalRow(Integer.parseInt(totalRow.toString()));
		}
		List<TruckSend> list = truckDriverService.getTruckSend(pager.getOffset(), pager.getLimit());
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("totalPage", pager.getTotalPage());
		jsonObject.put("list", list);
		return jsonObject.toString();
	}
	/**
	 * TruckNeed
	 */
	//查询TruckNeed
	@RequestMapping("/getTruckNeed.do")
	public @ResponseBody String getTruckNeed(HttpServletRequest request, HttpSession session){
		Integer totalRow = truckDriverService.TotalPage();
		Pager pager = new Pager();
		pager.setPage(Integer.valueOf(request.getParameter("page")));
		if(totalRow != 0){
			pager.setTotalRow(Integer.parseInt(totalRow.toString()));
		}
		List<TruckNeed> list = truckDriverService.getTruckNeed(pager.getOffset(), pager.getLimit());
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("totalPage", pager.getTotalPage());
		jsonObject.put("list", list);
		return jsonObject.toString();	
		
	}

}
