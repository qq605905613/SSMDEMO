package com.unionpay.opdept.mchntaudit.model;

import java.util.Date;

public class QueryTaskPagingServiceReq {
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
	 * 审核状态
	 */
	private String auditSt;

	/**
	 * 开始日期
	 */
    private Date startTime;
    
    /**
     * 结束日期
     */
    private Date endTime;
    
    /**
     * 用户CD
     */
    private String usrCd;
    
    /**
     * 开始位数
     */
    private int startNum;
    
    /**
     * 结束位数
     */
    private int endNum;
    
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

	public int getStartNum() {
		return startNum;
	}

	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}

	public int getEndNum() {
		return endNum;
	}

	public void setEndNum(int endNum) {
		this.endNum = endNum;
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

	public String getAuditSt() {
		return auditSt;
	}

	public void setAuditSt(String auditSt) {
		this.auditSt = auditSt;
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

	public String getUsrCd() {
		return usrCd;
	}

	public void setUsrCd(String usrCd) {
		this.usrCd = usrCd;
	}

	@Override
	public String toString() {
		StringBuffer str=new StringBuffer();
		str.append("QueryTaskPagingServiceReq [startPage=");
		str.append(startPage);
		str.append(", pageSize=");
		str.append(pageSize);
		str.append(", taskSt=");
		str.append(taskSt);
		str.append(", auditSt=");
		str.append(auditSt);
		str.append(", startTime=");
		str.append(startTime);
		str.append(", endTime=");
		str.append(endTime);
		str.append(", usrCd=");
		str.append(usrCd);
		str.append(", startNum=");
		str.append(startNum);
		str.append(", endNum=");
		str.append(endNum);
		str.append(",isWhiteMchnt=");
		str.append(isWhiteMchnt);
		str.append("]");
		return str.toString();
	}
	
}
