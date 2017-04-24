package com.unionpay.opdept.mchntaudit.model;

import java.util.Date;

public class QualityInspectTaskReq {

	/**
	 * 开始时间
	 */
	private Date startTime;

	/**
	 * 结束时间
	 */
	private Date endTime;

	/**
	 * 审核人员姓名
	 */
	private String assignUsrNm;

	/**
	 * 小组名
	 */
	private String groupId;

	/**
	 * 审核结果
	 */
	private String taskResult;

	/**
	 * 商户评分
	 */
	private String mchntRating;

	/**
	 * MCC类别
	 */
	private String mccTp;

	/**
	 * 所属分公司代码
	 */
	private String cupBranchIdCd;

	public String getCupBranchIdCd() {
		return cupBranchIdCd;
	}

	public void setCupBranchIdCd(String cupBranchIdCd) {
		this.cupBranchIdCd = cupBranchIdCd;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getAssignUsrNm() {
		return assignUsrNm;
	}

	public void setAssignUsrNm(String assignUsrNm) {
		this.assignUsrNm = assignUsrNm;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getTaskResult() {
		return taskResult;
	}

	public void setTaskResult(String taskResult) {
		this.taskResult = taskResult;
	}

	public String getMchntRating() {
		return mchntRating;
	}

	public void setMchntRating(String mchntRating) {
		this.mchntRating = mchntRating;
	}

	public String getMccTp() {
		return mccTp;
	}

	public void setMccTp(String mccTp) {
		this.mccTp = mccTp;
	}

	@Override
	public String toString() {
		StringBuffer builder = new StringBuffer();
		builder.append("QualityInspectTaskReq [startTime=");
		builder.append(startTime);
		builder.append(", endTime=");
		builder.append(endTime);
		builder.append(", assignUsrNm=");
		builder.append(assignUsrNm);
		builder.append(", groupId=");
		builder.append(groupId);
		builder.append(", taskResult=");
		builder.append(taskResult);
		builder.append(", mchntRating=");
		builder.append(mchntRating);
		builder.append(", mccTp=");
		builder.append(mccTp);
		builder.append(", cupBranchIdCd=");
		builder.append(cupBranchIdCd);
		builder.append("]");
		return builder.toString();
	}

}
