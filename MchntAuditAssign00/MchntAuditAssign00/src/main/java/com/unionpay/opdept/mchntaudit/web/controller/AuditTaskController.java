package com.unionpay.opdept.mchntaudit.web.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unionpay.opdept.mchntaudit.model.QueryTaskByPagingReq;
import com.unionpay.opdept.mchntaudit.model.QueryTaskPagingServiceReq;
import com.unionpay.opdept.mchntaudit.model.RejectReasonReq;

import com.unionpay.opdept.mchntaudit.orm.bean.AuditUser;
import com.unionpay.opdept.mchntaudit.orm.bean.RejectReason;
import com.unionpay.opdept.mchntaudit.orm.bean.TaskAssign;

import com.unionpay.opdept.mchntaudit.service.AssignTaskService;
import com.unionpay.opdept.mchntaudit.service.AuditTaskService;
import com.unionpay.opdept.mchntaudit.utils.ProjectConfig;
import com.unionpay.opdept.mchntaudit.utils.SessionUtil;

@Controller
@RequestMapping("audit")
public class AuditTaskController {

	@Resource
	private AssignTaskService assignTaskService;

	@Resource
	private AuditTaskService auditTaskService;
	
	
	
	private static final Logger logger = Logger.getLogger(AuditTaskController.class);

	// 每日挂起最大数量
	private static final String TASK_PENING_DAILYNUM = "task.pending.dailyNum";

	// 总挂起最大数量
	private static final String TASK_PENDING_TOTALNUM = "task.pending.totalNum";

	/**
	 * 获取非标实时工单任务
	 * 
	 * @param request
	 * @return AuditTask
	 * @throws Exception
	 */
	@RequestMapping(value = "getAuditTask.do", method = RequestMethod.POST)
	@ResponseBody
	public TaskAssign getAuditTask(HttpServletRequest request) throws Exception {
		AuditUser user = SessionUtil.checkSession(request);
		TaskAssign taskAssign = null;
		if (null != user) {
			taskAssign = assignTaskService.getTaskByUser(user);
		}
		return taskAssign;
	}

	/**
	 * 获取已分配任务
	 * 
	 * @param request
	 * @return TaskAssign
	 * @throws Exception
	 */
	@RequestMapping(value = "getPreviousTask.do", method = RequestMethod.POST)
	@ResponseBody
	public TaskAssign getPreviousTask(@Param("isWhiteMchnt") String isWhiteMchnt, HttpServletRequest request)
			throws Exception {
		AuditUser user = SessionUtil.checkSession(request);
		TaskAssign taskAssign = null;
		if (null != user && null != user.getUsrCd()) {
			taskAssign = assignTaskService.getPreviousTask(isWhiteMchnt, user.getUsrCd());
		}
		return taskAssign;
	}

	/**
	 * 审核通过
	 * 
	 * @param taskId
	 * @param rating
	 * @param request
	 * @return result
	 * @throws Exception
	 */
	@RequestMapping(value = "pass.do", method = RequestMethod.POST)
	@ResponseBody
	public int passTask(@RequestParam("taskId") String taskId, @RequestParam("rating") int rating,
			HttpServletRequest request) throws Exception {
		AuditUser user = SessionUtil.checkSession(request);
		int result = assignTaskService.passTask(rating, user.getUsrCd(), taskId);
		return result;
	}

