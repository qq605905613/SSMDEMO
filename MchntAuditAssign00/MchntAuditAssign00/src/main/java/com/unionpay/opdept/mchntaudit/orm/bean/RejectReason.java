package com.unionpay.opdept.mchntaudit.orm.bean;

import java.util.Date;

public class RejectReason {
    private String reasonId;

    private String taskId;

    private String reasonTp;

    private String reasonMemo;

    private String recSt;

    private String comments;

    private Date recCrtTs;

    private Date recUpdTs;

    public String getReasonId() {
        return reasonId;
    }

    public void setReasonId(String reasonId) {
        this.reasonId = reasonId == null ? null : reasonId.trim();
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId == null ? null : taskId.trim();
    }

    public String getReasonTp() {
        return reasonTp;
    }

    public void setReasonTp(String reasonTp) {
        this.reasonTp = reasonTp == null ? null : reasonTp.trim();
    }

    public String getReasonMemo() {
        return reasonMemo;
    }

    public void setReasonMemo(String reasonMemo) {
        this.reasonMemo = reasonMemo == null ? null : reasonMemo.trim();
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

    @Override
	public String toString() {
		StringBuffer builder = new StringBuffer();
		builder.append("RejectReason [reasonId=");
		builder.append(reasonId);
		builder.append(", taskId=");
		builder.append(taskId);
		builder.append(", reasonTp=");
		builder.append(reasonTp);
		builder.append(", reasonMemo=");
		builder.append(reasonMemo);
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