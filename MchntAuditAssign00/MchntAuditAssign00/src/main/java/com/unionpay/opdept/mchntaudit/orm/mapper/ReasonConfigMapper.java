package com.unionpay.opdept.mchntaudit.orm.mapper;

import java.util.List;

import com.unionpay.opdept.mchntaudit.orm.bean.ReasonConfig;
import com.unionpay.opdept.mchntaudit.orm.bean.ReasonConfigKey;

public interface ReasonConfigMapper {
	int deleteByPrimaryKey(ReasonConfigKey key);

	int insert(ReasonConfig record);

	int insertSelective(ReasonConfig record);

	ReasonConfig selectByPrimaryKey(ReasonConfigKey key);

	int updateByPrimaryKeySelective(ReasonConfig record);

	int updateByPrimaryKey(ReasonConfig record);

	List<ReasonConfig> selectAllConfig();
}