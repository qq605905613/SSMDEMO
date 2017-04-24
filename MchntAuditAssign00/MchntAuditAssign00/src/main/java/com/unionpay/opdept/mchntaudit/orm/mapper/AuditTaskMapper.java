package com.unionpay.opdept.mchntaudit.orm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.unionpay.opdept.mchntaudit.orm.bean.AuditTask;

public interface AuditTaskMapper {
	int deleteByPrimaryKey(String taskId);

	int insert(AuditTask record);

	int insertSelective(AuditTask record);

	AuditTask selectByPrimaryKey(String taskId);

	int updateByPrimaryKeySelective(AuditTask record);

	int updateByPrimaryKey(AuditTask record);
	//批量现场注册的队列
	List<AuditTask> selectNewBranchlist(int size);

	//分公司四审队列
	List<AuditTask> selectBranchTaskList(int size);
	
	List<AuditTask> selectHeadTaskList(int size);
	
	List<AuditTask> selectSaTaskList(int size);
	
	//政府类和重点机构的队列
	List<AuditTask> selectSpecialGoverTaskList(int size);
	
	//特殊计费—其它队列
	List<AuditTask> selectSpecialOtherTaskList(int size);
	
	//减免类—医疗机构\学校\慈善福利结构队列
	List<AuditTask> selectReduceTaskList(int size);
	
	//优惠类队列
	List<AuditTask> selectDiscountTaskList(int size);
	
	List<AuditTask> selectStockBaTaskList(int size);
	
	List<AuditTask> selectStockHsaTaskList(int size);

	// 查询剩余工单数量
	int selectRestTask(@Param("isWhiteMchnt") String isWhiteMchnt);
	
}