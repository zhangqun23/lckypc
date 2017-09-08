package com.mvc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mvc.entity.Ad;
import com.mvc.service.AdService;
import com.mvc.service.UserService;
import com.utils.Pager;
import com.utils.StringUtil;

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
<<<<<<< HEAD
	 *@Description: ad首页
=======
	 *@Description: 广告首页
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
	 *@Description: 页码变换
	 *@param @param request
	 *@param @param session
>>>>>>> 95c2cc36851a97c50c66e34ffdd019500e70fbd0
	 *@param @return
	 *@return String
	 *@throws
	 */
<<<<<<< HEAD
	@RequestMapping("/toAdPage.do")
	public String adPage() {
		return "adInformation/index";
=======
	@RequestMapping(value = "/getAdListByPage.do")//路径ok
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
>>>>>>> 95c2cc36851a97c50c66e34ffdd019500e70fbd0
	}
	
	/**
	 * 
	 * 
<<<<<<< HEAD
	 *@Title: getAdsByPrarm 
	 *@Description: 页码
=======
	 *@Title: editAd 
	 *@Description: ad修改、（查询）
	 *@param @param request
	 *@param @param session
	 *@param @return
	 *@return String
	 *@throws
	 */
	@RequestMapping("/editAd.do")//路径ok
	public @ResponseBody String editAd(HttpServletRequest request, HttpSession session) {
		JSONObject jsonObject= JSONObject.fromObject(request.getParameter("ad"));
		Ad ad= new  Ad();
		if (jsonObject.containsKey("ad_type")){
			if (StringUtil.strIsNotEmpty(jsonObject.getString("ad_type"))){
			ad.setAd_type(Integer.parseInt(jsonObject.getString("ad_type")));
			}
		}
		if (jsonObject.containsKey("ad_name")){
			if (StringUtil.strIsNotEmpty(jsonObject.getString("ad_name"))){
				ad.setAd_name(jsonObject.getString("ad_name"));
			}
		}
		if (jsonObject.containsKey("ad_tel")){
			if (StringUtil.strIsNotEmpty(jsonObject.getString("ad_tel"))){
				ad.setAd_tel(jsonObject.getString("ad_tel"));
			}	
		}
		if (jsonObject.containsKey("ad_title")){
			if (StringUtil.strIsNotEmpty(jsonObject.getString("ad_title"))){
				ad.setAd_title(jsonObject.getString("ad_title"));
			}
		}
		if (jsonObject.containsKey("ad_remark")){
			if (StringUtil.strIsNotEmpty(jsonObject.getString("ad_remark"))){
				ad.setAd_remark(jsonObject.getString("ad_remark"));
			}else{
				ad.setAd_remark("");
			}
		}
		if (jsonObject.containsKey("ad_content")){
			if (StringUtil.strIsNotEmpty(jsonObject.getString("ad_content"))){
				ad.setAd_content(jsonObject.getString("ad_content"));
			}
		}
		if (jsonObject.containsKey("ad_state")){
			if (StringUtil.strIsNotEmpty(jsonObject.getString("ad_state"))){
			ad.setAd_state(Integer.parseInt(jsonObject.getString("ad_state")));
			}
		}
		ad.setIs_delete(true);
		Ad result = null;
		if (jsonObject.containsKey("ad_id")) {
			if (StringUtil.strIsNotEmpty(jsonObject.getString("ad_id"))){
				ad.setAd_id(Integer.valueOf(jsonObject.getString("ad_id")));
				result = adService.saveAd(ad);// 修改广告
			}
		}
		JSONObject limit = new JSONObject();
		limit.put("result", result);
		return limit.toString(); 
	}
	
	/**
	 * 
	 * 
	 *@Title: selectAdByType 
	 *@Description: 返回相应类型的广告
>>>>>>> 95c2cc36851a97c50c66e34ffdd019500e70fbd0
	 *@param @param request
	 *@param @param session
	 *@param @return
	 *@return String
	 *@throws
	 */
