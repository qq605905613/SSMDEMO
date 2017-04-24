package com.unionpay.opdept.mchntaudit.orm.bean;

public class ReasonConfigKey {
    @Override
	public String toString() {
    	StringBuffer builder = new StringBuffer();
		builder.append("ReasonConfigKey [reasonTp=");
		builder.append(reasonTp);
		builder.append(", mchntTp=");
		builder.append(mchntTp);
		builder.append(", specDiscTp=");
		builder.append(specDiscTp);
		builder.append(", specDiscLvl=");
		builder.append(specDiscLvl);
		builder.append("]");
		return builder.toString();
	}

	private String reasonTp;

    private String mchntTp;

    private String specDiscTp;

    private String specDiscLvl;

    public String getReasonTp() {
        return reasonTp;
    }

    public void setReasonTp(String reasonTp) {
        this.reasonTp = reasonTp == null ? null : reasonTp.trim();
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
}