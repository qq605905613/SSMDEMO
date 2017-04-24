package com.unionpay.opdept.mchntaudit.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.unionpay.opdept.mchntaudit.orm.bean.AuditTask;
import com.unionpay.opdept.mchntaudit.orm.mapper.AuditTaskMapper;

public class PreviousTaskTimer extends TimerTask {

	public PreviousTaskTimer(String taskId, String userCd) {
		super();
		this.taskId = taskId;
		this.userCd = userCd;
		this.auditTaskMapper = (AuditTaskMapper) SpringContextUtil.getBean("auditTaskMapper");
	}

	private static final Logger logger = Logger.getLogger(PreviousTaskTimer.class);

	private AuditTaskMapper auditTaskMapper;

	private String taskId;

	private String userCd;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		AuditTask auditTask = auditTaskMapper.selectByPrimaryKey(taskId);
		// 如果待审核状态为分公司四审或总公司一审，则将后续派单提前派出
		if (null != auditTask && null != auditTask.getAuditSt()
				&& (auditTask.getAuditSt().equals("4") || auditTask.getAuditSt().equals("F"))) {
			auditTask.setAvoidUsr(userCd);
			auditTask.setTaskResult("1");
			auditTask.setRecCrtTs(new Date());
			auditTask.setRecUpdTs(new Date());
			if (auditTask.getAuditSt().equals("4")) {
				auditTask.setTaskId(
						new SimpleDateFormat("YYYYMMddHHmmss").format(new Date()) + auditTask.getMchntCd() + "F");
				auditTask.setAuditSt("F");
				auditTask.setTaskTp("HA");
			} else if (auditTask.getAuditSt().equals("F")) {
				auditTask.setTaskId(
						new SimpleDateFormat("YYYYMMddHHmmss").format(new Date()) + auditTask.getMchntCd() + "G");
				auditTask.setAuditSt("G");
			}
			int rsp = auditTaskMapper.insertSelective(auditTask);
			logger.info(
					"insertPrevious:[usrCd=" + userCd + "],[auditTask=" + auditTask.toString() + "],[rsp=" + rsp + "]");
		}
	}
}
