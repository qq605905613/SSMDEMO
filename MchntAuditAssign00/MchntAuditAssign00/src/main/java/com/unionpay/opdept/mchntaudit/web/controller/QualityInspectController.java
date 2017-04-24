package com.unionpay.opdept.mchntaudit.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unionpay.opdept.mchntaudit.model.QualityInspectTaskReq;
import com.unionpay.opdept.mchntaudit.model.QueryInspectByPagingReq;
import com.unionpay.opdept.mchntaudit.model.QueryInspectByPagingRsp;
import com.unionpay.opdept.mchntaudit.orm.bean.AuditUser;
import com.unionpay.opdept.mchntaudit.orm.bean.QualityInspect;
import com.unionpay.opdept.mchntaudit.service.QualityInspectService;
import com.unionpay.opdept.mchntaudit.utils.SessionUtil;

@Controller
@RequestMapping("qualityInspect")
public class QualityInspectController {

	@Resource
	private QualityInspectService qualityInspectService;

	/**
	 * 获取质检任务
	 * 
	 * @param qualityInspectTaskReq
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getInspectTask.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getInspectTask(@RequestBody QualityInspectTaskReq qualityInspectTaskReq,
			HttpServletRequest request) throws Exception {
		AuditUser user = SessionUtil.checkSession(request);
		Map<String, Object> result = new HashMap<String, Object>();
		if (user.getComments().equals("1")) {
			QualityInspect qualityInspect = qualityInspectService.getInspectTask(qualityInspectTaskReq, user);
			result.put("inspect", qualityInspect);
		}
		return result;
	}

	/**
	 * 处理质检任务
	 * 
	 * @param taskId
	 * @param isRight
	 * @param memo
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "processInspectTask.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> processInspectTask(@RequestParam("taskId") String taskId, @RequestParam String isRight,
			@RequestParam(value = "memo", required = false) String memo, HttpServletRequest request) throws Exception {
		AuditUser user = SessionUtil.checkSession(request);
		Map<String, Object> result = new HashMap<String, Object>();
		if (user.getComments().equals("1")) {
			int num = qualityInspectService.processInspectTask(taskId, isRight, memo);
			result.put("result", num);
		}
		return result;
	}

	/**
	 * 分页查询质检历史记录
	 * 
	 * @param req
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "queryInsByPaging.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryInspectByPaging(@RequestBody QueryInspectByPagingReq req,
			HttpServletRequest request) throws Exception {
		AuditUser user = SessionUtil.checkSession(request);
		Map<String, Object> result = new HashMap<String, Object>();
		if (user.getComments().equals("1")) {
			req.setUsrCd(user.getUsrCd());
			QueryInspectByPagingRsp rsp = qualityInspectService.queryInspectByPaging(req);
			result.put("result", rsp);
		}
		return result;
	}
}
