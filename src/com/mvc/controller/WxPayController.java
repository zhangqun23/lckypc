package com.mvc.controller;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.base.constants.wxPayConstants;
import com.mvc.service.WxPayService;
import com.utils.WxPayUtil;

public class WxPayController {
	
	@Autowired
	WxPayService wxPayService;
	
	public String PaySult(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
	       String resXml = "";

	       InputStream inStream;
	       try {
	           inStream = request.getInputStream();

	           ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
	           byte[] buffer = new byte[1024];
	           int len = 0;
	           while ((len = inStream.read(buffer)) != -1) {
	               outSteam.write(buffer, 0, len);
	           }
	           outSteam.close();
	           inStream.close();
	           String result = new String(outSteam.toByteArray(), "utf-8");// 获取微信调用我们notify_url的返回信息 
	           Map<String, String> map = WxPayUtil.xmlToMap(result);

	           if (map.get("result_code").toString().equalsIgnoreCase("SUCCESS")) {
	               //logger.error("微信支付----返回成功");
	               if (WxPayUtil.isSignatureValid(map,wxPayConstants.PATERNERKEY,"MD5")) {
	                   // 通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.
	                   resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
	                           + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
	                    // 处理业务 -修改订单支付状态  
	                    System.out.println("微信支付回调：修改的订单=" + map.get("out_trade_no"));
	                    wxPayService.updateTradeState(map.get("out_trade_no"));
	                    
	                }
	                // ------------------------------  
	                // 处理业务完毕  
	                // ------------------------------  
	                BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
	                out.write(resXml.getBytes());
	                out.flush();
	                out.close();
	           }
	       } catch (IOException e) {
	           //logger.error("支付回调发布异常：" + e);
	           e.printStackTrace();
	       }
	       return resXml;
	}

}