	/**
	 * 审核非标拒绝
	 * 
	 * @param taskId
	 * @param rjReason
	 * @param request
	 * @return result
	 * @throws Exception
	 */
	@RequestMapping(value = "reject.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> rejectTask(@RequestBody RejectReasonReq req, HttpServletRequest request)
			throws Exception {
		AuditUser user = SessionUtil.checkSession(request);
		List<RejectReason> reasonList = new ArrayList<RejectReason>();
		Map<String, Object> result = new HashMap<String, Object>();
		// 如果List不为空，则获取List
		if (null != req && null != req.getReasonList()) {
			reasonList = req.getReasonList();
		}
		// 如果填写其他原因，则加入List
		if (!req.getRjReason().equals("")) {
			RejectReason rj = new RejectReason();
			rj.setReasonTp("0");
			rj.setReasonMemo(req.getRjReason());
			reasonList.add(rj);
		}
		int rejectRsp = assignTaskService.rejectTask(reasonList, user.getUsrCd(), req.getTaskId());
		result.put("rsp", rejectRsp);
		return result;
	}

	/**
	 * 存量商户拒绝
	 * 
	 * @param taskId
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "rejectStockTask.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> rejectStockTask(@RequestParam("taskId") String taskId, HttpServletRequest request)
			throws Exception {
		AuditUser user = SessionUtil.checkSession(request);
		Map<String, Object> result = new HashMap<String, Object>();
		int rejectRsp = assignTaskService.rejectTask(null, user.getUsrCd(), taskId);
		result.put("rsp", rejectRsp);
		return result;
	}

	/**
	 * 挂起
	 * 
	 * @param taskId
	 * @param pdReason
	 * @param request
	 * @return result
	 * @throws Exception
	 */
	@RequestMapping(value = "pending.do", method = RequestMethod.POST)
	@ResponseBody
	public int pendingTask(@RequestParam("taskId") String taskId, @RequestParam("pdReason") String pdReason,
			HttpServletRequest request) throws Exception {
		AuditUser user = SessionUtil.checkSession(request);
		int result = assignTaskService.pendingTask(pdReason, user.getUsrCd(), taskId);
		return result;
	}

	/**
	 * 分页查询历史任务
	 * 
	 * @param startPage
	 * @param pageSize
	 * @param taskSt
	 * @param request
	 * @return list<TaskAssign>
	 * @throws Exception
	 */
	@RequestMapping(value = "queryTaskByPaging.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryTaskByPaging(QueryTaskByPagingReq req, HttpServletRequest request)
			throws Exception {
		AuditUser user = SessionUtil.checkSession(request);
		QueryTaskPagingServiceReq queryTaskPagingServiceReq = new QueryTaskPagingServiceReq();
		queryTaskPagingServiceReq = convertTaskByPaging(req, user);
		// 分页查询详细记录
		List<TaskAssign> taskList = assignTaskService.queryTaskByPaging(queryTaskPagingServiceReq);
		// 查询总数
		int total = assignTaskService.queryTotalTaskCount(queryTaskPagingServiceReq);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("taskList", taskList);
		resultMap.put("total", total);
		return resultMap;
	}

	/**
	 * 分页查询请求处理
	 * 
	 * @param req
	 * @return
	 */
	private QueryTaskPagingServiceReq convertTaskByPaging(QueryTaskByPagingReq req, AuditUser user) {
		QueryTaskPagingServiceReq request = new QueryTaskPagingServiceReq();
		if (null != req && null != req.getIsOrNotToday() && req.getIsOrNotToday().equals("1")) {
			Date startTime = getTodayStartTime();
			Date endTime = getTodayEndTime();
			request.setStartTime(startTime);
			request.setEndTime(endTime);
		}
		request.setAuditSt(req.getAuditSt());
		request.setPageSize(req.getPageSize());
		request.setStartPage(req.getStartPage());
		request.setTaskSt(req.getTaskSt());
		request.setUsrCd(user.getUsrCd());
		request.setIsWhiteMchnt(req.getIsWhiteMchnt());
		return request;
	}

	/**
	 * 获取今日挂起任务数量
	 * 
	 * @param taskSt
	 * @param request
	 * @return total 1:每日最大挂起数超出限制 2:总最大挂起数超出限制 0:未超出限制
	 * @throws Exception
	 */
	@RequestMapping(value = "queryTodayTask.do", method = RequestMethod.POST)
	@ResponseBody
	public int queryTodayTask(@RequestParam("taskSt") String taskSt, HttpServletRequest request) throws Exception {
		AuditUser user = SessionUtil.checkSession(request);
		Date startTime = getTodayStartTime();
		Date endTime = getTodayEndTime();
		int today = assignTaskService.queryTotalNumByTime(user.getUsrCd(), taskSt, startTime, endTime);
		if (today > Integer.valueOf(ProjectConfig.getValue(TASK_PENING_DAILYNUM)) - 1) {
			logger.debug("Daily Pending Task Number Limit Exceeded");
			return 1;
		}
		QueryTaskPagingServiceReq req = new QueryTaskPagingServiceReq();
		req.setUsrCd(user.getUsrCd());
		req.setTaskSt(taskSt);
		int total = assignTaskService.queryTotalTaskCount(req);
		if (total > Integer.valueOf(ProjectConfig.getValue(TASK_PENDING_TOTALNUM)) - 1) {
			logger.debug("Total Pending Task Number Limit Exceeded");
			return 2;
		}
		return 0;
	}

	private Date getTodayStartTime() {
		Calendar todayStart = Calendar.getInstance();
		todayStart.set(Calendar.HOUR_OF_DAY, 0);
		todayStart.set(Calendar.MINUTE, 0);
		todayStart.set(Calendar.SECOND, 0);
		todayStart.set(Calendar.MILLISECOND, 0);
		return todayStart.getTime();
	}

	private Date getTodayEndTime() {
		Calendar todayEnd = Calendar.getInstance();
		todayEnd.set(Calendar.HOUR_OF_DAY, 23);
		todayEnd.set(Calendar.MINUTE, 59);
		todayEnd.set(Calendar.SECOND, 59);
		todayEnd.set(Calendar.MILLISECOND, 999);
		return todayEnd.getTime();
	}

	/**
	 * 查询每日工单完成情况
	 * 
	 * @param request
	 * @return map
	 * @throws Exception
	 */
	@RequestMapping(value = "queryAllTask.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryAllTask(HttpServletRequest request) throws Exception {
		AuditUser user = SessionUtil.checkSession(request);
		Date startTime = getTodayStartTime();
		Date endTime = getTodayEndTime();
		List<TaskAssign> taskList = assignTaskService.queryTotalByTime(user.getUsrCd(), "-1", startTime, endTime);
		int psNum = 0;
		int pdNum = 0;
		int rjNum = 0;
		int onNum = 0;
		for (TaskAssign ta : taskList) {
			if (null != ta.getTaskResult() && ta.getTaskResult().equals("0")) {
				psNum++;
			}
			if (null != ta.getTaskResult() && ta.getTaskResult().equals("4")) {
				pdNum++;
			}
			if (null != ta.getTaskResult() && ta.getTaskResult().equals("2")) {
				rjNum++;
			}
			if (null != ta.getTaskResult() && ta.getTaskResult().equals("3")) {
				onNum++;
			}
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("psNum", psNum);
		result.put("pdNum", pdNum);
		result.put("rjNum", rjNum);
		result.put("onNum", onNum);
		return result;
	}

	/**
	 * 通过商户代码列表查询
	 * 
	 * @param mchntCd
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "queryTaskByMchntCd.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryTaskByMchntCd(@Param("mchntCd") String mchntCd, HttpServletRequest request)
			throws Exception {
		AuditUser user = SessionUtil.checkSession(request);
		TaskAssign taskAssign = new TaskAssign();
		taskAssign.setMchntCd(mchntCd);
		taskAssign.setAssignUsrCd(user.getUsrCd());
		List<TaskAssign> taskList = assignTaskService.queryTaskByRecord(taskAssign);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("taskList", taskList);
		return result;
	}

	/**
	 * 查询任务通过、拒绝、挂起原因
	 * 
	 * @param taskId
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "queryTaskByTaskId.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryTaskByTaskId(@Param("taskId") String taskId, HttpServletRequest request)
			throws Exception {
		AuditUser user = SessionUtil.checkSession(request);
		TaskAssign taskAssign = new TaskAssign();
		taskAssign.setTaskId(taskId);
		taskAssign.setAssignUsrCd(user.getUsrCd());
		List<TaskAssign> taskList = assignTaskService.queryTaskByRecord(taskAssign);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (null != taskList && null != taskList.get(0)) {
			String taskResult = taskList.get(0).getTaskResult();
			if (taskResult.equals("0")) {
				resultMap.put("reason", taskList.get(0).getMchntRating());
			} else if (taskResult.equals("2")) {
				List<RejectReason> reasonList = assignTaskService.queryRejectReason(taskId);
				if (null != reasonList) {
					String rejectMemo = "";
					for (int i = 0; i < reasonList.size(); i++) {
						rejectMemo += (i + 1) + ":" + reasonList.get(i).getReasonMemo() + "    ";
					}
					resultMap.put("reason", rejectMemo);
				}
			} else if (taskResult.equals("4")) {
				resultMap.put("reason", taskList.get(0).getPendingMemo());
			}
			resultMap.put("taskResult", taskResult);
		}
		return resultMap;
	}

	/**
	 * 查询总剩余工单数量
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "queryRestTask.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryRestTask(@Param("isWhiteMchnt") String isWhiteMchnt, HttpServletRequest request)
			throws Exception {
		SessionUtil.checkSession(request);
		int total = auditTaskService.queryRestTask(isWhiteMchnt);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", total);
		return resultMap;
	}

	/**
	 * 获取存量实时任务
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getStockAuditTask.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getStockAuditTask(HttpServletRequest request) throws Exception {
		AuditUser user = SessionUtil.checkSession(request);
		TaskAssign taskAssign = null;
		if (null != user) {
			taskAssign = assignTaskService.getStockTaskByUser(user);
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("taskAssign", taskAssign);
		return resultMap;
	}

}