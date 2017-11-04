var app = angular
		.module(
				'smgo',
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
	$routeProvider.when('/smgoList', {
		templateUrl : '/lckypc/jsp/smgoInformation/smgoList.html',
		controller : 'smgoController'
	}).when('/smgoUpdate', {
		templateUrl : '/lckypc/jsp/smgoInformation/smgoUpdate.html',
		controller : 'smgoController'
	});
} ]);
app.constant('baseUrl', '/lckypc/');
app.factory('services', [ '$http', 'baseUrl', function($http, baseUrl) {
	var services = {};

	// 根据限制条件筛选信息
	services.getSmgoListByPage = function(data) {
		return $http({
			method : 'post',
			url : baseUrl + 'smgo/getSmgoListByPage.do',
			data : data
		});
	};

	// 删除smgo信息
	services.deleteSmgo = function(data) {
		return $http({
			method : 'post',
			url : baseUrl + 'smgo/deleteSmgo.do',
			data : data
		});
	};

	// 添加smgo补录信息
	services.addEdit = function(data) {
		return $http({
			method : 'post',
			url : baseUrl + 'smgo/addEdit.do',
			data : data
		});
	}
	return services;
} ]);

app
		.controller(
				'smgoController',
				[
						'$scope',
						'services',
						'$location',
						function($scope, services, $location) {
							var smgo = $scope;
							var pa = 1;

							// 换页
							function pageTurn(totalPage, page, Func) {
								$(".tcdPageCode").empty();
								var $pages = $(".tcdPageCode");
								if ($pages.length != 0) {
									$(".tcdPageCode").createPage({
										pageCount : totalPage,
										current : page,
										backFn : function(p) {
											pa = p;
											Func(p);
										}
									});
								}
							}

							// 根据页数获取smgo信息
							function getSmgoListByPage(page) {
								var smgoLimit = null;
								var gotLimit = null;
								if (JSON.stringify(smgo.SGSLimit) != null) {
									smgoLimit = JSON.stringify(smgo.SGSLimit);
								}
								if (JSON.stringify(smgo.GotLimit) != null) {
									gotLimit = JSON.stringify(smgo.GotLimit);
								}
								services.getSmgoListByPage({
									page : page,
									smgoSego : smgoLimit,
									gotNeed : gotLimit
								}).success(function(data) {
									smgo.smgos = data.list;
								});
							}

							// smgo_sego限制
							smgo.getSmgoListBySD = function(page) {
								var smgoLimit = {
									smgo_sego : ""
								};
								var gotLimit = {
									startDate : "",
									endDate : ""
								};
								if (JSON.stringify(smgo.SGSLimit) != null) {
									smgoLimit = JSON.stringify(smgo.SGSLimit);
								}
								if (JSON.stringify(smgo.GotLimit) != null) {
									gotLimit = JSON.stringify(smgo.GotLimit);
								}
								services.getSmgoListByPage({
									page : page,
									smgoSego : smgoLimit,
									gotNeed : gotLimit
								}).success(
										function(data) {
											$scope.smgos = data.list;
											pageTurn(data.totalPage, page,
													getSmgoListByPage)
										});
							}

							// 添加smgo补录信息
							smgo.smgoInfo = {
								edit_price : "",
								edit_time : ""
							}
							smgo.addEdit = function(smgo_id) {
								var smgoLimits = JSON.stringify(smgo.smgoInfo);
								if(smgo.smgoInfo.edit_price==0){
									// 显示模态框
									$(".overlayer").fadeIn(200);
									$("#tipUpdate").fadeIn(200);
									// 金额为0提示模态框 左上角的X
									$(".tiptop a").click(function() {
										$("#tipUpdate").fadeOut(100);
										$(".overlayer").fadeOut(200);
									});
									// 金额为0提示模态框
									smgo.backTiJiao = function() {
										$("#tipUpdate").fadeOut(100);
										$(".overlayer").fadeOut(200);
									}
								}else{
									services.addEdit({
										smgoId : smgo_id,
										smgoNeed : smgoLimits
									}).success(function(data) {
										// 显示模态框
										$(".overlayer").fadeIn(200);
										$("#tipDel2").fadeIn(200);

									});
								}
								
							};
							// 左上角的X
							$(".tiptop a").click(function() {
								$("#tipDel2").fadeOut(100);
								$(".overlayer").fadeOut(200);
							});
							// 补录模态框隐藏
							smgo.backList = function() {
								$("#tipDel2").fadeOut(100);
								$(".overlayer").fadeOut(200);
								$location.path("smgoList");
							}


							// 删除smgo信息
							smgo.deleteSmgo = function(smgo_id) {
								// 显示模态框
								$(".overlayer").fadeIn(200);
								$("#tipDel").fadeIn(200);
								sessionStorage.setItem("smgoId", smgo_id);
							};
							// 左上角的X
							$(".tiptop a").click(function() {
								$("#tipDel").fadeOut(100);
								$(".overlayer").fadeOut(200);
							});
							// 点击按钮,模态框隐藏
							$("#sureDel").click(function() {
								$("#tipDel").fadeOut(100);
								$(".overlayer").fadeOut(200);
								// 进入后台
								var smgo_id = sessionStorage.getItem("smgoId");
								services.deleteSmgo({
									smgoId : smgo_id
								}).success(function(data) {
									smgo.getSmgoListBySD(pa);
								});
							});
							$("#cancelDel").click(function() {
								$("#tipDel").fadeOut(100);
								$(".overlayer").fadeOut(200);
							});

							// 查看ID，并记入sessionStorage
							smgo.getSmgoId = function(smgo) {
								var smgo = JSON.stringify(smgo);
								sessionStorage.setItem('smgo', smgo);
								$location.path("/smgoUpdate");
							};
							// 2017-8-30wdh更改时间的样式
							function changeDateType(date) {
								console.log("传进来的时间" + date);
								if (date != "") {
									var DateTime = new Date(date.time)
											.toLocaleDateString().replace(
													/\//g, '-');
								} else {
									var DateTime = "";
								}
								console.log("转化后的时间" + DateTime);
								return DateTime;
							}
							// 初始化
							function initData() {
								$("#smgo").show();
								if ($location.path().indexOf('/smgoList') == 0) {
									services.getSmgoListByPage({
										page : 1,
									}).success(
											function(data) {
												$scope.smgos = data.list;
												pageTurn(data.totalPage, 1,
														getSmgoListByPage);
											});
								} else if ($location.path().indexOf(
										'/smgoUpdate') == 0) {
									// 根据id获取smgo信息
									var smgo = sessionStorage.getItem("smgo");
									$scope.smgoInfo = JSON.parse(smgo);
									console.log($scope.smgoInfo);
									if ($scope.smgoInfo.edit_time) {
										$scope.smgoInfo.edit_time = changeDateType($scope.smgoInfo.edit_time);
									}
								}
							}
							initData();
						} ]);

// 时间的格式化的判断
app.filter('dateType', function() {
	return function(input) {
		var type = "";
		if (input != "" && input != undefined) {
			type = new Date(input).toLocaleDateString().replace(/\//g, '-');
		}
		return type;
	}
});

// 截取任务内容
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

// smgo_sego 0、1过滤
app.filter('findSego', function() {
	return function(input) {
		if (input == "0") {
			var output = "自行送货";
			return output;
		}
		if (input == "1") {
			var output = "上门取货";
			return output;
		}
	}
});
// null的判定
app.filter('sgFilter', function() {
	return function(input) {
		if (input == "" || input == null) {
			var input = "空";
			return input;
		} else {
			return input;
		}
	}
});
// 交易状态isFinish
app.filter('IsFinish', function() {
	return function(input) {
		if (input == "0") {
			var input = "未完成";
			return input;
		} else {
			var input = "已完成";
			return input;
		}
	}
});