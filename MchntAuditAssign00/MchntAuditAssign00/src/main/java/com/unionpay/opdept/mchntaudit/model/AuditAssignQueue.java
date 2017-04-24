 package com.unionpay.opdept.mchntaudit.model;

import java.util.List;

import com.unionpay.opdept.mchntaudit.orm.bean.AuditTask;
import com.unionpay.opdept.mchntaudit.orm.bean.AuditUser;
import com.unionpay.opdept.mchntaudit.orm.mapper.AuditTaskMapper;
import com.unionpay.opdept.mchntaudit.utils.ProjectConfig;
import com.unionpay.opdept.mchntaudit.utils.SpringContextUtil;

public class AuditAssignQueue {

	private AuditTaskMapper auditTaskMapper;

	// 单例模式
	private AuditAssignQueue() {
		this.auditTaskMapper = (AuditTaskMapper) SpringContextUtil.getBean("auditTaskMapper");
	}

	private static class LazyHolder {
		private static final AuditAssignQueue INSTANCE = new AuditAssignQueue();
	}

	public static final AuditAssignQueue getInstance() {
		return LazyHolder.INSTANCE;
	}

	private static final int SIZE;
	//减免类的队列用户标识
	private static final String RM_QUEUE_TYPE = "A";
	//特殊政府的的队列+重点商户
	private static final String SG_QUEUE_TYPE = "B";
	//特殊其他的队列
	private static final String SO_QUEUE_TYPE = "C";
	//重点机构
	//private static final String SM_QUEUE_TYPE = "B";
	//批量队列
	private static final String BA_QUEUE_TYPE = "Z";

	static {
		SIZE = Integer.valueOf(ProjectConfig.getValue("task.queue.size"));
	}
	
	private TaskQueue smQueue ;

	/**
	 * 减免类—医疗机构\学校\慈善福利结构队列
	 */
	private TaskQueue rmQueue;

	/**
	 * 特殊计费-政府服务类队列
	 */
	private TaskQueue sgQueue;

	/**
	 * 特殊计费—其它队列
	 */
	private TaskQueue soQueue;

	/**
	 * 优惠类队列
	 */
	private TaskQueue disQueue;
	
	/**
	 * 批量现场注册队列
	 */
	private TaskQueue bacQueue;

	/**
	 * 获取任务
	 * 
	 * @param user
	 * @return auditTask
	 */
	public synchronized AuditTask poll(AuditUser user) {
		if (null == user) {
			return null;
		}
		AuditTask auditTask = null;
		//重点机构和政府类的优先
		boolean isSg = user.getFlgBa().equals(SG_QUEUE_TYPE);
		// 特殊计费-政府服务类优先
		//boolean isSg = user.getFlgBa().equals(SG_QUEUE_TYPE);
		// 特殊计费-其它类优先
		boolean isSo = user.getFlgBa().equals(SO_QUEUE_TYPE);
		// 减免类—医疗机构\学校\慈善福利机构优先
		boolean isRm = user.getFlgBa().equals(RM_QUEUE_TYPE);
		//批量的队列
		boolean isBa = user.getFlgBa().equals(BA_QUEUE_TYPE);
		if (isSg){
			auditTask = getSgTask(user, this.smQueue);
		}
		
		if (isSo) {
			auditTask = getSoTask(user, this.soQueue);
		}
		if (isRm) {
			auditTask = getRmTask(user, this.rmQueue);
		}
		if (isBa) {
			auditTask = getBacTask(user, this.bacQueue);
			}
		if (null == auditTask){
			auditTask = getDisTask(user, this.disQueue);
			}
		if(auditTaskMapper.selectDiscountTaskList(SIZE).isEmpty()&&null == auditTask){
			auditTask =getBacTask(user, this.bacQueue);
		}
		
		
		return auditTask;
	}
	//重点商户的队列 +政府类
	private AuditTask getSgTask(AuditUser user, TaskQueue smQueue){
		if(null == smQueue){
			List<AuditTask> blist = auditTaskMapper.selectSpecialGoverTaskList(SIZE);
			smQueue = new TaskQueue(blist);		
		}
		//对列的取不到，用户冲突
		AuditTask auditTask = smQueue.poll(user);
		if (null == auditTask){
			//重新获取数据
			List<AuditTask> blist = auditTaskMapper.selectSpecialGoverTaskList(SIZE);
			smQueue =new TaskQueue(blist);
			auditTask = smQueue.poll(user);
 		}
		this.smQueue =smQueue;
		return auditTask;
		
	}
	

/*	// 特殊计费-政府服务类队列
	private AuditTask getSgTask(AuditUser user, TaskQueue sgQueue) {
		if (null == sgQueue) {
			List<AuditTask> bList = auditTaskMapper.selectSpecialGoverTaskList(SIZE);
			sgQueue = new TaskQueue(bList);
		}
		// 从队列中取，如果取不到，则队列中任务与该用户冲突
		AuditTask auditTask = sgQueue.poll(user);
		if (null == auditTask) {
			// 重新从数据库取队列
			List<AuditTask> bList = auditTaskMapper.selectSpecialGoverTaskList(SIZE);
			sgQueue = new TaskQueue(bList);
			auditTask = sgQueue.poll(user);
		}
		this.sgQueue = sgQueue;
		return auditTask;
	}*/

