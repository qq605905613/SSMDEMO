/**
 * 
 */
package com.unionpay.opdept.mchntaudit.model;

import com.unionpay.opdept.mchntaudit.orm.bean.AuditTask;
import com.unionpay.opdept.mchntaudit.orm.bean.AuditUser;

/**
 * @author DATAN
 *
 */
public class AuditTaskQueue {
	private TaskQueue batQueue;
	private TaskQueue hatQueue;
	
	public AuditTaskQueue(TaskQueue batQueue, TaskQueue hatQueue) {
		if(null == batQueue) {
			throw new NullPointerException("batQueue can not be null");
		}
		if(null == hatQueue) {
			throw new NullPointerException("hatQueue can not be null");
		}
		
		this.batQueue = batQueue;
		this.hatQueue = hatQueue;
	}
	
	public synchronized AuditTask poll(AuditUser auditUser){
		if(null == auditUser) {
			return null;
		}
		AuditTask ret = null;
		
		boolean isBAU = "1".equals(auditUser.getFlgBa());
		
		if(isBAU) {
			ret = batQueue.poll(auditUser);
		}
		if(null == ret) {
			ret = hatQueue.poll(auditUser);
		}
		
		return ret;
	}
}
