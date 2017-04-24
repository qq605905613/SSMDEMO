package com.unionpay.opdept.mchntaudit.model;

public class QueryTaskByPagingReq {

	/**
	 * 分页查询开始页数
	 */
	private int startPage;

	/**
	 * 每页查询个数
	 */
	private int pageSize;

	/**
	 * 任务结果
	 */
	private String taskSt;

	/**
	 * 是否查询当日任务
	 */
	private String isOrNotToday;

	/**
	 * 审核状态
	 */
	private String auditSt;
	
	/**
	 * 是否申请非标价格 1:是       0:否
	 */
	private String isWhiteMchnt;

	public String getIsWhiteMchnt() {
		return isWhiteMchnt;
	}

	public void setIsWhiteMchnt(String isWhiteMchnt) {
		this.isWhiteMchnt = isWhiteMchnt;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getTaskSt() {
		return taskSt;
	}

	public void setTaskSt(String taskSt) {
		this.taskSt = taskSt;
	}

	public String getIsOrNotToday() {
		return isOrNotToday;
	}

	public void setIsOrNotToday(String isOrNotToday) {
		this.isOrNotToday = isOrNotToday;
	}

	public String getAuditSt() {
		return auditSt;
	}

	public void setAuditSt(String auditSt) {
		this.auditSt = auditSt;
	}

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();
		str.append("QueryTaskByPagingReq [startPage=");
		str.append(startPage);
		str.append(", pageSize=");
		str.append(pageSize);
		str.append(", taskSt=");
		str.append(taskSt);
		str.append(", isOrNotToday=");
		str.append(isOrNotToday);
		str.append(", auditSt=");
		str.append(auditSt);
		str.append(",isWhiteMchnt=");
		str.append(isWhiteMchnt);
		str.append("]");
		return str.toString();
	}

}
