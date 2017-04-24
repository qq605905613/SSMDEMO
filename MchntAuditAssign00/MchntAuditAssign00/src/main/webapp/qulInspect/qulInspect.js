angular
		.module('inspectApp', [])
		.controller(
				'inspectCtrl',
				function($scope, $http, $window) {
					$(document).ready(function() {
						$scope.mccList = [ {
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
						$scope.taskRtList = [ {
							key : '0',
							value : '通过'
						}, {
							key : '2',
							value : '拒绝'
						}, {
							key : '',
							value : '审核结果'
						} ];
						$scope.mchntRtList = [ {
							key : '6',
							value : '6'
						}, {
							key : '7',
							value : '7'
						}, {
							key : '8',
							value : '8'
						}, {
							key : '9',
							value : '9'
						}, {
							key : '10',
							value : '10'
						}, {
							key : '',
							value : '通过评分'
						} ];

						$(".form_datetime").datetimepicker();

						$http({
							url : '/MchntAudit/user/getUser.do',
							method : 'post'
						}).success(function(data) {
							if ("" == data) {
								alert("登陆超时，请重新登录！")
								$window.location.href = "/MchntAudit";
							} else {
								$scope.usrNm = data.usrNm;
								$scope.comments = data.comments;
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

					$scope.getInspect = function() {
						var startTime = $('#startTime').val();
						var endTime = $('#endTime').val();

						$http(
								{
									method : 'post',
									url : '/MchntAudit/qualityInspect/getInspectTask.do',
									data : {
										'startTime' : startTime == "" ? undefined
												: new Date(startTime),
										'endTime' : endTime == "" ? undefined
												: new Date(endTime),
										'assignUsrNm' : $scope.assignUsrNm == "" ? undefined
												: $scope.assignUsrNm,
										'cupBranchIdCd' : $scope.cupBranchIdCd == "" ? undefined
												: $scope.cupBranchIdCd,
										'groupId' : $scope.groupId == "" ? undefined
												: $scope.groupId,
										'taskResult' : $scope.taskResult == "" ? undefined
												: $scope.taskResult,
										'mchntRating' : $scope.mchntRating == "" ? undefined
												: $scope.mchntRating,
										'mccTp' : $scope.mccTp == "" ? undefined
												: $scope.mccTp
									}
								})
								.success(
										function(data) {
											if (null == data.inspect) {
												$('#myDetailBox').modal('hide');
												alert('无符合要求的记录！');
											} else {
												$scope.taskId = data.inspect.taskId;
												$scope.mchntCd = data.inspect.mchntCd;
												$scope.mchntCnNm = data.inspect.mchntCnNm;
												$scope.connMd = data.inspect.connMd;
												$scope.auditSt = data.inspect.auditSt;
												var priceTp = {
													'specDiscTp' : data.inspect.specDiscTp,
													'specDiscLvl' : data.inspect.specDiscLvl,
													'mchntTp' : data.inspect.mchntTp,
												};
												$scope.priceTp = priceTp;
												$scope.mchntTp = data.inspect.mchntTp;
												$scope.mchntSrvTp = data.inspect.mchntSrvTp;
												$scope.cupBranchInsIdCd = data.inspect.cupBranchInsIdCd;
												$scope.taskRs = data.inspect.taskResult;
											}
										}).error(function() {
									$('#myDetailBox').modal('hide');
									alert("获取质检单异常！");
								});
					}

					$scope.dealInspect = function(isRight) {
						var flag = false;
						if (isRight == '1') {
							var memo = $scope.rightReason;
							flag = true;
						} else if (isRight == '2') {
							var memo = $scope.wrongReason;
							if (memo == undefined) {
								alert('拒绝原因不能为空');
							} else {
								flag = true;
							}
						}
						if (flag) {
							$http(
									{
										method : 'post',
										url : '/MchntAudit/qualityInspect/processInspectTask.do',
										params : {
											'taskId' : $scope.taskId,
											'isRight' : isRight,
											'memo' : memo
										}
									}).success(function(data) {
								if (data.result == 1) {
									alert('提交成功');
									$('#myWrongBox').modal('hide');
									$('#myRightBox').modal('hide');
									$('#myDetailBox').modal('hide');
									$scope.rightReason = undefined;
									$scope.wrongReason = undefined;
								} else {
									alert('提交失败');
								}
							}).error(function() {
								alert('提交质检任务未知异常');
							});
						}
					}
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
		}).filter('filterTaskRs', function() {
			return function(input) {
				switch (input) {
				case '0':
					return '通过';
					break;
				case '2':
					return '拒绝';
					break;
				}
			}
		});