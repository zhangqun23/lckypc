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
import com.mvc.entity.Smgo;
import com.mvc.entity.User;
import com.mvc.service.SmgoService;
import com.mvc.service.UserService;
import com.utils.Pager;

import net.sf.json.JSONObject;

/**
 * 
 * @ClassName: SmgoCotroller
 * @Description: smgo信息
 * @author ycj
 * @date 2017年9月6日 下午8:41:53 
 * 
 *
 */
@Controller
@RequestMapping("/smgo")
public class SmgoCotroller {

	@Autowired
	SmgoService smgoService;
	@Autowired
	UserService userService;
	
	/**
	 * 
	 * 
	 *@Title: smgoPage 
	 *@Description: smgo首页
	 *@param @return
	 *@return String
	 *@throws
	 */
	@RequestMapping("/toSmgoPage.do")
	public String smgoPage() {
		return "smgoInformation/index";
	}
	
	/**
	 * 
	 * 
	 *@Title: getSmgosByPrarm 
	 *@Description: 页码
	 *@param @param request
	 *@param @param session
	 *@param @return
	 *@return String
	 *@throws
	 */
	@RequestMapping("/getSmgoListByPage.do")
	public @ResponseBody String getSmgosByPrarm(HttpServletRequest request, HttpSession session) {
		JSONObject jsonObject = new JSONObject();
		String searchKey = request.getParameter("searchKey");
		Integer totalRow = smgoService.countTotal(searchKey);
		Pager pager = new Pager();
		pager.setPage(Integer.valueOf(request.getParameter("page")));
		pager.setTotalRow(Integer.parseInt(totalRow.toString()));
		List<Smgo> list = smgoService.findSmgoByPage(searchKey, pager.getOffset(), pager.getLimit());
		jsonObject.put("list", list);
		jsonObject.put("totalPage", pager.getTotalPage());
		System.out.println("totalPage:" + pager.getTotalPage());
		return jsonObject.toString();
	}
	
	/**
	 * 
	 * 
	 *@Title: selectSmgoBySego 
	 *@Description: 根据sego筛选smgo信息
	 *@param @param request
	 *@param @param session
	 *@param @return
	 *@return String
	 *@throws
	 */
	@RequestMapping("/selectSmgoBySego.do")
	public @ResponseBody String selectSmgoBySego(HttpServletRequest request, HttpSession session) {
		String smgoSego;
		List<Smgo> list;
		JSONObject jsonObject = new JSONObject();
		if(request.getParameter("smgoSego") != null){
			smgoSego = JSONObject.fromObject(request.getParameter("smgoSego")).getString("smgo_sego");
			Integer totalRow = smgoService.countSegoTotal(smgoSego);
			Pager pager = new Pager();
			pager.setPage(Integer.valueOf(request.getParameter("page")));
			pager.setTotalRow(Integer.parseInt(totalRow.toString()));
			list = smgoService.findSmgoBySego(smgoSego,pager.getOffset(),pager.getLimit());
			jsonObject.put("totalPage", pager.getTotalPage());
		}else{
			list = smgoService.findAlls();
		}
		jsonObject.put("list", list);
		return jsonObject.toString();
	}
	
	/**
	 * 
	 * 
	 *@Title: deleteSmgo 
	 *@Description: 删除smgo
	 *@param @param request
	 *@param @param session
	 *@param @return
	 *@return String
	 *@throws
	 */
	@RequestMapping("/deleteSmgo.do")
	public @ResponseBody String deleteSmgo(HttpServletRequest request, HttpSession session) {
		Integer smgoid = Integer.valueOf(request.getParameter("smgoId"));
		boolean result = smgoService.deleteIsdelete(smgoid);
		return JSON.toJSONString(result);
	}
	
	/**
	 * 
	 * 
	 *@Title: selectSmgoById 
	 *@Description: 根据id获取smgo信息
	 *@param @param request
	 *@param @param session
	 *@param @return
	 *@return String
	 *@throws
	 */
	@RequestMapping("/selectSmgoById.do")
	public @ResponseBody String selectSmgoById(HttpServletRequest request, HttpSession session) {
		int smgo_id = Integer.parseInt(request.getParameter("smgo_id"));
		session.setAttribute("smgo_id", smgo_id);
		Smgo smgo = smgoService.selectSmgoById(smgo_id);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("smgo", smgo);
		return jsonObject.toString();
	}
	
	/**
	 * 
	 * 
	 *@Title: updateSmgoById 
	 *@Description: 根据id修改smgo信息，成功返回1
	 *@param @param request
	 *@param @param session
	 *@param @return
	 *@param @throws ParseException
	 *@return Integer
	 *@throws
	 */
	@RequestMapping("/updateSmgoById.do")
	public @ResponseBody Integer updateSmgoById(HttpServletRequest request, HttpSession session) throws ParseException {
		User user = (User) session.getAttribute(SessionKeyConstants.LOGIN);
		JSONObject jsonObject = JSONObject.fromObject(request.getParameter("smgo"));
		Integer smgo_id = null;
		if (jsonObject.containsKey("smgo_id")) {
			smgo_id = Integer.parseInt(jsonObject.getString("smgo_id"));
		}
		Boolean flag = smgoService.updateSmgoBase(smgo_id, jsonObject, user);
		if (flag == true)
			return 1;
		else
			return 0;
	}
}
