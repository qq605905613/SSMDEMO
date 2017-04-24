package com.unionpay.opdept.mchntaudit.orm.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.unionpay.opdept.mchntaudit.model.QueryInspectByPagingReq;
import com.unionpay.opdept.mchntaudit.orm.bean.QualityInspect;

public interface QualityInspectMapper {
	int deleteByPrimaryKey(String taskId);

	int insert(QualityInspect record);

	int insertSelective(QualityInspect record);

	QualityInspect selectByPrimaryKey(String taskId);

	int updateByPrimaryKeySelective(QualityInspect record);

	int updateByPrimaryKey(QualityInspect record);

	QualityInspect selectByInspectUsrCd(String usrCd);

	int updateByTaskId(@Param("taskId") String taskId, @Param("isRight") String isRight, @Param("memo") String memo,
			@Param("date") Date date);

	//分页查询质检历史记录
	List<QualityInspect> queryInspectByPaging(QueryInspectByPagingReq req);

	//分页查询总数
	int queryInspectCountByPaging(QueryInspectByPagingReq req);
}