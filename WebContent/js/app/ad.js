var app = angular
		.module(
				'ad',
				[ 'ngRoute' ],
				function($httpProvider) {// ngRoute引入路由依赖
					$httpProvider.defaults.headers.put['Content-Type'] = 'application/x-www-form-urlencoded';
					$httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';

					// Override $http service's default transformRequest
					$httpProvider.defaults.transformRequest = [ function(data) {
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
											
	}).when('/adInfor',{
		templateUrl : '/lckypc/jsp/adInformation/adInfor.html',
		controller : 'adController'
	});
} ]);
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
	
	services.deleteAd = function(data) {
		return $http({
			method : 'post',
			url : baseUrl + 'ad/deleteAd.do',
			data : data
		});
	};

	services.selectAdByTitle = function(data) {
		return $http({
			method : 'post',
			url : baseUrl + 'ad/selectAdByTitle.do',
			data : data
		});
	};
	
	services.selectAdByType = function(data) {
		return $http({
			method : 'post',
			url : baseUrl + 'ad/selectAdByType.do',
			data : data
		});
	};
	services.selectAdByState = function(data) {
		return $http({
			method : 'post',
			url : baseUrl + 'ad/selectAdByState.do',
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
	
	services.updateAdById = function(data) {
		return $http({
			method : 'post',
			url : baseUrl + 'ad/updateAdById.do',
			data : data
		});
	};
	return services;

} ]);

app
		.controller(
				'adController',
				[
						'$scope',
						'services',
						'$location',
						'$routeParams',
						function($scope, services, $location) {

							var ad = $scope;
							var searchKey = null;
							// 换页
							function pageTurn(totalPage, page, Func) {

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

							
							// 根据页数获取用户列表
							function getAdListByPage(page) {
								services.getAdListByPage({
									page : page,
									searchKey : searchKey
								}).success(function(data) {
									ad.ads = data.list;
									
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
							ad.selectAdByType = function(){
								searchKey = ad.aType;
								service.getAdListByPage({
									page : 1,
									searchKey : searchKey
								}).success(function(data){
									ad.ads = data.list;
									pageTurn(data.totalPage, 1, getAdListByPage)
								});
							};
							ad.selectAdByState = function(){
								searchKey = ad.aState;
								service.getAdListByPage({
									page : 1,
									searchKey : searchKey
								}).success(function(data){
									ad.ads = data.list;
									pageTurn(data.totalPage, 1, getAdListByPage)
								});
							};
							
							// 删除旅游信息
							ad.deleteAd = function(ad_id) {
								if (confirm("是否删除该旅游信息？") == true) {
									services.deleteAd({
										adId : ad_id
									}).success(function(data) {

										ad.result = data;
										if (data == "true") {
											console.log("删除旅游信息成功！");
										} else {
											console.log("删除失败！");
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
													ad.ad = data.ad;
												});
							}
		// 修改旅游信息
							ad.updateAd = function() {
								
								var aFormData = JSON
										.stringify(ad.ad);
								services.updateAdById({
									ad : aFormData
								}).success(function(data) {
									alert("修改成功！");
								});
							};
	// 查看ID，并记入sessionStorage
							ad.getAdId = function(adId) {
								sessionStorage.setItem('adId', adId);
							};
							// 初始化
							function initData() {
								
								console.log("初始化页面信息");
								
								$("#ad").show();
								if ($location.path().indexOf('/adList') == 0) {
									searchKey = null;
									services.getAdListByPage({
										page : 1,
										searchKey : searchKey
									}).success(function(data) {
										ad.ads = data.list;
										pageTurn(data.totalPage, 1, getAdListByPage)
									});
								}
							    if($location.path().indexOf('/adList') == 0) {
										ad.flag = 1; // 标志位，用于控制按钮是否显示
										services.getAdList({
											services:getAdListByPage({
												page : 1
											})
											.success(
													function(data) {
														ad.ads = data.list;
														ad.totalPage = data.totalPage;
														var $pages = $(".tcdPageCode");
														if ($pages.length != 0) {
															$pages.createPage({
																pageCount : contract.totalPage,
																current : 1,
																backFn : function(p){
																	ad.getAdList(p);
																	ad.getAdListByPage(p);// 点击页码时获取第p页的数据
																}
															})
														}
													}
											)
										})
										}
								 else if ($location.path().indexOf(
									'/adUpdate') == 0) {
								// 根据ID获取信息
								var ad_id = sessionStorage
										.getItem('adId');
								services
										.selectAdById({
											ad_id : ad_id
										})
										.success(
												function(data) {
													ad.ad = data.ad;
												});
							}
							initData();
							$scope.$on('reGetData', function() {
								console.log("重新获取数据！");
								initData();
							});
							}}]);
app
.controller(
		'SelectAdController',
		[
				'$scope',
				'services',
				'$location',
				'$routeParams',
				function($scope, services, $location, $routeParams) {
					services.selectAdInfo({
						ad_id : $routeParams.adid
					}).success(function(data) {
						$scope.adList = data.list;
					});
				} ]);

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
			var shortInput = input.substr(0, 8);
			content = shortInput + "……";
		}
		return content;
	}
});
app.filter('adFilter',function(){ 
	return function(input){ 
		if(input == ""|| input == null){
			var input = "空";
			return input; 		
		}
		else{
			return input;
		}
	}
	});

//鼠标放置显示详情
app.filter('onmouse', function() {
	
	$('table').find('td').mouseover(function() {
	var content = $(this).text(); // 获取到内容
	});
	});
