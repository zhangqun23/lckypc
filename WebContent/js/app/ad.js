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
											
	})
} ]);
app.constant('baseUrl', '/lckypc/');
app.factory('services', [ '$http', 'baseUrl', function($http, baseUrl) {
	var services = {};
	
	services.deleteAd = function(data) {
		return $http({
			method : 'post',
			url : baseUrl + 'ad/deleteAd.do',
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
						function($scope, services, $location,$routeParams) {

							var ad = $scope;
							ad.ADLimit={
									ad_type:"",
									ad_name:"",
									ad_title:"",
									ad_content:"",
									ad_tel:"",
									ad_remark:"",
									ad_state:""
								}
								ad.ADSLimit={
									ad_state:"请选择审核状态"
									}
							ad.selectAdByState = function(){
								
								if(ad.ADSLimit.ad_state == "请选择审核状态"){
									return alert("请选择审核状态！")
									}
								
								console.log("dawda");
								
								var adLimit = JSON.stringify(ad.ADSLimit);
								services.selectAdByState({
									adState : adLimit
									
								}).success(function(data) {
									ad.adList = data.list;
								
								});
							}
							ad.adList=function(adId){
								$location.path('adList/'+adId);
							}
							
							//初始化
                            function initData() {
								
								console.log("初始化页面信息");
								
								if ($location.path().indexOf('/adList') == 0) {
									services.adList({
										
									}).success(function(data) {
										ad.adList = data.list;
										
										console.log("dawda");
									});
								} 
							}
							initData();
						} ]);
		
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
