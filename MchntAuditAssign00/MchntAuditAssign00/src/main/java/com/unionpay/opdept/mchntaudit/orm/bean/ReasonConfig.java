package com.unionpay.opdept.mchntaudit.orm.bean;

import java.util.Date;

public class ReasonConfig extends ReasonConfigKey {
    @Override
	public String toString() {
    	StringBuffer builder = new StringBuffer();
		builder.append("ReasonConfig [recSt=");
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

	private String recSt;

    private String comments;

    private Date recCrtTs;

    private Date recUpdTs;

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