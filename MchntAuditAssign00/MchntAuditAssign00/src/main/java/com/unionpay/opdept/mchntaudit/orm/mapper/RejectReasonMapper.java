package com.unionpay.opdept.mchntaudit.orm.mapper;

import java.util.List;

import com.unionpay.opdept.mchntaudit.orm.bean.RejectReason;

public interface RejectReasonMapper {
	int deleteByPrimaryKey(String reasonId);

	int insert(RejectReason record);

	int insertSelective(RejectReason record);

	RejectReason selectByPrimaryKey(String reasonId);

	int updateByPrimaryKeySelective(RejectReason record);

	int updateByPrimaryKey(RejectReason record);

	/**
	 * 批量插入
	 * 
	 * @param reasonList
	 * @return
	 */
	int insertByList(List<RejectReason> reasonList);

	/**
	 * 根据TaskID查询数据
	 * 
	 * @param taskId
	 * @return
	 */
	List<RejectReason> selectByTaskId(String taskId);
}