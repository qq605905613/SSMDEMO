/**
 * 
 */
package com.unionpay.opdept.mchntaudit.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Timer;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.unionpay.opdept.mchntaudit.model.AuditAssignQueue;
import com.unionpay.opdept.mchntaudit.model.QueryTaskPagingServiceReq;
import com.unionpay.opdept.mchntaudit.model.StockAuditAssignQueue;
import com.unionpay.opdept.mchntaudit.orm.bean.AuditTask;
import com.unionpay.opdept.mchntaudit.orm.bean.AuditUser;
import com.unionpay.opdept.mchntaudit.orm.bean.RejectReason;
import com.unionpay.opdept.mchntaudit.orm.bean.TaskAssign;
import com.unionpay.opdept.mchntaudit.orm.mapper.AuditTaskMapper;
import com.unionpay.opdept.mchntaudit.orm.mapper.RejectReasonMapper;
import com.unionpay.opdept.mchntaudit.orm.mapper.TaskAssignMapper;
import com.unionpay.opdept.mchntaudit.service.AssignTaskService;
import com.unionpay.opdept.mchntaudit.utils.PreviousTaskTimer;

/**
 * @author DATAN
 *
 */
@Service("assignTaskService")
@Transactional
public class AssignTaskServiceImpl implements AssignTaskService {

	private static final Logger logger = Logger.getLogger(AssignTaskServiceImpl.class);

	@Resource
	private AuditTaskMapper auditTaskMapper;

	@Resource
	private TaskAssignMapper taskAssignMapper;

	@Resource
	private RejectReasonMapper rejectReasonMapper;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public TaskAssign getTaskByUser(AuditUser user) {
		TaskAssign taskAssign = null;
		AuditAssignQueue auditAssignQueue = AuditAssignQueue.getInstance();
		AuditTask auditTask = auditAssignQueue.poll(user);
		if (null != auditTask) {
			auditTask.setTaskResult("3");
			auditTask.setRecUpdTs(new Date());
			int result = auditTaskMapper.updateByPrimaryKeySelective(auditTask);
			logger.info("[getTaskByUser=" + auditTask.toString() + "],[usrCd=" + user.getUsrCd() + "]");
			if (result != 1) {
				logger.error("update auditTask false!" + "[result=" + result + "]" + "[usrCd=" + user.getUsrCd() + "]");
				return null;
			}
			taskAssign = insertAssign(user, auditTask);
		}
		return taskAssign;
	}

