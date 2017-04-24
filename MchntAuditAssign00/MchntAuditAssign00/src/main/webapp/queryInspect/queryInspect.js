angular
		.module('queryInsApp', [])
		.controller(
				'queryInsCtrl',
				function($scope, $http, $window) {
					$(document)
							.ready(
									function() {
										$scope.mccTpList = [ {
											key : 'A',
											value : '减免-医疗/学校/慈善福利类'
										}, {
											key : 'B',
											value : '特殊计费-政府服务类'
										}, {
											key : 'C',
											value : '特殊计费-其他类'
										}, {
											key : 'Z',
											value : '优惠类'
										}, {
											key : '',
											value : 'MCC类别'
										} ];

										$scope.isRightList = [ {
											key : '1',
											value : '正确'
										}, {
											key : '2',
											value : '错误'
										}, {
											key : '',
											value : '质检结果'
										} ];

										$(".form_datetime").datetimepicker();
										$scope.startPage = 1;
										$scope.pageSize = 10;
										$http(
												{
													url : '/MchntAudit/user/getUser.do',
													method : 'post'
												})
												.success(
														function(data) {
															if ("" == data) {
																alert("登陆超时，请重新登录！")
																$window.location.href = "/MchntAudit";
															} else {
																$scope.usrNm = data.usrNm;
																$scope.comments=data.comments;
																$http(
																		{
																			url : '/MchntAudit/qualityInspect/queryInsByPaging.do',
																			method : 'post',
																			data : {
																				'startPage' : 1,
																				'pageSize' : 10
																			}
																		})
																		.success(
																				function(
																						data) {
																					var rsp = data.result;
																					$scope.totalNum = rsp.totalNum;
																					$scope.inspectList = rsp.inspectList;
																					$scope.totalPage = Math
																							.ceil($scope.totalNum
																									/ $scope.pageSize);
																				})
																		.error(
																				function() {
																					alert('获取质检历史记录未知异常！');
																				});
															}
														})
												.error(
														function() {
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

					// 上一页
					$scope.lastPage = function() {
						if ($scope.startPage != 1) {
							$scope.startPage = parseInt($scope.startPage) - 1;
							if ($scope.totalPage == 0) {
								$scope.totalPage = 1;
							}
							if ($scope.startPage > $scope.totalPage
									|| $scope.startPage < 1) {
								return;
							}
							$scope.queryInspectByPaging();
						} else {
							return;
						}
					}

					// 下一页
					$scope.nextPage = function() {
						if ($scope.totalPage == $scope.startPage
								|| $scope.totalPage == 0) {
							return;
						} else {
							$scope.startPage = parseInt($scope.startPage) + 1;
							if ($scope.totalPage == 0) {
								$scope.totalPage = 1;
							}
							if ($scope.startPage > $scope.totalPage
									|| $scope.startPage < 1) {
								return;
							}
							$scope.queryInspectByPaging();
						}
					}

					$scope.query = function() {
						$scope.startPage = 1;
						$scope.queryInspectByPaging();
					}

					$scope.queryInspectByPaging = function() {
						var startTime = $('#startTime').val();
						var endTime = $('#endTime').val();
						$http(
								{
									url : '/MchntAudit/qualityInspect/queryInsByPaging.do',
									method : 'post',
									data : {
										'startTime' : startTime == '' ? undefined
												: new Date(startTime),
										'endTime' : endTime == '' ? undefined
												: new Date(endTime),
										'startPage' : $scope.startPage,
										'pageSize' : $scope.pageSize,
										'isRight' : $scope.isRight == '' ? undefined
												: $scope.isRight,
										'mccTp' : $scope.mccTp == '' ? undefined
												: $scope.mccTp
									}
								}).success(
								function(data) {
									var rsp = data.result;
									$scope.totalNum = rsp.totalNum;
									$scope.inspectList = rsp.inspectList;
									$scope.totalPage = Math
											.ceil($scope.totalNum
													/ $scope.pageSize);
								}).error(function() {
							alert('获取质检历史记录未知异常！');
						});
					}
				})
		.filter(
				'filterPriceTp',
				function() {
					return function(input, param1, param2) {
						if (input == undefined) {
							return;
						} else {
							var mchntTp = input;
							var specDiscTp = param1;
							var specDiscLvl = param2;

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
		}).filter('filterTask', function() {
			return function(input) {
				switch (input) {
				case '0':
					return '通过';
					break;
				case '1':
					return '待处理(待分派)';
					break;
				case '2':
					return '拒绝';
					break;
				case '3':
					return '处理中(已分派)';
					break;
				case '4':
					return '挂起';
					break;
				}
			};
		}).filter('filterIsRight', function() {
			return function(input) {
				switch (input) {
				case '1':
					return '正确';
					break;
				case '2':
					return '错误';
					break;
				case '3':
					return '质检中';
					break;
				}
			}
		});
