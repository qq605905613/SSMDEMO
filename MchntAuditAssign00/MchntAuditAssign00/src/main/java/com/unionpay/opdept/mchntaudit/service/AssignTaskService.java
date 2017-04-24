/**
 * 
 */
package com.unionpay.opdept.mchntaudit.service;

import java.util.Date;
import java.util.List;

import com.unionpay.opdept.mchntaudit.model.QueryTaskPagingServiceReq;
import com.unionpay.opdept.mchntaudit.orm.bean.AuditUser;
import com.unionpay.opdept.mchntaudit.orm.bean.RejectReason;
import com.unionpay.opdept.mchntaudit.orm.bean.TaskAssign;

/**
 * @author DATAN
 *
 */
public interface AssignTaskService {

	/**
	 * 实时获取非标任务
	 * 
	 * @param user
	 *            用户
	 */
	public TaskAssign getTaskByUser(AuditUser user);

	/**
	 * 实时获取存量任务
	 * 
	 * @param user
	 *            用户
	 */
	public TaskAssign getStockTaskByUser(AuditUser user);

	/**
	 * 查询审核中任务
	 * 
	 * @param isWhiteMchnt
	 * @param userCd
	 * @return
	 */
	public TaskAssign getPreviousTask(String isWhiteMchnt, String userCd);

	/**
	 * 审核通过
	 * 
	 * @param rating
	 *            评分
	 * @param userCd
	 *            用户名
	 * @param taskId
	 *            任务ID
	 * @return int result
	 */
	public int passTask(int rating, String userCd, String taskId);

	/**
	 * 审核拒绝
	 * 
	 * @param reasonList
	 *            拒绝原因列表
	 * @param userCd
	 *            用户名
	 * @param taskId
	 *            任务ID
	 * @return
	 */
	public int rejectTask(List<RejectReason> reasonList, String userCd, String taskId);

	/**
	 * 挂起
	 * 
	 * @param pdReason
	 *            挂起原因
	 * @param userCd
	 *            用户名
	 * @param taskId
	 *            任务ID
	 * @return int result
	 */
	public int pendingTask(String pdReason, String userCd, String taskId);

	/**
	 * 分页查询历史记录
	 * 
	 * @param pageSize
	 * @param startPage
	 * @param userCd
	 * @param taskSt
	 * @return list<TaskAssign>
	 */
	public List<TaskAssign> queryTaskByPaging(QueryTaskPagingServiceReq req);

	/**
	 * 查询历史记录总条数
	 * 
	 * @param userCd
	 * @param taskSt
	 * @return total
	 */
	public int queryTotalTaskCount(QueryTaskPagingServiceReq req);

	/**
	 * 根据时间查询记录详情
	 * 
	 * @param userCd
	 * @param taskSt
	 * @param startTime
	 * @param endTime
	 * @return total
	 */
	public List<TaskAssign> queryTotalByTime(String userCd, String taskSt, Date startTime, Date endTime);

	/**
	 * 根据时间查询记录总条数
	 * 
	 * @param userCd
	 * @param taskSt
	 * @param startTime
	 * @param endTime
	 * @return total
	 */
	public int queryTotalNumByTime(String userCd, String taskSt, Date startTime, Date endTime);

	/**
	 * 根据taskAssign条件查询
	 * 
	 * @param taskAssign
	 * @return
	 */
	public List<TaskAssign> queryTaskByRecord(TaskAssign taskAssign);

	/**
	 * 根据taskId查询拒绝原因
	 * 
	 * @param taskId
	 * @return
	 */
	public List<RejectReason> queryRejectReason(String taskId);
}
