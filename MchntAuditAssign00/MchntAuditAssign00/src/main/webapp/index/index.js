angular.module('indexApp', []).controller('indexCtrl',
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
						$http({
							url:'/MchntAudit/audit/queryAllTask.do',
							method:'post'
						}).success(function(data){
							$scope.psNum=data.psNum;
							$scope.pdNum=data.pdNum;
							$scope.rjNum=data.rjNum;
							$scope.onNum=data.onNum;
						}).error(function(){
							alert("QueryAllTask Unknown Exception");
						});
					}
				}).error(function() {
					alert("登陆超时，请重新登录！")
					$window.location.href = "/MchntAudit";
				});
			});

			$scope.logout = function() {
				if (confirm("确认退出？")) {
					$http({
						method : "post",
						url : "/MchntAudit/user/logout.do"
					}).success(function() {
						$window.location.href = "/MchntAudit";
					}).error(function() {
						alert("系统异常");
					});
				}
			}
		});
