var app = angular
		.module(
				'travel',
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
	$routeProvider.when('/travelList', {
		templateUrl : '/lckypc/jsp/travelInformation/travelList.html',
		controller : 'travelController'
	}).when('/travelAdd', {
		templateUrl : '/lckypc/jsp/travelInformation/travelAdd.html',
		controller : 'travelController'
	}).when('/travelTradeList', {
				templateUrl : '/lckypc/jsp/travelInformation/travelTradeList.html',
				controller : 'travelController'
							
	});
} ]);
app.constant('baseUrl', '/lckypc/');
app.factory('services', [ '$http', 'baseUrl', function($http, baseUrl) {
	var services = {};
	services.getTravelListByPage = function(data) {
		return $http({
			method : 'post',
			url : baseUrl + 'travel/getTravelListByPage.do',
			data : data
		});
	};
	
	services.addTravel = function(data) {
		return $http({
			method : 'post',
			url : baseUrl + 'travel/addTravel.do',
			data : data
		});
	};
	
	services.deleteTravel = function(data) {
		return $http({
			method : 'post',
			url : baseUrl + 'travel/deleteTravel.do',
			data : data
		});
	};

	services.selectTravelByTitle = function(data) {
		return $http({
			method : 'post',
			url : baseUrl + 'travel/selectTravelByTitle.do',
			data : data
		});
	};
	services.selectTravelTradeByTel = function(data) {
		return $http({
			method : 'post',
			url : baseUrl + 'travel/selectTravelTradeByTel.do',
			data : data
		});
	};
	services.getTravelTradeListByPage = function(data) {
		return $http({
			method : 'post',
			url : baseUrl + 'travel/getTravelTradeListByPage.do',
			data : data
		});
	};

	return services;

} ]);

app
		.controller(
				'travelController',
				[
						'$scope',
						'services',
						'$location',
						function($scope, services, $location) {

							var travel = $scope;
							var traveltrade = $scope;
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
							function getTravelListByPage(page) {
								services.getTravelListByPage({
									page : page,
									searchKey : searchKey
								}).success(function(data) {
									travel.travels = data.list;
									
								});
							}
							// 添加旅游信息
							travel.addTravel = function() {
								
								var travelFormData = JSON
										.stringify(travel.travelInfo);
								services.addTravel({
									travel : travelFormData
								}).success(function(data) {
									alert("新建成功！");
								});
							};
							
							// 根据输入筛选信息
							travel.selectTravelByTitle = function() {
								searchKey = travel.traTitle;
								services.getTravelListByPage({
									page : 1,
									searchKey : searchKey
								}).success(function(data) {
									travel.travels = data.list;
									pageTurn(data.totalPage, 1, getTravelListByPage)
								});
							};
							
							traveltrade.selectTravelTradeByTel = function() {
								searchKey = traveltrade.trtrTel;
								services.getTravelTradeListByPage({
									page : 1,
									searchKey : searchKey
								}).success(function(data) {
									traveltrade.traveltrades = data.list;
									pageTurn(data.totalPage, 1, getTravelTradeListByPage)
								});
							};
							// 根据页数获取列表
							function getTravelTradeListByPage(page) {
								services.getTravelTradeListByPage({
									page : page,
									searchKey : searchKey
								}).success(function(data) {
									traveltrade.traveltrades = data.list;
									
								});
							}
							
							
							function initData() {
								
								console.log("初始化页面信息");
								
								$("#travel").show();
								if ($location.path().indexOf('/travelList') == 0) {
									searchKey = null;
									services.getTravelListByPage({
										page : 1,
										searchKey : searchKey
									}).success(function(data) {
										travel.travels = data.list;
										pageTurn(data.totalPage, 1, getTravelListByPage)
									});
								}
								else if ($location.path().indexOf('/travelTradeList') == 0) {
									searchKey = null;
									services.getTravelTradeListByPage({
										page : 1,
										searchKey : searchKey
									}).success(function(data) {
										traveltrade.traveltrades = data.list;
										pageTurn(data.totalPage, 1, getTravelTradeListByPage)
									});
								}
//								if($location.path().indexOf('/travelList') == 0) {
//										
//										travel.flag = 1; // 标志位，用于控制按钮是否显示
////										services.getTravelList({
//											services.getTravelListByPage({
//													page : 1
//												})
//												.success(
//														function(data) {
//															
//															travel.travels = data.list;
//															travel.totalPage = data.totalPage;
//															var $pages = $(".tcdPageCode");
//															if ($pages.length != 0) {
//																$pages
//																		.createPage({
//																			pageCount : contract.totalPage,
//																			current : 1,
//																			backFn : function(
//																					p) {
////																				travel.getTravelList(p);
//																				travel.getTravelListByPage(p);// 点击页码时获取第p页的数据
//																			}
//																		});
//															}
//														});

									
								 else if ($location.path().indexOf(
										'/travelAdd') == 0) {
									
								}
								
							}

							initData();
							
							$scope.$on('reGetData', function() {
								console.log("重新获取数据！");
								initData();
							});
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