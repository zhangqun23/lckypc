<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<meta charset="utf-8" />
<jsp:include page="/jsp/top.jsp" />
<section id="ng-section" class="main">
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
		</ul>
	</div>

	<div class="mainindex" ng-controller="IndexController">
		<div class="welinfo">
			<span><img src="${ctx}/images/sun.png" alt="天气" /></span> <b>欢迎使用洛川客运信息系统</b>
			<!-- <a href="#">帐号设置</a> -->
		</div>

		<div class="xline"></div>
		<br> <br>
		<div class="welinfo">
			<span><img src="${ctx}/images/dp.png" alt="提醒" /></span> <b>信息统计</b>
		</div>

		<ul class="infolist">
			<li has-permission='iAudiInvoTask'><a
				href="${ctx}/travel/toTravelPage.do#/travelList"
				class="">已发布旅游信息条数：{{waitAuditBillTaskNum}}</a></li>
			<li has-permission='iAssiTask'><a
				href="${ctx}/busNeed/toBusNeedPage.do#/busNeedList" 
				class="">未补录的班车预订信息条数：{{assistantTaskNum}}</a></li>
			<li has-permission='iEditTask'><a
				href="${ctx}/task/toTaskPage.do#/receiveTask" class="">补录合同任务：{{managerControlTaskNum}}</a></li>
			<li has-permission='iFiniInvoTask'><a
				href="${ctx}/invoice/toBillMngInvoicePage.do#/invoiceTaskList"
				class="">发票任务： {{billTaskNum}}</a></li>
			<li has-permission="iFiniRemoTask"><a 
				href="${ctx}/receiveMoney/toBillMngInvoicePage.do#/receiveMoneyTaskList"
				class="">核对到款任务： {{remoTaskNum}}</a></li>
			<li><a href="${ctx}/task/toTaskPage.do#/receiveTask" class="">普通任务：{{otherTaskNum}}</a></li>
		</ul>

		<div class="welinfo">
			<span><img src="${ctx}/images/dp.png" alt="提醒" /></span> <b>报警信息</b>
		</div>

		<ul class="infolist">
			<li has-permission='iDebtAlarm'><a
				href="${ctx}/alarm/toAlarmPage.do#/debtAlarmList" class="">收款相关：{{debtAlarmNum}}</a></li>
			<li has-permission='iOverdueAlarm'><a
				href="${ctx}/alarm/toAlarmPage.do#/overdueAlarmList" class="">工期相关：{{overdueAlarmNum}}</a></li>
			<li><a href="${ctx}/alarm/toAlarmPage.do#/taskAlarmList"
				class="">任务超时：{{taskAlarmNum}}</a></li>
		</ul>
	</div>
</section>
<jsp:include page="/jsp/left.jsp" />
<jsp:include page="/jsp/footer.jsp" />
<script src="${ctx}/js/app/index.js"></script>
</body>
</html>