package com.unionpay.opdept.mchntaudit.orm.bean;

import java.util.Date;

public class BranchContact {
    @Override
	public String toString() {
    	StringBuffer builder = new StringBuffer();
		builder.append("BranchContact [contactId=");
		builder.append(contactId);
		builder.append(", usedFor=");
		builder.append(usedFor);
		builder.append(", branchCd=");
		builder.append(branchCd);
		builder.append(", email=");
		builder.append(email);
		builder.append(", name=");
		builder.append(name);
		builder.append(", telNo=");
		builder.append(telNo);
		builder.append(", phoneNo=");
		builder.append(phoneNo);
		builder.append(", sendTp=");
		builder.append(sendTp);
		builder.append(", priSeq=");
		builder.append(priSeq);
		builder.append(", recCrtTs=");
		builder.append(recCrtTs);
		builder.append(", recUpdTs=");
		builder.append(recUpdTs);
		builder.append("]");
		return builder.toString();
	}

	private Integer contactId;

    private String usedFor;

    private String branchCd;

    private String email;

    private String name;

    private String telNo;

    private String phoneNo;

    private String sendTp;

    private Integer priSeq;

    private Date recCrtTs;

    private Date recUpdTs;

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public String getUsedFor() {
        return usedFor;
    }

    public void setUsedFor(String usedFor) {
        this.usedFor = usedFor == null ? null : usedFor.trim();
    }

    public String getBranchCd() {
        return branchCd;
    }

    public void setBranchCd(String branchCd) {
        this.branchCd = branchCd == null ? null : branchCd.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo == null ? null : telNo.trim();
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo == null ? null : phoneNo.trim();
    }

    public String getSendTp() {
        return sendTp;
    }

    public void setSendTp(String sendTp) {
        this.sendTp = sendTp == null ? null : sendTp.trim();
    }

    public Integer getPriSeq() {
        return priSeq;
    }

    public void setPriSeq(Integer priSeq) {
        this.priSeq = priSeq;
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