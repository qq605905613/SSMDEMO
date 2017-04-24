package com.unionpay.opdept.mchntaudit.orm.bean;

import java.util.Date;

public class AuditTask {
	private String taskId;

	private String taskTp;

	private Long taskPri;

	private Date taskCommitTs;

	private String taskCommitUsr;

	private String taskSt;

	private String taskResult;

	private String taskPlanUsr;

	private String taskExecUsr;

	private Date taskAssignTs;

	private Date taskPlanDoneTs;

	private Date taskExecDoneTs;

	private String mchntSrvTp;

	private String mchntCd;

	private String mchntCnNm;

	private String acqInsIdCd;

	private String acptInsIdCd;

	private String cupBranchInsIdCd;

	private String connMd;

	private String mchntTp;

	private String specDiscTp;

	private String specDiscLvl;

	private String isWhiteMchnt;

	private Date acqCommitAuditTs;

	private String auditSt;

	private String acqPri;

	private String auditTpPri;

	private String commissionTp;

	private String avoidUsr;

	private String mchntAuditId;

	private Date recCrtTs;

	private Date recUpdTs;

	private Date exportTs;

	public String getCommissionTp() {
		return commissionTp;
	}

	public void setCommissionTp(String commissionTp) {
		this.commissionTp = commissionTp;
	}

	public Date getExportTs() {
		return exportTs;
	}

	public void setExportTs(Date exportTs) {
		this.exportTs = exportTs;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId == null ? null : taskId.trim();
	}

	public String getTaskTp() {
		return taskTp;
	}

	public void setTaskTp(String taskTp) {
		this.taskTp = taskTp == null ? null : taskTp.trim();
	}

	public Long getTaskPri() {
		return taskPri;
	}

	public void setTaskPri(Long taskPri) {
		this.taskPri = taskPri;
	}

	public Date getTaskCommitTs() {
		return taskCommitTs;
	}

	public void setTaskCommitTs(Date taskCommitTs) {
		this.taskCommitTs = taskCommitTs;
	}

	public String getTaskCommitUsr() {
		return taskCommitUsr;
	}

	public void setTaskCommitUsr(String taskCommitUsr) {
		this.taskCommitUsr = taskCommitUsr == null ? null : taskCommitUsr.trim();
	}

	public String getTaskSt() {
		return taskSt;
	}

	public void setTaskSt(String taskSt) {
		this.taskSt = taskSt == null ? null : taskSt.trim();
	}

	@Override
	public String toString() {
		StringBuffer builder = new StringBuffer();
		builder.append("AuditTask [taskId=");
		builder.append(taskId);
		builder.append(", taskTp=");
		builder.append(taskTp);
		builder.append(", taskPri=");
		builder.append(taskPri);
		builder.append(", taskCommitTs=");
		builder.append(taskCommitTs);
		builder.append(", taskCommitUsr=");
		builder.append(taskCommitUsr);
		builder.append(", taskSt=");
		builder.append(taskSt);
		builder.append(", taskResult=");
		builder.append(taskResult);
		builder.append(", taskPlanUsr=");
		builder.append(taskPlanUsr);
		builder.append(", taskExecUsr=");
		builder.append(taskExecUsr);
		builder.append(", taskAssignTs=");
		builder.append(taskAssignTs);
		builder.append(", taskPlanDoneTs=");
		builder.append(taskPlanDoneTs);
		builder.append(", taskExecDoneTs=");
		builder.append(taskExecDoneTs);
		builder.append(", mchntSrvTp=");
		builder.append(mchntSrvTp);
		builder.append(", mchntCd=");
		builder.append(mchntCd);
		builder.append(", mchntCnNm=");
		builder.append(mchntCnNm);
		builder.append(", acqInsIdCd=");
		builder.append(acqInsIdCd);
		builder.append(", acptInsIdCd=");
		builder.append(acptInsIdCd);
		builder.append(", cupBranchInsIdCd=");
		builder.append(cupBranchInsIdCd);
		builder.append(", connMd=");
		builder.append(connMd);
		builder.append(", mchntTp=");
		builder.append(mchntTp);
		builder.append(", specDiscTp=");
		builder.append(specDiscTp);
		builder.append(", specDiscLvl=");
		builder.append(specDiscLvl);
		builder.append(", isWhiteMchnt=");
		builder.append(isWhiteMchnt);
		builder.append(", acqCommitAuditTs=");
		builder.append(acqCommitAuditTs);
		builder.append(", auditSt=");
		builder.append(auditSt);
		builder.append(", acqPri=");
		builder.append(acqPri);
		builder.append(", auditTpPri=");
		builder.append(auditTpPri);
		builder.append(", commissionTp=");
		builder.append(commissionTp);
		builder.append(", avoidUsr=");
		builder.append(avoidUsr);
		builder.append(", mchntAuditId=");
		builder.append(mchntAuditId);
		builder.append(", recCrtTs=");
		builder.append(recCrtTs);
		builder.append(", recUpdTs=");
		builder.append(recUpdTs);
		builder.append(", exportTs=");
		builder.append(exportTs);
		builder.append("]");
		return builder.toString();
	}

	public String getTaskResult() {
		return taskResult;
	}

	public void setTaskResult(String taskResult) {
		this.taskResult = taskResult == null ? null : taskResult.trim();
	}

