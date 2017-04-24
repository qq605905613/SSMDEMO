package com.unionpay.opdept.mchntaudit.orm.mapper;

import com.unionpay.opdept.mchntaudit.orm.bean.BranchContact;

public interface BranchContactMapper {
    int deleteByPrimaryKey(Integer contactId);

    int insert(BranchContact record);

    int insertSelective(BranchContact record);

    BranchContact selectByPrimaryKey(Integer contactId);

    int updateByPrimaryKeySelective(BranchContact record);

    int updateByPrimaryKey(BranchContact record);
}