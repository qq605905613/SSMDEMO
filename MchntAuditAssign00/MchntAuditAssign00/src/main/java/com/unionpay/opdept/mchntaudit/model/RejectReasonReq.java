package com.unionpay.opdept.mchntaudit.model;

import java.util.List;

import com.unionpay.opdept.mchntaudit.orm.bean.RejectReason;

public class RejectReasonReq {
	private String taskId;

	private String rjReason;

	private List<RejectReason> reasonList;

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getRjReason() {
		return rjReason;
	}

	@Override
	public String toString() {
		StringBuffer builder = new StringBuffer();
		builder.append("RejectReasonReq [taskId=");
		builder.append(taskId);
		builder.append(", rjReason=");
		builder.append(rjReason);
		builder.append(", reasonList=");
		builder.append(reasonList);
		builder.append("]");
		return builder.toString();
	}

	public void setRjReason(String rjReason) {
		this.rjReason = rjReason;
	}

	public List<RejectReason> getReasonList() {
		return reasonList;
	}

	public void setReasonList(List<RejectReason> reasonList) {
		this.reasonList = reasonList;
	}

}
