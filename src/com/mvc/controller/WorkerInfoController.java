package com.mvc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mvc.entity.WorkerInfo;
import com.mvc.service.WorkerInfoService;
import com.utils.MD5;
import com.utils.Pager;

import net.sf.json.JSONObject;

import com.alibaba.fastjson.JSON;

/**
 * 用户相关内容
 * 
 * @author wdh
 * @date 2017年8月28日
 */

@Controller
@RequestMapping("/workerInfo")
public class WorkerInfoController {
	@Autowired
	WorkerInfoService workerInfoService;
	/**
	 * 返回用户管理界面
	 * 
	 * @return
	 */
	@RequestMapping("/toWoinPage.do")
	public String woinPage() {
		return "workerInfomation/index";
	}
	/**
	 * 根据页数筛选用户列表
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getWoinListByPage.do")
	public @ResponseBody String getUsersByPrarm(HttpServletRequest request, HttpSession session) {
		JSONObject jsonObject = new JSONObject();
		String searchKey = request.getParameter("searchKey");
		Integer totalRow = workerInfoService.countTotal(searchKey);
		Pager pager = new Pager();
		pager.setPage(Integer.valueOf(request.getParameter("page")));
		pager.setTotalRow(Integer.parseInt(totalRow.toString()));
		List<WorkerInfo> list = workerInfoService.findWoinAllByPage(searchKey, pager.getOffset(), pager.getLimit());
		jsonObject.put("list", list);
		jsonObject.put("totalPage", pager.getTotalPage());
		System.out.println("totalPage:" + pager.getTotalPage());
		return jsonObject.toString();
	}

	
	/**
	 * 删除用户
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/deleteWoin.do")
	public @ResponseBody String deleteUser(HttpServletRequest request, HttpSession session) {
		Integer woinid = Integer.valueOf(request.getParameter("woinId"));
		boolean result = workerInfoService.deleteIsdelete(woinid);
		return JSON.toJSONString(result);
	}

	/**
	 * 添加,修改用户信息
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/addWoin.do")
	public @ResponseBody String addWoin(HttpServletRequest request, HttpSession session) {
		JSONObject jsonObject = new JSONObject();
		jsonObject = JSONObject.fromObject(request.getParameter("workerInfo"));
		WorkerInfo workerInfo = new WorkerInfo();
		workerInfo.setWoin_num(jsonObject.getString("woin_num"));
		workerInfo.setWoin_name(jsonObject.getString("woin_name"));
		workerInfo.setWoin_pwd(MD5.encodeByMD5(jsonObject.getString("woin_pwd")));
		workerInfo.setWoin_state(0);
		boolean result;

		if (jsonObject.containsKey("woin_id")) {
			workerInfo.setWoin_id(Integer.valueOf(jsonObject.getString("woin_id")));
			result = workerInfoService.save(workerInfo);// 修改用户信息
		} else {
			result = workerInfoService.save(workerInfo);// 添加用户信息
		}
		return JSON.toJSONString(result);
	}

	
	/**
	 * 根据ID查看用户详情
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/selectWoinById.do")
	public @ResponseBody String getWoinContentById(HttpServletRequest request, HttpSession session) {
		JSONObject jsonObject = new JSONObject();
		Integer woinid = Integer.valueOf(request.getParameter("woinid"));
		WorkerInfo workerInfo = workerInfoService.findWoinById(woinid);
		jsonObject.put("workerInfo", workerInfo);
		return jsonObject.toString();
	}

	/**
	 * 检查用户是否已经存在:返回1存在，返回0不存在
	 * 
	 * @param request
	 * @param session
	 * @param map
	 * @return
	 */
	@RequestMapping("/isWoinNumExist.do")
	public @ResponseBody Long checkWoinName(HttpServletRequest request, HttpSession session, ModelMap map) {
		String woinNum = request.getParameter("woinNum");
		Long result = workerInfoService.isExist(woinNum);
		return result;
	}

}