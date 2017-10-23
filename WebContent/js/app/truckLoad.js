var app = angular
		.module(
				'truckLoad',
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
app.run([ '$rootScope', '$location', function($rootScope, $location) {
	$rootScope.$on('$routeChangeSuccess', function(evt, next, previous) {
		console.log('路由跳转成功');
		$rootScope.$broadcast('reGetData');
	});
} ]);

//路由配置
app.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/truckList', {
		templateUrl : '/lckypc/jsp/truckLoad/truckDriver.html',
		controller : 'TruckLoadController'
	}).when('/truckNeedList', {
		templateUrl : '/lckypc/jsp/truckLoad/truckNeed.html',
		controller : 'TruckLoadController'
	}).when('/truckSend', {
		templateUrl : '/lckypc/jsp/truckLoad/truckSend.html',
		controller : 'TruckLoadController'
	})		
} ]);

app.constant('baseUrl', '/lckypc/');
app.factory('services', [ '$http', 'baseUrl', function($http, baseUrl) {
	var services = {};
/**
 * Truck
 */
	//查询Truck信息
	services.getTruckDriverList = function(data){
		return $http({
			method : 'post',
			url : baseUrl + 'truckLoad/getTruckDriverList.do',
			data : data
		});
	};
	//Truck信息模态框显示
	services.checkTruck = function(data){
		return $http({
			method : 'post',
			url : baseUrl + 'truckLoad/checkTruck.do',
			data : data
		});
	};
	//删除Truck信息
	services.deleteTruck = function(data){
		return $http({
			method : 'post',
			url : baseUrl + 'truckLoad/deleteTruck.do',
			data : data
		});
	}
	/**
	 * truckSend
	 */
	services.getTruckSend = function(data){
		return $http({
			method : 'post',
			url : baseUrl + 'truckLoad/getTruckSend.do',
			data : data
		});
	}
	/**
	 * truckNend
	 */
	services.getTruckNeed = function(data){
		return $http({
			method :'post',
			url : baseUrl + 'truckLoad/getTruckNeed.do',
			data :data
		});
	}
	return services;
} ]);

app.controller('TruckLoadController', [ '$scope', 'services', '$location',
     function($scope, services, $location) {
         var truckDrSdNd = $scope;      
         truckDrSdNd.trDLimit={
        	 trck_check : "0"
         }
		// 公共换页函数
		function pageTurn(totalPage, page, Func) {
			/*$(".tcdPageCode").empty();*/
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
	 /**
	  * Truck
	  */
		// 根据页数获取Truck信息
		function getTruckDriverList(page) {
			services.getTruckDriverList({
				page : page
				}).success(function(data) {
					truckDrSdNd.truckList = data.list;
				});
			}
         // 查询Truck信息
         truckDrSdNd.getTruckDriverListByCheck = function(){
        	 var trLimit = null;
				if(JSON.stringify(truckDrSdNd.trDLimit) != null){
					trLimit = JSON.stringify(truckDrSdNd.trDLimit);
				}
				services.getTruckDriverList({
					page : 1,
					trState : trLimit
				}).success(function(data){
					truckDrSdNd.truckList = data.list;
					pageTurn(data.totalPage, 1, getTruckDriverList);
				})
         };
         // Truck信息模态框显示
         truckDrSdNd.checkTruck = function() {        
				var TruckId = this.truckDrSdNd.trck_id;
				console.log(truckDrSdNd.trck_id);
				services.checkTruck({
					trck_id : TruckId
				}).success(function(data) {
					truckDrSdNd.tr = data.truck;													
					$(".overlayer").fadeIn(200);
					$("#tipCheck").fadeIn(200);
				});
				$(".cancel").click(function() {
					$("#tipCheck").fadeOut(100);
					$(".overlayer").fadeOut(200);
					
				});
			};
			// 删除Truck信息
			truckDrSdNd.deleteTruck = function (trck_id) {
				console.log(trck_id);
				if (confirm("确定删除此信息?")) {
					services.deleteTruck({
						trckId : trck_id
					}).success(function(data) {
						$location.path('truckList/');
					});
				} else {
					return null;
				}
			}
			// 2017-09-06 ghl隐藏模态框
			$(".tiptop a").click(function() {
				$(".overlayer").fadeOut(200);
				$(".tip").fadeOut(200);
			});
		/**
		 * truckSend
		 */
			truckDrSdNd.selectTruckSend = function(){
				services.getTruckSend({
					page : 1
				}).success(function(data){
					truckDrSdNd.truckSendList = data.list;
					pageTurn(data.totalPage, 1, getTruckSend);
				})
			}
			// 根据页数获取TruckSeed信息
			function getTruckSend(page) {
				services.getTruckSend({
					page : page
					}).success(function(data) {
						truckDrSdNd.truckSendList = data.list;
					});
				}
			/**
			 * truckNend
			 */
			//查询TruckNeed信息
			truckDrSdNd.selectTruckNeed = function(){
				services.getTruckNeed({
					page : 1
				}).success(function(data){
					truckDrSdNd.truckNeedList = data.list;
					pageTurn(data.totalPage,1,getTruckNeed);
				});
			}
			// 根据页数获取TruckNeed信息
			function getTruckNeed(page) {
				services.getTruckNeed({
					page : page
					}).success(function(data) {
						truckDrSdNd.truckNeedList = data.list;
					});
				}

             // 零担货运页面初始化
             function initPage() {
                 console.log("初始化页面信息");             
               if ($location.path().indexOf('/truckList') == 0) {
          /*       truckDrSdNd.trDLimit={
                        	 trck_check : "0"
                        }
                    var trLimit = null;
     				if(JSON.stringify(truckDrSdNd.trDLimit) != null){
     					trLimit = JSON.stringify(truckDrSdNd.trDLimit);
     				}
						services.getTruckDriverList({
							page : 1,
							trState : trLimit
						}).success(function(data) {
							truckDrSdNd.truckList = data.list;
							pageTurn(data.totalPage, 1, getTruckDriverList)
						});*/
            	   truckDrSdNd.getTruckDriverListByCheck();
                 }else if ($location.path().indexOf('/truckSend') == 0){
                	 truckDrSdNd.selectTruckSend();
                 }else if ($location.path().indexOf('/truckNeedList') == 0){
                	 truckDrSdNd.selectTruckNeed();
                 }	
                 
             }
              initPage();
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
//是否冷冻
app.filter('freeze',function(){
	return function(input){
		var type = "";
		if(input == 0){
			type = "无冷冻";
		}else if(input == 1){
			type = "有冷冻";
		}
		return type;
	}
});
//审核状态
app.filter('state',function(){
	return function(input){
		var type = "";
		if(input == 0){
			type = "未审核";
		}else if(input == 1){
			type = "已审核";
		}
		return type;
	}
});