	// 特殊计费—其它队列
	private AuditTask getSoTask(AuditUser user, TaskQueue soQueue) {
		if (null == soQueue) {
			List<AuditTask> bList = auditTaskMapper.selectSpecialOtherTaskList(SIZE);
			soQueue = new TaskQueue(bList);
		}
		// 从队列中取，如果取不到，则队列中任务与该用户冲突
		AuditTask auditTask = soQueue.poll(user);
		if (null == auditTask) {
			// 重新从数据库取队列
			List<AuditTask> bList = auditTaskMapper.selectSpecialOtherTaskList(SIZE);
			soQueue = new TaskQueue(bList);
			auditTask = soQueue.poll(user);
		}
		this.soQueue = soQueue;
		return auditTask;
	}

	// 减免类—医疗机构\学校\慈善福利结构队列
	private AuditTask getRmTask(AuditUser user, TaskQueue rmQueue) {
		if (null == rmQueue) {
			List<AuditTask> bList = auditTaskMapper.selectReduceTaskList(SIZE);
			rmQueue = new TaskQueue(bList);
		}
		// 从队列中取，如果取不到，则队列中任务与该用户冲突
		AuditTask auditTask = rmQueue.poll(user);
		if (null == auditTask) {
			// 重新从数据库取队列
			List<AuditTask> bList = auditTaskMapper.selectReduceTaskList(SIZE);
			rmQueue = new TaskQueue(bList);
			auditTask = rmQueue.poll(user);
		}
		this.rmQueue = rmQueue;
		return auditTask;
	}

	// 优惠类队列
	private AuditTask getDisTask(AuditUser user, TaskQueue disQueue) {
		if (null == disQueue) {
			List<AuditTask> bList = auditTaskMapper.selectDiscountTaskList(SIZE);
			disQueue = new TaskQueue(bList);
		}
		// 从队列中取，如果取不到，则队列中任务与该用户冲突
		AuditTask auditTask = disQueue.poll(user);
		if (null == auditTask) {
			// 重新从数据库取队列
			List<AuditTask> bList = auditTaskMapper.selectDiscountTaskList(SIZE);
			disQueue = new TaskQueue(bList);
			auditTask = disQueue.poll(user);
		}
		this.disQueue = disQueue;
		return auditTask;
	}
   //批量队列
	private AuditTask getBacTask(AuditUser user , TaskQueue bacQueue){
		if (null == bacQueue) {
			List<AuditTask> bList = auditTaskMapper.selectNewBranchlist(SIZE);
		
			bacQueue = new TaskQueue(bList);
		}
		// 从队列中取，如果取不到，则队列中任务与该用户冲突
		AuditTask auditTask = bacQueue.poll(user);
		if (null == auditTask) {
			// 重新从数据库取队列
			List<AuditTask> bList = auditTaskMapper.selectNewBranchlist(SIZE);
			bacQueue = new TaskQueue(bList);
			auditTask = bacQueue.poll(user);
		}
		this.bacQueue = bacQueue;
		return auditTask;
		
	}
	
}
