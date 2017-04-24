angular
		.module('taskApp', [])
		.controller(
				'taskCtrl',
				function($scope, $http, $window, $interval) {
					// 初始化
					$(document).ready(function() {
						$scope.passBox = false;
						$scope.rejectBox = false;
						$scope.pendingBox = false;
						$scope.showButton = false;
						$scope.LoadBtn = false;
						$scope.reasonList = new Array();

						$http({
							url : '/MchntAudit/user/getUser.do',
							method : 'post'
						}).success(function(data) {
							if ("" == data) {
								alert("登陆超时，请重新登录！")
								$window.location.href = "/MchntAudit";
							} else {
								$scope.comments = data.comments;
								$scope.queryRestTask();
							}
						}).error(function() {
							alert("登陆超时，请重新登录！")
							$window.location.href = "/MchntAudit";
						});
					});
				

					$scope.getTask = function() {
						$scope.LoadBtn = true;
						$http({
							method : 'post',
							url : '/MchntAudit/audit/getPreviousTask.do',
							params : {
								'isWhiteMchnt' : '1'
							}
						})
								.success(
										function(data) {
											if ("" != data) {
												$scope.taskAssign = data;
												$scope.mchntCd = data.mchntCd;
												$scope.mchntCnNm = data.mchntCnNm;
												$scope.connMd = data.connMd;
												$scope.taskId = data.taskId;
												$scope.mchntTp = data.mchntTp;
												$scope.mchntSrvTp = data.mchntSrvTp;
												$scope.cupBranchInsIdCd = data.cupBranchInsIdCd;
												$scope.auditSt = data.auditSt;
												$scope.acptInsIdCd =data.acptInsIdCd;
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
															url : '/MchntAudit/audit/getAuditTask.do'
														})
														.success(
																function(res) {
																	if ("" == res) {
																		alert('今日任务已派发完毕！');
																		$scope.LoadBtn = false;
																	} else {
																		$scope.taskAssign = res;
																		$scope.mchntCd = res.mchntCd;
																		$scope.mchntCnNm = res.mchntCnNm;
																		$scope.connMd = res.connMd;
																		$scope.taskId = res.taskId;
																		$scope.mchntTp = res.mchntTp;
																		$scope.mchntSrvTp = res.mchntSrvTp;
																		$scope.cupBranchInsIdCd = res.cupBranchInsIdCd;
																		$scope.auditSt = res.auditSt;
																		$scope.acptInsIdCd =res.acptInsIdCd;
																		$scope.auditSt = res.auditSt;
																		var priceTp = {
																			'specDiscTp' : res.specDiscTp,
																			'specDiscLvl' : res.specDiscLvl,
																			'mchntTp' : res.mchntTp,
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

					// 点击审核按钮
					/*$scope.passPop = function() {
						$scope.passBox = true;
					}*/

					// 关闭审核通过窗口
					$scope.closePass = function() {
						$scope.passBox = false;
					}

					// 审核通过
					$scope.passPop = function() {
						var reg = new RegExp("^[0-9]*$");
						var rating = 8;
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
									//alert('提交成功！');
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
					
				
					// 点击审核拒绝按钮
					$scope.rejectPop = function() {
						$scope.rejectBox = true;
						$http({
							method : 'post',
							url : '/MchntAudit/auditUtil/queryRejectReason.do',
							params : {
								mchntTp : $scope.taskAssign.mchntTp,
								specDiscTp : $scope.taskAssign.specDiscTp,
								specDiscLvl : $scope.taskAssign.specDiscLvl
							}
						}).success(
								function(data) {
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
										if (commonReason.reasonMemo
												.indexOf('材料缺失') != -1) {
											reason_list_a.push(commonReason);
										}
										if (commonReason.reasonMemo
												.indexOf('已交但模糊:') != -1) {
											reason_list_b.push(commonReason);
										}
										if (commonReason.reasonMemo
												.indexOf('提交错误:') != -1) {
											reason_list_c.push(commonReason);
										}
										if (commonReason.reasonMemo
												.indexOf('资料作假:') != -1) {
											reason_list_d.push(commonReason);
										}
										if (commonReason.reasonMemo
												.indexOf('坐标异常:') != -1) {
											reason_list_e.push(commonReason);
										}
										if (commonReason.reasonMemo
												.indexOf('特殊情况:') != -1) {
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
						$scope.reasonList = new Array();
						$scope.rejectBox = false;
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
					

					// 点击挂起按钮
					$scope.pendingPop = function() {
						$scope.pendingBox = true;
					}

					// 关闭审核挂起窗口
					$scope.closePending = function() {
						$scope.pendingBox = false;
					}

					// 审核挂起
					$scope.pending = function() {
						$http({
							method : 'post',
							url : '/MchntAudit/audit/queryTodayTask.do',
							params : {
								'taskSt' : '4'
							}

						}).success(function(data) {
							if (data == '1') {
								alert("每日最大挂起数超出限制！");
								return;
							}
							if (data == '2') {
								alert("总最大挂起数超出限制！");
								return;
							} else {
								var pendingReason = $('#pendingReason').val();
								if (pendingReason) {
									$http({
										method : 'post',
										url : '/MchntAudit/audit/pending.do',
										params : {
											'taskId' : $scope.taskId,
											'pdReason' : pendingReason
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
									alert('挂起原因不能为空！')
								}
							}
						}).error(function() {
							alert("未知异常！")
						});

					}

					$scope.queryRestTask = function() {
						$http({
							url : '/MchntAudit/audit/queryRestTask.do',
							method : 'post',
							params : {
								'isWhiteMchnt' : '1'
							}
						}).success(function(data) {
							$scope.restNum = data.total;
						}).error(function() {
							alert("获取剩余任务数异常!");
						});
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

					$interval(function() {
						$scope.queryRestTask();
					}, 30000);
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
		}
				
				).filter('filterAcp',function(){
				
					return function(input){
						if (input == undefined) {
							return  ;
						} 	
						else if(input.substr(6,2) == '11' || input.substr(6,2) == '10'){
							return '天津'	 ;		
						}else if(input.substr(6,2) == '12' || input.substr(6,2) == '13' || input.substr(6,2) == '14' ||input.substr(6,2) == '15' 
						 ){
							return '河北';
						}
						else if(input.substr(6,2) == '16' || input.substr(6,2) == '17' || input.substr(6,2) == '18' ) {	
							return '山西';
						}else if (input.substr(6,2) == '19'|| input.substr(6,2) == '20'||input.substr(6,2) =='21' ){
							return '内蒙古'
							
						}else if (input.substr(6,2) == '22' ||input.substr(6,2) =='23' ){
							if (input.substr(7,3) == '222'){
								return '大连';
							}
							return '辽宁' ;
						}else if(input.substr(6,2) == '24' || input.substr(6,2) == '25'){
							return '吉林';
							
						}else if (input.substr(6,2) == '26'|| input.substr(6,2) == '27' || input.substr(6,2) == '28'  ){
							return '黑龙江';
						}else if (input.substr(6,2) == '29'){
							return '上海';
							
						}else if (input.substr(6,2) == '30'||input.substr(6,2) == '31'||input.substr(6,2) == '32'){
							return '江苏';
						}else if (input.substr(6,2) == '33' || input.substr(6,2) == '34' ||input.substr(6,2) == '35' ){
								return '浙江' ;
						}else if (input.substr(6,2) == '36' || input.substr(6,2) == '37' || input.substr(6,2) == '38'){
							return '安徽'	;					
						}else if (input.substr(6,2) == '39'||input.substr(6,2) == '40'||input.substr(6,2) == '41'){
							return '福建' ;							
						}else if ( input.substr(6,2) == '42'||input.substr(6,2) == '43'||input.substr(6,2) == '44'){
							return '江西';
							
						}else if (input.substr(6,2) == '45' ||input.substr(6,2) == '46'||input.substr(6,2) == '47'||input.substr(6,2) == '48'){
							return '山东';
							
						}else if (input.substr(6,2) == '49'||input.substr(6,2) == '50'||input.substr(6,2) == '51'){
							return '河南';							
						}else if (input.substr(6,2) == '52'||input.substr(6,2) == '53'||input.substr(6,2) == '54'){
							return '湖北';
						}else if (input.substr(6,2) == '55' ||input.substr(6,2) == '56'||input.substr(6,2) == '57'){
							return '湖南';
						}else if (input.substr(6,2) == '58'||input.substr(6,2) == '59'|| input.substr(6,2) == '60'){
							return '广东';
							
						}else if (input.substr(6,2) == '61'||input.substr(6,2) == '62' || input.substr(6,2) == '63'){
							return '广西';
						}else if (input.substr(6,2) == '64'){
							return '海南'
						}else if(input.substr(6,2) == '65'||input.substr(6,2) == '66'||input.substr(6,2) == '66'||input.substr(6,2) == '67'||input.substr(6,2) == '68'){
							return '四川';
						}else if(input.substr(6,2) == '69'){
							return '重庆';
						}else if (input.substr(6,2) == '70'||input.substr(6,2) == '71'||input.substr(6,2) == '72' ){
							return '贵州';
						}else if (input.substr(6,2) == '73'||input.substr(6,2) == '74'||input.substr(6,2) == '75' || input.substr(6,2) =='76'){
							return '云南';
						}else if (input.substr(6,2) == '79'||input.substr(6,2)=='80'||input.substr(6,2)=='81'){
							return '陕西';
							
						}else if (input.substr(6,2) == '82'||input.substr(6,2)=='83'||input.substr(6,2)=='84'){
							return '甘肃';
							
						}else if (input.substr(6,2)=='85'||input.substr(6,2)=='86'){
							return '青海';
						}else if (input.substr(6,2)=='87'){
							return '宁夏';
						}else if (input.substr(6,2)=='88'||input.substr(6,2)=='89'||input.substr(6,2)=='90'){
							return '新疆';
						}
					}
				});