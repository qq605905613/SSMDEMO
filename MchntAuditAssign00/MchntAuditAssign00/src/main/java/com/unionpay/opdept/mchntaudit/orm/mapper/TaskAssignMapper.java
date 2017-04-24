package com.unionpay.opdept.mchntaudit.orm.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.unionpay.opdept.mchntaudit.model.QualityInspectTaskReq;
import com.unionpay.opdept.mchntaudit.model.QueryTaskPagingServiceReq;
import com.unionpay.opdept.mchntaudit.orm.bean.TaskAssign;

public interface TaskAssignMapper {
	int deleteByPrimaryKey(String taskId);

	int insert(TaskAssign record);

	int insertSelective(TaskAssign record);

	TaskAssign selectByPrimaryKey(String taskId);

	int updateByPrimaryKeySelective(TaskAssign record);

	int updateByPrimaryKey(TaskAssign record);

	// 根据userCd查询第一个任务
	TaskAssign selectTaskByUser(@Param("isWhiteMchnt") String isWhiteMchnt, @Param("usrCd") String usrCd);

	int updateByPrimaryKeyAndUserSelective(TaskAssign record);

	// 分页查询
	List<TaskAssign> selectTaskByPaging(QueryTaskPagingServiceReq req);

	// 查询记录条数
	int selectTotalTaskCount(QueryTaskPagingServiceReq req);

	// 根据时间查询记录条数
	int selectTaskCountByTime(@Param("userCd") String userCd, @Param("taskSt") String taskSt,
			@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	// 根据时间查询记录详情
	List<TaskAssign> queryTaskByTime(@Param("userCd") String userCd, @Param("taskSt") String taskSt,
			@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	// 必填值：assign_usr_cd
	List<TaskAssign> selectTaskByRecord(TaskAssign record);
	
	//抽取质检任务
	TaskAssign selectInspectTask(QualityInspectTaskReq req);
	
	//修改COMMENTS为空的任务
	int updateByCommentsSelective(TaskAssign record);
}