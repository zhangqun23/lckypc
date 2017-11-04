﻿<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8" />
<title>旅游信息管理</title>
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/admin.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/zhou.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/li.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/lib/jquery-1.9.1.min.js"></script>
</head>

<body style="background:url(${ctx}/images/topbg.gif) repeat-x;">
	<header>
		<div class="topleft">
			<a href="/lckypc/login/toIndex.do" target="_parent"><img
				class="img-logo" src="${ctx}/images/luochuan.gif" title="系统首页" /></a>
		</div>



		<div class="topright">
			<ul>
				<%-- <li><span><img src="${ctx}/images/help.png" title="帮助"  class="helpimg"/></span><a href="#">帮助</a></li> --%>
				<!-- <li><a href="#">关于</a></li> -->
				<li><a href="/lckypc/login/logout.do">安全退出</a></li>
			</ul>

			<div class="user">
				<span id="userNum"></span> <i>消息</i><a
					href="/lckypc/login/toIndex.do"><b id="newsNum">0</b></a>
			</div>

		</div>
		<div id="news" style="height: auto; width: auto;">
			<ul>
				<li><a
					href="/lckypc/travel/toTravelTradePage.do#/travelTradeList">当天旅游交易数：<b
						id="travel_num"></b></a></li>
				<li><a href="/lckypc/busNeed/toBusNeedPage.do#/busNeedList">班车定制：<b
						id="busNeed_num"></b></a></li>
				<li><a href="/lckypc/ad/toAdPage.do#/adList">广告审核：<b
						id="ad_num"></b></a></li>
				<li><a href="/lckypc/truckLoad/toTruckLoadPage.do#/truckList">货车审核：<b
						id="truck_num"></b></a></li>
				<li><a href="/lckypc/smgo/toSmgoPage.do#/smgoList">小件快运：<b
						id="smallGoods_num"></b></a></li>
			</ul>
		</div>
		<audio id="audio" class="hidden">
			<source src="${ctx}/audio/Msg.mp3"></source>
			<source src="${ctx}/audio/msg.wav"></source>
		</audio>
	</header>
	<section class="containner">
		<!-- 加载模态框 -->
<!-- 		<div class="overlayer"></div> -->
		<div class="tipLoading">
			<img class="tipimage" src="../images/wait.gif" />
			<div class="tiptext">正在加载，请稍后……</div>
		</div>
		<!-- 加载模态框 -->
		<script type="text/javascript"
			src="${ctx}/js/lib/jquery.json-2.2.min.js"></script>
		<script type="text/javascript">
			//消息闪烁
			var show = function() { //有新消息时在title处闪烁提示
				var step = 0, _title = document.title;
				var timer = setInterval(function() {
					step++;
					if (step == 3) {
						step = 1
					}
					;
					if (step == 1) {
						document.title = '【　　　】' + _title
					}
					;
					if (step == 2) {
						document.title = '【新消息】' + _title
					}
					;
				}, 500);
				return [ timer, _title ];
			}
			var clear = function(timerArr) { //去除闪烁提示，恢复初始title文本
				if (timerArr) {
					clearInterval(timerArr[0]);
					document.title = timerArr[1];
				}
				;
			}

			function initData() {
				$.getJSON("/lckypc/login/getInitData.do", {}, function(data) {
					$("#travel_num").text(data.alarmStatistic.travel_num);
					$("#busNeed_num").text(data.alarmStatistic.busNeed_num);
					$("#ad_num").text(data.alarmStatistic.ad_num);
					$("#truck_num").text(data.alarmStatistic.truck_num);
					$("#smallGoods_num").text(
							data.alarmStatistic.smallGoods_num);
					msgCnt = 0 + data.alarmStatistic.travel_num
							+ data.alarmStatistic.busNeed_num
							+ data.alarmStatistic.ad_num
							+ data.alarmStatistic.truck_num
							+ data.alarmStatistic.smallGoods_num;
					$("#newsNum").html(msgCnt);
				})
			}
			$(function() {
				var blinkTitl
				var cookie = {};
				var cookies = document.cookie;
				if (cookies === "")
					return cookie;
				var list = cookies.split(";");
				for (var i = 0; i < list.length; i++) {
					var cookieString = list[i];
					var p = cookieString.indexOf("=");
					var name = cookieString.substring(0, p);
					var value = cookieString.substring(p + 1,
							cookieString.length);
					console.log(name);
					console.log(value);
					cookie[name.trim()] = value;
				}
				$('#userNum').html(cookie.userNum);

				initData();
				var msgCnt;
				var title = document.title;
				window.setInterval(showalert, 1000 * 60 * 5);
				function showalert() {
					var lastMsgCnt = sessionStorage.getItem("msgCnt");
					$.getJSON("/lckypc/login/getInitData.do", {},
							function(data) {
								$("#travel_num").text(
										data.alarmStatistic.travel_num);
								$("#busNeed_num").text(
										data.alarmStatistic.busNeed_num);
								$("#ad_num").text(data.alarmStatistic.ad_num);
								$("#truck_num").text(
										data.alarmStatistic.truck_num);
								$("#smallGoods_num").text(
										data.alarmStatistic.smallGoods_num);
								msgCnt = 0 + data.alarmStatistic.travel_num
										+ data.alarmStatistic.busNeed_num
										+ data.alarmStatistic.ad_num
										+ data.alarmStatistic.truck_num
										+ data.alarmStatistic.smallGoods_num;
								$("#newsNum").html(msgCnt);
								sessionStorage.setItem("msgCnt", msgCnt);
								if (msgCnt > lastMsgCnt) {
									//todo--提示爆闪
									var timerArr = show();
									$('#audio')[0].play();
									setTimeout(function() { //此处是过一定时间后自动消失
										clear(timerArr);
									}, 5000);
								} else {
									document.title = title;
								}

							});

				}
				//鼠标移到user上时显示消息的div，在消息div以外任意地方点击鼠标时消息div隐藏
				$(".user").hover(function() {
					$("#news").show();
				}, function() {
				})
				$(document).click(function() {
					$("#news").hide();

				});
				$("#news").click(function(event) {
					event.stopPropagation();//阻止事件冒泡
				});
			});
		</script>