	public String getTaskPlanUsr() {
		return taskPlanUsr;
	}

	public void setTaskPlanUsr(String taskPlanUsr) {
		this.taskPlanUsr = taskPlanUsr == null ? null : taskPlanUsr.trim();
	}

	public String getTaskExecUsr() {
		return taskExecUsr;
	}

	public void setTaskExecUsr(String taskExecUsr) {
		this.taskExecUsr = taskExecUsr == null ? null : taskExecUsr.trim();
	}

	public Date getTaskAssignTs() {
		return taskAssignTs;
	}

	public void setTaskAssignTs(Date taskAssignTs) {
		this.taskAssignTs = taskAssignTs;
	}

	public Date getTaskPlanDoneTs() {
		return taskPlanDoneTs;
	}

	public void setTaskPlanDoneTs(Date taskPlanDoneTs) {
		this.taskPlanDoneTs = taskPlanDoneTs;
	}

	public Date getTaskExecDoneTs() {
		return taskExecDoneTs;
	}

	public void setTaskExecDoneTs(Date taskExecDoneTs) {
		this.taskExecDoneTs = taskExecDoneTs;
	}

	public String getMchntSrvTp() {
		return mchntSrvTp;
	}

	public void setMchntSrvTp(String mchntSrvTp) {
		this.mchntSrvTp = mchntSrvTp == null ? null : mchntSrvTp.trim();
	}

	public String getMchntCd() {
		return mchntCd;
	}

	public void setMchntCd(String mchntCd) {
		this.mchntCd = mchntCd == null ? null : mchntCd.trim();
	}

	public String getMchntCnNm() {
		return mchntCnNm;
	}

	public void setMchntCnNm(String mchntCnNm) {
		this.mchntCnNm = mchntCnNm == null ? null : mchntCnNm.trim();
	}

	public String getAcqInsIdCd() {
		return acqInsIdCd;
	}

	public void setAcqInsIdCd(String acqInsIdCd) {
		this.acqInsIdCd = acqInsIdCd == null ? null : acqInsIdCd.trim();
	}

	public String getAcptInsIdCd() {
		return acptInsIdCd;
	}

	public void setAcptInsIdCd(String acptInsIdCd) {
		this.acptInsIdCd = acptInsIdCd == null ? null : acptInsIdCd.trim();
	}

	public String getCupBranchInsIdCd() {
		return cupBranchInsIdCd;
	}

	public void setCupBranchInsIdCd(String cupBranchInsIdCd) {
		this.cupBranchInsIdCd = cupBranchInsIdCd == null ? null : cupBranchInsIdCd.trim();
	}

	public String getConnMd() {
		return connMd;
	}

	public void setConnMd(String connMd) {
		this.connMd = connMd == null ? null : connMd.trim();
	}

	public String getMchntTp() {
		return mchntTp;
	}

	public void setMchntTp(String mchntTp) {
		this.mchntTp = mchntTp == null ? null : mchntTp.trim();
	}

	public String getSpecDiscTp() {
		return specDiscTp;
	}

	public void setSpecDiscTp(String specDiscTp) {
		this.specDiscTp = specDiscTp == null ? null : specDiscTp.trim();
	}

	public String getSpecDiscLvl() {
		return specDiscLvl;
	}

	public void setSpecDiscLvl(String specDiscLvl) {
		this.specDiscLvl = specDiscLvl == null ? null : specDiscLvl.trim();
	}

	public String getIsWhiteMchnt() {
		return isWhiteMchnt;
	}

	public void setIsWhiteMchnt(String isWhiteMchnt) {
		this.isWhiteMchnt = isWhiteMchnt == null ? null : isWhiteMchnt.trim();
	}

	public Date getAcqCommitAuditTs() {
		return acqCommitAuditTs;
	}

	public void setAcqCommitAuditTs(Date acqCommitAuditTs) {
		this.acqCommitAuditTs = acqCommitAuditTs;
	}

	public String getAuditSt() {
		return auditSt;
	}

	public void setAuditSt(String auditSt) {
		this.auditSt = auditSt == null ? null : auditSt.trim();
	}

	public String getAcqPri() {
		return acqPri;
	}

	public void setAcqPri(String acqPri) {
		this.acqPri = acqPri == null ? null : acqPri.trim();
	}

	public String getAuditTpPri() {
		return auditTpPri;
	}

	public void setAuditTpPri(String auditTpPri) {
		this.auditTpPri = auditTpPri == null ? null : auditTpPri.trim();
	}

	public String getAvoidUsr() {
		return avoidUsr;
	}

	public void setAvoidUsr(String avoidUsr) {
		this.avoidUsr = avoidUsr == null ? null : avoidUsr.trim();
	}

	public String getMchntAuditId() {
		return mchntAuditId;
	}

	public void setMchntAuditId(String mchntAuditId) {
		this.mchntAuditId = mchntAuditId == null ? null : mchntAuditId.trim();
	}

	public Date getRecCrtTs() {
		return recCrtTs;
	}

	public void setRecCrtTs(Date recCrtTs) {
		this.recCrtTs = recCrtTs;
	}

	public Date getRecUpdTs() {
		return recUpdTs;
	}

	public void setRecUpdTs(Date recUpdTs) {
		this.recUpdTs = recUpdTs;
	}
}