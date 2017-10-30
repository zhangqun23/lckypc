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
	}).when('/checktravelTrade', {
		templateUrl : '/lckypc/jsp/travelInformation/travelDetail.html',
		controller : 'travelController'
	}).when('/travelUpdate',	{
				templateUrl : '/lckypc/jsp/travelInformation/travelUpdate.html',
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
	services.getTravelTradeListByID = function(data) {
		return $http({
			method : 'post',
			url : baseUrl + 'travel/getTravelTradeListByID.do',
			data : data
		});
	};
	services.updateTravelById = function(data) {
		return $http({
			method : 'post',
			url : baseUrl + 'travel/updateTravelById.do',
			data : data,
		});
	};
	services.selectTravelById = function(data) {
		return $http({
			method : 'post',
			url : baseUrl + 'travel/selectTravelById.do',
			data : data
		});
	};
	// 查看旅游信息
	services.checkTravel = function(data) {
		return $http({
			method : 'post',
			url : baseUrl + 'travel/selectTravelById.do',
			data : data,
		});
	};
	// 查看旅游交易信息
	services.checkTravelTrade = function(data) {
		return $http({
			method : 'post',
			url : baseUrl + 'travel/selectTravelTradeById.do',
			data : data,
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
								
								var travelFormData = JSON.stringify(travel.travelInfo);
								console.log(travelFormData);
								services.addTravel({
									travel : travelFormData
								}).success(function(data) {
									
									alert("新建成功！");
									$location.path('travelList/');
							});
							}
							
							// 根据标题筛选旅游信息
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
							// 删除旅游信息							
							travel.deleteTravel = function(travel_id) {								
								$(".overlayer").fadeIn(200);
								$("#tipDelTra").fadeIn(200);								
								$(".tiptop a").click(function() {
									$("#tipDelTra").fadeOut(100);
									$(".overlayer").fadeOut(200);
									});
								
								$("#sureDelTra").click(function(){
									$("#tipDelTra").fadeOut(100);
									$(".overlayer").fadeOut(200);
									//进入后台
									services.deleteTravel({
										travelId : travel_id
									}).success(function(data) {
										initData();
									});
									});
								$("#cancelDelTra").click(function(){
									$("#tipDelTra").fadeOut(100);
									$(".overlayer").fadeOut(200);
									});
								
							}
							// 删除旅游信息
//							travel.deleteTravel = function(travel_id) {
//								console.log("成功！");
//								if (confirm("是否删除该旅游信息？") == true) {
//									services.deleteTravel({
//										travelId : travel_id
//									}).success(function(data) {
//
//										travel.result = data;
//										if (data == "true") {
//											console.log("删除旅游信息成功！");
//										} else {
//											console.log("删除失败！");
//										}
//										initData();
//									});
//								}
//							}
							// 读取旅游信息
							travel.selectTravelById=function(travelId) {
								console.log(travelId);
								var travel_id = sessionStorage.getItem('travelId');
								alert(travelId);
								services.selectTravelById({
											travel_id : travelId
										})
										.success(
												function(data) {
													travel.tra = data.travel;
													travel.travel = data.travel;
													if (data.travel.travel_stime) {
														travel.travel.travel_stime = changeDateType(data.travel.travel_stime);
													}
													if(data.travel.travel_discount){
														travel.travelInfo.travel_discount = changeFloat(data.travel.travel_discount);
													}
												});
							};
							
							// 修改旅游信息
							travel.updateTravel = function() {
								
								var traFormData = JSON.stringify(travel.travelInfo);
								services.updateTravelById({
									travel : traFormData
								}).success(function(data) {
									alert("修改成功！");
									$location.path('travelList/');
								});
							};
							// 查看ID，并记入sessionStorage
							travel.getTravelId = function(travelId) {
								sessionStorage.setItem('travelId', travelId);
							
							};
							// 查看ID，并记入sessionStorage
							travel.getTravelTradeId = function(trtrId) {
								sessionStorage.setItem('trtrId', trtrId);
							
							};
							// 根据页数获取旅游交易列表
							function getTravelTradeListByPage(page) {
								services.getTravelTradeListByPage({
									page : page,
									searchKey : searchKey
								}).success(function(data) {
									traveltrade.traveltrades = data.list;
									
								});
							}
							// 根据页数获取旅游交易列表
							function getTravelTradeListByID(page) {
								var travel_id = sessionStorage.getItem('travelId');						
								services.getTravelTradeListByID({
									page : page,
									searchKey : searchKey,
									travel_id : travel_id
								}).success(function(data) {
									traveltrade.traveltrades = data.list;
									
								});
							}
							//根据联系方式筛选旅游交易信息
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
							
							// 查看旅游信息
							travel.checkTravel = function() {
								var travelId = this.travel.travel_id;
									services.checkTravel({
										travel_id : travelId
									}).success(
													function(data) {
														travel.travel = data.travel;
														$(".overlayer").fadeIn(200);
														$("#tipCheck").fadeIn(200);

													});
									$(".cancel").click(function() {
										$("#tipCheck").fadeOut(100);
										$(".overlayer").fadeOut(200);
										travel.travel = "";
									});
							};
							// 查看旅游交易信息
							traveltrade.checkTravelTrade = function() {
								var trtrId = this.trtr.trtr_id;
									services.checkTravelTrade({
										trtr_id : trtrId
									}).success(function(data) {
													traveltrade.trtr = data.traveltrade;
													$(".overlayer").fadeIn(200);
													$("#tipCheck").fadeIn(200);

													});
									$(".cancel").click(function() {
										$("#tipCheck").fadeOut(100);
										$(".overlayer").fadeOut(200);
										traveltrade.trtr = "";
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
							
							//2017-8-31wdh修正float-JSON显示误差
							function changeFloat(f){
								console.log("传进来的" + f);
								if(!f){
									var num = parseFloat('0').toFixed(2);
								} else {
									var num = parseFloat(f).toFixed(2);
								}
								console.log("转化后的" + num);
								return num;
							}
								
							//2017-09-06 wdh隐藏模态框
							$(".tiptop a").click(function() {
								$(".overlayer").fadeOut(200);
								$(".tip").fadeOut(200);
							});
							
				//初始化
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
										pageTurn(data.totalPage, 1, getTravelListByPage);
										
									});
								}
								else if ($location.path().indexOf('/travelTradeList') == 0) {
									searchKey = null;
									services.getTravelTradeListByPage({
										page : 1,
										searchKey : searchKey
									}).success(function(data) {
										traveltrade.traveltrades = data.list;
										traveltrade.totalRow="打印："+data.totalRow;
										traveltrade.totalP="打印："+data.totalPage;
										pageTurn(data.totalPage, 1, getTravelTradeListByPage);
										
									});
								}
								 else if ($location.path().indexOf('/travelAdd') == 0) {
									
								}
								 else if ($location.path().indexOf('/travelUpdate') == 0) {
									
								// 根据ID获取信息
								var travel_id = sessionStorage.getItem('travelId');
							
								services.selectTravelById({
											travel_id : travel_id
										})
										.success(
												function(data) {
													
													travel.travelInfo = data.travel;
													
													if (data.travel.travel_stime) {
														travel.travelInfo.travel_stime = changeDateType(data.travel.travel_stime);
													}
													if(data.travel.travel_discount){
														travel.travelInfo.travel_discount = changeFloat(data.travel.travel_discount);
													}
													if(data.travel.travel_discount){
														travel.travelInfo.travel_mprice = changeFloat(data.travel.travel_mprice);
													}
													if(data.travel.travel_discount){
														travel.travelInfo.travel_cprice = changeFloat(data.travel.travel_cprice);
													}
												});
								
							}
								//获取信息完
								 else if ($location.path().indexOf('/checktravelTrade') == 0) {
										// 根据ID获取信息
									 	searchKey = null;
										var travel_id = sessionStorage.getItem('travelId');
										services.selectTravelById({
													travel_id : travel_id
												})
												.success(
														function(data) {
															travel.travelInfo = data.travel;
															
															if (data.travel.travel_stime) {
																travel.travelInfo.travel_stime = changeDateType(data.travel.travel_stime);
															}
															if(data.travel.travel_discount){
																travel.travelInfo.travel_discount = changeFloat(data.travel.travel_discount);
															}
															if(data.travel.travel_discount){
																travel.travelInfo.travel_mprice = changeFloat(data.travel.travel_mprice);
															}
															if(data.travel.travel_discount){
																travel.travelInfo.travel_cprice = changeFloat(data.travel.travel_cprice);
															}
														});
										//获取对应交易信息
										services.getTravelTradeListByID({
											page : 1,
											searchKey : searchKey,
											travel_id : travel_id
										}).success(function(data) {
											traveltrade.traveltrades = data.list;
											traveltrade.totalPa=""+data.totalPage;
											traveltrade.sum1=data.sumlist;
											pageTurn(data.totalPage, 1, getTravelTradeListByID);
										});
									}
									
										//获取信息完

							}

							initData();
							
							var $numberFormat = $(".numberFormat");
							var numberRegexp = /^\d+(\.{0,1}\d+){0,1}$/;
							$(".numberFormat").blur(
									function() {
										if (!numberRegexp.test(this.value)) {
											$(this).parent().children("span")
													.css('display', 'inline');
										}
									});
							$(".numberFormat").click(
									function() {
										$(this).parent().children("span").css(
												'display', 'none');
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
//			if(input.length()<8){
				var shortInput = input.substr(0, 8);
			content = shortInput + "……";
//			}
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
//固定电话
function checkTel(obj){
	 var tel=document.getElementById("travelInfo.travel_tel").value;
	 if(!((/^0\d{2,3}-?\d{7,8}$/.test(tel))||(/^1(3|4|5|7|8)\d{9}$/.test(tel)))){
		 alert("电话格式有误，请重填!");
		 obj.value='';
	}
	}
//手机号
function checkPhone(obj){ 
    var phone = document.getElementById('travelInfo.travel_tel').value;
    if(!(/^1(3|4|5|7|8)\d{9}$/.test(phone))){ 
        alert("手机号码有误，请重填");  
        obj.value='';
    } 
} 
function checkTest(obj){ 
    var phone = document.getElementById('travelInfo.travel_tel').value;
    if(!(/^0\d{2,3}-?\d{7,8}$/.test(phone))){ 
        alert("固定电话号码有误，请重填");  
        obj.value='';
    } 
} 
//比较剩余票数和总票数
function compare(obj){
	  var tnum=document.getElementById("travelInfo.travel_total_num").value;
	  var lnum=document.getElementById("travelInfo.travel_left_num").value;
	  if(parseInt(tnum)<parseInt(lnum)){
	
		alert('剩余票数应小于等于总票数');
		obj.value='';}
	
}
//交易状态过滤器
app.filter('trState', function() {
	return function(input) {
		var state = "";
		if (input == "0")
			state = "未付";
		else if (input == "1")
			state = "已付";
		else if (input == "2")
			state = "已退款";
		else if (!input)
			state = "";
		return state;
	}
});
//小数过滤器
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