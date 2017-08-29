var app = angular
		.module(
				'woin',
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
//var permissionList;
//angular.element(document).ready(function() {
//	console.log("获取权限列表！");
//	$.get('/lckypc/login/getUserPermission.do', function(data) {
//		permissionList = data; // 
//		angular.bootstrap($("#user"), [ 'user' ]); // 手动加载angular模块
//	});
//});
//
//app.directive('hasPermission', function($timeout) {
//	return {
//		restrict : 'ECMA',
//		link : function(scope, element, attr) {
//			var key = attr.hasPermission.trim(); // 获取页面上的权限值
//			var keys = permissionList;
//			var regStr = "\\s" + key + "\\s";
//			var reg = new RegExp(regStr);
//			if (keys.search(reg) < 0) {
//				element.css("display", "none");
//			}
//		}
//	};
//});
app.run([ '$rootScope', '$location', function($rootScope, $location) {
	$rootScope.$on('$routeChangeSuccess', function(evt, next, previous) {
		console.log('路由跳转成功');
		$rootScope.$broadcast('reGetData');
	});
} ]);

// 路由配置
app.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/workerInfoList', {
		templateUrl : '/lckypc/jsp/systemManagement/workerInfoList.html',
		controller : 'workerInfoController'
	});
} ]);
app.constant('baseUrl', '/lckypc/');
app.factory('services', [ '$http', 'baseUrl', function($http, baseUrl) {
	var services = {};
	services.getWoinListByPage = function(data) {
		return $http({
			method : 'post',
			url : baseUrl + 'workerInfo/getWoinListByPage.do',
			data : data
		});
	};

	
	services.addWoin = function(data) {
		return $http({
			method : 'post',
			url : baseUrl + 'workerInfo/addWoin.do',
			data : data
		});
	};
	services.deleteWoin = function(data) {
		return $http({
			method : 'post',
			url : baseUrl + 'workerInfo/deleteWoin.do',
			data : data
		});
	};

	services.selectWoinByName = function(data) {
		return $http({
			method : 'post',
			url : baseUrl + 'workerInfo/selectWoinByName.do',
			data : data
		});
	};
	services.selectWoinById = function(data) {
		return $http({
			method : 'post',
			url : baseUrl + 'workerInfo/selectWoinById.do',
			data : data
		});
	};
	
	return services;
} ]);

