angular.module('loginApp', []).controller('loginCtrl',
		function($scope, $http, $window) {
			$scope.submit = function() {
				var account = $scope.loginId;
				var pwd = $scope.pwd;
				if (undefined == account) {
					alert("账号不能为空");
				} else if (undefined == pwd) {
					alert("密码不能为空");
				} else {
					$http({
						method : 'post',
						url : 'user/login.do',
						params : {
							'loginId' : account,
							'pwd' : pwd
						}
					}).success(function(data) {
						if (!data) {
							alert('账号密码错误');
						} else {
							if(data.user.comments=='1'){
								$window.location.href='qulInspect/qulInspect.html';
							}else{
								$window.location.href = 'index/index.html';
							}
						}
					}).error(function() {
						alert('系统错误');
					});
				}
			}
		});
