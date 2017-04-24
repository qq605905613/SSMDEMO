package com.unionpay.opdept.mchntaudit.orm.bean;

import java.util.Date;

public class TaskAssign {
    @Override
	public String toString() {
    	StringBuffer builder = new StringBuffer();
		builder.append("TaskAssign [taskId=");
		builder.append(taskId);
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
		builder.append(", mchntAuditId=");
		builder.append(mchntAuditId);
		builder.append(", assignTs=");
		builder.append(assignTs);
		builder.append(", assignUsrCd=");
		builder.append(assignUsrCd);
		builder.append(", assignGroupId=");
		builder.append(assignGroupId);
		builder.append(", planDoneTs=");
		builder.append(planDoneTs);
		builder.append(", actualDoneTs=");
		builder.append(actualDoneTs);
		builder.append(", taskResult=");
		builder.append(taskResult);
		builder.append(", mchntRating=");
		builder.append(mchntRating);
		builder.append(", pendingMemo=");
		builder.append(pendingMemo);
		builder.append(", rejectMemo=");
		builder.append(rejectMemo);
		builder.append(", recSt=");
		builder.append(recSt);
		builder.append(", comments=");
		builder.append(comments);
		builder.append(", recCrtTs=");
		builder.append(recCrtTs);
		builder.append(", recUpdTs=");
		builder.append(recUpdTs);
		builder.append("]");
		return builder.toString();
	}

	private String taskId;

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

    private String mchntAuditId;

    private Date assignTs;

    private String assignUsrCd;

    private String assignGroupId;

    private Date planDoneTs;

    private Date actualDoneTs;

    private String taskResult;

    private Integer mchntRating;

    private String pendingMemo;

    private String rejectMemo;

    private String recSt;

    private String comments;

    private Date recCrtTs;

    private Date recUpdTs;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId == null ? null : taskId.trim();
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

    public String getMchntAuditId() {
        return mchntAuditId;
    }

    public void setMchntAuditId(String mchntAuditId) {
        this.mchntAuditId = mchntAuditId == null ? null : mchntAuditId.trim();
    }

    public Date getAssignTs() {
        return assignTs;
    }

    public void setAssignTs(Date assignTs) {
        this.assignTs = assignTs;
    }

    public String getAssignUsrCd() {
        return assignUsrCd;
    }

    public void setAssignUsrCd(String assignUsrCd) {
        this.assignUsrCd = assignUsrCd == null ? null : assignUsrCd.trim();
    }

    public String getAssignGroupId() {
        return assignGroupId;
    }

    public void setAssignGroupId(String assignGroupId) {
        this.assignGroupId = assignGroupId == null ? null : assignGroupId.trim();
    }

    public Date getPlanDoneTs() {
        return planDoneTs;
    }

    public void setPlanDoneTs(Date planDoneTs) {
        this.planDoneTs = planDoneTs;
    }

    public Date getActualDoneTs() {
        return actualDoneTs;
    }

    public void setActualDoneTs(Date actualDoneTs) {
        this.actualDoneTs = actualDoneTs;
    }

    public String getTaskResult() {
        return taskResult;
    }

    public void setTaskResult(String taskResult) {
        this.taskResult = taskResult == null ? null : taskResult.trim();
    }

    public Integer getMchntRating() {
        return mchntRating;
    }

    public void setMchntRating(Integer mchntRating) {
        this.mchntRating = mchntRating;
    }

    public String getPendingMemo() {
        return pendingMemo;
    }

    public void setPendingMemo(String pendingMemo) {
        this.pendingMemo = pendingMemo == null ? null : pendingMemo.trim();
    }

    public String getRejectMemo() {
        return rejectMemo;
    }

    public void setRejectMemo(String rejectMemo) {
        this.rejectMemo = rejectMemo == null ? null : rejectMemo.trim();
    }

    public String getRecSt() {
        return recSt;
    }

    public void setRecSt(String recSt) {
        this.recSt = recSt == null ? null : recSt.trim();
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments == null ? null : comments.trim();
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