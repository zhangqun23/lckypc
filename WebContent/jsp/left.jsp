<script type="text/javascript" src="${ctx}/js/lib/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="${ctx}/js/lib/jquery.json-2.2.min.js"></script>
<section class="leftbar"  style="display:block;background-color:red;">
	<dl class="leftmenu"  style="display:block">
				
		<!-- 基础信息管理 -->
		<dd id="userManagement" class="userManager" >
			<div class="title">
				<span><img src="${ctx}/images/leftico02.png" /></span>用户管理
			</div>
			<ul id="system-ul" class="menuson">
				<li id="roleList"><cite></cite> <a
					href="${ctx}/role/toUserManagePage.do#/roleList">角色管理</a><i></i></li>
				<li id="userList"><cite></cite> <a
					href="${ctx}/role/toUserManagePage.do#/userList">用户管理</a><i></i></li>
				
			</ul>
		</dd>
		
		<!-- 旅游信息管理 -->
		<dd id="travel" class="travelManager" >
			<div class="title ">
				<span><img src="${ctx}/images/leftico01.png" /></span>旅游管理
			</div>
			<ul id="travel-ul" class="menuson">
				<li id="travelAdd"><cite></cite> <a
					href="${ctx}/travel/toTravelPage.do#/travelAdd">新建旅游信息</a><i></i></li>
				<li id="travelList"><cite></cite> <a
					href="${ctx}/travel/toTravelPage.do#/travelList">旅游信息查询</a><i></i></li>
				<li id="travelTradeList"><cite></cite> <a
					href="${ctx}/travel/toTravelTradePage.do#/travelTradeList">旅游交易信息查询</a><i></i></li>
			<%-- href="${ctx}/travelTrade/toTravelPage.do#/travelTradeList"> --%>
			</ul>
		</dd>
		
		<!-- 广告信息管理 -->
		<dd id="ad" class="adManager" >
			<div class="title ">
				<span><img src="${ctx}/images/leftico01.png" /></span>广告管理
			</div>
			<ul id="ad-ul" class="menuson">
				<li id="adList"><cite></cite> <a
					href="${ctx}/ad/toAdPage.do#/adList">广告信息查询</a><i></i></li>
			</ul>
		</dd>
		
		<!-- smgo信息管理 -->
		<dd id="smgo" class="smgoManager" >
			<div class="title ">
				<span><img src="${ctx}/images/leftico01.png" /></span>smgo管理
			</div>
			<ul id="smgo-ul" class="menuson">
				<li id="smgoList"><cite></cite> <a
					href="${ctx}/smgo/toSmgoPage.do#/smgoList">smgo信息查询</a><i></i></li>
			</ul>
		</dd>
	</dl>
</section>
<script>
	$(document)
			.ready(
					function() {
						//根据权限显示左侧栏相关条目
						$
								.get(
										"/CIMS/login/getLeftbarPermission.do",
										function(data) {
											console.log("左侧栏权限：" + data);
											var leftbarPermission = data
													.substring(1,
															data.length - 2)
													.split(",");
											for (var i = 0, len = leftbarPermission.length; i < len; i++) {
												if (leftbarPermission[i].trim()) {
													var $temp = $('.'
															+ leftbarPermission[i]
																	.trim());
													if ($temp) {
														$temp.css('display',
																'block');
													}
												}

											}
										});
						//点击li时将当前页面的信息存入sessionStorage
						var $li = $('.leftmenu li');
						$li.click(function() {
							sessionStorage.setItem("currentPage", $(this).attr(
									'id'));
						});
					});
</script>
