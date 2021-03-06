jQuery(function($) {
	// 备份jquery的ajax方法
	var _ajax = $.ajax;
	// 重写ajax方法，先判断登录在执行success函数
	$.ajax = function(opt) {
		var _success = opt && opt.success || function(a, b) {
		};
		var _opt = $.extend(opt, {
			success : function(data, textStatus,htr) {
				// 如果后台将请求重定向到了登录页，则data里面存放的就是登录页的源码，这里需要找到data是登录页的证据(标记)
				var sessionStatus = htr.getResponseHeader("sessionstatus");
				if (sessionStatus == "timeOut") {
					alert("登录超时，请重新登录！");
					location.href = "/MchntAudit";
					return;
				}
				_success(data, textStatus);
			}
		});
		_ajax(_opt);
	};
});