	private TaskAssign insertAssign(AuditUser user, AuditTask auditTask) {
		TaskAssign taskAssign = new TaskAssign();
		taskAssign.setTaskId(auditTask.getTaskId());
		taskAssign.setMchntSrvTp(auditTask.getMchntSrvTp());
		taskAssign.setMchntCd(auditTask.getMchntCd());
		taskAssign.setMchntCnNm(auditTask.getMchntCnNm());
		taskAssign.setAcqInsIdCd(auditTask.getAcqInsIdCd());
		taskAssign.setAcptInsIdCd(auditTask.getAcptInsIdCd());
		taskAssign.setCupBranchInsIdCd(auditTask.getCupBranchInsIdCd());
		taskAssign.setConnMd(auditTask.getConnMd());
		taskAssign.setMchntTp(auditTask.getMchntTp());
		taskAssign.setSpecDiscTp(auditTask.getSpecDiscTp());
		taskAssign.setSpecDiscLvl(auditTask.getSpecDiscLvl());
		taskAssign.setIsWhiteMchnt(auditTask.getIsWhiteMchnt());
		taskAssign.setAcqCommitAuditTs(auditTask.getAcqCommitAuditTs());
		taskAssign.setAuditSt(auditTask.getAuditSt());
		taskAssign.setMchntAuditId(auditTask.getMchntAuditId());
		taskAssign.setAssignTs(new Date());
		taskAssign.setAssignUsrCd(user.getUsrCd());
		taskAssign.setAssignGroupId(user.getGroupId());
		taskAssign.setPlanDoneTs(new Date(new Date().getTime() + 3 * 24 * 3600 * 1000));
		taskAssign.setActualDoneTs(new Date());
		taskAssign.setTaskResult("3");
		taskAssign.setRecSt("0");
		taskAssign.setRecCrtTs(new Date());
		taskAssign.setRecUpdTs(new Date());
		taskAssignMapper.insertSelective(taskAssign);
		return taskAssign;
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public TaskAssign getPreviousTask(String isWhiteMchnt, String userCd) {
		TaskAssign taskAssign = taskAssignMapper.selectTaskByUser(isWhiteMchnt, userCd);
		return taskAssign;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int passTask(int rating, String userCd, String taskId) {
		TaskAssign taskAssign = new TaskAssign();
		taskAssign.setActualDoneTs(new Date());
		taskAssign.setTaskResult("0");
		taskAssign.setMchntRating(rating);
		taskAssign.setRecUpdTs(new Date());
		taskAssign.setAssignUsrCd(userCd);
		taskAssign.setTaskId(taskId);
		int result = taskAssignMapper.updateByPrimaryKeyAndUserSelective(taskAssign);
		logger.info("passTask：[usrCd=" + userCd + "],[result=" + result + "],[passTask=" + taskAssign.toString() + "]");
		if (result == 1) {
			Timer timer = new Timer();
			PreviousTaskTimer preTaskTimer = new PreviousTaskTimer(taskId, userCd);
			timer.schedule(preTaskTimer, 1800000);
			return result;
		}
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int rejectTask(List<RejectReason> reasonList, String userCd, String taskId) {
		// 修改taskAssign表中task_result=2
		TaskAssign taskAssign = new TaskAssign();
		taskAssign.setActualDoneTs(new Date());
		taskAssign.setTaskResult("2");
		taskAssign.setRecUpdTs(new Date());
		taskAssign.setAssignUsrCd(userCd);
		taskAssign.setTaskId(taskId);
		taskAssignMapper.updateByPrimaryKeyAndUserSelective(taskAssign);
		logger.info("rejectTask：[usrCd=" + userCd + "],[taskAssign=" + taskAssign.toString() + "]");
		// 将原因详情插入拒绝原因表rejectReason
		if (null != reasonList) {
			for (int i = 0; i < reasonList.size(); i++) {
				reasonList.get(i).setReasonId(taskId + reasonList.get(i).getReasonTp());
				reasonList.get(i).setTaskId(taskId);
				reasonList.get(i).setRecSt("0");
				reasonList.get(i).setRecCrtTs(new Date());
				reasonList.get(i).setRecUpdTs(new Date());
				logger.info("[usrCd=" + userCd + "]," + reasonList.get(i).toString());
			}
			rejectReasonMapper.insertByList(reasonList);
		}
		return 1;
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public int pendingTask(String pdReason, String userCd, String taskId) {
		TaskAssign taskAssign = new TaskAssign();
		taskAssign.setActualDoneTs(new Date());
		taskAssign.setTaskResult("4");
		taskAssign.setPendingMemo(pdReason);
		taskAssign.setRecUpdTs(new Date());
		taskAssign.setAssignUsrCd(userCd);
		taskAssign.setTaskId(taskId);
		int result = taskAssignMapper.updateByPrimaryKeyAndUserSelective(taskAssign);
		logger.info("pendingTask:[usrCd=" + userCd + "],[result=" + result + "],[taskAssign=" + taskAssign.toString()
				+ "]");
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<TaskAssign> queryTaskByPaging(QueryTaskPagingServiceReq req) {
		int startNum = req.getPageSize() * (req.getStartPage() - 1) + 1;
		int endNum = req.getPageSize() * req.getStartPage();
		req.setStartNum(startNum);
		req.setEndNum(endNum);
		List<TaskAssign> taskList = taskAssignMapper.selectTaskByPaging(req);
		logger.info("queryTaskByPaging:[usrCd=" + req.getUsrCd() + "],[req=" + req.toString() + "]");
		return taskList;
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public int queryTotalTaskCount(QueryTaskPagingServiceReq req) {
		int total = taskAssignMapper.selectTotalTaskCount(req);
		logger.info("queryTotalTaskCount:[usrCd=" + req.getUsrCd() + "],[req=" + req.toString() + "],[total=" + total
				+ "]");
		return total;
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<TaskAssign> queryTotalByTime(String userCd, String taskSt, Date startTime, Date endTime) {
		if (null != taskSt && taskSt.equals("-1")) {
			taskSt = null;
		}
		List<TaskAssign> taskList = taskAssignMapper.queryTaskByTime(userCd, taskSt, startTime, endTime);
		logger.info("queryTotalByTime:[usrCd=" + userCd + "],[taskSt=" + taskSt + "],[startTime=" + startTime
				+ "],[endTime=" + endTime + "]");
		return taskList;
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public int queryTotalNumByTime(String userCd, String taskSt, Date startTime, Date endTime) {
		int total = taskAssignMapper.selectTaskCountByTime(userCd, taskSt, startTime, endTime);
		logger.info("queryTotalNumByTime:[usrCd=" + userCd + "],[taskSt=" + taskSt + "],[startTime=" + startTime
				+ "],[endTime=" + endTime + "],[total=" + total + "]");
		return total;
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<TaskAssign> queryTaskByRecord(TaskAssign taskAssign) {
		List<TaskAssign> taskList = taskAssignMapper.selectTaskByRecord(taskAssign);
		logger.info("queryTaskByRecord:[taskAssign=" + taskAssign + "]");
		return taskList;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public TaskAssign getStockTaskByUser(AuditUser user) {
		TaskAssign taskAssign = null;
		StockAuditAssignQueue queue = StockAuditAssignQueue.getInstance();
		AuditTask auditTask = queue.poll(user);
		if (null != auditTask) {
			logger.info("getStockTaskByUser:[usrCd=" + user.getUsrCd() + "],[auditTask=" + auditTask.toString() + "]");
			auditTask.setTaskResult("3");
			auditTask.setRecUpdTs(new Date());
			int result = auditTaskMapper.updateByPrimaryKeySelective(auditTask);
			if (result != 1) {
				logger.error("update auditTask false,[result=" + result + "]");
				return null;
			}
			taskAssign = insertAssign(user, auditTask);
		}
		return taskAssign;
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<RejectReason> queryRejectReason(String taskId) {
		List<RejectReason> reasonList = rejectReasonMapper.selectByTaskId(taskId);
		logger.info("queryRejectReason:[taskId=" + taskId + "]");
		return reasonList;
	}
}
