var app = angular
		.module(
				'ad',
				[ 'ngRoute' ],
				function($httpProvider) {// ngRoute引入路由依赖
					$httpProvider.defaults.headers.put['Content-Type'] = 'application/x-www-form-urlencoded';
					$httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';

					// Override $http service's default transformRequest
					$httpProvider.defaults.transformRequest = [ function(data) {
						/**
						 * The workhorse; converts an object to
						 * x-www-form-urlencoded serialization.
						 * 
						 * @param {Object}
						 *            obj
						 * @return {String}
						 */
						var param = function(obj) {
							var query = '';
							var name, value, fullSubName, subName, subValue, innerObj, i;

							for (name in obj) {
								value = obj[name];

								if (value instanceof Array) {
									for (i = 0; i < value.length; ++i) {
										subValue = value[i];
										fullSubName = name + '[' + i + ']';
										innerObj = {};
										innerObj[fullSubName] = subValue;
										query += param(innerObj) + '&';
									}
								} else if (value instanceof Object) {
									for (subName in value) {
										subValue = value[subName];
										fullSubName = name + '[' + subName
												+ ']';
										innerObj = {};
										innerObj[fullSubName] = subValue;
										query += param(innerObj) + '&';
									}
								} else if (value !== undefined
										&& value !== null) {
									query += encodeURIComponent(name) + '='
											+ encodeURIComponent(value) + '&';
								}
							}

							return query.length ? query.substr(0,
									query.length - 1) : query;
						};

						return angular.isObject(data)
								&& String(data) !== '[object File]' ? param(data)
								: data;
					} ];
				});
// 获取权限列表


app.run([ '$rootScope', '$location', function($rootScope, $location) {
	$rootScope.$on('$routeChangeSuccess', function(evt, next, previous) {
		console.log('路由跳转成功');
		$rootScope.$broadcast('reGetData');
	});
} ]);

// 路由配置
app.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/adList', {
		templateUrl : '/lckypc/jsp/adInformation/adList.html',
		controller : 'adController'
	}).when('/adUpdate',	{
				templateUrl : '/lckypc/jsp/adInformation/adUpdate.html',
				controller : 'adController'							
	});
}]);
app.constant('baseUrl', '/lckypc/');
app.factory('services', [ '$http', 'baseUrl', function($http, baseUrl) {
	var services = {};
	services.getAdListByPage = function(data) {
		return $http({
			method : 'post',
			url : baseUrl + 'ad/getAdListByPage.do',
			data : data
		});
	};
	
	services.editState = function(data){
		return $http({
			method : 'post',
			url : baseUrl + 'ad/editState.do',
			data :data
		})
	}
	services.deleteAd = function(data) {
		return $http({
			method : 'post',
			url : baseUrl + 'ad/deleteAd.do',
			data : data
		});
	};
	
	services.selectAdtById = function(data) {
		return $http({
			method : 'post',
			url : baseUrl + 'ad/selectAdById.do',
			data : data
		});
	};
	services.selectAdByState = function(data) {
		return $http({
			method : 'post',
			url : baseUrl + 'ad/selectAdByState.do',
			data : data
		});
	}

	return services;

} ]);

