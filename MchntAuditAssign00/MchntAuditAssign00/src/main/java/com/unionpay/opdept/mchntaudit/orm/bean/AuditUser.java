package com.unionpay.opdept.mchntaudit.orm.bean;

import java.util.Date;

public class AuditUser {
    @Override
	public String toString() {
    	StringBuffer builder = new StringBuffer();
		builder.append("AuditUser [usrId=");
		builder.append(usrId);
		builder.append(", usrCd=");
		builder.append(usrCd);
		builder.append(", usrNm=");
		builder.append(usrNm);
		builder.append(", loginPwd=");
		builder.append(loginPwd);
		builder.append(", groupId=");
		builder.append(groupId);
		builder.append(", flgBa=");
		builder.append(flgBa);
		builder.append(", flg1st=");
		builder.append(flg1st);
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

	private Integer usrId;

    private String usrCd;

    private String usrNm;

    private String loginPwd;

    private String groupId;

    private String flgBa;

    private String flg1st;

    private String recSt;

    private String comments;

    private Date recCrtTs;

    private Date recUpdTs;

    public Integer getUsrId() {
        return usrId;
    }

    public void setUsrId(Integer usrId) {
        this.usrId = usrId;
    }

    public String getUsrCd() {
        return usrCd;
    }

    public void setUsrCd(String usrCd) {
        this.usrCd = usrCd == null ? null : usrCd.trim();
    }

    public String getUsrNm() {
        return usrNm;
    }

    public void setUsrNm(String usrNm) {
        this.usrNm = usrNm == null ? null : usrNm.trim();
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd == null ? null : loginPwd.trim();
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
    }

    public String getFlgBa() {
        return flgBa;
    }

    public void setFlgBa(String flgBa) {
        this.flgBa = flgBa == null ? null : flgBa.trim();
    }

    public String getFlg1st() {
        return flg1st;
    }

    public void setFlg1st(String flg1st) {
        this.flg1st = flg1st == null ? null : flg1st.trim();
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