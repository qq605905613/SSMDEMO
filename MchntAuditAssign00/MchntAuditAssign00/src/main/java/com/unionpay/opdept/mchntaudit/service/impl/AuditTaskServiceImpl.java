/**
 * 
 */
package com.unionpay.opdept.mchntaudit.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.unionpay.opdept.mchntaudit.orm.bean.AuditTask;
import com.unionpay.opdept.mchntaudit.orm.mapper.AuditTaskMapper;
import com.unionpay.opdept.mchntaudit.service.AuditTaskService;

/**
 * @author DATAN
 *
 */
@Service("auditTaskService")
public class AuditTaskServiceImpl implements AuditTaskService {
	
	@Resource
	private AuditTaskMapper auditTaskMapper;

	/* (non-Javadoc)
	 * @see com.unionpay.opdept.mchntaudit.service.AuditTaskService#fetchAuditTask(int)
	 */
	@Override
	public List<AuditTask> fetchBranchTask(int size) {
		if(size < 1) {
			return null;
		}
		
		return this.auditTaskMapper.selectBranchTaskList(size);
	}

	/* (non-Javadoc)
	 * @see com.unionpay.opdept.mchntaudit.service.AuditTaskService#setPlanUser(com.unionpay.opdept.mchntaudit.orm.bean.AuditTask)
	 */
	@Override
	public int setPlanUser(AuditTask auditTask) {
		if(null == auditTask) {
			return 0;
		}
		AuditTask task = new AuditTask();
		task.setTaskId(auditTask.getTaskId());
		task.setTaskPlanUsr(auditTask.getTaskPlanUsr());
		task.setTaskPlanDoneTs(new Date());
		
		this.auditTaskMapper.updateByPrimaryKeySelective(task);
		return 0;
	}

	@Override
	public List<AuditTask> fetchHeadTask(int size) {
		if (size < 1) {
			return null;
		}
		
		return this.auditTaskMapper.selectHeadTaskList(size);
	}
	
	@Override
	public int queryRestTask(String isWhiteMchnt) {
		int total=this.auditTaskMapper.selectRestTask(isWhiteMchnt);
		return total;
	}

}