app
		.controller(
				'adController',
				[
						'$scope',
						'services',
						'$location',
						function($scope, services, $location) {

							var ad = $scope;
							var searchKey = null;
							// 换页
							function pageTurn(totalPage, page, Func) {
								$(".tcdPageCode").empty();
								var $pages = $(".tcdPageCode");
								if ($pages.length != 0) {
									$(".tcdPageCode").createPage({
										pageCount : totalPage,
										current : page,
										backFn : function(p) {
											Func(p);
										}
									});
								}
							}
							
							// 根据页数获取ad信息
							function getAdListByPage(page) {
								services.getAdListByPage({
									page : page,
									searchKey : searchKey
								}).success(function(data) {
									ad.ads = data.list;
								});
							}
							
							//根据state筛选ad信息
							ad.ADSLimit={
									ad_state:"审核状态"
							}
							ad.selectAdByState = function(){
								var searchKey = null;
								var adLimit = JSON.stringify(ad.ADSLimit);
								services.selectAdByState({
									page : 1,
									adState : adLimit,
									searchKey : searchKey
								}).success(function(data){
									ad.ads = data.list;
									pageTurn(data.totalPage, 1, ad.selectAdByState)
								});
							}

							// 根据输入筛选信息
							ad.selectAdByTitle = function() {
								searchKey = ad.aTitle;
								services.getAdListByPage({
									page : 1,
									searchKey : searchKey
								}).success(function(data) {
									ad.ads = data.list;
									pageTurn(data.totalPage, 1, getAdListByPage)
								});
							};
							
							// 删除ad信息
							ad.deleteAd = function(ad_id) {
								if (confirm("是否删除该ad信息？") == true) {
									services.deleteAd({
										adId : ad_id
									}).success(function(data) {

										ad.result = data;
										if (data == "true") {
											console.log("删除ad信息成功！");
										} else {
											console.log("删除失败！");
										}
										initData();
									});
								}
							}
							
							//变更state状态
							ad.editState = function(ad_id){
								if(confirm("确定提交变更?") == true){
									services.editState({
										adId : ad_id
									}).success(function(data){
										if(data == "true"){
											alert("变更state信息成功！");
											getAdListByPage(1);
										}else{
											console.log("变更state信息失败！请刷新重试！");
										}	
										initData();
									});
								}
							}
							// 读取旅游信息
							function selectAdById() {
								var ad_id = sessionStorage.getItem('adId');
								services
										.selectAdById({
											ad_id : ad_id
										})
										.success(
												function(data) {
													ad.a = data.ad;
												});
							}
							
							//查询ad信息
							ad.adInfo = function(){
								var aFormData = JSON.stringify(ad.adInfo);
								service.selectAdtById({
									ad : aFormData
								}).success(function(data){
									console.log("获取ad信息");
								})
							}
	                        // 查看ID，并记入sessionStorage
							ad.getAdId = function(adid) {
								/*var adidd = JSON.stringify(adid);
								sessionStorage.setItem('adid',adidd);
								
								console.log(adidd);
								*/
								console.log(sessionStorage.getItem('adid'));
								
								$location.path("adUpdate/");
							};
							
							//初始化
							function initData() {
								console.log("初始化页面信息");
								$("#ad").show();
								if ($location.path().indexOf('/adList') == 0) {
									searchKey = null;
									services.getAdListByPage({
										page : 1,
										searchKey : searchKey
									}).success(function(data) {
										$scope.ads = data.list;
										pageTurn(data.totalPage, 1, getAdListByPage)
									});
								}else if ($location.path().indexOf('/adUpdate') == 0) {
									var adid=sessionStorage.getItem("adid");
									$scope.adInfo = JSON.parse(adid);
							}}
							initData();
							}]);

//时间的格式化的判断
app.filter('dateType', function() {
	return function(input) {

		var type = "";
		if (input != null) {
			type = new Date(input).toLocaleDateString().replace(/\//g, '-');
		}

		return type;
	}
});
//截取任务内容
app.filter('cutString', function() {
	return function(input) {
		var content = "";
		if (input != "") {
			var shortInput = input.substr(0, 4);
			content = shortInput + "……";
		}
		return content;
	}
});

//鼠标放置显示详情
app.filter('onmouse', function() {
	
	$('table').find('td').mouseover(function() {
	var content = $(this).text(); // 获取到内容
	});
	});

//state 0、1转换
app.filter('findstate',function(){
	return function(input){
		if(input == "0"){
			var output = "未审核";
			return output;
		}else{
			var output = "已审核";
			return output;
		}
	}
})
//type 0、1、2转换
app.filter('findtype', function() {
	return function(input) {
		if (input == "0") {
			var output = "旅游";
			return output;
		}else if(input == "1"){
			var output = "招工";
			return output;
			}else{
				var output = "其他";
				return output;
			}
		}	
});
