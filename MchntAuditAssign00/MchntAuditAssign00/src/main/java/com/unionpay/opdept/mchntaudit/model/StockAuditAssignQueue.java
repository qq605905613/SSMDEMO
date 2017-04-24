package com.unionpay.opdept.mchntaudit.model;

import java.util.List;

import com.unionpay.opdept.mchntaudit.orm.bean.AuditTask;
import com.unionpay.opdept.mchntaudit.orm.bean.AuditUser;
import com.unionpay.opdept.mchntaudit.orm.mapper.AuditTaskMapper;
import com.unionpay.opdept.mchntaudit.utils.ProjectConfig;
import com.unionpay.opdept.mchntaudit.utils.SpringContextUtil;

public class StockAuditAssignQueue {

	private AuditTaskMapper auditTaskMapper;

	public final static StockAuditAssignQueue getInstance() {
		return LazyHolder.INSTANCE;
	}

	private static class LazyHolder {
		private static final StockAuditAssignQueue INSTANCE = new StockAuditAssignQueue();
	}

	private StockAuditAssignQueue() {
		this.auditTaskMapper = (AuditTaskMapper) SpringContextUtil.getBean("auditTaskMapper");
	}

	private static final int SIZE;

	static {
		SIZE = Integer.valueOf(ProjectConfig.getValue("task.queue.size"));
	}

	// 分公司四审队列
	private TaskStockQueue baQueue;

	// 总公司一、二审队列
	private TaskStockQueue hsaQueue;

	public synchronized AuditTask poll(AuditUser user) {
		if (null != user && null != user.getUsrCd() && null != user.getFlg1st()) {
			AuditTask auditTask = null;
			boolean isBau = "1".equals(user.getFlg1st());
			if (isBau) {
				auditTask = getBaTask(user, this.baQueue);
			}
			if (null == auditTask) {
				auditTask = getHsaTask(user, this.hsaQueue);
			}
			return auditTask;
		}
		return null;

	}

	private AuditTask getBaTask(AuditUser user, TaskStockQueue baQueue) {
		if (null == baQueue) {
			List<AuditTask> taskList = auditTaskMapper.selectStockBaTaskList(SIZE);
			baQueue = new TaskStockQueue(taskList);
		}
		AuditTask auditTask = baQueue.poll(user);
		if (null == auditTask) {
			List<AuditTask> taskList = auditTaskMapper.selectStockBaTaskList(SIZE);
			baQueue = new TaskStockQueue(taskList);
			auditTask = baQueue.poll(user);
		}
		this.baQueue = baQueue;
		return auditTask;

	}

	private AuditTask getHsaTask(AuditUser user, TaskStockQueue hsaQueue) {
		if (null == hsaQueue) {
			List<AuditTask> taskList = auditTaskMapper.selectStockHsaTaskList(SIZE);
			hsaQueue = new TaskStockQueue(taskList);
		}
		AuditTask auditTask = hsaQueue.poll(user);
		if (null == auditTask) {
			List<AuditTask> taskList = auditTaskMapper.selectStockHsaTaskList(SIZE);
			hsaQueue = new TaskStockQueue(taskList);
			auditTask = hsaQueue.poll(user);
		}
		this.hsaQueue = hsaQueue;
		return auditTask;
	}
}
