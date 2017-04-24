package com.unionpay.opdept.mchntaudit.model;

import java.util.List;

import com.unionpay.opdept.mchntaudit.orm.bean.QualityInspect;

public class QueryInspectByPagingRsp {

	private int totalNum;

	private List<QualityInspect> inspectList;

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public List<QualityInspect> getInspectList() {
		return inspectList;
	}

	public void setInspectList(List<QualityInspect> inspectList) {
		this.inspectList = inspectList;
	}

	@Override
	public String toString() {
		StringBuffer builder = new StringBuffer();
		builder.append("QueryInspectByPagingRsp [totalNum=");
		builder.append(totalNum);
		builder.append(", inspectList=");
		builder.append(inspectList);
		builder.append("]");
		return builder.toString();
	}

}
