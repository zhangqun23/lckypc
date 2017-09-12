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
	}).when('/smgoUpdate',	{
		templateUrl : '/lckypc/jsp/smgoInformation/smgoUpdate.html',
		controller : 'smgoController'							
	});
}]);
app.constant('baseUrl', '/lckypc/');
app.factory('services', [ '$http', 'baseUrl', function($http, baseUrl) {
	var services = {};
	services.getSmgoListByPage = function(data) {
		return $http({
			method : 'post',
			url : baseUrl + 'smgo/getSmgoListByPage.do',
			data : data
		});
	};
	
	services.selectSmgoBySego = function(data){
		return $http({
			method : 'post',
			url : baseUrl + 'smgo/selectSmgoBySego.do',
			data : data
		});
	};
	
	services.deleteSmgo = function(data) {
		return $http({
			method : 'post',
			url : baseUrl + 'smgo/deleteSmgo.do',
			data : data
		});
	};
	
	//添加补录信息
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
							
							// 根据页数获取smgo信息
							function getSmgoListByPage(page) {
								services.getSmgoListByPage({
									page : page,
									searchKey : searchKey
								}).success(function(data) {
									smgo.smgos = data.list;
									console.log(data.list)
								});
							}
							smgo.smgoInfoss={
									edit_price : "",
									edit_time : ""
							}
							//添加edit补录信息    (有问题、好像没进来)
							smgo.addEdit = function(){		
								var smgoid = sessionStorage.getItem('smgoid');
								console.log(smgo.smgoInfoss);
								var smgoLimits = JSON.stringify(smgo.smgoInfoss);
								services.addEdit({
									smgoNeed : smgoLimits,
									smgoid : smgoid
								}).success(function(data) {
									alert("补录成功");
									console.log(data.list)
								});
							};
							
							//根据smgo_sego筛选smgo信息
							smgo.SGSLimit={
									smgo_sego:"送货方式"
							}
							smgo.selectSmgoBySego = function(){
								var searchKey = null;
								var smgoLimit = JSON.stringify(smgo.SGSLimit);
								services.selectSmgoBySego({
									page : 1,
									smgoSego : smgoLimit,
									searchKey : searchKey
								}).success(function(data){
									smgo.smgos = data.list;
									pageTurn(data.totalPage, 1, smgo.selectSmgoBySego)
								});
							}

							// 根据输入Name筛选信息
							smgo.selectSmgoByName = function() {
								searchKey = smgo.aName;
								services.getSmgoListByPage({
									page : 1,
									searchKey : searchKey
								}).success(function(data) {
									smgo.smgos = data.list;
									pageTurn(data.totalPage, 1, getSmgoListByPage)
								});
							};
							
							// 删除smgo信息
							smgo.deleteSmgo = function(smgo_id) {
								if (confirm("是否删除该smgo信息？") == true) {
									services.deleteSmgo({
										smgoId : smgo_id
									}).success(function(data) {
										smgo.result = data;
										if (data == "true") {
											console.log("删除smgo信息成功！");
										} else {
											console.log("删除smgo信息失败！");
										}
										initData();
									});
								}
							}
							
	                        // 查看ID，并记入sessionStorage
							smgo.getSmgoId = function(smgoid) {	
								var smgoidd = JSON.stringify(smgoid);
								sessionStorage.setItem('smgoid',smgoidd);	
								console.log(smgoidd);
								console.log(sessionStorage.getItem('smgoid'));	
								$location.path("smgoUpdate/");
							};
							
							//初始化
							function initData() {
								console.log("初始化页面信息");
								$("#smgo").show();
								if ($location.path().indexOf('/smgoList') == 0) {
									searchKey = null;
									services.getSmgoListByPage({
										page : 1,
										searchKey : searchKey
									}).success(function(data) {
										$scope.smgos = data.list;
										pageTurn(data.totalPage, 1, getSmgoListByPage)
									});
								}else if ($location.path().indexOf('/smgoUpdate') == 0) {
									//根据id获取smgo信息
									var smgoid = sessionStorage.getItem("smgoid");
									$scope.smgoInfo = JSON.parse(smgoid);
							}}
							initData();
							}]);

//时间的格式化的判断
app.filter('dateType', function() {
	return function(input) {
		var type = "";
		if (input != "") {
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

//smgo_sego 0、1过滤
app.filter('findSego',function(){
	return function(input){
		if(input == "0"){
			var output = "自行取货";
			return output;
		}else{
			var output = "送货上门";
			return output;
		}
	}
});
