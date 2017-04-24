package com.unionpay.opdept.mchntaudit.orm.bean;

import java.util.Date;

public class ReasonType {
    @Override
	public String toString() {
    	StringBuffer builder = new StringBuffer();
		builder.append("ReasonType [reasonTp=");
		builder.append(reasonTp);
		builder.append(", reasonMemo=");
		builder.append(reasonMemo);
		builder.append(", recSt=");
		builder.append(recSt);
		builder.append(", isCommon=");
		builder.append(isCommon);
		builder.append(", comments=");
		builder.append(comments);
		builder.append(", recCrtTs=");
		builder.append(recCrtTs);
		builder.append(", recUpdTs=");
		builder.append(recUpdTs);
		builder.append("]");
		return builder.toString();
	}

	private String reasonTp;

    private String reasonMemo;

    private String recSt;

    private String isCommon;

    private String comments;

    private Date recCrtTs;

    private Date recUpdTs;

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

    public String getIsCommon() {
        return isCommon;
    }

    public void setIsCommon(String isCommon) {
        this.isCommon = isCommon == null ? null : isCommon.trim();
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