package com.mvc.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.mvc.entity.SmallGoods;
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
		List<SmallGoods> list = smgoService.findSmgoByPage(searchKey, pager.getOffset(), pager.getLimit());
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
		List<SmallGoods> list = null;
		JSONObject jsonObject = new JSONObject();
		if(request.getParameter("smgoSego") != null){
			smgoSego = JSONObject.fromObject(request.getParameter("smgoSego")).getString("smgo_sego");
			Integer totalRow = smgoService.countSegoTotal(smgoSego);
			Pager pager = new Pager();
			pager.setPage(Integer.valueOf(request.getParameter("page")));
			pager.setTotalRow(Integer.parseInt(totalRow.toString()));
			list = smgoService.findSmgoBySego(smgoSego,pager.getOffset(),pager.getLimit());
			jsonObject.put("totalPage", pager.getTotalPage());
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
	 *@Title: addEdit 
	 *@Description: 添加补录信息
	 *@param @param request
	 *@param @param session
	 *@param @return
	 *@param @throws ParseException
	 *@return String
	 *@throws
	 */
	@RequestMapping("/addEdit.do")
	public @ResponseBody String addEdit(HttpServletRequest request, HttpSession session) throws ParseException {
		JSONObject jsonObject = new JSONObject();
		jsonObject = JSONObject.fromObject(request.getParameter("smgoNeed"));
		SmallGoods smgo = new SmallGoods();
		Date edittime = null;
		float editprice = 0;
		if(jsonObject.containsKey("edit_time")){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(jsonObject.getString("edit_time"));
			smgo.setEdit_time(date);
			edittime = sdf.parse(jsonObject.getString("edit_time"));	
		}
		if(jsonObject.containsKey("edit_price")){
			smgo.setEdit_price(Float.parseFloat(jsonObject.getString("edit_price")));
			editprice = Float.parseFloat(jsonObject.getString("edit_price"));
		}
		smgo.setIs_delete(false);
		boolean result = false;
		if(request.getParameter("smgoid") !=null){
			String temp = request.getParameter("smgoid");
			Integer smgoid = Integer.parseInt(temp);
			result = smgoService.update(edittime, editprice, smgoid);
		}
		return JSON.toJSONString(result);
	}
}
