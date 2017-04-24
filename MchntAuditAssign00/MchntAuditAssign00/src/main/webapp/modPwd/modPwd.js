angular
		.module('modPwdApp', [])
		.controller(
				'modPwdCtrl',
				function($scope, $http, $window) {
					// 初始化
					$(document).ready(function() {
						$http({
							url : '/MchntAudit/user/getUser.do',
							method : 'post'
						}).success(function(data) {
							if ("" == data) {
								alert("登陆超时，请重新登录！")
								$window.location.href = "/MchntAudit";
							} else {
								$scope.usrNm = data.usrNm;
								$scope.comments=data.comments;
							}
						}).error(function() {
							alert("登陆超时，请重新登录！")
							$window.location.href = "/MchntAudit";
						});
					});
					
					$scope.getAll = function(){
						$http({
							method : 'post',
							url : '/MchntAudit/user/getAll.do'
						}).success(
							function(data){
								$scope.user = data.result;
								//alert(annoInfo.annId);
								
								
							}
						)
						
						
					}
					$scope.setFla = function(userId,flaBa){
						$http({
								method : 'post',
								url : '/MchntAudit/user/modfla.do',
								params : {
										'userId' : userId,
										'flaBa'  : flaBa,
								}
							
							
					}).success(
							function (data){
								if(data==1){
									alert("修改成功")
								}
								
							}
					)
						
						
						
					}

					$scope.modifyPwd = function() {
						var oldPwd = $scope.oldPwd;
						var newPwd1 = $scope.newPwd1;
						var newPwd2 = $scope.newPwd2;
						if (undefined == oldPwd) {
							alert("旧密码不能为空!");
							return;
						} else if (undefined == newPwd1) {
							alert("新密码不能为空!");
							return;
						} else if (undefined == newPwd2) {
							alert("新密码不能为空!");
							return;
						} else if (newPwd1 != newPwd2) {
							alert('两次密码不一致!');
							return;
						} else if (oldPwd == newPwd1) {
							alert('旧密码与新密码不能一致');
							return;
						} else {
							$http({
								method : 'post',
								url : '/MchntAudit/user/modPwd.do',
								params : {
									'oldPwd' : oldPwd,
									'newPwd' : newPwd1
								}
							})
									.success(
											function(data) {
												if (data == 1) {
													alert('修改成功!');
													$window.location.href = "/MchntAudit/index/index.html";
												} else {
													alert('修改失败!');
												}
											}).error(function() {
										alert('未知异常!');
									});

						}
					}

				});
