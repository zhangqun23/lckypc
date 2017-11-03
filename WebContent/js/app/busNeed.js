var app = angular
		.module(
				'busNeed',
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
	$routeProvider.when('/busNeedList', {
		templateUrl : '/lckypc/jsp/busInformation/busNeedList.html',
		controller : 'busNeedController'
	}).when('/busNeedUpdate', {
		templateUrl : '/lckypc/jsp/busInformation/busNeedDetail.html',
		controller : 'busNeedController'

	});
} ]);
app.constant('baseUrl', '/lckypc/');
app.factory('services', [ '$http', 'baseUrl', function($http, baseUrl) {
	var services = {};
	services.getBusNeedListByPage = function(data) {
		return $http({
			method : 'post',
			url : baseUrl + 'busNeed/getBusNeedListByPage.do',
			data : data
		});
	};
	services.deleteBusNeed = function(data) {
		return $http({
			method : 'post',
			url : baseUrl + 'busNeed/deleteBusNeed.do',
			data : data
		});
	};
	services.selectBusNeedByTel = function(data) {
		return $http({
			method : 'post',
			url : baseUrl + 'travel/selectBusNeedByTel.do',
			data : data
		});
	};
	services.selectBusNeedById = function(data) {
		return $http({
			method : 'post',
			url : baseUrl + 'busNeed/selectBusNeedById.do',
			data : data
		});
	};
	services.selectBusTradeByBNId = function(data) {
		return $http({
			method : 'post',
			url : baseUrl + 'busNeed/selectBusTradeByBNId.do',
			data : data
		});
	};

	// 补录信息
	services.repeatAddBusNeed = function(data) {
		return $http({
			method : 'post',
			url : baseUrl + 'busNeed/repeatAddBusNeed.do',
			data : data
		});
	};
	// 查看信息
	services.checkBusNeed = function(data) {
		return $http({
			method : 'post',
			url : baseUrl + 'busNeed/selectBusNeedById.do',
			data : data,
		});
	};
	return services;

} ]);

