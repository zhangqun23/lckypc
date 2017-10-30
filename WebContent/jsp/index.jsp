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
				href="/lckypc/busNeed/toBusNeedPage.do#/busNeedList" class="">班车定制需求：{{alarmStatistic.busNeed_num}}</a></li>
			<li has-permission='iAudiInvoTask'><a
				href="/lckypc/ad/toAdPage.do#/adList" class="">广告审核：{{alarmStatistic.ad_num}}</a></li>
			<li has-permission='iAudiInvoTask'><a
				href="${ctx}/invoice/toBillMngInvoicePage.do#/invoiceTaskList"
				class="">货车审核：{{alarmStatistic.truck_num}}</a></li>
			<li has-permission='iAudiInvoTask'><a
				href="${ctx}/invoice/toBillMngInvoicePage.do#/invoiceTaskList"
				class="">小件快运：{{alarmStatistic.smallGoods_num}}</a></li>
		</ul>


	</div>
</section>
<jsp:include page="/jsp/left.jsp" />
<jsp:include page="/jsp/footer.jsp" />
<script src="${ctx}/js/app/index.js"></script>
</body>
</html>