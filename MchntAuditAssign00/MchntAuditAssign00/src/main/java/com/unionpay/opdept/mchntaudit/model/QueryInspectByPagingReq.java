package com.unionpay.opdept.mchntaudit.model;

import java.util.Date;

public class QueryInspectByPagingReq {

	/**
	 * 分页查询开始页数
	 */
	private int startPage;

	/**
	 * 每页查询个数
	 */
	private int pageSize;

	/**
	 * 质检结果
	 */
	private String isRight;

	/**
	 * mcc类型
	 */
	private String mccTp;

	/**
	 * 开始时间
	 */
	private Date startTime;

	/**
	 * 结束时间
	 */
	private Date endTime;

	/**
	 * 质检用户CD
	 */
	private String usrCd;

	/**
	 * 开始个数
	 */
	private int startNum;

	/**
	 * 结束个数
	 */
	private int endNum;

	@Override
	public String toString() {
		StringBuffer builder = new StringBuffer();
		builder.append("QueryInspectByPagingReq [startPage=");
		builder.append(startPage);
		builder.append(", pageSize=");
		builder.append(pageSize);
		builder.append(", isRight=");
		builder.append(isRight);
		builder.append(", mccTp=");
		builder.append(mccTp);
		builder.append(", startTime=");
		builder.append(startTime);
		builder.append(", endTime=");
		builder.append(endTime);
		builder.append(", usrCd=");
		builder.append(usrCd);
		builder.append(", startNum=");
		builder.append(startNum);
		builder.append(", endNum=");
		builder.append(endNum);
		builder.append("]");
		return builder.toString();
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

	public String getUsrCd() {
		return usrCd;
	}

	public void setUsrCd(String usrCd) {
		this.usrCd = usrCd;
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

	public String getIsRight() {
		return isRight;
	}

	public void setIsRight(String isRight) {
		this.isRight = isRight;
	}

	public String getMccTp() {
		return mccTp;
	}

	public void setMccTp(String mccTp) {
		this.mccTp = mccTp;
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

}
