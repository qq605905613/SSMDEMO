package com.unionpay.opdept.mchntaudit.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.unionpay.opdept.mchntaudit.orm.bean.AuditUser;
import com.unionpay.opdept.mchntaudit.web.controller.AuditTaskController;

public class SessionUtil {

	private static final Logger logger = Logger.getLogger(AuditTaskController.class);

	/**
	 * 用户session校验
	 * 
	 * @param request
	 * @return AuditUser
	 * @throws Exception
	 */
	public static AuditUser checkSession(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		if (null == session || null == session.getAttribute("user")) {
			logger.error("check session expired");
			throw new Exception("check session expired");
		}
		AuditUser user = (AuditUser) session.getAttribute("user");
		return user;
	}
}
