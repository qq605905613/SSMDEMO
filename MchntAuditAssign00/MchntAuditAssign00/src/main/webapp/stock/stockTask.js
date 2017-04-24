angular
		.module('stockTaskApp', [])
		.controller(
				'stockTaskCtrl',
				function($scope, $window, $http,$interval) {
					// 初始化
					$(document).ready(function() {
						$scope.showButton = false;
						$scope.LoadBtn = false;

						$http({
							url : '/MchntAudit/user/getUser.do',
							method : 'post'
						}).success(function(data) {
							if ("" == data) {
								alert("登陆超时，请重新登录！")
								$window.location.href = "/MchntAudit";
							} else {
								$scope.comments=data.comments;
								$scope.queryRestTask();
							}
						}).error(function() {
							alert("登陆超时，请重新登录！")
							$window.location.href = "/MchntAudit";
						});
					});

					// 查询剩余数量
					$scope.queryRestTask = function() {
						$http({
							url : '/MchntAudit/audit/queryRestTask.do',
							method : 'post',
							params : {
								'isWhiteMchnt' : '0'
							}
						}).success(function(data) {
							$scope.restNum = data.total;
						}).error(function() {
							alert("获取剩余任务数异常!");
						});
					}

					// 登出
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

					// 审核通过
					$scope.pass = function() {
						if (confirm("确认通过？")) {
							$http({
								method : 'post',
								url : '/MchntAudit/audit/pass.do',
								params : {
									'taskId' : $scope.taskId,
									'rating' : 0
								}
							}).success(function(data) {
								if (data == 1) {
									alert('提交成功！');
									location.reload(true);
								} else {
									alert('提交失败');
								}
							}).error(function() {
								alert('未知异常！');
							});
						}
					}

					// 审核拒绝
					$scope.reject = function() {
						if (confirm("确认拒绝？")) {
							$http({
								method : 'post',
								url : '/MchntAudit/audit/rejectStockTask.do',
								params : {
									'taskId' : $scope.taskId,
								}
							}).success(function(data) {
								if (data.rsp == 1) {
									alert('提交成功！');
									location.reload(true);
								} else {
									alert('提交失败');
								}
							}).error(function() {
								alert('未知异常！');
							});
						}
					}

					// 获取任务
					$scope.getTask = function() {
						$scope.LoadBtn = true;
						$http({
							method : 'post',
							url : '/MchntAudit/audit/getPreviousTask.do',
							params : {
								'isWhiteMchnt' : '0'
							}
						})
								.success(
										function(data) {
											if ("" != data) {
												$scope.mchntCd = data.mchntCd;
												$scope.mchntCnNm = data.mchntCnNm;
												$scope.connMd = data.connMd;
												$scope.taskId = data.taskId;
												$scope.mchntTp = data.mchntTp;
												$scope.mchntSrvTp = data.mchntSrvTp;
												$scope.cupBranchInsIdCd = data.cupBranchInsIdCd;
												$scope.auditSt = data.auditSt;
												var priceTp = {
													'specDiscTp' : data.specDiscTp,
													'specDiscLvl' : data.specDiscLvl,
													'mchntTp' : data.mchntTp,
												};
												$scope.priceTp = priceTp;
												$scope.showButton = true;
												$scope.LoadBtn = false;
											} else {
												$http(
														{
															method : 'post',
															url : '/MchntAudit/audit/getStockAuditTask.do'
														})
														.success(
																function(res) {
																	if (null == res.taskAssign) {
																		alert('今日任务已派发完毕！');
																		$scope.LoadBtn = false;
																	} else {
																		var response = res.taskAssign;
																		$scope.mchntCd = response.mchntCd;
																		$scope.mchntCnNm = response.mchntCnNm;
																		$scope.connMd = response.connMd;
																		$scope.taskId = response.taskId;
																		$scope.mchntTp = response.mchntTp;
																		$scope.mchntSrvTp = response.mchntSrvTp;
																		$scope.cupBranchInsIdCd = response.cupBranchInsIdCd;
																		$scope.auditSt = response.auditSt;
																		var priceTp = {
																			'specDiscTp' : response.specDiscTp,
																			'specDiscLvl' : response.specDiscLvl,
																			'mchntTp' : response.mchntTp,
																		};
																		$scope.priceTp = priceTp;
																		$scope.showButton = true;
																		$scope.LoadBtn = false;
																	}
																})
														.error(
																function() {
																	alert('未知异常');
																	$scope.LoadBtn = false;
																});
											}
										}).error(function() {
									alert('未知异常！');
									$scope.LoadBtn = false;
								});
					}
					
					$interval(function(){
						$scope.queryRestTask();
					},30000);
				})
		.filter('filterConnMd', function() {
			return function(input) {
				if ('I' == input) {
					return '间联';
				} else if ('S' == input || 'P' == input) {
					return '直联';
				}

			}
		})
		.filter(
				'filterPriceTp',
				function() {
					return function(input) {
						if (input == undefined) {
							return;
						} else {
							var specDiscTp = input.specDiscTp;
							var specDiscLvl = input.specDiscLvl;
							var mchntTp = input.mchntTp;
							if ((mchntTp == '8651' || mchntTp == '9211'
									|| mchntTp == '9222' || mchntTp == '9223'
									|| mchntTp == '9311' || mchntTp == '9399')
									&& ((specDiscTp == '01' && specDiscLvl == '1')
											|| specDiscTp == '02' || specDiscTp == '03')) {
								return '特殊计费-政府服务类';
							} else if ((specDiscTp == '01' && specDiscLvl == '1')
									|| specDiscTp == '02' || specDiscTp == '03') {
								return '特殊计费-其他';
							} else if (mchntTp == '5411') {
								return '优惠类-超市';
							} else if (mchntTp == '4900') {
								return '优惠类-公共缴费';
							} else if (mchntTp == '5541' || mchntTp == '5542') {
								return '优惠类-加油';
							} else if (mchntTp == '4111' || mchntTp == '4121'
									|| mchntTp == '4131' || mchntTp == '4511'
									|| mchntTp == '4784') {
								return '优惠类-交通运输';
							} else if (mchntTp == '8211' || mchntTp == '8220'
									|| mchntTp == '8351' || mchntTp == '8241') {
								return '减免类-非营利教育机构';
							} else if (mchntTp == '8062' || mchntTp == '8011'
									|| mchntTp == '8021' || mchntTp == '8031'
									|| mchntTp == '8041' || mchntTp == '8042'
									|| mchntTp == '8049' || mchntTp == '8099') {
								return '减免类-非营利医疗机构';
							} else if (mchntTp == '8398') {
								return '减免类-慈善福利机构';
							} else {
								return '标准类';
							}
						}
					}
				}).filter('filterAuditSt', function() {
			return function(input) {
				switch (input) {
				case '4':
					return '分公司待四审';
					break;
				case 'F':
					return '总公司待一审';
					break;
				case 'G':
					return '总公司待二审';
					break;
				}
			}
		});