app
		.controller(
				'busNeedController',
				[
						'$scope',
						'services',
						'$location',
						function($scope, services, $location) {

							var bune = $scope;
							var butr = $scope;
							var searchKey = null;
							bune.butrState = "0";
							var pa = 1;
							// 换页
							function pageTurn(totalPage, page, Func) {

								var $pages = $(".tcdPageCode");
								if ($pages.length != 0) {
									$(".tcdPageCode").createPage({
										pageCount : totalPage,
										current : page,
										backFn : function(p) {
											pa=p;
											Func(p);
										}
									});
								}
							}

							// 根据页数获取信息列表
							function getBusNeedListByPage(page) {
								console.log("列表成功！");
								services.getBusNeedListByPage({
									page : page,
									searchKey : searchKey,
									butrState : ButrState

								}).success(function(data) {
									bune.busNeeds = data.list;

								});
							}
							
							// 删除信息							
							bune.deleteBusNeed = function(bune_id) {								
								$(".overlayer").fadeIn(200);
								$("#tipDelBune").fadeIn(200);								
								sessionStorage.setItem("buneId",bune_id);
							}
							$(".tiptop a").click(function() {
								$("#tipDelTra").fadeOut(100);
								$(".overlayer").fadeOut(200);
								});
							
							$("#sureDelBune").click(function(){
								$("#tipDelBune").fadeOut(100);
								$(".overlayer").fadeOut(200);
								//进入后台
								var bune_id=sessionStorage.getItem("buneId");
								services.deleteBusNeed({
									busNeedId : bune_id
								}).success(function(data) {
									services.getBusNeedListByPage({
										page : page,
										searchKey : bune.buneTel,
										butrState : bune.butrState
									}).success(
											function(data) {
												bune.busNeeds = data.list;
												pageTurn(data.totalPage, page,
														getBusNeedListByPage)
											});
								});
								});
							$("#cancelDelBune").click(function(){
								$("#tipDelBune").fadeOut(100);
								$(".overlayer").fadeOut(200);
								});
							// 删除信息
//							bune.deleteBusNeed = function(bune_id) {
//								console.log("成功！");
//								if (confirm("是否删除该条信息？") == true) {
//									services.deleteBusNeed({
//										busNeedId : bune_id
//									}).success(function(data) {
//
//										bune.result = data;
//										if (data == "true") {
//											console.log("删除成功！");
//										} else {
//											console.log("删除失败！");
//										}
//										initData();
//									});
//								}
//							}
							// 读取旅游信息
							function selectBusNeedById() {
								var bune_id = sessionStorage
										.getItem('busNeedId');
								services
										.selectBusNeedById({
											bune_id : bune_id
										})
										.success(
												function(data) {
													bune.BusNeedPage = data.busNeed;									 
													
							 						if (data.busNeed.butr_time.time) {
							 							bune.BusNeedPage.butr_time= changeDateType(data.busNeed.butr_time);
							 							}

												});
							}
							// 读取对应旅游交易信息
							function selectBusBusTradeByBNId() {
								var bune_id = sessionStorage
										.getItem('busNeedId');
								services.selectBusBusTradeByBNId({
									bune_id : bune_id
								}).success(function(data) {

									butr.busTrade = data.busTrade;
									// if (data.bune.bune_time) {
									// data.bune.bune_time =
									// changeDateType(data.bune.bune_time);
									// }

								});
							}
							// 查看ID，并记入sessionStorage,无操作
							bune.saveBuneId = function(busNeedId) {
								sessionStorage.setItem('busNeedId', busNeedId);
							};

							// 查看ID，并记入sessionStorage
							bune.getBuneId = function(busNeedId) {
								services.selectBusNeedById({
									bune_id : busNeedId
								}).success(function(data) {
									console.log(data.busNeed);
									bune.BusNeedPage = data.busNeed;
								})

							};

							// 根据联系方式筛选旅游交易信息
							bune.selectBusNeedByTel = function(page) {
								var ButrState = bune.butrState;
								searchKey = bune.buneTel;
								console.log(ButrState);
								
								if(JSON.stringify(bune.butrState) != null && JSON.stringify(bune.butrState) != ""){
									ButrState = JSON.stringify(bune.butrState);
								}
								services.getBusNeedListByPage({
									page : page,
									searchKey : searchKey,
									butrState : ButrState
								}).success(
										function(data) {
											bune.busNeeds = data.list;
											pageTurn(data.totalPage, page,
													getBusNeedListByPage)
										});
							};

							// 查看信息
							bune.checkBusNeed = function() {
								var busNeedId = this.bune.bune_id;
								services.checkBusNeed({
									bune_id : busNeedId
								}).success(function(data) {
									bune.busNeed = data.busNeed;

									$(".overlayer").fadeIn(200);
									$("#tipCheck").fadeIn(200);

								});
								$(".cancel").click(function() {
									$("#tipCheck").fadeOut(100);
									$(".overlayer").fadeOut(200);
									bune.busNeed = "";
								});
							};

							// 补录信息
							bune.repeatAddBusNeed = function() {
								var busFormData = JSON.stringify(bune.BusNeedPage);
								
								services.repeatAddBusNeed(
										{
											busNeed : busFormData,
											bune_id : sessionStorage
													.getItem('busNeedId')
										}).success(function(data) {
									alert("补录成功！");
									console.log(busFormData);
									bune.selectBusNeedByTel(pa);
								});
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

							// 2017-8-31wdh修正float-JSON显示误差
							function changeFloat(f) {
								console.log("传进来的" + f);
								if (!f) {
									var num = parseFloat('0').toFixed(2);
								} else {
									var num = parseFloat(f).toFixed(2);
								}
								console.log("转化后的" + num);
								return num;
							}

							// 2017-09-06 wdh隐藏模态框
							$(".tiptop a").click(function() {
								$(".overlayer").fadeOut(200);
								$(".tip").fadeOut(200);
							});

							function initData() {

								console.log("初始化页面信息");

								$("#busNeed").show();
								if ($location.path().indexOf('/busNeedList') == 0) {
									searchKey = null;
									ButrState = "0";
									services.getBusNeedListByPage({
										page : 1,
										searchKey : searchKey,
										butrState : ButrState
									}).success(
											function(data) {
												bune.busNeeds = data.list;
												pageTurn(data.totalPage, 1,
														getBusNeedListByPage)
											});
								} else if ($location.path().indexOf(
										'/busNeedUpdate') == 0) {
									var bune_id = sessionStorage.getItem('busNeedId');									
										bune.getBuneId(bune_id);
									services.selectBusNeedById({
									 		bune_id : bune_id
									 				}).success(function(data) {																			
									 						bune.BusNeedPage = data.busNeed;									 																				
									 						if (data.busNeed.butr_time) {
									 							bune.BusNeedPage.butr_time = changeDateType(data.busNeed.butr_time);
									 							}
									 });

								}

							}
							initData();

						} ]);
// 时间的格式化的判断
app.filter('dateType', function() {
	return function(input) {

		var type = "";
		if (input != null) {
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
			// if(input.length()<8){
			var shortInput = input.substr(0, 8);
			content = shortInput + "……";
			// }
		}

		return content;
	}
});

//没有输入详情显示为空 
app.filter('sgFilter',function() { 
	return function(input){ 
		if(input == "" || input == null){
			var input = "空";
			return input; 		
		}
		else{
			return input;
		}
	}
});

//只允许输入两位小数的正则判断
function changeTwoNum(obj){
	//清除"数字"和"."以外的字符
	  obj.value = obj.value.replace(/[^\d.]/g,"");
	  
    //验证第一个字符是数字而不是.
	  obj.value = obj.value.replace(/^\./g,"");
	 
	//只保留第一个. 清除多余的
	  obj.value = obj.value.replace(/\.{2,}/g,".");
	  obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
	 
	//只能输入两个小数 
	  obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');
}
//车牌号
function checkBusNumber(obj){ 
    var num = document.getElementById('BusNeedPage.bune_bus').value;
    if(!(/^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$/.test(num))){ 
        alert("车牌号码有误，请重填!");  
        obj.value='';
    } 
} 

// 小数过滤器
app.filter('cutFloat', function() {
	return function(input) {
		if (!input) {
			var money = parseFloat('0').toFixed(2);
		} else {
			var money = parseFloat(input).toFixed(2);
		}

		return money;
	}
});
//
app.directive("myFormat", function() {
	return {
		restrict : 'ECMA',
		require : 'ngModel',
		scope : true,
		link : function(scope, elem, attrs, controller) {
			var dateRegexp = /^[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}$/;

			// Model变化时执行
			// 初始化指令时BU执行
			scope.$watch(attrs.ngModel, function(val) {
				if (val) {
					return 22;
				}

			});
		}
	}
});