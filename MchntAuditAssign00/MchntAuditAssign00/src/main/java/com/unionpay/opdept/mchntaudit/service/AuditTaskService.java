/**
 * 
 */
package com.unionpay.opdept.mchntaudit.service;

import java.util.List;

import com.unionpay.opdept.mchntaudit.orm.bean.AuditTask;

/**
 * @author DATAN
 *
 */
public interface AuditTaskService {
	
	/**
	 * 获取9800、0000待初级评估任务列表
	 * @param size
	 * @return
	 */
	public List<AuditTask> fetchBranchTask(int size);
	
	/**
	 * 获取待复核评估任务列表
	 * @param size
	 * @return
	 */
	public List<AuditTask> fetchHeadTask(int size);
	
	/**
	 * 设置分派处理任务的用户及计划完成时间
	 * @param auditTask
	 * @return
	 */
	public int setPlanUser(AuditTask auditTask);
	
	/**
	 * 查询剩余任务数量
	 * @param isWhiteMchnt
	 * @return
	 */
	public int queryRestTask(String isWhiteMchnt);
}
