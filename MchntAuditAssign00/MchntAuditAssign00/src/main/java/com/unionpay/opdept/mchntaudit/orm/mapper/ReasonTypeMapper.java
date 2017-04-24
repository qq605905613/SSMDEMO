package com.unionpay.opdept.mchntaudit.orm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.unionpay.opdept.mchntaudit.orm.bean.ReasonType;

public interface ReasonTypeMapper {
	int deleteByPrimaryKey(String reasonTp);

	int insert(ReasonType record);

	int insertSelective(ReasonType record);

	ReasonType selectByPrimaryKey(String reasonTp);

	int updateByPrimaryKeySelective(ReasonType record);

	int updateByPrimaryKey(ReasonType record);

	List<ReasonType> selectAllType(@Param("isCommon") String isCommon);
}