package com.unionpay.opdept.mchntaudit.service;

import com.unionpay.opdept.mchntaudit.model.QualityInspectTaskReq;
import com.unionpay.opdept.mchntaudit.model.QueryInspectByPagingReq;
import com.unionpay.opdept.mchntaudit.model.QueryInspectByPagingRsp;
import com.unionpay.opdept.mchntaudit.orm.bean.AuditUser;
import com.unionpay.opdept.mchntaudit.orm.bean.QualityInspect;

public interface QualityInspectService {

	/**
	 * 获取质检任务
	 */
	public QualityInspect getInspectTask(QualityInspectTaskReq qualityInspectTaskReq, AuditUser user);

	/**
	 * 处理质检任务
	 */
	public int processInspectTask(String taskId, String isRight, String memo);

	/**
	 * 分页查询质检历史任务
	 * 
	 * @param req
	 * @return
	 */
	public QueryInspectByPagingRsp queryInspectByPaging(QueryInspectByPagingReq req);
}
