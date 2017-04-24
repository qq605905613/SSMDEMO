angular
		.module('queryTaskApp', [])
		.controller(
				'queryTaskCtrl',
				function($scope, $http, $window) {
					// 初始化
					$(document)
							.ready(
									function() {

										$("#detailBox").hide();
										$scope.passBox = false;
										$scope.rejectBox = false;
										$scope.pendingBtn = false;
										$scope.reasonList = new Array();
										$scope.startPage = 1;
										$scope.pageSize = "10";
										$scope.psModel = [ '10', '20', '50' ];
										$scope.type = [ {
											id : '-1',
											value : '全部'
										} ];
										$scope.auditSt = [ {
											id : '-1',
											value : '全部'
										} ];
										$scope.dateChoose = [ {
											id : '0',
											value : '全部'
										} ];
										$scope.isWhiteMht = [ {
											id : '-1',
											value : '全部'
										} ];
										$scope.tpModel = [ {
											id : '0',
											value : '已通过'
										}, {
											id : '2',
											value : '已拒绝'
										}, {
											id : '4',
											value : '挂起'
										}, {
											id : '-1',
											value : '全部'
										} ];
										$scope.dateModel = [ {
											id : '1',
											value : '今日'
										}, {
											id : '0',
											value : '全部'
										} ];
										$scope.stModel = [ {
											id : '4',
											value : '分公司四审'
										}, {
											id : 'F',
											value : '总公司一审'
										}, {
											id : 'G',
											value : '总公司二审'
										}, {
											id : '-1',
											value : '全部'
										} ];
										$scope.isWhiteModel = [ {
											id : '1',
											value : '是'
										}, {
											id : '0',
											value : '否'
										}, {
											id : '-1',
											value : '全部'
										} ];
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
																return;
															} else {
																$scope.usrNm = data.usrNm;
																$scope.comments=data.comments;
																$http(
																		{
																			method : 'post',
																			url : '/MchntAudit/audit/queryTaskByPaging.do',
																			params : {
																				'startPage' : $scope.startPage,
																				'pageSize' : $scope.pageSize,
																			}
																		})
																		.success(
																				function(
																						data) {
																					$scope.taskLists = data.taskList;
																					$scope.totalCount = data.total;
																					$scope.totalPage = Math
																							.ceil($scope.totalCount
																									/ $scope.pageSize)
																				})
																		.error(
																				function() {
																					alert('未知异常！');
																				});
															}
														})
												.error(
														function() {
															alert("登陆超时，请重新登录！")
															$window.location.href = "/MchntAudit";
															return;
														});
									});

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

					// 查询
					$scope.query = function() {
						$scope.startPage = 1;
						$scope.queryByPaging();
					}

					$scope.queryByPaging = function() {
						$http(
								{
									method : 'post',
									url : '/MchntAudit/audit/queryTaskByPaging.do',
									params : {
										'startPage' : $scope.startPage,
										'pageSize' : $scope.pageSize,
										'taskSt' : $scope.type.id == '-1' ? undefined
												: $scope.type.id,
										'isOrNotToday' : $scope.dateChoose.id,
										'auditSt' : $scope.auditSt.id == '-1' ? undefined
												: $scope.auditSt.id,
										'isWhiteMchnt' : $scope.isWhiteMht.id == '-1' ? undefined
												: $scope.isWhiteMht.id
									}
								}).success(
								function(data) {
									$scope.taskLists = data.taskList;
									$scope.totalCount = data.total;
									$scope.totalPage = Math
											.ceil($scope.totalCount
													/ $scope.pageSize)
								}).error(function() {
							alert('未知异常！');
						});
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
							$scope.queryByPaging();
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
							$scope.queryByPaging();
						}
					}

					// 弹出审核通过窗口
					$scope.passPop = function(taskId) {
						$scope.passBox = true;
						$scope.taskId = taskId;
					}

					// 关闭审核通过窗口
					$scope.closePass = function() {
						$scope.passBox = false;
					}

					// 弹出审核拒绝窗口
					$scope.rejectPop = function(taskId,mchntTp,specDiscTp,specDiscLvl) {
						$scope.rejectBox = true;
						$scope.taskId = taskId;
						$http({
							method : 'post',
							url : '/MchntAudit/auditUtil/queryRejectReason.do',
							params : {
								mchntTp : mchntTp,
								specDiscTp : specDiscTp,
								specDiscLvl : specDiscLvl
							}
						}).success(function(data) {
							var reason_list_a = new Array();
							var reason_list_b = new Array();
							var reason_list_c = new Array();
							var reason_list_d = new Array();
							var reason_list_e = new Array();
							var reason_list_f = new Array();
							var map = data.reason;
							for ( var common in map) {
								var commonReason = {
									reasonTp : common,
									reasonMemo : map[common]
								}
								if(commonReason.reasonMemo.indexOf('提交不完整:')!=-1){
									reason_list_a.push(commonReason);
								}
								if(commonReason.reasonMemo.indexOf('已交但模糊:')!=-1){
									reason_list_b.push(commonReason);
								}
								if(commonReason.reasonMemo.indexOf('提交错误:')!=-1){
									reason_list_c.push(commonReason);
								}
								if(commonReason.reasonMemo.indexOf('资料作假:')!=-1){
									reason_list_d.push(commonReason);
								}
								if(commonReason.reasonMemo.indexOf('坐标异常:')!=-1){
									reason_list_e.push(commonReason);
								}
								if(commonReason.reasonMemo.indexOf('特殊情况:')!=-1){
									reason_list_f.push(commonReason);
								}
							}
							$scope.reason_list_a = reason_list_a;
							$scope.reason_list_b = reason_list_b;
							$scope.reason_list_c = reason_list_c;
							$scope.reason_list_d = reason_list_d;
							$scope.reason_list_e = reason_list_e;
							$scope.reason_list_f = reason_list_f;
						}).error(function() {
							alert("获取拒绝原因异常！");
						});
					}

					// 关闭审核拒绝窗口
					$scope.closeReject = function() {
						$scope.rejectBox = false;
						$scope.reasonList = new Array();
					}

					// 审核通过
					$scope.pass = function() {
						var reg = new RegExp("^[0-9]*$");
						var rating = $('#rating').val();
						if (reg.test(rating) && parseInt(rating) < 11
								&& parseInt(rating) > 5) {
							$http({
								method : 'post',
								url : '/MchntAudit/audit/pass.do',
								params : {
									'taskId' : $scope.taskId,
									'rating' : rating
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
						} else {
							alert("输入错误!");
						}
					}

					// 审核拒绝
					$scope.reject = function() {
						var rjReason = $('#rejectReason').val();
						if ($scope.reasonList.length != 0 || "" != rjReason) {
							$http({
								method : 'post',
								url : '/MchntAudit/audit/reject.do',
								headers : {
									contentType : 'application/json',
									dataType : 'json'
								},
								data : {
									'taskId' : $scope.taskId,
									'reasonList' : $scope.reasonList,
									'rjReason' : rjReason
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
						} else {
							alert('拒绝原因不能为空！')
						}
					}
					
					$scope.updateChecked = function(checked, reasonTp,
							reasonMemo) {
						if (true == checked) {
							var reason = {
								reasonTp : reasonTp,
								reasonMemo : reasonMemo
							}
							$scope.reasonList.push(reason);
						} else if (false == checked) {
							for (var i = 0; i < $scope.reasonList.length; i++) {
								if (reasonTp == $scope.reasonList[i].reasonTp) {
									var index = i;
								}
							}
							if (index > -1) {
								$scope.reasonList.splice(index, 1);
							}
						}
					}

					// 根据商户代码查询商户
					$scope.queryTaskByMchnt = function() {
						if ($scope.mchnt == undefined) {
							return;
						} else {
							$http(
									{
										method : 'post',
										url : '/MchntAudit/audit/queryTaskByMchntCd.do',
										params : {
											'mchntCd' : $scope.mchnt
										}
									}).success(function(data) {
								$scope.taskLists = data.taskList;
							}).error(function() {
								alert("查询商户代码异常");
							});
						}
					}

					// 展示商户详细信息
					$scope.showDetail = function(taskId) {
						$("#detailBox").show("slow");
						$http({
							url : '/MchntAudit/audit/queryTaskByTaskId.do',
							method : 'post',
							params : {
								'taskId' : taskId
							}
						}).success(function(data) {
							$scope.detailReason = data.reason;
							$scope.detailType = data.taskResult;
						}).error(function() {
							alert("展示商户详情异常");
						});
					}

					$scope.hideDetail = function() {
						$("#detailBox").hide();
					}

				})
		.filter('filterTask', function() {
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
		})
		.filter('filterConnMd', function() {
			return function(input) {
				if ('I' == input) {
					return '间联';
				} else {
					return '直联';
				}

			}
		})
		.filter('filterAuditSt', function() {
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
				}).filter('filterDetailType', function() {
			return function(input) {
				switch (input) {
				case '0':
					return '通过评分:';
					break;
				case '2':
					return '拒绝原因:';
					break;
				case '4':
					return '挂起原因:';
					break;
				}
			}
		}).filter('filterWhiteMchnt', function() {
			return function(input) {
				switch (input) {
				case '1':
					return '是';
					break;
				case '0':
					return '否';
					break;
				}
			}
		});
