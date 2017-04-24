/**
 * 
 */
package com.unionpay.opdept.mchntaudit.model;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

import org.apache.log4j.Logger;

import com.unionpay.opdept.mchntaudit.orm.bean.AuditTask;
import com.unionpay.opdept.mchntaudit.orm.bean.AuditUser;

/**
 * @author DATAN
 *
 */
public class AuditorThread extends Thread {
	private static final Logger logger = Logger.getLogger(com.unionpay.opdept.mchntaudit.model.AuditorThread.class);

	private AuditUser usr;
	private int taskNum;
	private AuditTaskQueue atq;
	private CountDownLatch cdl;
	
	public AuditorThread(AuditUser auditUser, int assignTaskNum, AuditTaskQueue auditTaskQueue, CountDownLatch countDownLatch) {
		if(null == auditUser) {
			throw new NullPointerException("AuditUser can not be null");
		}
		if(null == auditTaskQueue) {
			throw new NullPointerException("AuditTaskQueue can not be null");
		}
		if(null == countDownLatch) {
			throw new NullPointerException("CountDownLatch can not be null");
		}
		
		this.usr = auditUser;
		this.taskNum = assignTaskNum;
		this.atq = auditTaskQueue;
		this.cdl = countDownLatch;
	}
	
	public void run() {
		while(taskNum > 0) {
			AuditTask task = atq.poll(usr);
			if(null == task) {
				break;
			}
			
			logger.debug(usr.getUsrCd()+" get task: " + task.getTaskId());
			try {
				sleep(500+new Random().nextInt(200));
			} catch (InterruptedException e) {
				logger.error(e);
			}
			
			taskNum --;
		}
		
		this.cdl.countDown();
		logger.debug(usr.getUsrCd() + " finished");
	}
}