<<<<<<< HEAD
	@RequestMapping("/getAdListByPage.do")
	public @ResponseBody String getAdsByPrarm(HttpServletRequest request, HttpSession session) {
=======
	@RequestMapping("/selectAdByType.do")//路径ok
	public @ResponseBody String selectAdByType(HttpServletRequest request, HttpSession session){
		String adType;
		List<Ad> list ;
		if(request.getParameter("adType") != null){
		adType= JSONObject.fromObject(request.getParameter("adType")).getString("ad_type");
		list = adService.findAdByType(Integer.parseInt(adType));//返回相应类型的广告
		}else{
			list = adService.findAdAlls();//类型为空返回全部广告
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("list", list);
		return jsonObject.toString();
	}
	
	/**
	 * 
	 * 
	 *@Title: selectAdByState 
	 *@Description: 返回相应状态的广告
	 *@param @param request
	 *@param @param session
	 *@param @return
	 *@return String
	 *@throws
	 */
	@RequestMapping("/selectAdByState.do")//路径ok
	public @ResponseBody String selectAdByState(HttpServletRequest request, HttpSession session){
		String adState;
		List<Ad> list ;
		if(request.getParameter("adState") != null){
		adState= JSONObject.fromObject(request.getParameter("adState")).getString("ad_state");
		list = adService.findAdByState(Integer.parseInt(adState));//返回相应类型的广告
		}else{
			list = adService.findAdAlls();//类型为空返回全部广告
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("list", list);
		return jsonObject.toString();
	}
	
	/**
	 * 
	 * 
	 *@Title: deleteAd 
	 *@Description: 根据id删除ad
	 *@param @param request
	 *@param @return
	 *@return String
	 *@throws
	 */
	@RequestMapping("/deleteAd.do")//路径ok
	public @ResponseBody String deleteAd (HttpServletRequest request){
		Integer adId = Integer.parseInt(request.getParameter("ad_id"));
		Boolean flag = adService.deleteAd(adId);
>>>>>>> 95c2cc36851a97c50c66e34ffdd019500e70fbd0
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("flag", flag);
		return jsonObject.toString();
	}
<<<<<<< HEAD
=======
	
	/**
	 * 
	 * 
	 *@Title: selectAdInfo 
	 *@Description: 根据id查找ad
	 *@param @param request
	 *@param @return
	 *@return String
	 *@throws
	 */
	@RequestMapping("/selectAdInfo.do")//路径ok
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
//	@RequestMapping("/selectAdById.do")
//	public @ResponseBody String selectAdById(HttpServletRequest request, HttpSession session) {
//		int ad_id = Integer.parseInt(request.getParameter("ad_id"));
//		session.setAttribute("ad_id", ad_id);
//		Ad ad = adService.selectAdById(ad_id);
//		JSONObject jsonObject = new JSONObject();
//		jsonObject.put("ad", ad);
//		return jsonObject.toString();
//	}
	
	/**
	 * 返回旅游管理界面
	 * 
	 * @return
	 */
>>>>>>> 95c2cc36851a97c50c66e34ffdd019500e70fbd0
	
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
<<<<<<< HEAD
	@RequestMapping("/deleteAd.do")
	public @ResponseBody String deleteAd(HttpServletRequest request, HttpSession session) {
		Integer adid = Integer.valueOf(request.getParameter("adId"));
		boolean result = adService.deleteIsdelete(adid);
		return JSON.toJSONString(result);
	}
	
=======
//	@RequestMapping(value = "/deleteAd.do")
//	public @ResponseBody String deleteAd(HttpServletRequest request, HttpSession session) {
//		Integer adid = Integer.valueOf(request.getParameter("adId"));
//		boolean result = adService.deleteIsdelete(adid);
//		return JSON.toJSONString(result);
//	}

>>>>>>> 95c2cc36851a97c50c66e34ffdd019500e70fbd0
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
<<<<<<< HEAD
	@RequestMapping("/editState.do")	
	public @ResponseBody boolean editState(HttpServletRequest request, HttpSession session) {
		Integer adid = Integer.valueOf(request.getParameter("adId"));
		boolean result = adService.editState(adid);
		return result;
	}
=======
//	@RequestMapping("/isAdTitleExist.do")
//	public @ResponseBody Long checkUserName(HttpServletRequest request, HttpSession session, ModelMap map) {
//		String adTitle = request.getParameter("adTitle");
//		Long result = adService.isExist(adTitle);
//		return result;
//	}
	
>>>>>>> 95c2cc36851a97c50c66e34ffdd019500e70fbd0
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
<<<<<<< HEAD
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
=======
//	@RequestMapping("/selectAdByTitle.do")
//	public @ResponseBody String selectAdByTitle(HttpServletRequest request, HttpSession session) {
//		String aTitle = request.getParameter("ad_title");
//		session.setAttribute("ad_title", aTitle);
//		List<Ad> ad = adService.selectAdByTitle(aTitle, null, null);//有问题
//		JSONObject jsonObject = new JSONObject();
//		jsonObject.put("ad", ad);
//		return jsonObject.toString();
//	}
	
	/**
	 * 根据合同ID修改旅游信息
	 * 
	 * @param request
	 * @param session
	 * @return 成功返回1，失败返回0
	 * @throws ParseException 
	 */
//	@RequestMapping("/updateAdById.do")
//	public @ResponseBody Integer updateAdById(HttpServletRequest request, HttpSession session) throws ParseException {
//		User user = (User) session.getAttribute(SessionKeyConstants.LOGIN);
//		JSONObject jsonObject = JSONObject.fromObject(request.getParameter("ad"));
//		Integer ad_id = null;
//		if (jsonObject.containsKey("ad_id")) {
//			ad_id = Integer.parseInt(jsonObject.getString("ad_id"));
//		}
//		Boolean flag = adService.updateAdBase(ad_id, jsonObject, user);
//		if (flag == true)
//			return 1;
//		else
//			return 0;
//	}
}
>>>>>>> 95c2cc36851a97c50c66e34ffdd019500e70fbd0