app
		.controller(
				'workerInfoController',
				[
						'$scope',
						'services',
						'$location',
						function($scope, services, $location) {

							var woin = $scope;
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
							function getWoinListByPage(page) {
								services.getWoinListByPage({
									page : page,
									searchKey : searchKey
								}).success(function(data) {
									woin.woins = data.list;
								});
							}
							// 功能模块权限字段名根据页数
//							var perName = [ "con_per", "task_per", "bill_per",
//									"system_per", "index_per", "left_per" ];
							// 初始化权限数据容器
//							function initCheckBoxData() {
//								$("input:checkbox[name='selectAllChkBx']")
//										.attr("checked", false);
//
//								woin.selecwoin= {};
//								for (var i = 0; i < 6; i++) {
//									user.selected[perName[i]] = new Array();
//									for (var j = 0; j < 12; j++)
//										user.selected[perName[i]][j] = 0;
//								}
//								console.log(user.selected);
//							}
							// 根据用户选择更新权限数据容器
//							var updateSelected = function(action, clazz, name) {
//								if (action == 'add') {
//									user.selected[clazz][name] = 1;
//								}
//								if (action == 'remove') {
//									user.selected[clazz][name] = 0;
//								}
//							}
//							user.selectAll = function($event, subPerName) {
//								if ($event.target.checked == true) {
//									for (var i = 0; i < 10; i++)
//										user.selected[subPerName][i] = 1;
//								} else {
//									for (var i = 0; i < 10; i++)
//										user.selected[subPerName][i] = 0;
//								}
//
//							}
							// 根据用户选择更新权限数据容器
//							user.updateSelection = function(e, clazz, name) {
//								var checkbox = e.target;
//								var action = (checkbox.checked ? 'add'
//										: 'remove');
//								updateSelected(action, clazz, name);
//							}
//							// 控件内容初始化
//							user.isSelected = function(clazz, name) {
//								var t = user.selected[clazz][name];
//								return t;
//							}

							// 用户模态框开始
							// 点击新建按钮事件
							woin.addNewWoin = function(e) {
								preventDefault(e);
								
								woin.addingwoin = "";
								$(".overlayer").fadeIn(200);
								$(".tip").fadeIn(200);
								$("#addWoin-form").slideDown(200);
								$("#editWoin-form").hide();
								

							};

							// 点击修改时弹出模态框
							woin.editWoinBtn = function(obj) {
								var woin_id = this.woin.woin_id;
								

								$(".overlayer").fadeIn(200);
								$(".tip").fadeIn(200);
								$("#addWoin-form").hide();
								$("#editWoin-form").slideDown(200);
								
							};

							// 修改报用户
							$(".sure2").click(function() {
								var EditWoin = JSON.stringify(woin.editWoin);
								services.addWoin({
									woin : EditWoin
								}).success(function(data) {
									alert("修改成功！");
									getWoinListByPage(1);
									woin.editWoin = "";
								});

								$(".overlayer").fadeOut(100);
								$(".tip").fadeOut(100);
							});
							// 隐藏模态框
							$(".tiptop a").click(function() {
								$(".overlayer").fadeOut(200);
								$(".tip").fadeOut(200);
							});

							$(".cancel").click(function() {
								
								$(".overlayer").fadeOut(100);
								$(".tip").fadeOut(100);
							});
							// 添加用户
							$(".sure1")
									.click(
											function() {
												// 输入验证
												var flag = false;
												if (!woin.addingwoin.woin_num) {
													$('#woinNumError')
															.css('display',
																	'inline');
													flag = true;
												} else if (woin.addingwoin.woin_num
														.trim() == "") {
													$('#woinNumError')
															.css('display',
																	'inline');
													flag = true;
												}
												if (!woin.addingwoin.woin_pwd) {
													$('#woinPwdError')
															.css('display',
																	'inline');
													flag = true;
												} else if (woin.addingwoin.woin_pwd.length < 6) {
													$('#woinPwdError')
															.css('display',
																	'inline');
													flag = true;
												}
												if (!woin.addingwoin.woin_name) {
													$('#woinNameError')
															.css('display',
																	'inline');
													flag = true;
												} else if (woin.addingwoin.woin_name
														.trim() == "") {
													$('#woinNameError')
															.css('display',
																	'inline');
													flag = true;
												}
												if (!woin.addingwoin.woin_permission) {
													$('#woinPerError')
															.css('display',
																	'inline');
													flag = true;
												} else if (!woin.addingwoin.woin_permission
														.trim() == "") {
													$('#woinPerError')
															.css('display',
																	'inline');
													flag = true;
												}
												
												
												
												// 验证完毕
												var AddWoin = JSON
														.stringify(woin.addingwoin);
												services.addWoin({
													woin : AddWoin
												}).success(function(data) {
													alert("新建成功！");
													getWoinListByPage(1);
													woin.addingwoin = "";
												});

												$(".overlayer").fadeOut(100);
												$(".tip").fadeOut(100);
											});
							// 模态框完

							
							// 删除用户
							woin.deleteWoin = function(woin_id) {
								if (confirm("是否删除该用户？") == true) {
									services.deleteWoin({
										woinId : woin_id
									}).success(function(data) {

										woin.result = data;
										if (data == "true") {
											console.log("删除用户列表成功！");
										} else {
											console.log("删除用户列表失败！");
										}
										initData();
									});
								}
							}
							
//							function preventDefault(e) {
//								if (e && e.preventDefault) {
//									// 阻止默认浏览器动作(W3C)
//									e.preventDefault();
//								} else {
//									// IE中阻止函数器默认动作的方式
//									window.event.returnValue = false;
//									return false;
//								}
//							}
							// 初始化
							function initData() {
								console.log("初始化页面信息");
								
								$("#woin").show();
								if ($location.path().indexOf('/workerInfoList') == 0) {
									searchKey = null;
									services.getWoinListByPage({
										page : 1,
										searchKey : searchKey
									}).success(function(data) {
										woin.woins = data.list;
										pageTurn(data.totalPage, 1, getWoinListByPage)
									});
								}
//								 else if ($location.path().indexOf('/userAdd') == 0) {
//									
//
//								}
							}

							initData();
							
							$scope.$on('reGetData', function() {
								console.log("重新获取数据！");
								initData();
							});

						} ]);

